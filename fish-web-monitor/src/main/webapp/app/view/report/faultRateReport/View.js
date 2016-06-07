Ext.define('Eway.view.report.faultRateReport.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_view',

	requires : [
			'Eway.view.report.faultRateReport.BrandView',
			'Eway.view.report.faultRateReport.TypeView',
			'Eway.view.report.faultRateReport.ModuleView'],

	title : EwayLocale.report.faultRateReport.viewTitle,
	layout : 'card',
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				xtype:'report_faultRateReport_brandView'
			},{
				xtype:'report_faultRateReport_typeView'
			},{
				xtype:'report_faultRateReport_moduleView'
			}]
		});

		this.callParent(arguments);
	}
});