Ext.define('Eway.view.report.faultRateReport.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_view',

	requires : [ 'Eway.view.report.faultRateReport.BrandGrid',
			'Eway.view.report.faultRateReport.BrandCharts',
			'Eway.view.report.faultRateReport.TypeView' ],

	title : EwayLocale.report.faultRateReport.viewTitle,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				items : [ {
					xtype : 'datefield',
					fieldLabel : EwayLocale.report.faultRateReport.dateMonth,
					labelWidth : 50,
					editable : false,
					value : new Date(),
					labelAlign : 'left',
					format : 'Y-m',
					name : 'dateMonth',
					width : 220,
					anchor : '100%',
					height : 20,
					listeners : {
						blur : {
							fn : function(This, options) {
								return;
							}
						}
					}
				}, {
					height : 40,
					tbar : [ '->', {
						text : EwayLocale.button.search,
						action : 'query',
						glyph : 0xf002,
					} ]
				} ]
			}, {
				region : 'center',
				xtype : 'panel',
				layout : 'card',
				items : [ {
					name : 'groupPanel',
					xtype : 'panel',
					layout : 'border',
					items : [ {
						xtype : 'report_faultRateReport_BrandGrid',
						region : 'north',
						border : true,
						height : 150
					}, {
						xtype : 'report_faultRateReport_BrandCharts',
						region : 'center',
					} ]
				}, {
					xtype : 'report_faultRateReport_typeView'
				} ]
			} ],
			listeners : {
				activate : function(panel) {
					panel.down('report_faultRateReport_BrandGrid').getStore()
							.load();
					panel.down('report_faultRateReport_BrandCharts').down(
							'cartesian').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});