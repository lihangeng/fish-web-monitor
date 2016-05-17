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
				header : '型号',
				dataIndex : 'typeName',
				width:130
			},{
				header : '故障数',
				dataIndex : 'fault',
				width:130
			},{
				header : '交易数',
				dataIndex : 'trade',
				width:130
			},{
				header : '故障率',
				dataIndex : 'rate',
				flex:1
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});