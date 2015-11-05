Ext.define('Eway.view.version.distribute.VersionStatusPie', {
    extend: 'Ext.Panel',
    alias: 'widget.versionstatus_pie',
    requires: ['Ext.chart.theme.Muted'],
    width: 550,
    title:{
    	height :24
    },
    tools:[{
	    type:'refresh',
	    tooltip: 'Refresh'
	}],
    
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
                angleField: 'taskStatusNumber',
                label: {
                    field: 'taskStatusText',
                    calloutLine: {
                        length: 20,
                        width: 3
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get('taskStatusText') + ': ' + storeItem.get('taskStatusNumber') );
                    }
                }
            }]
        }];

        this.callParent();
    }
});