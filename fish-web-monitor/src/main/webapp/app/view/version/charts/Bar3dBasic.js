Ext.define('Eway.view.version.charts.Bar3dBasic', {
    extend: 'Ext.Panel',
    alias: 'widget.bar_3d',
    requires: ['Ext.chart.theme.Muted'],
    width: 450,
    height:300,
    config:{
    	columnField:'data1',
    	rowField:'month'
    },
    initComponent: function() {
        var me = this;
        this.myDataStore = Ext.create('Eway.store.version.VersionCharts');
        Ext.apply(this,{
        	items:[{
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
                height: 300,
                style: 'background: #fff',
                padding: '0 0 0 0',
                insetPadding: 15,
                animation: Ext.isIE8 ? false : {
                    easing: 'backOut',
                    duration: 5
                },
                shadow: false,
                store: me.myDataStore,
                sprites: [{
                    type  : 'text',
                    text  : '版本详情',
                    font  : '14px Helvetica',
                    fontStyle:'oblique',
                    width : 100,
                    height: 30,
                    x : 40, //the sprite x position
                    y : 12  //the sprite y position
                }],
                axes: [{
                    type: 'numeric3d',
                    position: 'left',
//                    majorTickSteps: 2,
                    minimum: 0,
                    fields: [me.getColumnField()],  
                    label: {
                        textAlign: 'right'
                    }
//                ,renderer: function (v, layoutContext) {
//                    return Ext.util.Format.number(layoutContext.renderer(v) , '0');
//                }
                ,
                    grid: {
                        odd: {
                            fillStyle: 'rgba(255, 255, 255, 0.06)'
                        },
                        even: {
                            fillStyle: 'rgba(0, 0, 0, 0.03)'
                        }
                    }
                }, {
                    type: 'category3d',
                    position: 'bottom',
                    fields: [me.getRowField()],
                    label: {
                        rotate: {
                            degrees: -45
                        }
                    },
                    grid: true
                }],
                series: [{
                    type: 'bar3d',
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
                        	this.setHtml(storeItem.get(me.getRowField()) + ': ' + storeItem.get(me.getColumnField()) + ' views');
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
                                          fillStyle: colors[index],
                                          strokeStyle: index % 2 ? 'none' : 'black',
                                          opacity: index % 2 ? 1 : 0.5
                                      };
                                  };
                              })()
                }]
            }]});
        	this.callParent();
    }
});