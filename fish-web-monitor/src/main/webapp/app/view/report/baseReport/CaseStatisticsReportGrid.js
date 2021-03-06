Ext.define('Eway.view.report.baseReport.CaseStatisticsReportGrid', {
	alias : 'widget.baseReport_CaseStatisticsReportGrid',
	extend: 'Ext.panel.Panel',

	requires : [ 'Eway.lib.Util','Ext.chart.theme.Muted' ],
    config:{
    	columnField:'total',
    	rowField:'angle'
    },
	border : false,
	closable : false ,
    initComponent: function() {
    	var me = this;
        var store = Ext.create('Eway.store.report.baseReport.CaseStatisticsReport');
        Ext.apply(this, {
        tbar : [ '->', {
			text : EwayLocale.button.search,
			glyph : 0xf002,
			action : 'query',
			code:'caseStatisticsQuery',
			listeners:{
				'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
		}
		/*, {
			text : EwayLocale.report.openrate.device.importStat,
			glyph : 0xf1c3,
			action : 'importStat'
		}*/
		 ],
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
                height: 430,
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
//                    majorTickSteps: 2,
                    minimum: 0,
                    fields: [me.getColumnField()],
                    label: {
                        textAlign: 'right'
                    },
                    grid: {
                        odd: {
                            fillStyle: 'rgba(255, 255, 255, 0.06)'
                        },
                        even: {
                            fillStyle: 'rgba(0, 0, 0, 0.03)'
                        }
                    }
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
                        	storeItem.setHtml(item.get(me.getRowField()) + ': ' + item.get(me.getColumnField()));
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