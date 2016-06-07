Ext.define('Eway.view.report.faultRateReport.TypeGrid', {
	alias : 'widget.report_faultRateReport_TypeGrid',
	extend : 'Eway.view.base.Grid',
	height :200,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar : [ {
					text : EwayLocale.report.faultRateReport.back,
					glyph : 0xf122,
					action : 'back',
					tooltip : EwayLocale.report.faultRateReport.back,
					code : 'back'
				}, "->", {
					glyph : 0xf060,
					action : 'pref',
					tooltip : EwayLocale.report.faultRateReport.preVendor,
					code : 'pref'
				}, {
					glyph : 0xf061,
					action : 'next',
					tooltip : EwayLocale.report.faultRateReport.nextVendor,
					code : 'next'
			} ],
			columns : [ {
				header : EwayLocale.report.faultRateReport.devType,
				dataIndex : 'name',
				width:200
			},{
				header : EwayLocale.report.faultRateReport.tradeCount,
				dataIndex : 'tradeCount',
				width:160
			},{
				header : EwayLocale.report.faultRateReport.faultCount,
				dataIndex : 'faultCount',
				width:160
			},{
				header : EwayLocale.report.faultRateReport.rate,
				dataIndex : 'rate',
				flex:1
			},{
				header:EwayLocale.report.faultRateReport.details,
				width:180,
				dataIndex:'detail',
				renderer: function(value,meta,record) {
					return "&nbsp;<img src='resources/images/accept.png' style='cursor:pointer'>";
				}
			}]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});