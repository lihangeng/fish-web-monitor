Ext.define('Eway.controller.report.run.AnalysisReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Eway.store.report.run.AnalysisReport' ],

	models : [ 'Eway.model.report.run.AnalysisReport' ],

	views : [ 'Eway.view.report.run.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#reportRunew',
		autoCreate : true,
		xtype : 'report_run_view'
	} ],

	init : function() {
		this.onQuery();
		this.control({
			'report_run_view button[action=query]' : {
				click : this.onQuery
			}
		});
	}
});