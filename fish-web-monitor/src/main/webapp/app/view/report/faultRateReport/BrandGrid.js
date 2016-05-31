Ext.define('Eway.view.report.faultRateReport.BrandGrid', {
	alias : 'widget.report_faultRateReport_BrandGrid',
	extend : 'Eway.view.base.Grid',
	width : 400,
	height :400,
	initComponent : function() {
		var store=Ext.create('Eway.store.report.faultRateReport.Brand');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [ {
				header : EwayLocale.report.faultRateReport.vendorName,
				dataIndex : 'name',
				width:130
			},{
				header : EwayLocale.report.faultRateReport.tradeCount,
				dataIndex : 'tradeCount',
				width:130
			},{
				header : EwayLocale.report.faultRateReport.faultCount,
				dataIndex : 'faultCount',
				width:130
			},{
				header : EwayLocale.report.faultRateReport.rate,
				dataIndex : 'rate',
				flex:1
			}]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});