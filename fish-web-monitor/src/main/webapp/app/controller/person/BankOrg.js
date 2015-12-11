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
			}

		});
	},

	onBeforeitemdblclick : function(){
		return false;
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
			Eway.alert(EwayLocale.tip.bankOrg.manager.set.chooseOrg);
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
		
		if(sm2.getCount()!=1){
			Eway.alert(EwayLocale.tip.bankOrg.manager.set.chooseOneManager);
			return;
		}
		
		if(sm2.getCount() == 1) {
			record.set('userGuid',sm2.getLastSelected().data.guid);
			record.save({
				success : function(record,operation){
					store.load();
					Eway.alert(EwayLocale.tip.bankOrg.manager.set.managerSuccess);
					addManagerwin.close();
			    },
			    failure: function(record,operation){
					Eway.alert(EwayLocale.tip.bankOrg.manager.set.managerFail);
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
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,EwayLocale.tip.bankOrg.manager.remove.confirm,
									function(button,text){
										if(button=="yes"){
											this.onRemoveManagerConfirm(sm);
										}
									},this);
		}else {
			Eway.alert(EwayLocale.tip.bankOrg.manager.remove.reChoose);
		}
	},

	onRemoveManagerConfirm: function(sm){
		var record = sm.getLastSelected();
		var store = this.getGrid().getStore();
		record.set('userGuid',0);
		record.save({
			success : function(record,operation){
				store.load();
				Eway.alert(EwayLocale.tip.bankOrg.manager.remove.delSuccess);
			},
			failure: function(record,operation){
				Eway.alert(EwayLocale.tip.bankOrg.manager.remove.delFail);
			}
		});
	},

	// 根据条件查询：
	onQuery:function(){
		var	grid = this.getGrid();
		var store = grid.getStore();
		store.cleanUrlParam();
		store.setBaseParam('type','0');
		var data = this.getFilterForm().getForm().getValues();
		store.setBaseParam('orgId',data["orgId"]);
		for(var i in data){
			if(i=="org"){
				continue;
			}
			store.setUrlParam(i,data[i])
		}
		store.loadPage(1);
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
						Eway.alert(EwayLocale.addSuccess);
				    	//增加成功后查询条件不带入后台查询
				    	store.setUrlParamsByObject({
								type : '0'
							}
						);
						//增加机构后立即跳转到所增加的机构父机构下
						store.loadPage(1);
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
			Eway.alert(EwayLocale.choiceUpdateMsg);
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
					Eway.alert(EwayLocale.updateSuccess);
					if(data.code==ewayUser.getOrgCode()){
						ewayUser.setOrgName(data.name);
					}
					//防止翻页后数据总量变化，所以把参数带入，翻页的逻辑不会变更
					store.setUrlParamsByObject({
							type : '0'
					});
					store.load();
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
			Eway.alert(EwayLocale.tip.bankOrg.move.chooseOrg);
		}
	},

	onMoveConfirm : function() {
		var ewayView = this.getEwayView();
		var sm = this.getGrid().getSelectionModel();
		var record = sm.getLastSelected();
		var win = this.getMoveWin();
		var moveForm = this.getMoveWin().down("form").getForm();
		var parentId = record.data.parentId;
		var guid = record.data.guid;
		var store = this.getGrid().getStore();
		if (moveForm.isValid()) {
			var data = moveForm.getValues();
			Ext.Ajax.request({
			method : 'POST',
			url : 'api/person/organization/move',
			params : {
				guid : guid,
				parentId : data.parentId
			},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					Eway.alert(EwayLocale.tip.bankOrg.move.moveSuccess);
					//组织树的刷新:
//					var treePanel = ewayView.down('treepanel');
					if(data.code==ewayUser.getOrgCode()){
						ewayUser.setOrgName(data.name);
					}
					//防止翻页后数据总量变化，所以把参数带入，翻页的逻辑不会变更
					store.setUrlParamsByObject({
							type : '0'
					});
					store.load();
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
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							var parentId = record.data.parentId;
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									var store = this.getGrid().getStore();
									//防止翻页后数据变化，所以把参数带入，翻页的逻辑不会变更
									store.setUrlParamsByObject({
											type : '0'
									});
									//增加机构后立即跳转到所增加的机构父机构下
									store.loadPage(1);
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									record.dropped = false;
									Ext.MessageBox.show({
										title : EwayLocale.tip.tips,
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
			Eway.alert(EwayLocale.choiceDeleteMsg);
		}
	}

});