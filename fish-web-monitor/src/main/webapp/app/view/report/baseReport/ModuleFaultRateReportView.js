Ext.define('Eway.view.report.baseReport.ModuleFaultRateReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.moduleFaultRateReportView',

	requires : ['Eway.view.report.baseReport.ModuleFaultRateReportFilter',
			    'Eway.view.report.baseReport.ModuleFaultRateReportGrid' ],

	title : EwayLocale.report.moduleFaultRateReport.Title,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'moduleFaultRateReportFilter'
			}, {
				region : 'center',
				xtype : 'moduleFaultRateReportGrid'
			} ],

		});

		this.callParent(arguments);
	}
});