Ext.define("Eway.view.machine.device.DeviceModuleStatus", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.machine_device_devicemodulestatus',

	title : EwayLocale.machine.device.devBasicMsg,

	requires : [ 'Eway.view.machine.device.module.Status',
			'Eway.view.machine.device.module.StatusCDM',
			'Eway.view.machine.device.module.StatusSIU',
			'Eway.view.machine.device.module.StatusCIM',
			'Eway.view.machine.device.module.StatusIDC',
			'Eway.view.machine.device.module.StatusJPR',
			'Eway.view.machine.device.module.StatusPIN',
			'Eway.view.machine.device.module.StatusRPR',
			'Eway.view.machine.device.module.StatusTTU',
			'Eway.view.machine.device.module.StatusISC',
			'Eway.view.machine.device.module.StatusICC',
			'Eway.view.machine.device.module.StatusFGP',
			'Eway.view.machine.device.module.StatusPBK',
			'Eway.view.machine.device.module.StatusCAM',
			'Eway.view.machine.device.module.StatusBCR' ,
			'Eway.view.machine.device.module.StatusNFC',
			'Eway.view.machine.device.module.StatusUKR',
			'Eway.view.machine.device.module.StatusUKD' ],

	layout : 'border',
	autoScroll: false,
	
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				height:'auto',
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
				}, {
					itemId : 'iccid',
					xtype : 'machine_device_module_statusicc'
				}, {
					itemId : 'iscid',
					xtype : 'machine_device_module_statusisc'
				}, {
					itemId : 'fgpid',
					xtype : 'machine_device_module_statusfgp'
				}, {
					itemId : 'pbkid',
					xtype : 'machine_device_module_statuspbk'
				} , {
					itemId : 'camid',
					xtype : 'machine_device_module_statuscam'
				} , {
					itemId : 'bcrid',
					xtype : 'machine_device_module_statusbcr'
				} , {
					itemId : 'nfcid',
					xtype : 'machine_device_module_statusnfc'
				} , {
					itemId : 'ukdid',
					xtype : 'machine_device_module_statusukd'
				} , {
					itemId : 'ukrid',
					xtype : 'machine_device_module_statusukr'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});