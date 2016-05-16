Ext.define('Eway.view.report.faultRateReport.ModuleGrid', {
	alias : 'widget.report_faultRateReport_ModuleGrid',
	extend : 'Eway.view.base.Grid',
	initComponent : function() {
//		var store = Ext.create('Eway.store.parameter.devParameter.DevInfo');
//		store.loadPage(1);
		Ext.apply(this, {
//			initRegion : true,
//			store : store,
			columns : [ {
				header : '模块',
				dataIndex : 'brand',
				width:100
			},{
				header : '故障数',
				dataIndex : 'fault',
				width:100
			},{
				header : '交易数',
				dataIndex : 'trade',
				width:100
			},{
				header : '故障率',
				dataIndex : 'rate',
				width:100
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
//				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});