Ext.define('Eway.view.report.faultRateReport.BrandView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.report_faultRateReport_brandView',

	requires : [ 'Eway.view.report.faultRateReport.BrandGrid',
			'Eway.view.report.faultRateReport.BrandCharts',
			'Ext.ux.form.MonthComBox'
			],

	title : EwayLocale.report.faultRateReport.viewTitle,
	closable: false,
	layout : 'border',
	initComponent : function() {
		var store=Ext.create('Eway.store.report.faultRateReport.Brand');
		Ext.apply(this, {
			items : [{
						region : 'north',
						xtype:'form',
						layout:'column',
						items : [ {
							xtype : 'MonthComBox',
							fieldLabel : EwayLocale.report.faultRateReport.dateMonth,
							editable : false,
							value : Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -1),'Ym'),
							labelAlign : 'left',
							format : 'Ym',
							name : 'dateMonth',
							columnWidth : 0.4,
							anchor : '100%',
							height : 30,
							canClear: false
						}, {
							xtype : 'displayfield',
							name : 'monthLimit',
							value :　6,
							hidden : true
						} ]
					},  {
						xtype : 'report_faultRateReport_BrandGrid',
						region : 'center',
						store:store
					}, {
						xtype : 'report_faultRateReport_BrandCharts',
						store:store,
						region : 'south'
					} ],
				listeners : {
					afterrender : function(panel) {
						store.setBaseParam("dateTime", panel.down('form').getForm().getValues().dateMonth);
						panel.down('report_faultRateReport_BrandGrid').getStore().load();
						panel.down('report_faultRateReport_BrandCharts').down('cartesian').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});