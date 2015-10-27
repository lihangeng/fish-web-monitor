Ext.define('Eway.controller.monitor.MonitorSummaryInfo', {
	extend : 'Eway.controller.base.FishController',

	views : [ 'Eway.view.monitor.charts.View' ],
	infoView : 'Eway.view.monitor.cashinit.Info',

	refs : [ {
		ref : 'ewayView',
		selector : 'monitor_view',
		autoCreate : true,
		xtype : 'monitor_view'
	} ],
	init : function() {
		this.control({
			
		});
	}

	
});