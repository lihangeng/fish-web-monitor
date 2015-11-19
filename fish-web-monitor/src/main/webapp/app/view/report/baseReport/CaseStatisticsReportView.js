Ext.define('Eway.view.report.baseReport.CaseStatisticsReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_CaseStatisticsReportView',

	requires : [
			'Eway.view.report.baseReport.CaseStatisticsReportFilterForm',
			'Eway.view.report.baseReport.CaseStatisticsReportGrid'],

	title : EwayLocale.report.baseReport.caseStatisticsRep,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'baseReport_CaseStatisticsReportFilterForm'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'baseReport_CaseStatisticsReportGrid'
			} ]
		});
		this.callParent(arguments);
	}
});