Ext.define('Eway.view.report.baseReport.TransactionDaysCountView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_TransactionDaysCountView',

	requires : [
			'Eway.view.report.baseReport.TransactionDaysCountFilterForm',
			'Eway.view.report.baseReport.TransactionDaysCountGrid'],

	title : Eway.locale.report.baseReport.tradeDaysCountRep,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'baseReport_TransactionDaysCountFilterForm'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'baseReport_TransactionDaysCountGrid'
			} ]
		});
		this.callParent(arguments);
	}
});