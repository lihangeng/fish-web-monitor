Ext.define("Eway.view.machine.device.module.Property", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.machine_device_module_property',
	height : 180,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				anchor : '90%',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.idc,
				name : 'propertyIdc',
				minHeight : 20,
				code : 'IDC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.rfc,
				name : 'propertyNfc',
				minHeight : 20,
				code : 'NFC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.jpr,
				name : 'propertyJpr',
				minHeight : 20,
				code : 'JPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.cdm,
				name : 'propertyCdm',
				minHeight : 20,
				code : 'CDM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.siu,
				name : 'propertySiu',
				minHeight : 20,
				code : 'SIU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.cim,
				name : 'propertyCim',
				minHeight : 20,
				code : 'CIM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.ttu,
				name : 'propertyTtu',
				minHeight : 20,
				code : 'TTU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.rpr,
				name : 'propertyRpr',
				minHeight : 20,
				code : 'RPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.pin,
				name : 'propertyPin',
				minHeight : 20,
				code : 'PIN',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.icc,
				name : 'propertyIcc',
				minHeight : 20,
				code : 'ICC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.isc,
				name : 'propertyIsc',
				minHeight : 20,
				code : 'ISC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.fgp,
				name : 'propertyFgp',
				minHeight : 20,
				code : 'FGP',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pbk,
				name : 'propertyPbk',
				minHeight : 20,
				code : 'PBK',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cam,
				name : 'propertyCam',
				minHeight : 20,
				code : 'CAM',
				listeners : {
					'beforerender': this.isHidden
				}
			} ,{
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.bcr,
				name : 'propertyBcr',
				minHeight : 20,
				code : 'BCR',
				listeners : {
					'beforerender': this.isHidden
				}
			} ]
		});
		this.callParent(arguments);
	},
	
	isHidden : function(field) {
		
		var record = field.up('window').record;
		
		var type = record.get('type');
		var typeData = Ext.typeLinkModData
		var strs = typeData[type];
		var has = false;
		Ext.each(strs, function(item) {
			if(item == field.code) {
				has = true;
				return false;
			}
		});
		return has;
	}
});