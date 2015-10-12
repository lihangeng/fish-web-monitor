Ext.define("Eway.view.machine.device.module.PropertyCIM", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertycim',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : Eway.locale.machine.device.CIMInfo,
				titleAlign: 'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelWidth: 150,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.canEscrow,
						name : 'canEscrow',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.shutterControlSupported,
						name : 'shutterControlSupported'
					}, {
						fieldLabel : Eway.locale.machine.device.maxAcceptItems,
						name : 'maxAcceptItems'
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
						fieldLabel : Eway.locale.machine.device.canDetectCashInserted,
						name : 'canDetectCashInserted',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.canDetectCashTaken,
						name : 'canDetectCashTaken'
					}, {
						fieldLabel : Eway.locale.machine.device.retractAreas,
						name : 'retractAreas'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});