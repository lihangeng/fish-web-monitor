Ext.define('Eway.view.index.VersionDistributePie', {
    extend: 'Ext.Panel',
    xtype: 'versionDistributePie',
    requires:['Eway.store.version.ComboVersionType'],

    title :EwayLocale.index.versionDistributePie,
    
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
			},
			change : function(me, newValue, oldValue, eOpts ){
				var pie = me.up('versionDistributePie').down('polar');
				var store = pie.getStore();
				store.load({
					params : {
						versionTypeId : newValue,
						displayNumber : 0
					}
				});
			}
		}
	},{
	    type:'refresh',
	    tooltip: 'Refresh',
	    handler: function(event, toolEl, panelHeader) {
	     	var panel = this.up('versionDistributePie');
	     	var comboxValue = panel.down('combo').getValue();
	     	panel.down('polar').getStore().load({
	     			params : {
						versionTypeId : comboxValue,
						displayNumber : 0
					}});
	    }
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
            height: 260,
            store: me.myDataStore,
            insetPadding: 10,
            innerPadding: 10,
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
                        length: 20,
                        width: 2
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
                    	storeItem.setHtml(item.get('versionNo') + ' : ' + item.get('versionNoNumber') + " " + EwayLocale.index.amount);
                    }
                }
            }]
        }];

        this.callParent();
    }
});