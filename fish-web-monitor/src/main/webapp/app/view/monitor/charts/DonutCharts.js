Ext.define('Eway.view.monitor.charts.DonutCharts', {
    extend: 'Ext.Panel',
    alias: 'widget.pie-donut',
    width: 650,
    config:{
            angleField:'displayName',
            labelField:'numberInfo',
            stores:''
    },
    initComponent: function() {
        var me = this;

        me.myDataStore = this.getStores();
//        me.myDataStore = Ext.create('Eway.store.monitor.charts.DonutChartsSummary');

        me.items = [{
            xtype: 'polar',
            width: '100%',
            height: 500,
            store: this.myDataStore,
            insetPadding: 50,
            innerPadding: 20,
            legend: {
                docked: 'right'
            },
            interactions: ['rotate', 'itemhighlight'],
//            sprites: [{
//                type: 'text',
//                text: 'Donut Charts - Basic',
//                fontSize: 22,
//                width: 100,
//                height: 30,
//                x: 40, // the sprite x position
//                y: 20  // the sprite y position
//            }, {
//                type: 'text',
//                text: 'Data: IDC Predictions - 2017',
//                x: 12,
//                y: 425
//            }, {
//                type: 'text',
//                text: 'Source: Internet',
//                x: 12,
//                y: 440
//            }],
            series: [{
                type: 'pie',
                angleField: this.getLabelField(),
                donut: 50,
                label: {
                    field: this.getAngleField(),
                    display: 'outside'
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get(me.getLabelField()) + ': ' + storeItem.get(me.getAngleField()));
                    }
                }
            }]
        }];

        this.callParent();
    }
});