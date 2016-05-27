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
				header : '品牌',
				dataIndex : 'name',
				width:130
			},{
				header : '交易数',
				dataIndex : 'tradeCount',
				width:130
			},{
				header : '故障数',
				dataIndex : 'faultCount',
				width:130
			},{
				header : '故障率(百分比)',
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