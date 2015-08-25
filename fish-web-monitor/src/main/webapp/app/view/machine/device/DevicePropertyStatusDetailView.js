Ext.define('Eway.view.machine.device.DevicePropertyStatusDetailView', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_devicepropertystatusdetailview',

	requires : [ 'Eway.view.machine.device.DeviceModuleStatus',
			'Eway.view.machine.device.SpInfo',
			'Eway.view.machine.device.SoftAndHardwareInfo',
			'Eway.view.machine.device.DeviceModuleConfig' ],

	title : '设备详细信息',
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
					title : '系统软硬件信息',
					xtype : 'machine_device_softAndHardwareInfo'
				}, {
					title : '模块硬件版本信息（实时）',
					xtype : 'machine_device_spinfo'
				}, {
					title : '设备模块状态（实时）',
					xtype : 'machine_device_devicemodulestatus'
				}, {
					xtype : 'machine_device_deviceModuleConfig',
					title : '设备模块属性信息（实时）'
				} ]
			}
		});
		this.callParent(arguments);
	}
});