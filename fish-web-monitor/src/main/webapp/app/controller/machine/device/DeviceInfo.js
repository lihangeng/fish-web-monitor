Ext.define('Eway.controller.machine.device.DeviceInfo', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Hour', 'Minute', 'machine.DeviceAtmType', 'machine.PersonM',
			'machine.PersonTM' ],

	models : [ 'machine.Person' ],

	views : [ 'Eway.view.machine.device.Info' ],

	infoView : 'Eway.view.machine.device.Info',

	init : function() {
	},
	onMonitorToDeviceInfo : function(deviceid) {
		var win = Ext.create(this.infoView);
		this.terminalId = deviceid;

		win.down('form').getForm().load({
			url : 'api/machine/device/findByTerminalId',
			scope : this,
			params : {
				terminalId : deviceid
			},
			method : 'GET',
			success : function(form, action) {
				this.terminalId = form.findField('terminalId').getValue();
			},
			failture : function(form, action) {
				Eway.alert("Data Load Fail!");
			}
		});
		this.showInfoView(win);
	},

	showInfoView : function(win) {
		var tabPanel = win.down('tabpanel');

		// 维护员
		var maintain = tabPanel.query('[itemid=maintainItemID]')[0];

		// 管机员
		var tubeMachine = tabPanel.query('[itemid=tubeMachineItemID]')[0];

		maintain.on('render', this.renderMaintain, this);
		tubeMachine.on('render', this.renderTubeMachine, this);

		win.show();
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
	}
});