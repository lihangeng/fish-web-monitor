/**
 * 人员管理--银行机构管理菜单控制：
 */
Ext.define('Eway.controller.person.BankOrg', {
	extend : 'Eway.controller.base.Controller',

	stores: [
		'person.organization.BankOrganization',
		'person.organization.OrganizationStateDict',
		'person.organization.OrganizationTypeDict',
		'person.organization.OrganizationLevelDict',
		'person.person.Person',
		'person.organization.ServiceObject',
		'person.organization.OrganizationLevelDict'
	],
	models: ['person.organization.BankOrganization','Dict','person.person.Person'],
	views: [ 'person.bankOrg.View'],

	refs: [{
		ref: 'ewayView',
		selector: '#bank_organization',
		autoCreate: true,
		xtype: 'bank_organization_view'
	}, {
		ref: 'grid',
		selector: 'bank_organization_grid'
	}, {
		ref: 'addWin',
		selector: 'bank_organization_add'
	}, {
		ref: 'updateWin',
		selector: 'bank_organization_update'
	}, {
		ref : 'moveWin',
		selector : 'bank_organization_move'
	}, {
		ref: 'AddManagerWin',
		selector: 'organziation_addManager'
	},{
		ref :'filterForm',
		selector:'bank_organization_filterform'
	},{
		ref :'organizationTree',
		selector:'treepanel'
	}],

	init: function() {
		this.control({
			'bank_organization_view button[action=query]' :{
				click : this.onQuery
			},
			'bank_organization_view button[action=add]': {
				click: this.onAdd
			},
			'bank_organization_view button[action=remove]': {
				click: this.onRemove
			},
			'bank_organization_view button[action=move]' : {
				click : this.onMove
			},
			'bank_organization_view button[action=update]': {
				click: this.onUpdate
			},
			'bank_organization_grid menuitem[action=setManager]': {
				click: this.onSetManager
			},
			'bank_organization_grid menuitem[action=removeManager]': {
				click: this.onRemoveManager
			},
			'bank_organization_view button[action=deepQuery]': {
				click: this.onDeepQuery
			},
			'bank_organization_view treepanel': {//多个事件应该这么写
				afterrender: this.onTreePanelAfterRender,
				itemclick: this.onItemClick,
				beforeitemdblclick : this.onBeforeitemdblclick
			}

		});
	},

	onBeforeitemdblclick : function(){
		return false;
	},

	onTreePanelAfterRender : function(treepanel){
		var selectedNode = this.getSelectionInTree();
		var nodeId = selectedNode.data.id;
		var store = this.getGrid().getStore();
		store.setBaseParam('selectedNode',nodeId);
		store.setBaseParam('type','0');
	  	store.loadPage(1);
	    var actionTip = this.getEwayView().down("tbtext[action=tip]");
	    actionTip.setText('<font color="red">'+selectedNode.data.text+'</font>'+Eway.locale.tip.bankOrg.downGradeOrg);
	},

	/**
	 * 单击机构树节点
	 * 显示下级树节点，同时刷新机构列表
	 */
	onItemClick: function(view, node, item, index, e) {
		/** 取消双击展开折叠菜单行为 */
		view.toggleOnDblClick = false;
		view.expand(node);
		this.treeExpand = false;
		var store = this.getGrid().getStore();
		store.cleanUrlParam();
		store.setBaseParam('type','0');
		store.setBaseParam('selectedNode',node.data.id);
		store.loadPage(1);
        var actionTip = this.getGrid().down("tbtext[action=tip]");
    	actionTip.setText('<font color="red">'+node.data.text+'</font>'+Eway.locale.tip.bankOrg.downGradeOrg);
    },

	/**
	 * 设置机构管理员：
	 */
	onSetManager: function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var addManagerWin = Ext.create('Eway.view.person.organization.AddOrganizationManager');
			var personGrid = addManagerWin.down('Organization_organizationManagerGrid');
			personGrid.getStore().cleanUrlParam();

			personGrid.down('pagingtoolbar').on('beforechange',Ext.bind(this.onRefleshPerson,this,[personGrid.down('pagingtoolbar'),record.data.guid]),this);
			personGrid.down('button[action="set"]').on('click',Ext.bind(this.onSetManagerConfirm,this,[addManagerWin]),this);
			addManagerWin.show();
			personGrid.getStore().load({
				params : {
					organizationId:record.data.guid
				}
			});
		}
		else {
			Eway.alert(Eway.locale.tip.bankOrg.manager.set.chooseOrg);
		}
	},

	onRefleshPerson: function(pagingtoolbar,guid){
		pagingtoolbar.store.proxy.extraParams = {organizationId :guid};
	},

	onSetManagerConfirm: function (addManagerwin){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var personGrid = addManagerwin.down('Organization_organizationManagerGrid')
		var store = this.getGrid().getStore();
		var sm2 = personGrid.getSelectionModel();
		var record = sm1.getLastSelected();
		if(sm2.getCount() == 1) {
			record.set('userGuid',sm2.getLastSelected().data.guid);
			record.save({
				success : function(record,operation){
					store.load();
					Eway.alert(Eway.locale.tip.bankOrg.manager.set.managerSuccess);
					addManagerwin.close();
			    },
			    failure: function(record,operation){
					Eway.alert(Eway.locale.tip.bankOrg.manager.set.managerFail);
				}
			});
		}
	},

	/**
	 * 删除机构管理员：
	 */
	onRemoveManager:function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1&&sm.getLastSelected().get('userGuid')!=null
				&&sm.getLastSelected().get('userGuid')!=0) {
			Ext.MessageBox.confirm(Eway.locale.tip.remove.confirm.title,Eway.locale.tip.bankOrg.manager.remove.confirm,
									function(button,text){
										if(button=="yes"){
											this.onRemoveManagerConfirm(sm);
										}
									},this);
		}else {
			Eway.alert(locale.tip.bankOrg.manager.remove.reChoose);
		}
	},

	onRemoveManagerConfirm: function(sm){
		var record = sm.getLastSelected();
		var store = this.getGrid().getStore();
		record.set('userGuid',0);
		record.save({
			success : function(record,operation){
				store.load();
				Eway.alert(Eway.locale.tip.bankOrg.manager.remove.delSuccess);
			},
			failure: function(record,operation){
				Eway.alert(Eway.locale.tip.bankOrg.manager.remove.delFail);
			}
		});
	},

	// 根据条件查询：
	onQuery:function(){
		var nodeId = this.getSelectionInTree().data.id,
			grid = this.getGrid();
		var store = grid.getStore();
		store.cleanUrlParam();
		store.setBaseParam('type','0');
		store.setBaseParam('orgId',nodeId);
		var data = this.getFilterForm().getForm().getValues();
		for(var i in data){
			store.setUrlParam(i,data[i])
		}
		var actionTip = grid.down("tbtext[action=tip]");
		actionTip.setText(Eway.locale.tip.bankOrg.orgEligible);
		store.loadPage(1);
	},

	//深度查询
	onDeepQuery: function(){
		var ewayView = this.getEwayView();
		var store = this.getGrid().getStore();
		store.cleanUrlParam();
		store.setBaseParam('type','0');
		var data = this.getFilterForm().getForm().getValues();
		for(var i in data){
			store.setUrlParam(i,data[i])
		}
		var actionTip = ewayView.down("tbtext[action=tip]");
		actionTip.setText(Eway.locale.tip.bankOrg.orgEligible);
		store.loadPage(1);
		ewayView.down('treepanel').getSelectionModel().select(0,true);
	},

	/**
	 * 增加机构信息：
	 */
	onAdd: function() {
		var win = Ext.create('Eway.view.person.bankOrg.Add');
		//清除orgLevel信息
		win.down('field[name="parentId"]').setValue('');
		win.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[win]),this);
		var code = win.down("form").getForm().findField("code");
		var organizationType = win.down('form').getForm().findField("organizationType");
		//上级机构默认显示当前用户所属机构。
		win.down('field[name="parentId"]').setValue(ewayUser.getOrgId());
		win.down('field[name="parent"]').setValue(ewayUser.getOrgName());
		win.show();
	},

	onAddConfirm: function(win) {
		var ewayView = this.getEwayView();
		var addForm =win.down("form").getForm();
		var data = addForm.getValues();
		var record = Ext.create('Eway.model.person.organization.BankOrganization',data);
		var store = this.getGrid().getStore();
		if(addForm.isValid()){
			record.set("organizationType",'0');
			record.set("organizationState",'1');
			record.save({
				success : function(record,operation){
						Eway.alert(Eway.addSuccess);
						//组织树的刷新:
						ewayView.down('treepanel').getStore().load();
				    	ewayView.down('treepanel').getSelectionModel().select(0,true);//选择根节点
				    	//增加成功后查询条件不带入后台查询
				    	store.setUrlParamsByObject(null);
						//增加机构后立即跳转到所增加的机构父机构下
						store.load({
							params:{
								selectedNode:data.parentId,
								type : '0'
							}
						});
						var actionTip = ewayView.down("tbtext[action=tip]");
	    				actionTip.setText('<font color="red">'+record.data.parent+'</font>'+Eway.locale.tip.bankOrg.downGradeOrg);
						win.close();
			    },
			    failure: function(record,operation){
					Eway.alert(operation.getError());
				}
			});
		}
	},

	/**
	 * 更新一条机构信息：
	 */
	onUpdate: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.person.bankOrg.Update');
			var record = sm.getLastSelected();
			var upddata = record.data;
			var selected;
			var stores=Ext.create('Eway.store.person.organization.OrganizationLevelDict');
	        Ext.Array.forEach(stores.data.items,function(item,index){
	        	if(item.data.value==upddata.orgLevel){
	        		selected=item;
	        	}
	        });
			win.down('field[name="code"]').setValue(upddata.code);
			win.down('field[name="zip"]').setValue(upddata.zip);
			win.down('field[name="description"]').setValue(upddata.description);
			win.down('field[name="address"]').setValue(upddata.address);

			win.down('field[name="orgLevel"]').select(selected);
			win.down('field[name="parentId"]').setValue(upddata.parentId);

			win.down('field[name="parent"]').setValue(upddata.parent);
			win.down('field[name="name"]').setValue(upddata.name);
			if(record.data.code==ewayUser.getOrgCode()){
				win.down('field[name="code"]').setReadOnly(true);
			}
			win.down('button[action="update"]').on('click',this.onUpdateConfirm,this);
			win.show();
		}
		else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	getUpdateFormFiled : function(name){
    	return this.getUpdateForm().findField(name);
    },

    getUpdateForm : function(){
    	return this.getUpdateWin().down("form").getForm();
    },

	onUpdateConfirm: function() {
		var ewayView = this.getEwayView();
		var sm = this.getGrid().getSelectionModel();
		var record = sm.getLastSelected();
		var parentId = record.data.parentId;
		var updateForm = this.getUpdateForm();
		var win = this.getUpdateWin();
		var code = record.data.code;
		var store = this.getGrid().getStore();
		if(updateForm.isValid()){
			var data = updateForm.getValues();
			record.set("address",data.address);
			record.set("code",data.code);
			record.set("description",data.description);
			record.set("name",data.name);
			record.set("parent",data.parent);
			record.set("parentId",data.parentId);
			record.set("zip",data.zip);
			record.set("serviceObjectId",data.serviceObjectId);
			record.set("orgLevel",data.orgLevel);
			record.save({
				success : function(record,operation){
					Eway.alert(Eway.updateSuccess);
					//组织树的刷新:
					var treePanel = ewayView.down('treepanel');
					if(data.code==ewayUser.getOrgCode()){
						ewayUser.setOrgName(data.name);
						treePanel.setRootNode({
							id: ewayUser.getOrgId(),
							text: ewayUser.getOrgName(),
							expanded: true
						});
					}else{
						treePanel.getStore().load();
					}
					treePanel.getSelectionModel().select(0,true);//选择根节点

					//增加机构后立即跳转到所增加的机构父机构下
					store.load({
						params:{
							selectedNode:record.data.parentId,
							type : '0'
						}
					});
					var actionTip = ewayView.down("tbtext[action=tip]");
	    			actionTip.setText('<font color="red">'+record.data.parent+'</font>'+Eway.locale.tip.bankOrg.downGradeOrg);
					win.close();
				},
				failure: function(record,operation){
					Eway.alert(operation.getError());
					//解决脏数据
					store.rejectChanges();
				}

			});
		}
	},
	onMove : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var win = Ext.create('Eway.view.person.bankOrg.Move');
			var record = sm.getLastSelected();
			var upddata = record.data;
			var selected;
			var stores = Ext.create('Eway.store.person.organization.OrganizationLevelDict');
			Ext.Array.forEach(stores.data.items, function(item,index) {
				if (item.data.display == upddata.orgLevel) {
					selected = item;
				}
			});
			win.down('button[action="move"]').on('click',this.onMoveConfirm, this);
			win.show();
		} else {
			Eway.alert(Eway.locale.tip.bankOrg.move.chooseOrg);
		}
	},

	onMoveConfirm : function() {
		var ewayView = this.getEwayView();
		var sm = this.getGrid().getSelectionModel();
		var record = sm.getLastSelected();
		var win = this.getMoveWin();
		var moveForm = this.getMoveWin().down("form").getForm();
		var parentId = record.data.parentId;
		var code = record.data.code;
		var store = this.getGrid().getStore();
		if (moveForm.isValid()) {
			var data = moveForm.getValues();
			Ext.Ajax.request({
			method : 'POST',
			url : 'api/person/organization/move',
			params : {
				code : code,
				parentId : data.parentId
			},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					Eway.alert(Eway.locale.tip.bankOrg.move.moveSuccess);
					//组织树的刷新:
					var treePanel = ewayView.down('treepanel');
					if(data.code==ewayUser.getOrgCode()){
						ewayUser.setOrgName(data.name);
						treePanel.setRootNode({
							id: ewayUser.getOrgId(),
							text: ewayUser.getOrgName(),
							expanded: true
						});
					}else{
						treePanel.getStore().load();
					}
					treePanel.getSelectionModel().select(0,true);//选择根节点
					//增加机构后立即跳转到所增加的机构父机构下
					store.load({
						params:{
							selectedNode:record.data.parentId,
							type : '0'
						}
					});
					var actionTip = ewayView.down("tbtext[action=tip]");
	    			actionTip.setText('<font color="red">'+record.data.parent+'</font>'+Eway.locale.tip.bankOrg.downGradeOrg);
					win.close();
				} else {
					Eway.alert(object.errorMsg);
				}
			},
			failure : function(record, operation) {
				Eway.alert(operation.getError());
				//解决脏数据
				store.rejectChanges();
			}
		});
		}
	},
	/**
	 * 删除一条机构信息：
	 */
	onRemove: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(Eway.locale.tip.remove.confirm.title,
					Eway.locale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(Eway.deleteSuccess);
									this.getGrid().getStore().remove(record);
									var selections = this.getEwayView().down('treepanel').getSelectionModel().getSelection();
					            	if(!Ext.isEmpty(selections)){
					            		this.getEwayView().down('treepanel').getStore().load({node:selections[0]});//刷新当前节点
					            		if(!selections[0].isExpanded()){
					            			selections[0].expand(true);//如果没有展开，则展开选中节点
					            		}
					            	}
								},
								failure: function(record,operation){
									Ext.MessageBox.show({
										title : Eway.locale.tip.tips,
										msg : operation.getError(),
										modal : true,
										fn : function callBack(id){
									     	if(id=='yes'){
											    var win = Ext.create('Eway.view.person.organization.OrglinkWin');
												var personStore = win.down('linkedPersonGrid').getStore();
												personStore.setBaseParam('organizationId',record.data.guid);
												personStore.load();
												var deviceStore = win.down('linkedDeviceGrid').getStore();
												deviceStore.setBaseParam('organization',record.data.guid);
												deviceStore.load();
												win.show();
											}
										},
										buttons : Ext.Msg.YESNO
								     });
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(Eway.choiceDeleteMsg);
		}
	},

	//得到组织树中选中的节点
	getSelectionInTree : function(){
		 var treePanel = this.getEwayView().down('treepanel');
		 var selections = treePanel.getSelectionModel().getSelection();
		 var selectedNode = null;
	     if(Ext.isEmpty(selections)){//如果当前没有选中任何节点
	    	treePanel.getSelectionModel().select(0,true);//默认选择根节点
	    	selectedNode = treePanel.getRootNode();
	     }else{
	    	selectedNode = selections[0];
	     }
	     return selectedNode;
	}
});