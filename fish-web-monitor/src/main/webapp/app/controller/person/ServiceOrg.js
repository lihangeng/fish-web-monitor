/**
 * 人员管理--服务商机构管理菜单控制：
 */
Ext.define('Eway.controller.person.ServiceOrg', {
	extend : 'Eway.controller.base.Controller',

	stores : ['person.organization.ServiceOrganization',
			'person.organization.OrganizationStateDict',
			'person.organization.OrganizationTypeDict',
			'person.person.Person'],
	models : ['person.organization.Organization', 'Dict',
			'person.person.Person'],
	views : ['person.serviceOrg.View'],

	refs : [{
				ref : 'ewayView',
				selector : '#ser_organization',
				autoCreate : true,
				xtype : 'ser_organization_view'
			}, {
				ref : 'grid',
				selector : 'ser_organization_grid'
			}, {
				ref : 'addWin',
				selector : 'ser_organization_add'
			}, {
				ref : 'updateWin',
				selector : 'ser_organization_update'
			}, {
				ref : 'AddManagerWin',
				selector : 'organziation_addManager'
			}, {
				ref : 'filterForm',
				selector : 'ser_organization_filterform'
			}],
	//
	init : function() {
		this.control({
			'ser_organization_view button[action=query]' : {
				click : this.onQuery
			},
			'ser_organization_view button[action=add]' : {
				click : this.onAdd
			},
			'ser_organization_view button[action=remove]' : {
				click : this.onRemove
			},
			'ser_organization_view button[action=update]' : {
				click : this.onUpdate
			},
			'ser_organization_grid menuitem[action=setManager]' : {
				click : this.onSetManager
			},
			'ser_organization_grid menuitem[action=removeManager]' : {
				click : this.onRemoveManager
				}
			});
		},

	

	/**
	 * 设置机构管理员：
	 */
	onSetManager : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var addManagerWin = Ext
					.create('Eway.view.person.organization.AddManager');
			var personGrid = addManagerWin
					.down('Organization_organizationPersonGrid');
			personGrid.getStore().cleanUrlParam();

			personGrid.down('pagingtoolbar').on(
					'beforechange',
					Ext.bind(this.onRefleshPerson, this, [
									personGrid.down('pagingtoolbar'),
									record.data.guid]), this);
			personGrid.down('button[action="set"]').on('click',
					Ext.bind(this.onSetManagerConfirm, this, [addManagerWin]),
					this);
			addManagerWin.show();
			personGrid.getStore().load({
				params : {
					organizationId : record.data.guid
				}
			});
		} else {
			Eway.alert(EwayLocale.tip.serviceOrg.chooseOrg);
		}
	},

	onRefleshPerson : function(pagingtoolbar, guid) {
		pagingtoolbar.store.proxy.extraParams = {
			organizationId : guid
		};
	},

	onSetManagerConfirm : function(addManagerwin) {
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var personGrid = addManagerwin
				.down('Organization_organizationPersonGrid');
		var store = this.getGrid().getStore();
		var sm2 = personGrid.getSelectionModel();
		var record = sm1.getLastSelected();
		if (sm2.getCount() == 1) {
			record.set('userGuid', sm2.getLastSelected().data.guid);
			record.save({
						success : function(record, operation) {
							store.load();
							Eway.alert(EwayLocale.tip.bankOrg.manager.set.managerSuccess);
							addManagerwin.close();
						},
						failure : function(record, operation) {
							Eway.alert(EwayLocale.tip.bankOrg.manager.set.managerFail);
						},
						button: addManagerwin.down('button[action="set"]')
					});
		}
	},

	/**
	 * 删除机构管理员：
	 */
	onRemoveManager : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1 && sm.getLastSelected().get('userGuid') != null
				&& sm.getLastSelected().get('userGuid') != 0) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title, EwayLocale.tip.remove.confirm.info,
					function(button, text) {
						if (button == "yes") {
							this.onRemoveManagerConfirm(sm);
						}
					}, this);
		} else {
			Eway.alert(EwayLocale.tip.serviceOrg.remove.reChoose);
		}
	},

	onRemoveManagerConfirm : function(sm) {
		var record = sm.getLastSelected();
		var store = this.getGrid().getStore();
		record.set('userGuid', 0);
		record.save({
					success : function(record, operation) {
						store.load();
						Eway.alert(EwayLocale.tip.bankOrg.manager.remove.delSuccess);
					},
					failure : function(record, operation) {
						Eway.alert(EwayLocale.tip.bankOrg.manager.remove.delFail);
					}
				});
	},

	/**
	 * 根据条件查询：
	 */
	onQuery : function() {
		var ewayView = this.getEwayView();
		var store = this.getGrid().getStore();
		store.cleanUrlParam();
		store.setBaseParam('type', '1');
		var data = this.getFilterForm().getForm().getValues();
		for (var i in data) {
			if(i=="org"){
				continue;
			}
			store.setUrlParam(i, data[i])
		}
		store.loadPage(1);
	},

	/**
	 * 增加机构信息：
	 */
	onAdd : function() {
		var win = Ext.create('Eway.view.person.serviceOrg.Add');
		win.down('button[action="add"]').on('click',
				Ext.bind(this.onAddConfirm, this, [win]), this);
		var code = win.down("form").getForm().findField("code");
		var organizationType = win.down('form').getForm()
				.findField("organizationType");
		//维护商机构的上级机构默认显示机构。
//		win.down('field[name="parentId"]').setValue(1);
//		win.down('field[name="parent"]').setValue(EwayLocale.person.bankPer.organizationName);
		win.show();
	},

	onAddConfirm : function(win) {
		var ewayView = this.getEwayView();
		var addForm = win.down("form").getForm();
		var data = addForm.getValues();
		var record = Ext.create('Eway.model.person.organization.ServiceOrganization',
				data);
		var store = this.getGrid().getStore();
		if (addForm.isValid()) {
			record.set("organizationType", '1');
			record.set("organizationState", '1');
			record.save({
						success : function(record, operation) {
							Eway.alert(EwayLocale.addSuccess);
							store.setUrlParamsByObject({
								type : '1'
							});
							store.loadPage(1);
							win.close();
						},
						failure : function(record, operation) {
							Eway.alert(operation.getError());
						},
						button: win.down('button[action="add"]')
					});
		}
	},

	/**
	 * 更新一条机构信息：
	 */
	onUpdate : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var win = Ext.create('Eway.view.person.serviceOrg.Update');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			if (record.data.code == Eway.user.getOrgCode()) {
				win.down('field[name="code"]').setReadOnly(true);
			}
			win.down('button[action="update"]').on('click',this.onUpdateConfirm, this);
			win.show();
		} else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	getUpdateFormFiled : function(name) {
		return this.getUpdateForm().findField(name);
	},

	getUpdateForm : function() {
		return this.getUpdateWin().down("form").getForm();
	},

	onUpdateConfirm : function() {
		var ewayView = this.getEwayView();
		var sm = this.getGrid().getSelectionModel();
		var record = sm.getLastSelected();
		var parentId = record.data.parentId;
		var updateForm = this.getUpdateForm();
		var win = this.getUpdateWin();
		var code = record.data.code;
		var store = this.getGrid().getStore();
		if (updateForm.isValid()) {
			var data = updateForm.getValues();
			record.set("address", data.address);
			record.set("code", data.code);
			record.set("description", data.description);
			record.set("name", data.name);
			record.set("parent", data.parent);
			record.set("parentId", data.parentId);
			record.set("zip", data.zip);
			record.set("serviceObjectId", data.serviceObjectId);
			record.save({
						success : function(record, operation) {
							Eway.alert(EwayLocale.updateSuccess);
							if (data.code == Eway.user.getOrgCode()) {
								Eway.user.setOrgName(data.name);
							} 
							store.setUrlParamsByObject({
								type : '1'
							});
							store.load();
							win.close();
						},
						failure : function(record, operation) {
							Eway.alert(operation.getError());
							//解决脏数据
							store.rejectChanges();
						},
						button: win.down('button[action="update"]')
					});
		}
	},

	/**
	 * 删除一条机构信息：
	 */
	onRemove : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title, EwayLocale.tip.remove.confirm.info, function(button,
					text) {
				if (button == "yes") {
					var record = sm.getLastSelected();
					record.erase({
						success : function() {
							Eway.alert(EwayLocale.deleteSuccess);
							var store = this.getGrid().getStore();
							store.setUrlParamsByObject({
								type : '1'
							});
							store.loadPage(1);
						},
						failure : function(record, operation) {
							//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
							record.dropped = false;
//							if (operation.request.scope.reader.jsonData.flag) {
							if (operation.request._operation.request._scope.reader.rawData.flag) {
								Ext.MessageBox.show({
									title : EwayLocale.tip.tips,
									msg : operation.getError(),
									modal : true,
									fn : function callBack(id) {
										if (id == 'yes') {
											var win = Ext
													.create('Eway.view.person.serviceOrg.SerLinkedDevice');
											var personStore = win
													.down('linkedPersonGrid')
													.getStore();
											personStore.setBaseParam(
													'organizationId',
													record.data.guid);
											personStore.load();
											var deviceStore = win
													.down('linkedDeviceSerGrid')
													.getStore();
											deviceStore.setBaseParam(
													'organizationID',
													record.data.guid);
											deviceStore.load();
											win.show();
										} else if (id == 'no') {
										}
									},
									buttons : Ext.Msg.YESNO
								});
							} else {
								Eway.alert(operation.getError());
							}
						},
						scope : this
					});
				}
			}, this);
		} else {
			Eway.alert(EwayLocale.choiceDeleteMsg);
		}
	},

	onOrganizationTypeChange : function(field, newValue) {
		if (newValue == '0') {
			field.up("form").getForm().findField("serviceObjectId").disable();
		} else {
			field.up("form").getForm().findField("serviceObjectId").enable();
		}
	}
});