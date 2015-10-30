Ext.define('Eway.view.index.RetainCardByDay', {
    extend: 'Ext.Panel',
    xtype: 'retainCardByDay',

    title :Eway.locale.index.retainCardTrendTitle,
    
	tools:[{
	    type:'refresh',
	    handler: function(event, toolEl, panelHeader) {
	     	this.up('retainCardByDay').down('cartesian').getStore().load();
	    }
	}],

    initComponent: function() {
        var me = this;
        me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['month', 'data1'],
            proxy: {
                type: 'ajax',
                url: 'api/index/retainCardByDay',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        });
        me.myDataStore.load();
        me.items = [{
            xtype: 'cartesian',
            width: '100%',
            height: 300,
           interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },
            store: this.myDataStore,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40
            },
            sprites: [{
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
                    return v.substring(5);
                }/*,
                label: {
                    rotate: {
                        degrees: -45
                    }
                }*/
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
                        this.setHtml(storeItem.get('data1'));
                    }
                }
            }]
        }];

        me.callParent();
    }
});