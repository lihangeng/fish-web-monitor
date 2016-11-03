Ext.define('Eway.view.machine.detail.basic.DeviceInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_deviceInfo',

	 tools: [ {
	        itemId: 'refreshDeviceInfo',
	        type: 'refresh',
	        tooltip: EwayLocale.button.refresh
	    },{
	        itemId: 'collapse',
	        type: 'collapse',
	        hidden: true,
	        tooltip: '收起',
	        callback: function(panel) {
	        	Ext.getCmp('deviceInfo').setHidden(true);
	        	panel.down('#collapse').setHidden(true);
	        	panel.down('#expand').show();
	        }
	    }, {
	        type: 'expand',
	        itemId: 'expand',
	        tooltip: '展开',
	        callback: function (panel) {
	        	Ext.getCmp('deviceInfo').show();
	            panel.down('#expand').setHidden(true);
	            panel.down('#collapse').show();
	        }
	    }],
	requires : ['Eway.view.machine.detail.basic.HiddenDeviceInfo','Eway.view.machine.detail.basic.OftenDevicesInfo'  ],
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype:'form',
				items:[{
					xtype:'detail_basic_oftenDevicesInfo'
				},{
					xtype:'detail_basic_hiddenDevicesInfo',
					id:'deviceInfo',
					hidden:true
				}]
			}]
		});
		this.callParent(arguments);
	}
});
