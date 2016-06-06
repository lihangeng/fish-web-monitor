Ext.define('Eway.view.report.faultRateReport.BrandGrid', {
	alias : 'widget.report_faultRateReport_BrandGrid',
	extend : 'Eway.view.base.Grid',
	width : 400,
	height :400,
	initComponent : function() {
		var store=Ext.create('Eway.store.report.faultRateReport.Brand');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [ {
				header : EwayLocale.report.faultRateReport.vendorName,
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
				header:'详情',
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