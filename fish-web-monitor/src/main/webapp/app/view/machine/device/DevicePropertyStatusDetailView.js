Ext.define('Eway.view.machine.device.DevicePropertyStatusDetailView', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_devicepropertystatusdetailview',

	requires : [ 'Eway.view.machine.device.DeviceModuleStatus',
			'Eway.view.machine.device.SpInfo',
			'Eway.view.machine.device.SoftAndHardwareInfo',
			'Eway.view.machine.device.DeviceModuleConfig' ],

	title : EwayLocale.machine.device.devTailMsg,
	maximizable : true,
	modal : true,
	resizable : false,
	constrainHeader : true,
	layout : 'fit',
	width : 800,
	height : 590,
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'tabpanel',
				activeTab : 0,
				split : true,
				border : true,
				items : [ {
					title : EwayLocale.machine.device.systemHardwareInfo,
					xtype : 'machine_device_softAndHardwareInfo'
				}, {
					title : EwayLocale.machine.device.moduleVersionInfo,
					xtype : 'machine_device_spinfo'
				}, {
					title : EwayLocale.machine.device.devModuleStatusInfo,
					xtype : 'machine_device_devicemodulestatus'
				}, {
					xtype : 'machine_device_deviceModuleConfig',
					title : EwayLocale.machine.device.devModuleAttributeInfo
				} ]
			}
		});
		this.callParent(arguments);
	}
});