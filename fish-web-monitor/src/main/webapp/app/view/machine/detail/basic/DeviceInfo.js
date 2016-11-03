Ext.define('Eway.view.machine.detail.basic.DeviceInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_deviceInfo',

	 tools: [ {
	        itemId: 'refreshDeviceInfo',
	        type: 'refresh',
	        tooltip: EwayLocale.button.refresh,
	        callback: function() {
	            // do refresh
	        }
	    },{
	        itemId: 'collapse',
	        type: 'collapse',
	        hidden: true,
	        tooltip: '收起',
	        callback: function(panel) {
	        	Ext.getCmp('deviceInfo').setHidden(true);
	        	panel.down('#expand').show();
	        	panel.down('#collapse').setHidden(true);
	        }
	    }, {
	        type: 'expand',
	        itemId: 'expand',
	        tooltip: '展开',
	        callback: function (panel) {
	        	Ext.getCmp('deviceInfo').show();
	            panel.down('#collapse').show();
	            panel.down('#expand').setHidden(true);
	        }
	    }],
	requires : ['Eway.view.machine.detail.basic.DevicesInfo','Eway.view.machine.detail.basic.OftenDevicesInfo'  ],
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype:'form',
				items:[{
					xtype:'detail_basic_OftenDevicesInfo'
				},{
					xtype:'detail_basic_DevicesInfo',
					id:'deviceInfo',
					hidden:true
				}]
			}],
			
			listeners : {
				beforerender : function(panel) {
//					this.refreshData();
				}
			}
		});

		this.callParent(arguments);
	},
	refreshData:function(){
		var me = this;
		var termianlId = me.up("tabpanel").getTerminalId();
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/devicedetail/basicInfo',
			params:{'termianlId':termianlId},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
						me.down("form").loadRecord(Ext.create('Eway.model.machine.Device',object.data));
					}else{
						Eway.alert(object.errorMsg);
					}
					me.unmask();
				},
				failure:function(){
					me.unmask();
				}
		});
	}
    
});
