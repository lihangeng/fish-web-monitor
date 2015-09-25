Ext.define('Eway.view.version.distribute.VersionStatusPie', {
    extend: 'Ext.Panel',
    alias: 'widget.versionstatus_pie',
    requires: ['Ext.chart.theme.Muted'],
    width: 450,
    height:300,
    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['os', 'data1' ],
            data: [
                { os: 'Android', data1: 68.3 },
                { os: 'BlackBerry', data1: 1.7 },
                { os: 'iOS', data1: 17.9 },
                { os: 'Windows Phone', data1: 10.2 },
                { os: 'Others', data1: 1.9 }
            ]
        });


        me.items = [{
            xtype: 'polar',
            theme: 'default-gradients',
            width: '100%',
            height: 300,
            store: me.myDataStore,
            insetPadding: 30,
            innerPadding: 20,
            legend: {
                docked: 'left'
            },
            interactions: ['rotate', 'itemhighlight'],
            sprites: [{
                type: 'text',
                text: '版本分布图',
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            }],
            series: [{
                type: 'pie',
                angleField: 'data1',
                label: {
                    field: 'os',
                    calloutLine: {
                        length: 60,
                        width: 3
                        // specifying 'color' is also possible here
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get('os') + ': ' + storeItem.get('data1') + '%');
                    }
                }
            }]
        }];

        this.callParent();
    }
});