Ext.define('Eway.view.report.faultRateReport.TypeCharts', {
	alias : 'widget.report_faultRateReport_TypeCharts',
	extend: 'Ext.Panel',
    xtype: 'column-stacked',

	requires : [ 'Eway.lib.Util','Ext.chart.theme.Muted' ],
    config:{
    	columnField:'rate',
    	rowField:'typeName'
    },
	border : false,
	closable : false ,	
    initComponent: function() {
    	var me = this;
        Ext.apply(this, {
        items : [{
                xtype: 'cartesian',
                reference: 'chart',
                width: '100%',
                theme: {
                    type: 'muted'
                },
                legend: {
                    docked: 'bottom'
                },
                store: {type: 'typeRate'},
                plugins: {
                    ptype: 'chartitemevents',
                    moveEvents: true,
                    clickEvents: true,
                    dbClickEvents: true
                },
                height: 400,
                style: 'background: #fff',
                padding: '0 0 0 0',
                insetPadding: {
                    top: 45,
                    left: 15,
                    right: 15,
                    bottom: 15
                },
                animation: Ext.isIE8 ? false : {
                    easing: 'backOut',
                    duration: 5
                },
                shadow: false,
                sprites: [{
                    type  : 'text',
                    text:'不同型号交易故障率',
                    font  : '14px Helvetica',
                    fontStyle:'oblique',
                    width : 100,
                    height: 30,
                    x : 20, //the sprite x position
                    y : 25  //the sprite y position
                }],
                axes: [{
                    type: 'numeric',
                    position: 'left',
                    minimum: 0,
                    adjustByMajorUnit: true,
                    fields: ['rate'],
                    renderer: function (v) { return v.toFixed(); },
                    grid: true
                }, {
                    type: 'category',
                    position: 'bottom',
                    fields: ['typeName'],
                    grid: true,
                    label: {
                        rotate: {
                            degrees: -45
                        }
                    }
                }],
                series: [{
                    type: 'bar',
                    axis: 'left',
                    xField: 'typeName',
                    title: [ '故障数', '交易数', '故障率(百分比)' ],
                    yField: [ 'fault','trade','rate' ],
                    stacked: false,
                    style: {
                        opacity: 0.80
                    },
                    label: {
                        field: [ 'fault','trade','rate' ],// me.getColumnField(),
                        display: 'insideEnd'
                    },
                    tooltip: {
                    	trackMouse: true,
                        style: 'background: #fff',
                        renderer: function(storeItem, item) {
                            var type = item.series.getTitle()[Ext.Array.indexOf(item.series.getYField(), item.field)];
                            this.setHtml(type + ' for ' + storeItem.get('typeName') + ': ' + storeItem.get(item.field));
                        }
                    },
                }]
            }]
        }) ;

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