//根据类型获得组织机构树控件：

Ext.define('Eway.view.common.OrgComboOrgTree',{
	extend : 'Ext.form.field.Picker',
	alias : 'widget.common_orgComboOrgTree',
	fieldLabel : EwayLocale.commen.orgFramework,
	readOnly:false,
	editable:true,
	//隐藏域控件
	hiddenField:'',
	//机构树面板
	treePanel:'',
	//机构列表面板
	orgGridPanel:'',
	//支持键盘事件
	enableKeyEvents:true,
	//是否模糊匹配查询
	matching:true,
	config : {
		//orgId隐藏域控件Name
		hiddenValue : '',
		//过滤条件(主要为区分银行机构还是维护商机构)
		filters : '',
		//根节点是否显示
		rootVisible : false,
		//机构树是否存在
		treeExist : '',
		defaultRootId : 1,
		defaultRootName: EwayLocale.commen.orgFramework,
		expandRoot:true,
		//父类容器的类型
		parentXtype:'form'
	},
	//点击清理按钮进行清理操作
	onClearClick : function(){
		this.getOtherCompement();
		this.setOrgValue('','');
	},
	listeners:{
		keydown:function( _this, e, eOpts){
			if((!e.isSpecialKey()||e.getKeyName()=='BACKSPACE'||e.getKeyName()=='ENTER'||e.getKeyName()=='SPACE'||e.getKeyName()=='DELETE')
				&&e.getKeyName()!='F1'&&e.getKeyName()!='F2'&&e.getKeyName()!='F3'&&e.getKeyName()!='F4'
					&&e.getKeyName()!='F5'&&e.getKeyName()!='F6'&&e.getKeyName()!='F7'&&e.getKeyName()!='F8'
						&&e.getKeyName()!='F9'&&e.getKeyName()!='F10'&&e.getKeyName()!='F11'&&e.getKeyName()!='F12'){
				console.debug(e.getKeyName());
				//键盘事件触发显示机构列表查询并显示
				if(e.keyCode==13){
					this.matching = true;
					this.onTrigger1Click()
					if(this.editable&&!this.readOnly&&this.matching)
						this.queryMsg(_this.getValue());
				}
				//当键盘敲击事件不是回车时候清空orgId的值
				else if(_this.hiddenField.getValue()!=""){
					_this.hiddenField.setValue("");
				}
			}
		},
		blur:function( field, event, eOpts ){
			var gridpanel = field.getPicker();
			//当机构失去焦点分情况处理
			//1.机构下无任何picker
			//2.机构picker是树
			//3.机构picker是grid并且不是匹配查询
			//以上3中情况判断orgId是否为空，如果为空则直接清除机构数据框中的数据
			if(gridpanel==undefined||gridpanel.$className == 'Ext.tree.Panel'
				||(gridpanel.$className != 'Ext.tree.Panel'&&!this.matching)){
				if(field.hiddenField.getValue()==""){
					field.setValue("");
				}
				return;
			}
		},
		collapse:function(field,opts){
			var me = this;
			var gridpanel = field.getPicker();
			if(gridpanel.$className == 'Ext.tree.Panel'){
				return;
			}
			me.matching = false;
			var value = field.getValue();
			var store = undefined;
			if(gridpanel&&null!=gridpanel){
				store=gridpanel.getStore();
			}
			else{
				return;
			}
			var setEmpty=true;
			//查询列表中没有匹配值，则将值设为空
			if(undefined!=store){
				store.each(function(item,index){
					if(item.data.name==value){
						me.setOrgValue(item.data.name,item.data.guid);
						setEmpty = false;
					}
				},this);
			}
			if(setEmpty){
				this.setOrgValue('','');
			}
			
		}
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
	//获取加载树的基础数据信息
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
		this.setEditable(true);
	},
	//找到机构树主控件，默认为form下，但是有些特别情况在toolbar下，需要特别处理
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
			this.matching = false;
			this.picker=this.creatTreePanel();
			this.picker.ownerCmp = this;
			this.picker.setVisible(true);
			this.expand();
			this.focus();
		}
		else if("orgGridPanel"==type){
			this.matching = true;
			if(gridpanel&&null!=gridpanel&&gridpanel.isVisible()){
				return;
			}
			this.collapse();
			this.picker=this.createPanel();
			this.picker.ownerCmp = this;
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
		this.blur();
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
		        { text: EwayLocale.commen.matchOrg,  dataIndex: 'name', flex: 1,menuDisabled:true },
		        { text: EwayLocale.commen.orgID, dataIndex: 'guid',hidden:true }
		    ],
		    height: 100,
			minHeight : 100,
		    maxHeight : 280,
		    bufferedRenderer:false,
			deferRowRender:true,
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
		this.matching = false;
		this.setOrgValue(record.get('name'),record.get('guid'));
		this.collapse();
	},
	//设置当前值
	setOrgValue:function(displayText,value){
		this.setValue(displayText);
		this.hiddenField.setValue(value);
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
		var defaultValue = this.hiddenField.getValue();
		var defaultDispaly = this.getValue();
		if(defaultValue !=""&&defaultDispaly!=""){
			treePanel.setRootNode({
				id: defaultValue,
				text: defaultDispaly,
				expanded: isExpanded
			});
		}
		else if(data.type == "1" || Eway.user.getOrgType() == ""){//维护商
			treePanel.setRootNode({
				id: 1,
				text: EwayLocale.commen.orgFramework,
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
	//选择机构树确认输入
	onTreeItemClick : function(view,record){
		this.setOrgValue(record.get('text'),record.get('id'));
		this.collapse();
	},
	//手动输入字符串进行查询
	onChange:function(newVal,oldVal){
		var clearTip = this.getTrigger("clear");
		if(undefined==clearTip){
			return;
		}
		if(this.canClear&&!this.readOnly){
			if(newVal && newVal!== "" ){
				this.getTrigger("clear").show();
			}else{
				this.getTrigger("clear").hide();
				this.onTriggerClick();
			}
		}
		
	},
	//到后台进行模糊查询
	queryMsg:function(newVal){
		var gridpanel=this.getPicker( );
		var store =  gridpanel.getStore();
		store.removeAll();
		gridpanel.scrollTo(0,0,true);
		if(newVal==""||newVal==this.emptyText){
			return ;
		}
		else{
			if(gridpanel&&null!=gridpanel&&gridpanel.isVisible()){
				var object = this.getFilterData();
				object.name=newVal;
				store.setUrlParamsByObject(object);
				store.load();
			}
		}
	}
});
