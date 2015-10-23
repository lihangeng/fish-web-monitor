Ext.define('Eway.view.index.LineBasic', {
    extend: 'Ext.Panel',
    xtype: 'lineBasic',
    requires:['Eway.store.index.FaultTrendByDay'],

//    width: 650,
    title :Eway.locale.index.dailyFaultPic,

    initComponent: function() {
        var me = this;
       /* me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['month', 'data1' ],
            data: [
                { month: '4-10', data1: 20 },
                { month: '4-11', data1: 20 },
                { month: '4-12', data1: 19 },
                { month: '4-13', data1: 18 },
                { month: '4-14', data1: 18 },
                { month: '4-15', data1: 17 },
                { month: '4-16', data1: 16 }
            ]
        });*/
        me.myDataStore = Ext.create('Eway.store.index.FaultTrendByDay');
        me.myDataStore.load();
        me.items = [{
            xtype: 'cartesian',
            width: '100%',
            height: 300,
           /* interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },*/
            store: this.myDataStore,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40
            },
            sprites: [/*{
                type: 'text',
                text: 'Line Charts - Basic Line',
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            },{
                type: 'text',
                text: 'Data: Browser Stats 2012',
                fontSize: 10,
                x: 12,
                y: 470
            },  */{
                type: 'text',
                text: Eway.locale.index.dailyFaultPic,
                fontSize: 10,
                x: 230,
                y: 485
            }],
            axes: [{
                type: 'numeric',
                fields: 'data1',
                position: 'left',
                grid: true,
                minimum: 0,
//                maximum: 48,
                renderer: function (v, layoutContext) {
                    return layoutContext.renderer(v);
                }
            }, {
                type: 'category',
                fields: 'month',
                position: 'bottom',
                grid: true,
                renderer: function (v) {
                    return v.substring(4);
                },
                label: {
                    rotate: {
                        degrees: -45
                    }
                }
            }],
            series: [{
                type: 'line',
                xField: 'month',
                yField: 'data1',
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'data1',
                    display: 'over'
                },
                highlight: {
                    fillStyle: '#000',
                    radius: 5,
                    lineWidth: 2,
                    strokeStyle: '#fff'
                },
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get('month') + Eway.locale.index.faultAmount + storeItem.get('data1'));
                    }
                }
            }]
        }];

        me.callParent();
    }
});