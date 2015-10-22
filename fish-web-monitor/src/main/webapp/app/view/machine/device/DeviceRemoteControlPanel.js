
Ext.define('Eway.view.machine.device.DeviceRemoteControlPanel', {
	alias: 'widget.device.deviceRemoteControlPanel',
	extend: 'Ext.panel.Panel',
	
	requires: ['Eway.lib.Util'],

	border : false,
	autoFit:true,
	
	initComponent: function() {
		Ext.apply(this, {
			initRegion : true,
			tbar: [{
				xtype : 'button',
				text: Eway.locale.machine.device.remoteBrowseDisk,
				action: 'remoteBrowseDisk'
   			 }]
		});
		
		this.callParent(arguments);
	}
});