Ext.define('Eway.view.report.faultRateReport.BrandCharts', {
	alias : 'widget.report_faultRateReport_BrandCharts',
	extend: 'Eway.view.base.Panel',

	requires : [ 'Eway.lib.Util','Ext.chart.theme.Muted' ],
    config:{
    	columnField:'rate',
    	rowField:'brandName'
    },
	border : false,
	closable : false ,
    initComponent: function() {
    	var me = this;
    	var store=Ext.create('Eway.store.report.faultRateReport.Brand');
        Ext.apply(this, {
        items : [{
                xtype: 'cartesian',
                reference: 'chart',
                width: '100%',
                theme: {
                    type: 'muted'
                },
                plugins: {
                    ptype: 'chartitemevents',
                    moveEvents: true,
                    clickEvents: true,
                    dbClickEvents: true
                },
                height: 400,
                style: 'background: #fff',
                padding: '0 0 0 0',
                insetPadding: 15,
                animation: Ext.isIE8 ? false : {
                    easing: 'backOut',
                    duration: 5
                },
                shadow: false,
                store: store,
                sprites: [{
                    type  : 'text',
                    font  : '14px Helvetica',
                    fontStyle:'oblique',
                    width : 100,
                    height: 30,
                    x : 40, //the sprite x position
                    y : 12  //the sprite y position
                }],
                axes: [{
                    type: 'numeric',
                    position: 'left',
                    minimum: 0,
                    fields: [me.getColumnField()],
                    label: {
                        renderer: function(v) { return v.toFixed() + '%'; }
                    },
                    grid: true
                }, {
                    type: 'category',
                    position: 'bottom',
                    fields: [me.getRowField()],
                    grid: true
                }],
                series: [{
                    type: 'bar',
                    axis: 'left',
                    xField: me.getRowField(),
                    yField: me.getColumnField(),
                    label: {
                        field:  me.getColumnField(),
                        display: 'insideEnd'
                    },
                    tooltip: {
                        trackMouse: true,
                        style: 'background: #fff',
                        renderer: function(storeItem, item) {
                        	this.setHtml(storeItem.get(me.getRowField()) + ': ' + storeItem.get(me.getColumnField()));
                        }
                    },
                    renderer: (function () {
                        var colors = [
                                      '#8ca640',
                                      '#974144',
                                      '#4091ba',
                                      '#8e658e',
                                      '#3b8d8b',
                                      '#b86465',
                                      '#d2af69',
                                      '#6e8852',
                                      '#3dcc7e',
                                      '#a6bed1',
                                      '#cbaa4b',
                                      '#998baa'
                                  ];

                                  return function (sprite, config, data, index) {
                                      return {
                                          fillStyle: colors[index % 12]
//                                          strokeStyle: index % 2 ? 'none' : 'black',
//                                          opacity: index % 2 ? 1 : 0.5
                                      };
                                  };
                              })()
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