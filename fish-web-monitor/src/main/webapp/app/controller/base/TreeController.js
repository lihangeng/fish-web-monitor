
Ext.define('Eway.controller.base.TreeController', {
	extend: 'Eway.controller.base.FishController',

	selectedRecord : undefined,
	selectedTreeNode : undefined,
	filterConfig : {xtype : '', height : 90},
	gridConfig : {xtype : ''},
	detailConfig : {xtype : '', height : 100},
	addFormConfig : {xtype : '', title : '增加'},
	updateFormConfig : {xtype : '', title : '修改'},
	treeConfig : {xtype : '', width : 200},

	makeEwayView : function() {
		var sm = new Ext.grid.RowSelectionModel({
			singleSelect : true,
			listeners : {
				rowselect : function(sm, rowIdx, record) {
					this.selectRecord(record);
				},
				scope : this
			}
		});
		return new Ext.Panel({
			id : this.id,
			title : this.text,
//			closable : true,
			layout : 'border',
			items : [{
				region : 'west',
				width : this.treeConfig.width,
				itemId : 'tree',
				xtype : this.treeConfig.xtype,
				listeners : {
					click : this.onClickTreeNode,
					scope : this
				},
				tbar : [/*{
						xtype:'textfield',
						enableKeyEvents:true,
						listeners:{
							render: function(f){
            					this.filter = new QM.ux.TreeFilter(this.ownerCt.ownerCt,{
//									clearAction : 'expand',
									ignoreFolder:false
								});//初始化TreeFilter
								var map=new Ext.KeyMap(f.el, {
										key : Ext.EventObject.ENTER,
										fn :function(){
              									f.filter.filter(f.getValue());
                						},
										scope : this
									}
								);
							},
            				keyup: {//添加键盘点击监听
                			fn:function(t,e){
                  				t.filter.filter(t.getValue());
                			},
                			buffer: 350
            				}
						}
					},*/'->', {
					xtype : 'buttongroup',
					items : [{
						text : '刷新',
						listeners : {
							click : this.onRefresh,
							scope : this
						}
					}]
				}]
			},{
				region : 'center',
				layout : 'border',
				items : [{
					region : 'north',
					height : this.filterConfig.height,
					itemId : 'filerForm',
					xtype : this.filterConfig.xtype
				}, {
					region : 'center',
					itemId : 'grid',
					xtype : this.gridConfig.xtype,
					tbar : this.make_tbar(),
					sm : sm,
					store : this.makeStore()
				/*}, {
					region : 'south',
					height : this.detailConfig.height,
					itemId : 'detail',
					xtype : this.detailConfig.xtype*/
				}]
			}]

		});

	},

	getCompById : function(itemId) {
		return this.getEwayView().find('itemId', itemId)[0];
	},

	//得到显示主要数据的grid
	getGrid : function(){
		return this.getCompById("grid");
	},

	//得到查询条件 的form
	getFilterForm : function(){
		return this.getCompById("filerForm");
	},

	//得到treePanel
	getTree : function(){
		return this.getCompById("tree");
	},

	onClickTreeNode : function(node) {
		this.selectedTreeNode = node;
		this.reload(node);
	},
	onSearchTree:function(t,e){
		var filter = new QM.ux.TreeFilter(this.getTree(),{
//			clearAction : 'expand',
			ignoreFolder:false
		});
		var text=this.getTree().getTopToolbar().findByType('textfield')[0].getValue();
		filter.filter(text);
	},
	/*onTreeSearch:function(e){
		var tree=this.getTree();
		var text=this.getTree().getTopToolbar().findByType('textfield')[0].getValue();
		var filter = new Ext.tree.TreeFilter(tree, {
	        clearBlank: true,
	        autoClear: true
    	});
		var hiddenPkgs = [];
		Ext.each(hiddenPkgs, function(n){
            n.ui.show();
        });
		if(!text){
            filter.clear();
            return;
        };
//        tree.expandAll();
		// 根据输入制作一个正则表达式，'i'代表不区分大小写
        var re = new RegExp(Ext.escapeRe(text), 'i');
        filter.filterBy(function(n){
            // 只过滤叶子节点，这样省去枝干被过滤的时候，底下的叶子都无法显示
            return !n.isLeaf() || re.test(n.text);
        });
        hiddenPkgs = [];
        tree.root.cascade(function(n) {
            if(!n.isLeaf() && n.ui.ctNode.offsetHeight < 3){
                n.ui.hide();
                hiddenPkgs.push(n);
            }
        });
	},*/
	onRefresh : function(){
		var node = this.getTree().getRootNode();
		node.reload();
	},

	onRemove : function() {
		var selectModel = this.getGrid().getSelectionModel();
		var selectedRecord = selectModel.getSelected();
		if(selectModel.getCount()==1){
		Ext.MessageBox.confirm("请确认",
			"是否删除该记录?",
			function(button,text) {
				if(button=="yes"){
					this.getStore().remove(this.selectedRecord);
					this.selectRecord(null);
				}
			}, this);
		}else{
			Eway.alert(Eway.choiceDeleteMsg);
		}
	},

	reload : function(node){
		var store = this.getStore();
		store.clearUrlParam();
		store.setUrlParam('parentId',node.id);
		store.load({
			params : {
				start : 0,
				limit : Eway.PAGE_SIZE
			}
		});
	}

});