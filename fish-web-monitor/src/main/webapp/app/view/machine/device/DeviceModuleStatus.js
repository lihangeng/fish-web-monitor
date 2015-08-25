Ext.define("Eway.view.machine.device.DeviceModuleStatus", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.machine_device_devicemodulestatus',

	title : '设备基本信息',

	requires : [ 'Eway.view.machine.device.module.Status',
			'Eway.view.machine.device.module.StatusCDM',
			'Eway.view.machine.device.module.StatusSIU',
			'Eway.view.machine.device.module.StatusCIM',
			'Eway.view.machine.device.module.StatusIDC',
			'Eway.view.machine.device.module.StatusJPR',
			'Eway.view.machine.device.module.StatusPIN',
			'Eway.view.machine.device.module.StatusRPR',
			'Eway.view.machine.device.module.StatusTTU' ],

	layout : 'border',
	autoScroll: false,
	
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'machine_device_module_status'
			}, {
				region : 'center',
				layout : 'card',
				activeItem : -1,
				itemId : 'statusItemId',
				defaults : {
					autoScroll : true
				},
				items : [ {
					itemId : 'cdmid',
					xtype : 'machine_device_module_statuscdm'
				}, {
					itemId : 'cimid',
					xtype : 'machine_device_module_statuscim'
				}, {
					itemId : 'idcid',
					xtype : 'machine_device_module_statusidc'
				}, {
					itemId : 'jprid',
					xtype : 'machine_device_module_statusjpr'
				}, {
					itemId : 'pinid',
					xtype : 'machine_device_module_statuspin'
				}, {
					itemId : 'rprid',
					xtype : 'machine_device_module_statusrpr'
				}, {
					itemId : 'ttuid',
					xtype : 'machine_device_module_statusttu'
				}, {
					itemId : 'siuid',
					xtype : 'machine_device_module_statussiu'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});