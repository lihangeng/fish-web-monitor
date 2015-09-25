Ext.define('Eway.view.version.distribute.VersionPie', {
    extend: 'Ext.Panel',
    alias: 'widget.version_pie',
    requires: ['Ext.chart.theme.Muted'],
    width: 450,
    height:200,
    title:'',
    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['versionNo', 'versionNoNumber', 'versionId', 'versionTypeId' ],
            proxy: {
                type: 'ajax',
                url: 'api/version/version/distribute',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        });


        me.items = [{
            xtype: 'polar',
            theme: 'default-gradients',
            width: '100%',
            height: 200,
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
                docked: 'left'
            },
            interactions: ['rotate', 'itemhighlight'],
//            sprites: [{
//                type: 'text',
//                text: '版本分布图',
//                fontSize: 22,
//                width: 100,
//                height: 30,
//                x: 40, // the sprite x position
//                y: 20  // the sprite y position
//            }],
            series: [{
                type: 'pie',
                angleField: 'versionNoNumber',
                label: {
                    field: 'versionNo',
                    calloutLine: {
                        length: 30,
                        width: 3
                        // specifying 'color' is also possible here
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get('versionNo') + ': ' + storeItem.get('versionNoNumber') + '%');
                    }
                }
            }]
        }];

        this.callParent();
    }
});