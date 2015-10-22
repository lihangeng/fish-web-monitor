Ext.define("Eway.view.machine.device.module.PropertyPIN", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertypin',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title :  Eway.locale.machine.device.PINInfo,
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
						fieldLabel :  Eway.locale.machine.device.canEBC,
						name : 'canEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel :  Eway.locale.machine.device.canCBC,
						name : 'canCBC'
					}, {
						fieldLabel : Eway.locale.machine.device.canMAC,
						name : 'canMAC'
					}, {
						fieldLabel : Eway.locale.machine.device.canRSA,
						name : 'canRSA'
					}, {
						fieldLabel : Eway.locale.machine.device.canVerifyVISA,
						name : 'canVerifyVISA'
					}, {
						fieldLabel :  Eway.locale.machine.device.canVerifyDES,
						name : 'canVerifyDES'
					}, {
						fieldLabel : Eway.locale.machine.device.functionKeys,
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
						fieldLabel :Eway.locale.machine.device.canTripleEBC,
						name : 'canTripleEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.canTripleCBC,
						name : 'canTripleCBC'
					}, {
						fieldLabel : Eway.locale.machine.device.canTripleMAC,
						name : 'canTripleMAC'
					}, {
						fieldLabel : Eway.locale.machine.device.canTripleCFB,
						name : 'canTripleCFB'
					}, {
						fieldLabel : Eway.locale.machine.device.canVerifyECB,
						name : 'canVerifyECB'
					}, {
						fieldLabel :Eway.locale.machine.device.canDESOffset,
						name : 'canDESOffset'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});