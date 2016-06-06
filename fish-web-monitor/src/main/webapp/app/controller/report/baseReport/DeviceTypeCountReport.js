
Ext.define('Eway.controller.report.baseReport.DeviceTypeCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	models : ['Dict'],



	views : ['Eway.view.report.baseReport.DeviceTypeCountReportView'],

	stores : ['monitor.card.DeviceAtmVendor','monitor.card.DeviceType'],


	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_DeviceTypeCountReportView',
		autoCreate : true,
		xtype : 'baseReport_DeviceTypeCountReportView'
	} ],

	init : function() {
		this.control({
			'baseReport_DeviceTypeCountReportView button[action=query]':{
				click : this.onQuery
			}
		});
	}


});