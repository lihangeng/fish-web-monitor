Ext.define("Eway.view.machine.device.module.Property", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.machine_device_module_property',
	height : 140,
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
				fieldLabel : EwayLocale.machine.device.IDC,
				name : 'propertyIdc',
				minHeight : 20,
				code : 'IDC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.JPR,
				name : 'propertyJpr',
				minHeight : 20,
				code : 'JPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.CDM,
				name : 'propertyCdm',
				minHeight : 20,
				code : 'CDM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.SIU,
				name : 'propertySiu',
				minHeight : 20,
				code : 'SIU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.CIM,
				name : 'propertyCim',
				minHeight : 20,
				code : 'CIM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.TTU,
				name : 'propertyTtu',
				minHeight : 20,
				code : 'TTU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.RPR,
				name : 'propertyRpr',
				minHeight : 20,
				code : 'RPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.PIN,
				name : 'propertyPin',
				minHeight : 20,
				code : 'PIN',
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