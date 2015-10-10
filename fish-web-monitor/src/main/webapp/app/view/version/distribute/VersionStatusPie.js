Ext.define('Eway.view.version.distribute.VersionStatusPie', {
    extend: 'Ext.Panel',
    alias: 'widget.versionstatus_pie',
    requires: ['Ext.chart.theme.Muted'],
    width: 450,
    height:300,
    initComponent: function() {
        var me = this;
        
        me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['taskStatusText', 'taskStatusNumber','versionTypeId','versionId','taskStatus' ],
            proxy: {
                type: 'ajax',
                url: 'api/version/version/distributeStatus',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            },
            autoLoad :false
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
//            sprites: [{
//                type: 'text',
//                text: '版本下发历史图',
//                fontSize: 22,
//                width: 100,
//                height: 30,
//                x: 40, // the sprite x position
//                y: 20  // the sprite y position
//            }],
            series: [{
                type: 'pie',
                angleField: 'taskStatusNumber',
                label: {
                    field: 'taskStatusText',
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
                        this.setHtml(storeItem.get('taskStatusText') + ': ' + storeItem.get('taskStatusNumber') + '%');
                    }
                }
            }]
        }];

        this.callParent();
    }
});