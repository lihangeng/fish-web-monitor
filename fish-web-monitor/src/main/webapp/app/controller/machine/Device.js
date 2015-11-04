Ext.define('Eway.controller.machine.Device', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.Device', 'Hour', 'Minute', 'machine.DeviceAtmType',
			'machine.PersonM', 'machine.PersonTM',
			'machine.DeviceAwayFlagComboBox',
			'machine.atmType.DeviceAtmVendor',
			'machine.atmType.DeviceAtmCatalog' ],

	models : [ 'machine.Device', 'machine.Person' ],

	views : [ 'machine.device.View' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#device',
		autoCreate : true,
		xtype : 'device.view',
		id : 'device'
	}, {
		ref : 'grid',
		selector : 'device_grid'
	}, {
		ref : 'addWin',
		selector : 'device_add'
	}, {
		ref : 'updateWin',
		selector : 'device_update'
	}, {
		ref : 'filterForm',
		selector : 'device_filterform'
	} ],

	addView : 'Eway.view.machine.device.Add',
	updateView : 'Eway.view.machine.device.Update',
	infoView : 'Eway.view.machine.device.Info',

	formConfig : {
		form : 'Eway.view.machine.device.Form',
		xtype : 'machine_device_form',
		title : Eway.locale.machine.device.devInfo,
		width : 700,
		height : 450
	},

	init : function() {
		this.initBaseControl();
		this.control({
			'#device button[action=add]' : {
				click : this.onAdd
			},
			'#device button[action=query]' : {
				click : this.onQuery
			},
			'#device button[action=remove]' : {
				click : this.onRemove
			},
			'#device button[action=info]' : {
				click : this.onInfo
			},
			'#device button[action=update]' : {
				click : this.onUpdate
			},
			'#device button[action=export]' : {
				click : this.onExport
			}
		});
	},
	onExport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();

		// 查询输入验证
		if (bool == false) {
			return
		}
		var values = form.getValues();

		var params = "";
		if (values.address) {
			params += '&address=' + values.address;
		}
		if (values.awayFlag) {
			params += '&awayFlag=' + values.awayFlag;
		}
		if (values.devCatalogId) {
			params += '&devCatalogId=' + values.devCatalogId;
		}
		if (values.devService) {
			params += '&devService=' + values.devService;
		}
		if (values.devType) {
			params += '&devType=' + values.devType;
		}
		if (values.devVendorId) {
			params += '&devVendorId=' + values.devVendorId;
		}
		if (values.endCashboxLimit) {
			params += '&endCashboxLimit=' + values.endCashboxLimit;
		}
		if (values.ip) {
			params += '&ip=' + values.ip;
		}
		if (values.organization) {
			params += '&organization=' + values.organization;
		}
		if (values.terminalId) {
			params += '&terminalId=' + values.terminalId;
		}

		window.location.href = 'api/machine/device/export?_dc=' + params;
	},
	onInfo : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var win = Ext.create(this.infoView);

			var record = sm.getLastSelected();
			this.terminalId = record.get('terminalId');
			win.down('form').getForm().loadRecord(record);

			var tabPanel = win.down('tabpanel');

			// 维护员
			var maintain = tabPanel.query('[itemid=maintainItemID]')[0];

			// 管机员
			var tubeMachine = tabPanel.query('[itemid=tubeMachineItemID]')[0];

			maintain.on('render', this.renderMaintain, this);
			tubeMachine.on('render', this.renderTubeMachine, this);

			win.show();

		} else {
			Eway.alert(Eway.locale.tip.search.record);
		}
	},
	// 加载维护员的数据
	renderMaintain : function(tab) {
		var params = {
			terminalId : this.terminalId,
			type : 1
		};
		tab.onReload(params);
	},
	// 加载管机员的数据
	renderTubeMachine : function(tab) {
		var params = {
			terminalId : this.terminalId,
			type : 0
		};
		tab.onReload(params);
	},
	_onAddOrUpdate : function(action){
		var title = action=='add' ? Eway.locale.button.add+this.formConfig.title : Eway.locale.button.update+this.formConfig.title;
		var me = this;
		Ext.require([this.formConfig.form],function(){
			if(action=='update'){
				var grid = this.getGridPanel(),
					sm = grid.getSelectionModel(),
					count = sm.getCount();
				if(count == 0){
					Eway.alert(Eway.choiceUpdateMsg);
					return;
				}
				else if(count > 1){
					Eway.alert(Eway.locale.tip.update.one);
					return;
				}
			}

			var win = Ext.create('Eway.view.base.Window',{
				title : title,
				width : this.formConfig.width ? this.formConfig.width : 500,
				items : [{
					xtype : this.formConfig.xtype
				}]
			});
			win.setAction(action);
			if(action == 'add'){
			    //将设备维护商默认选择怡化
				win.down('field[name="devServiceId"]').setValue("2");
				win.down('field[name="devServiceName"]').setValue(Eway.yihua);

                //如果是维护商用户或者是admin用户登陆时设置银行机构为空。
				if(ewayUser.getOrgId() == 1 || ewayUser.getOrgType() == 1){
					win.down('field[name="orgId"]').setValue(null);
					win.down('field[name="orgName"]').setValue(null);
				//如果是银行机构登陆则默认显示当前机构
				}else{
					win.down('field[name="orgId"]').setValue(ewayUser.getOrgId());
					win.down('field[name="orgName"]').setValue(ewayUser.getOrgName());
				}
			}
			win.down('button[action="confirm"]').on('click',me._save,me);
			if(action == 'update'){
				grid = this.getGridPanel(),
				sm = grid.getSelectionModel(),
				form = win.down('form');
				var record = sm.getLastSelected();
				form.loadCusRecord(record);
				this.boforeShowUpdateWin(win,grid,record);
			}else{
				this.beforeShowAddWin(win,grid);
			}
			win.show();
		},this);
	}
});