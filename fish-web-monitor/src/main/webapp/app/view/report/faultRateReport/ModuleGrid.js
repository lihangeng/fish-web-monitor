Ext.define('Eway.view.report.faultRateReport.ModuleGrid', {
	alias : 'widget.report_faultRateReport_ModuleGrid',
	extend : 'Eway.view.base.Grid',
	initComponent : function() {
		var store = Ext.create('Eway.store.report.faultRateReport.Module');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [ {
				header : '模块',
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