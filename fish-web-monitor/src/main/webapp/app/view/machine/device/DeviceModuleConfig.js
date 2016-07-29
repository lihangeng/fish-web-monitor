Ext.define('Eway.view.machine.device.DeviceModuleConfig', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.machine_device_deviceModuleConfig',

	requires : [ 'Eway.view.machine.device.module.Property',
			'Eway.view.machine.device.module.PropertyRPR',
			'Eway.view.machine.device.module.PropertySIU',
			'Eway.view.machine.device.module.PropertyIDC',
			'Eway.view.machine.device.module.PropertyCDM',
			'Eway.view.machine.device.module.PropertyCIM',
			'Eway.view.machine.device.module.PropertyJPR',
			'Eway.view.machine.device.module.PropertyPIN',
			'Eway.view.machine.device.module.PropertyTTU',
			'Eway.view.machine.device.module.PropertyISC',
			'Eway.view.machine.device.module.PropertyICC',
			'Eway.view.machine.device.module.PropertyFGP',
			'Eway.view.machine.device.module.PropertyPBK' ,
			'Eway.view.machine.device.module.PropertyCAM' ,
			'Eway.view.machine.device.module.PropertyBCR' ,
			'Eway.view.machine.device.module.PropertyNFC' ,
			'Eway.view.machine.device.module.PropertyUKR' ,
			'Eway.view.machine.device.module.PropertyUKD' ],

	title : EwayLocale.machine.device.devModuleMsg,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				height:'auto',
				xtype : 'machine_device_module_property'
			}, {
				region : 'center',
				layout : 'card',
				activeItem : -1,
				itemId : 'propertyItemId',
				defaults : {
					autoScroll : true
				},
				items : [ {
					itemId : 'cdmid',
					xtype : 'machine_device_module_propertycdm'
				}, {
					itemId : 'cimid',
					xtype : 'machine_device_module_propertycim'
				}, {
					itemId : 'idcid',
					xtype : 'machine_device_module_propertyidc'
				}, {
					itemId : 'jprid',
					xtype : 'machine_device_module_propertyjpr'
				}, {
					itemId : 'pinid',
					xtype : 'machine_device_module_propertypin'
				}, {
					itemId : 'rprid',
					xtype : 'machine_device_module_propertyrpr'
				}, {
					itemId : 'ttuid',
					xtype : 'machine_device_module_propertyttu'
				}, {
					itemId : 'siuid',
					xtype : 'machine_device_module_propertysiu'
				}, {
					itemId : 'iccid',
					xtype : 'machine_device_module_propertyicc'
				}, {
					itemId : 'iscid',
					xtype : 'machine_device_module_propertyisc'
				}, {
					itemId : 'fgpid',
					xtype : 'machine_device_module_propertyfgp'
				}, {
					itemId : 'pbkid',
					xtype : 'machine_device_module_propertypbk'
				} , {
					itemId : 'camid',
					xtype : 'machine_device_module_propertycam'
				} , {
					itemId : 'bcrid',
					xtype : 'machine_device_module_propertybcr'
				}  , {
					itemId : 'nfcid',
					xtype : 'machine_device_module_propertynfc'
				} , {
					itemId : 'ukdid',
					xtype : 'machine_device_module_propertyukd'
				} , {
					itemId : 'ukrid',
					xtype : 'machine_device_module_propertyukr'
				}  ]
			} ]
		});

		this.callParent(arguments);
	}

});