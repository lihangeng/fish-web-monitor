Ext.define('Eway.view.report.faultRateReport.TypeView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.report_faultRateReport_typeView',

	requires : [ 'Eway.view.report.faultRateReport.TypeGrid',
			'Eway.view.report.faultRateReport.TypeCharts' ],
	border : false,
	closable : false,
	layout : 'border',
	initComponent : function() {
		var store = Ext.create('Eway.store.report.faultRateReport.Type');
		Ext.apply(this, {
			items : [ {
					xtype : 'report_faultRateReport_TypeGrid',
					region : 'north',
					store: store
				}, {
					xtype : 'report_faultRateReport_TypeCharts',
					region : 'center',
					store: store
			}]
		});
		this.callParent(arguments);
	}
});