Ext.define('Eway.view.machine.detail.run.CaseFaultInfo', {
    extend: 'Ext.Panel',
    xtype: 'faultTrend',
    requires:['Eway.store.machine.detail.FaultTrend'],

    title : {
    	text :EwayLocale.index.dailyFaultPic,
    	height:24
    },

	tools:[{
	    type:'refresh',
	    tooltip: EwayLocale.button.refresh,
	    handler: function(event, toolEl, panelHeader) {
	     	this.up('faultTrend').down('cartesian').getStore().load();
	    }
	}],

    initComponent: function() {
        var me = this;
        me.myDataStore = Ext.create('Eway.store.machine.detail.FaultTrend');
        me.items = [{
            xtype: 'cartesian',
            width: '100%',
            height: 260,
            interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },
            store: this.myDataStore,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40,
                top : 30
            },
            sprites: [{
                type: 'text',
                text: EwayLocale.index.dailyFaultPic,
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
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'category',
                fields: 'month',
                position: 'bottom',
                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
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
                    	storeItem.setHtml(item.get('month') + EwayLocale.index.faultAmount + item.get('data1'));
                    }
                }
            }]
        }];

        me.callParent();
    }
});