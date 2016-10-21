Ext.define('Eway.view.machine.detail.run.CashInitInfo', {
    extend: 'Ext.Panel',
    xtype: 'cashInitInfo',
    requires:['Eway.store.machine.detail.CashSettleInit'],

    title : {
    	text :EwayLocale.deviceInfo.CashSettleInit,
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
        me.myDataStore = Ext.create('Eway.store.machine.detail.CashSettleInit');
        me.items = [{
            xtype: 'cartesian',
            width: '100%',
            height: 500,
            interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },
            legend: {
                docked: 'bottom'
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
                fields: ['initAmt','leftAmt', 'depositAmt', 'withdrawalAmt'],
                position: 'left',
                grid: true,
                minimum: 0,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'category',
                fields: 'date',
                position: 'bottom',
                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'numeric',
                fields: [ 'deposit','withdrawal'],
                position: 'right',
                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }],
            series: [{
                type: 'line',
                title:EwayLocale.monitor.business.cashInit.amt,
                xField: 'date',
                yField: 'initAmt',
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'initAmt',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.monitor.business.cashInit.amt + item.get('initAmt'));
                    }
                }
            },{
                type: 'line',
                xField: 'date',
                yField: 'leftAmt',
                title:EwayLocale.deviceInfo.clearAmt,
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'leftAmt',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.deviceInfo.clearAmt + item.get('leftAmt'));
                    }
                }
            
            },{
                type: 'line',
                xField: 'date',
                yField: 'depositAmt',
                title:EwayLocale.monitor.business.settlement.cimAmt,
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'depositAmt',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.monitor.business.settlement.cimAmt+ item.get('depositAmt'));
                    }
                }
            
            },{
                type: 'line',
                xField: 'date',
                yField: 'withdrawalAmt',
                title:EwayLocale.monitor.business.settlement.cdmAmt,
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'withdrawalAmt',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.monitor.business.settlement.cdmAmt + item.get('withdrawalAmt'));
                    }
                }
            
            },{
                type: 'line',
                xField: 'date',
                yField: 'withdrawal',
                title:EwayLocale.deviceInfo.withdrawalCount,
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'withdrawal',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.deviceInfo.withdrawalCount + item.get('withdrawal'));
                    }
                }
            
            },{
                type: 'line',
                xField: 'date',
                yField: 'deposit',
                title:EwayLocale.deviceInfo.depositCount,
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'deposit',
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
                    	storeItem.setHtml(item.get('date') + EwayLocale.deviceInfo.depositCount + item.get('deposit'));
                    }
                }
            
            }]
        }];

        me.callParent();
    }
});