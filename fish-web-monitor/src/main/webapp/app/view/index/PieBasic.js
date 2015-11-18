Ext.define('Eway.view.index.PieBasic', {
    extend: 'Ext.Panel',
    xtype: 'pieBasic',
    
    title :EwayLocale.index.devStatusDisPic,

    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['os', 'data1' ],
            data: [
                { os: EwayLocale.index.normalDev, data1: 100 },
                { os: EwayLocale.index.unknownDev, data1: 5 },
                { os: EwayLocale.index.exceptionDev, data1: 10 }
            ]
        });


        me.items = [{
            xtype: 'polar',
            theme: 'default-gradients',
            width: '100%',
            height: 300,
            store: me.myDataStore,
            insetPadding: 50,
            innerPadding: 20,
            legend: {
                docked: 'bottom'
            },
            interactions: ['rotate', 'itemhighlight'],
           /* sprites: [{
                type: 'text',
                text: 'Pie Charts - Basic',
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            }, {
                type: 'text',
                text: 'Data: IDC Predictions - 2017',
                x: 12,
                y: 425
            }, {
                type: 'text',
                text: 'Source: Internet',
                x: 12,
                y: 440
            }],*/
            series: [{
                type: 'pie',
                angleField: 'data1',
                label: {
                    field: 'os',
                    calloutLine: {
                        length: 40,
                        width: 3
                        // specifying 'color' is also possible here
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get('os') + ': ' + storeItem.get('data1') + EwayLocale.index.amount);
                    }
                }
            }]
        }];

        this.callParent();
    }
});