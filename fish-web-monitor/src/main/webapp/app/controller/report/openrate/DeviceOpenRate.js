Ext.define('Eway.controller.report.openrate.DeviceOpenRate', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Eway.store.report.openrate.DeviceOpenRate',
	           'machine.atmType.DeviceAtmVendor',
	           'machine.DeviceAtmType',
	           'machine.atmType.DeviceAtmCatalog',
	           'machine.DeviceAwayFlagComboBox',
	           'machine.PersonM', 'machine.PersonTM',
	           'person.person.PersonStateFilterDict',
			   'person.person.PersonJob'],

	models : [ 'Eway.model.report.openrate.DeviceOpenRate' ],

	views : [ 'Eway.view.report.openrate.device.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#openrateView',
		autoCreate : true,
		xtype : 'report_openrate_device_view'
	} ],

	init : function() {
		this.onQuery();
		this.control({
			'report_openrate_device_view button[action=query]' : {
				click : this.onQuery
			},
			'report_openrate_device_view button[action=importStat]' : {
				click : this.onImport
			}
		});
	},
	onImport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		var param = 'statType='+values.statType+'&day='+values.day+'&month='+values.month+'&year='+values.year+'&terminalId='+values.terminalId;
//		+'&devVendor='+values.devVendor+'&devType='+values.devType+'&devCatalogId'+values.devCatalogId+'&awayFlag'+values.awayFlag+'&org'+values.org+'&openRate'+values.openRate
		window.location.href = 'api/report/openrate/device/importStat?' + param;
	}
});