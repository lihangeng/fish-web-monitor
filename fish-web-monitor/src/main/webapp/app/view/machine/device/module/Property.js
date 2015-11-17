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
				readOnly : true
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.IDC,
				name : 'propertyIdc',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.JPR,
				name : 'propertyJpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.CDM,
				name : 'propertyCdm',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.SIU,
				name : 'propertySiu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.CIM,
				name : 'propertyCim',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.TTU,
				name : 'propertyTtu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.RPR,
				name : 'propertyRpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.PIN,
				name : 'propertyPin',
				minHeight : 20
			} ]
		});
		this.callParent(arguments);
	}
});