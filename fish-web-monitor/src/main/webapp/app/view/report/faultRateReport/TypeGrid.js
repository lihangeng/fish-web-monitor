Ext.define('Eway.view.report.faultRateReport.TypeGrid', {
	alias : 'widget.report_faultRateReport_TypeGrid',
	extend : 'Eway.view.base.Grid',
	height :200,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar : [ {
					text : '返回',
					glyph : 0xf122,
					action : 'back',
					tooltip : '返回',
					code : 'back'
				}, "->", {
					//text : '上一个品牌',
					glyph : 0xf060,
					action : 'pref',
					tooltip : '上一个品牌',
					code : 'pref'
				}, {
					//text : '下一个品牌',
					glyph : 0xf061,
					action : 'next',
					tooltip : '下一个品牌',
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