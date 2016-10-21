Ext.define('Eway.view.machine.detail.run.TradingInfo', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.tradingInfo',
    title : {
    	text:EwayLocale.deviceInfo.controllerInfo,
    	height:24
    },
    requires: ['Ext.chart.theme.Muted'],
    
    tools:[{
	    type:'refresh',
	    tooltip: EwayLocale.button.refresh,
	    handler: function(event, toolEl, panelHeader) {
	     	this.up('tradingInfo').down('polar').getStore().load();
	    }
	}],
    
    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create('Eway.store.machine.detail.Trading');
        me.items = [{
            xtype: 'polar',
            theme: 'default-gradients',
            width: '100%',
            height: 260,
            store: me.myDataStore,
            insetPadding: 10,
            innerPadding: 20,
            
            plugins: {
                ptype: 'chartitemevents',
                moveEvents: true,
                clickEvents: true,
                dbClickEvents: true
            },
            
            legend: {
                docked: 'right'
            },
            interactions: ['rotate', 'itemhighlight'],
            series: [{
                type: 'pie',
                angleField: 'transCount',
                label: {
                    field: 'transName',
                    calloutLine: {
                        length: 30,
                        width: 3
                    },
                    renderer: function(value, _this) {
                        return "";
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                    	storeItem.setHtml(item.get('transName') + '('+EwayLocale.deviceInfo.tradingCount + item.get('transCount')+EwayLocale.deviceInfo.tradingMoney+item.get('transAmt')+')');
                    }
                }
            }]
        }];

        this.callParent();
    }
});