Ext.define('Eway.view.report.faultRateReport.TypeGrid', {
	alias : 'widget.report_faultRateReport_TypeGrid',
	extend : 'Eway.view.base.Grid',
	initComponent : function() {
		var store = Ext.create('Eway.store.report.faultRateReport.Type');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
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
			}]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});