Ext.define('Eway.view.parameter.paramMonitor.TaskView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_paramMonitor_TaskView',

	requires : [ 'Eway.view.parameter.paramMonitor.TaskFilterForm',
			'Eway.view.parameter.paramMonitor.TaskGrid' ],

	config : {
		jobId : 0
	},
	layout : 'border',
	closable:false,
	name : 'paramMonitorDetails',
	initComponent : function() {
		Ext.apply(this, {
			layout : 'border',
			initRegion : true,
			border : true,
			weight : 15,
			tbar : [ {
				text : EwayLocale.report.faultRateReport.back,
				glyph : 0xf122,
				action : 'back',
				tooltip : EwayLocale.report.faultRateReport.back,
				code : 'back'
			}, "->", {
				glyph : 0xf060,
				action : 'pref',
				tooltip : EwayLocale.report.faultRateReport.preType,
				code : 'pref'
			}, {
				glyph : 0xf061,
				action : 'next',
				tooltip : EwayLocale.report.faultRateReport.nextType,
				code : 'next'
			} ],
			items : [ {
				region : 'north',
				xtype : 'parameter_paramMonitor_TaskFilterForm'
			}, {
				region : 'center',
				jobId : this.getJobId(),
				xtype : 'parameter_paramMonitor_TaskGrid'
			} ]
		});
		this.callParent(arguments);
	}
});