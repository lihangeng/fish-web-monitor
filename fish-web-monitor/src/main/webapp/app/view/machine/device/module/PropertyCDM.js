Ext.define("Eway.view.machine.device.module.PropertyCDM", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertycdm',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : Eway.locale.machine.device.CDMInfo,
				titleAlign: 'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						labelWidth: 130,
						readOnly : true,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel :Eway.locale.machine.device.hasStack,
						name : 'hasStack',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.hasShutter,
						name : 'hasShutter'
					}, {
						fieldLabel : Eway.locale.machine.device.canRetract,
						name : 'canRetract'
					}, {
						fieldLabel :Eway.locale.machine.device.canDetectCashTaken,
						name : 'canDetectCashTaken'
					}, {
						fieldLabel : Eway.locale.machine.device.canTestPhysicalUnits,
						name : 'canTestPhysicalUnits'
					}, {
						fieldLabel : Eway.locale.machine.device.maxDispensBills,
						name : 'maxDispensBills'
					} ]
				}, {
					columnWidth : .5,
					layout : 'anchor',
					border : false,
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelWidth: 130,
						xtype : 'textfield',
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.logicalUnits,
						name : 'logicalUnits',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.physicalUnits,
						name : 'physicalUnits'
					}, {
						fieldLabel : Eway.locale.machine.device.currency,
						name : 'currency'
					}, {
						fieldLabel : Eway.locale.machine.device.currencies,
						name : 'currencies'
					}, {
						fieldLabel : Eway.locale.machine.device.exponents,
						name : 'exponents'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});