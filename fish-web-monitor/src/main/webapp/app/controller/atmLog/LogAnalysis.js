Ext.define('Eway.controller.atmLog.LogAnalysis',{
	extend : 'Eway.controller.base.FishController',
	views : [ 'Eway.view.atmLog.LogAnalysisView' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#atmLog_LogAnalysisView ',
		autoCreate : true,
		xtype : 'atmLog_LogAnalysisView'
	}, {
		ref : 'displayForm',
		selector : 'register_form'
	} ],
	init : function(){

	}
});