Ext.define('Eway.view.machine.detail.run.TradingVolumeInfo', {
    extend: 'Ext.panel.Panel',
    xtype: 'tradingVolume',
    width: 650,

    items: [{
        xtype: 'cartesian',
        reference: 'chart',
        width: '100%',
        height: 500,
        legend: {
            docked: 'right'
        },
        store: {
            type: 'browsers'
        },
        insetPadding: 40,
//        sprites: [{
//            type: 'text',
//            text: 'Line Charts - Marked Lines',
//            fontSize: 22,
//            width: 100,
//            height: 30,
//            x: 40, // the sprite x position
//            y: 20  // the sprite y position
//        }, {
//            type: 'text',
//            text: 'Data: Browser Stats 2012',
//            fontSize: 10,
//            x: 12,
//            y: 470
//        }, {
//            type: 'text',
//            text: 'Source: http://www.w3schools.com/',
//            fontSize: 10,
//            x: 12,
//            y: 485
//        }],
        axes: [{
            type: 'numeric',
            fields: ['data1', 'data2', 'data3', 'data4' ],
            position: 'left',
            grid: true,
            minimum: 0,
            renderer: 'onAxisLabelRender'
        }, {
            type: 'category',
            fields: 'month',
            position: 'bottom',
            grid: true,
            label: {
                rotate: {
                    degrees: -45
                }
            }
        }],
        series: [{
            type: 'line',
            title: 'IE',
            xField: 'month',
            yField: 'data1',
            marker: {
                type: 'square',
                fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }, {
            type: 'line',
            title: 'Firefox',
            xField: 'month',
            yField: 'data2',
            marker: {
                type: 'triangle',
                fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }, {
            type: 'line',
            title: 'Chrome',
            xField: 'month',
            yField: 'data3',
            marker: {
                type: 'arrow',
                fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }, {
            type: 'line',
            title: 'Safari',
            xField: 'month',
            yField: 'data4',
            marker: {
                type: 'cross',
                fx: {
                    duration: 200,
                    easing: 'backOut'
                }
            },
            highlightCfg: {
                scaling: 2
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }]
    }]
});