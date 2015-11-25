Ext.define('Eway.view.report.baseReport.CaseTrendReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.caseTrendReportView',

	requires : [
			'Eway.view.report.baseReport.CaseTrendReportFilterForm',
			'Eway.view.report.baseReport.CaseTrendReportGrid'],

	title : EwayLocale.report.baseReport.caseTrendReportTitle,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'caseTrendReportFilterForm',
				height : 80
			}, {
				region : 'center',
				xtype : 'caseTrendReportGrid'
			} ]
		});
		this.callParent(arguments);
	}
});