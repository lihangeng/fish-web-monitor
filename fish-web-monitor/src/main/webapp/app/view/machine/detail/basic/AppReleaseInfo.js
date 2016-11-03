Ext.define('Eway.view.machine.detail.basic.AppReleaseInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_appReleaseInfo',
    
    tools: [ {
        itemId: 'refreshVersion',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
    }],
	
	requires : [  ],
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'left',
				xtype : 'displayfield',
				labelWidth : 100,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .33,
				fieldLabel : EwayLocale.version.View.nowVersionNo,
				name : 'versionNo'
			},{
				columnWidth : .33,
				fieldLabel : EwayLocale.deviceInfo.lastUpdateTime,
				name : 'lastUpdateTime'
			},{
				columnWidth : .33,
				fieldLabel : EwayLocale.deviceInfo.maxUpdateVersion,
				name : 'updateVersion',
				renderer: function(value,meta,record) {
					return '<a href="#" class="link">'+value+'</font></a>';
				}
			}]
		});

		this.callParent(arguments);
	}
});
