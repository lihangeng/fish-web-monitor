Ext.define("Eway.view.machine.device.module.PropertyPIN", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertypin',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title :  EwayLocale.machine.device.PINInfo,
				titleAlign:'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel :  EwayLocale.machine.device.canEBC,
						name : 'canEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel :  EwayLocale.machine.device.canCBC,
						name : 'canCBC'
					}, {
						fieldLabel : EwayLocale.machine.device.canMAC,
						name : 'canMAC'
					}, {
						fieldLabel : EwayLocale.machine.device.canRSA,
						name : 'canRSA'
					}, {
						fieldLabel : EwayLocale.machine.device.canVerifyVISA,
						name : 'canVerifyVISA'
					}, {
						fieldLabel :  EwayLocale.machine.device.canVerifyDES,
						name : 'canVerifyDES'
					}, {
						fieldLabel : EwayLocale.machine.device.functionKeys,
						xtype : 'textareafield',
						name : 'functionKeys'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textfield',
						labelWidth: 150,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel :EwayLocale.machine.device.canTripleEBC,
						name : 'canTripleEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.canTripleCBC,
						name : 'canTripleCBC'
					}, {
						fieldLabel : EwayLocale.machine.device.canTripleMAC,
						name : 'canTripleMAC'
					}, {
						fieldLabel : EwayLocale.machine.device.canTripleCFB,
						name : 'canTripleCFB'
					}, {
						fieldLabel : EwayLocale.machine.device.canVerifyECB,
						name : 'canVerifyECB'
					}, {
						fieldLabel :EwayLocale.machine.device.canDESOffset,
						name : 'canDESOffset'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});