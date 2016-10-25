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
//                grid: true,
                minimum: 0,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'category',
                fields: 'date',
                position: 'bottom',
//                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'numeric',
                fields: [ 'deposit','withdrawal'],
                position: 'right',
//                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }],
            series: [{
            	stacked:false,
                type: 'bar',
                title:[EwayLocale.deviceInfo.withdrawalCount,EwayLocale.deviceInfo.depositCount],
                xField: 'date',
                yField: ['withdrawal','deposit'],
//                style: {
//                    lineWidth: 4
//                },
//                marker: {
//                    radius: 4
//                },
                highlight: {
                    fillStyle: '#000',
                    radius: 5,
                    lineWidth: 2,
                    strokeStyle: '#fff'
                },
                tooltip: {
                    trackMouse: true,
//                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem,record, item) {
                    	storeItem.setHtml(record.get('date') + item.series._title[item.index]+record.get(item.field));
                    }
                }
            
            },{
            	stacked:false,
                type: 'bar',
                title:[EwayLocale.monitor.business.cashInit.amt,EwayLocale.deviceInfo.clearAmt,
                       EwayLocale.monitor.business.settlement.cimAmt,EwayLocale.monitor.business.settlement.cdmAmt],
                xField: 'date',
                yField: ['initAmt','leftAmt','depositAmt','withdrawalAmt'],
//                style: {
//                    lineWidth: 4
//                },
//                marker: {
//                    radius: 4
//                },
//                label: {
//                    field: 'initAmt',
//                    display: 'over'
//                },
                highlight: {
                    fillStyle: '#000',
                    radius: 5,
                    lineWidth: 2,
                    strokeStyle: '#fff'
                },
                tooltip: {
                    trackMouse: true,
//                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem,record, item) {
                    	storeItem.setHtml(record.get('date') +item.series._title[item.index]+ record.get(item.field));
                    }
                }
            }]
        }];

        me.callParent();
    }
});