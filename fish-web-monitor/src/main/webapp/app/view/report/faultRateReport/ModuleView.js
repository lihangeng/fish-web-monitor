Ext.define('Eway.view.report.faultRateReport.ModuleView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_moduleView',

	requires : [ 'Eway.lib.Util',
			'Eway.view.report.faultRateReport.ModuleGrid',
			'Eway.view.report.faultRateReport.ModuleCharts' ],
	closable : false,
	initComponent : function() {
		var me = this;
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
				xtype : 'report_faultRateReport_ModuleGrid',
				region : 'north',
				border : true,
				height : 140
			}, {
				xtype : 'report_faultRateReport_ModuleCharts',
				region : 'center',
			} ]
		});

		this.callParent(arguments);
	}
});