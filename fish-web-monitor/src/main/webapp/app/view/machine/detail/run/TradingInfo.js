Ext.define('Eway.view.machine.detail.run.TradingInfo', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.tradingInfo',

    requires: ['Ext.chart.theme.Muted'],
    width: 650,
    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create('Eway.store.machine.detail.Trading');
//        me.myDataStore.setBaseParam("terminalId",me.getAppheader().down("textfield[name=terminalId]").getValue());
//        me.myDataStore.load();
        me.items = [{
            xtype: 'polar',
            theme: 'default-gradients',
            width: '100%',
            height: 300,
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
                        // specifying 'color' is also possible here
                    },
                    renderer: function(value, _this) {
                        return "";
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                    	storeItem.setHtml(item.get('transName') + '( 交易笔数:' + item.get('transCount')+' 交易金额:'+item.get('transAmt')+')');
                    }
                }
            }]
        }];

        this.callParent();
    }
});