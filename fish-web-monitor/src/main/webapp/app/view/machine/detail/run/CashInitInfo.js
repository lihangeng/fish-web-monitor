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
	     	this.up('cashInitInfo').down('cartesian').getStore().load();
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
            store: this.myDataStore,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40,
                top : 30
            },
            axes: [{
                type: 'numeric',
                fields: ['initAmt','leftAmt', 'depositAmt', 'withdrawalAmt'],
                position: 'left',
                minimum: 0,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'category',
                fields: 'date',
                position: 'bottom',
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'numeric',
                fields: [ 'deposit','withdrawal'],
                position: 'right',
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }],
            series: [{
                type: 'line',
                title:EwayLocale.deviceInfo.withdrawalCount,
                xField: 'date',
                axis: 'right',
                yField: 'withdrawal',
                style: {
                    opacity: 0.80
                },
                marker: {
                    type: 'cross',
                    fx: {
                        duration: 200
                    }
                },
                highlightCfg: {
                    scaling: 2,
                    rotationRads: Math.PI / 4
                },

                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem,record, item) {
                    	storeItem.setHtml(record.get('date') + item.series._title+record.get(item.field));
                    }
                }
            
            },{

                type: 'line',
                title:EwayLocale.deviceInfo.depositCount,
                xField: 'date',
                yField: 'deposit',
                style: {
                    opacity: 0.80
                },
                marker: {
                    type: 'cross',
                    fx: {
                        duration: 200
                    }
                },
                highlightCfg: {
                    scaling: 2,
                    rotationRads: Math.PI / 4
                },
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem,record, item) {
                    	storeItem.setHtml(record.get('date') + item.series._title+record.get(item.field));
                    }
                }
            
            
            },{
            	stacked:false,
                type: 'bar',
                title:[EwayLocale.monitor.business.cashInit.amt,EwayLocale.deviceInfo.clearAmt,
                       EwayLocale.monitor.business.settlement.cimAmt,EwayLocale.monitor.business.settlement.cdmAmt],
                xField: 'date',
                yField: ['initAmt','leftAmt','depositAmt','withdrawalAmt'],
                style: {
                    opacity: 0.80
                },
                highlight: {
                    fillStyle: 'rgba(204, 230, 73, 1.0)',
                    strokeStyle: 'black'
                },

                tooltip: {
                    trackMouse: true,
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem,record, item) {
                    	var fieldIndex = Ext.Array.indexOf(item.series.getYField(), item.field),
                        brand = item.series.getTitle()[fieldIndex];
                    	storeItem.setHtml(record.get('date') +brand+ record.get(item.field));
                    }
                }
            }
            ]
        }];

        me.callParent();
    }
});