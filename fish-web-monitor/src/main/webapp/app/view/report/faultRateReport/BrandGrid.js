Ext.define('Eway.view.report.faultRateReport.BrandGrid', {
	alias : 'widget.report_faultRateReport_BrandGrid',
	extend : 'Eway.view.base.Grid',
	height :400,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				action : 'query',
				glyph : 0xf002,
			} ],
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
			}/*,{
				menuDisabled: true,
	            sortable: false,
	            xtype: 'actioncolumn',
	            width: 50,
	            items: [{
	                iconCls: 'menu-system',
	                tooltip: '点击查看该品牌下所有的型号故障率情况'
	            }]
			}*/]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});