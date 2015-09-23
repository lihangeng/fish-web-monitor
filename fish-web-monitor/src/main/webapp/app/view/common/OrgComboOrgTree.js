//根据类型获得组织机构树控件：

Ext.define('Eway.view.common.OrgComboOrgTree',{
	extend : 'Ext.form.field.Picker',
	alias : 'widget.common_orgComboOrgTree',

	fieldLabel : '组织机构',
	readOnly:false,
	editable:true,
	isOrg:true,
	hiddenField:'',
	treePanel:'',
	orgGridPanel:'',
	matchFieldWidth:true,
	config : {
		hiddenValue : '',
		filters : '',
		rootVisible : false,
		treeExist : '',
		defaultRootId : 1,
		defaultRootName: '组织机构',
		expandRoot:true,
		isFilterOrgStatus:true,
		parentXtype:'form'
	},

	onClearClick : function(){
		this.getOtherCompement();
		this.setValue('');
		this.hiddenField.setValue('');
	},

	reflesh : function(){
		var data = this.getFilterData();
		if(this.getTreeExist()){
			this.treeExist.store.load( {
					params : data
			});
		}else{
			this.createPicker();
		}
	},

	getFilterData : function(){
		var filter = this.getFilters() || '{}';
		return Ext.decode(filter);
	},
	//展示查询面板
	onTrigger1Click:function(){
		var me = this;
		this.createPicker("orgGridPanel");
	},
	//展示树
	onTriggerClick:function(){
		var me = this;
		this.createPicker("treePanel");
	},
	getOtherCompement:function(){
		var orgorg = this.getHiddenValue();
		if(this.getParentXtype()=="form"){
			this.treePanel = this.up('form').down("treepanel");
			this.orgGridPanel = this.up('form').down("orgGridPanel");
			this.hiddenField = this.up('form').getForm().findField(orgorg);
		}
		else if(this.getParentXtype()=="toolbar"){
			this.treePanel = this.up('toolbar').down("treepanel");
			this.orgGridPanel = this.up('toolbar').down("orgGridPanel");
			this.hiddenField = this.up('toolbar').down("hidden");
		}
	},
	//此方法为必须实现的方法
	createPicker : function(type){
		this.getOtherCompement();
		var treepanel=this.treePanel;
		var gridpanel = this.orgGridPanel;
		if("treePanel"==type){
			if(treepanel&&null!=treepanel&&treepanel.isVisible()){
				return;
			}
			this.collapse();
			this.setEditable(false);
			this.picker=this.creatTreePanel();
			this.picker.ownerCmp = this;
			this.picker.setVisible(true);
			this.expand();
		}
		else if("orgGridPanel"==type){
			if(gridpanel&&null!=gridpanel&&gridpanel.isVisible()){
				return;
			}
			this.collapse();
			this.setEditable(true);
			this.picker=this.createPanel();
			this.picker.ownerCmp = this;
			this.picker.setVisible(true);
			this.expand();
			this.focus();
		}
	},
	//丢失焦点时进行一次匹配操作，如果完全匹配成功，则使用匹配成功的值，否则清空数据
	onBlur:function(e){
		var me = this;
        me.triggerWrap.removeCls(me.triggerWrapFocusCls);
        me.inputWrap.removeCls(me.inputWrapFocusCls);
        me.invokeTriggers('onFieldBlur', [e]);
		if(me.readOnly){
			return;
		}
		//移除焦点样式
        me.callParent(arguments);
        me.removeCls(me.fieldFocusCls);
		var gridpanel=me.getPicker();
		//如果对象为treepanel不做任何处理
		if(gridpanel.$className=='Ext.tree.Panel'){
			return;
		}
		var orgorg = this.getHiddenValue();
		this.blur();
		var value = me.getValue();
		var store = undefined;
		if(gridpanel&&null!=gridpanel){
			store=gridpanel.getStore();
		}
		else{
			return;
		}
		var setEmpty=true;
		if(undefined!=store){
			store.each(function(item,index){
				if(item.data.name==value){
					me.hiddenField.setValue(item.data.guid);
					setEmpty = false;
				}
			},this);
		}
		if(setEmpty){
			me.hiddenField.setValue("");
			me.setValue("");
		}
	},
	createPanel:function(){
		var me = this;
		var data = me.getFilterData();
		var width= me.inputEl.getWidth()+24;
		if(!this.triggers.clear.hidden){
			width=width+24;
		}
		me.store = Ext.create('Eway.store.person.organization.MatchingOrganization');
		var panel = Ext.create('Ext.grid.OrgPanel', {
		    store: me.store,
		    hideHeaders:true,
		    columns: [
		        { text: '匹配机构',  dataIndex: 'name', flex: 1,menuDisabled:true },
		        { text: '机构ID', dataIndex: 'guid',hidden:true }
		    ],
		    height: 100,
			minHeight : 100,
		    maxHeight : 280,
		    width:width,
		    autoScroll: true,
		    floating : true,
		    listeners : {
		    	scope : me,
		    	cellclick:me.onCellClick
		    }
		});
		return panel;
	},
	//点击确认数据
	onCellClick:function(_this, td, cellIndex, record, tr, rowIndex, e, eOpts ){
		this.setValue(record.get('name'));
		this.setEditable(false);
		var orgorg = this.getHiddenValue();
		this.hiddenField.setValue(record.get('guid'));
		this.collapse();
	},
	creatTreePanel:function(){
		var me = this;
		var data = me.getFilterData();
		me.store = Ext.create('Eway.store.common.OrganizationTree');
		var treePanel = Ext.create('Ext.tree.Panel',{
			hidden : true,
			floating : true,
			minHeight : 280,
			height: 280,
			autoScroll: true,
		 	rootVisible : me.getRootVisible(),
			store : me.store,
			listeners : {
				scope : me,
				itemclick : me.onTreeItemClick,
				beforeload : me.treeBeforeLoad
			}
		});
		//如果不显示根节点则展开否则，不予展开
		var isExpanded = false;
		if(me.getRootVisible()){
			isExpanded = false;
		}
		else{
			isExpanded = true;
		}
		if(data.type == "1" || ewayUser.getOrgType() == ""){//维护商
			treePanel.setRootNode({
				id: 1,
				text: '组织机构',
				expanded: isExpanded
			});
		}
		if(!isExpanded){
			var width= me.inputEl.getWidth()+24;
			treePanel.setWidth(width);
		}
		this.setTreeExist(treePanel);
		return treePanel;
	},

	treeBeforeLoad : function(store, operation, options) {
		store.proxy.extraParams = this.getFilterData() || {};
	},

	onTreeItemClick : function(view,record){
		this.setValue(record.get('text'));
		var orgorg = this.getHiddenValue();
		this.hiddenField.setValue(record.get('id'));
		this.collapse();
	},
	
	onChange:function(newVal,oldVal){
		if(this.editable&&!this.readOnly)
			this.queryMsg(newVal);
	},
	//到后台进行模糊查询
	queryMsg:function(newVal){
		if(newVal==""||newVal==this.emptyText){
			return ;
		}
		else{

//		  	 var gridpanel = Ext.get(Ext.DomQuery.selectNode('table.x-datepicker-eventEl',me.getEl().dom));
			var gridpanel=this.getPicker( );
			if(gridpanel&&null!=gridpanel&&gridpanel.isVisible()){
				var store =  gridpanel.getStore();
				var object = this.getFilterData();
				object.name=newVal;
				store.setUrlParamsByObject(object);
				store.load();
			}
		}
	}
});