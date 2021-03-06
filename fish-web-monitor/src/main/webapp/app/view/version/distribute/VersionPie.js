Ext.define('Eway.view.version.distribute.VersionPie', {
    extend: 'Ext.Panel',
    alias: 'widget.version_pie',
    requires: ['Ext.chart.theme.Muted'],
    title:{
    	height :24
    },
    
    tools:[{
		xtype:'combo',
		store :Ext.create('Eway.store.version.ComboVersionType'),
		valueField : 'id',
		displayField : 'desc',
	    queryMode: 'local',
	    canClear:false,
	    editable : false,
	    listeners : {
			afterrender:function(){
				var me = this;
				var store = this.getStore();
				this.store.load({
					callback : function(records, operation, success) {
						if (success) {
							var record = store.getAt(0);
							if(undefined!=record){
								me.select( record );
							}
						}
					},
					scope : this
				});
			}
		}
	},{
	    type:'refresh',
	    tooltip: EwayLocale.button.refresh
	}],
    
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
                angleField: 'versionNoNumber',
                label: {
                    field: 'versionNo',
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
                    	storeItem.setHtml('V'+item.get('versionNo') + '( ' + item.get('versionNoNumber')+EwayLocale.index.amount+')');
                    }
                }
            }]
        }];

        this.callParent();
    }
});