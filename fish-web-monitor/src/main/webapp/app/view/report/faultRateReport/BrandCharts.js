Ext.define('Eway.view.report.faultRateReport.BrandCharts', {
	alias : 'widget.report_faultRateReport_BrandCharts',
	extend : 'Ext.Panel',
	xtype : 'column-stacked',

	requires : [ 'Eway.lib.Util', 'Ext.chart.theme.Muted' ],
	config : {
		columnField : 'rate',
		rowField : 'name'
	},
	border : false,
	closable : false,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			items : [ {
				xtype : 'cartesian',
				reference : 'chart',
				width : '100%',
				theme : {
					type : 'muted'
				},
				legend : {
					docked : 'right'
				},
				store : {
					type : 'brandRate'
				},
				plugins : {
					ptype : 'chartitemevents',
					moveEvents : true,
					clickEvents : true,
					dbClickEvents : true
				},
				height : 340,
				style : 'background: #fff',
				padding : '0 0 0 0',
				insetPadding : {
					top : 45,
					left : 15,
					right : 15,
					bottom : 15
				},
				animation : Ext.isIE8 ? false : {
					easing : 'backOut',
					duration : 5
				},
				shadow : false,
				sprites : [ {
					type : 'text',
					text : EwayLocale.report.faultRateReport.vendorRate,
					font : '14px Helvetica',
					fontStyle : 'oblique',
					width : 100,
					height : 30,
					x : 20, 
					y : 25
				} ],
				axes : [ {
					type : 'numeric',
					position : 'left',
					minimum : 0,
					adjustByMajorUnit : true,
					fields : [ 'rate' ],
					renderer : function(axis, label, layoutContext) {
						return layoutContext.renderer(label);
					},
					grid : true
				}, {
					type : 'category',
					position : 'bottom',
					fields : [ 'name' ],
					grid : true,
					label : {
						rotate : {
							degrees : -45
						}
					}
				} ],
				series : [ {
					type : 'bar',
					axis : 'left',
					xField : 'name',
					title : [ EwayLocale.report.faultRateReport.tradeCount,
							EwayLocale.report.faultRateReport.faultCount,
							EwayLocale.report.faultRateReport.rate ],
					yField : [ 'tradeCount', 'faultCount', 'rate' ],
					stacked : false,
					style : {
						opacity : 0.80
					},
					label : {
						field : [ 'tradeCount', 'faultCount', 'rate' ],
						display : 'insideEnd'
					},
					tooltip : {
						trackMouse : true,
						style : 'background: #fff',
						renderer : function(storeItem, item) {
							var brand = item.series.getTitle()[Ext.Array
									.indexOf(item.series.getYField(),
											item.field)];
							storeItem.setHtml(brand + ' for '
									+ item.get('name') + ': '
									+ item.get(item.field));
						}
					},
				} ]
			} ]
		});

		this.callParent();
	},

	onReload : function() {
		this.getStore().load();
	},
	/**
	 * Custom function used for column renderer
	 * 
	 * @param {Object}
	 *            val
	 */
	pctChange : function(val) {
		if (val >= 20) {
			return '<span style="color:green;">' + val + '%</span>';
		} else if (val < 20) {
			return '<span style="color:red;">' + val + '%</span>';
		}
		return val;
	}
});