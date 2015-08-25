
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
				text: '远程浏览',
				action: 'remoteBrowseDisk'
   			 }]
		});
		
		this.callParent(arguments);
	}
});