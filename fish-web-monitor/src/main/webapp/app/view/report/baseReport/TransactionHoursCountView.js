Ext.define('Eway.view.report.baseReport.TransactionHoursCountView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_TransactionHoursCountView',

	requires : [
			'Eway.view.report.baseReport.TransactionHoursCountFilterForm',
			'Eway.view.report.baseReport.TransactionHoursCountGrid'],

	title : EwayLocale.report.baseReport.tradeHoursCountRep,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'baseReport_TransactionHoursCountFilterForm'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'baseReport_TransactionHoursCountGrid'
			} ]
		});
		this.callParent(arguments);
	}
});