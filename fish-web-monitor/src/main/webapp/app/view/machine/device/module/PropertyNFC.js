Ext.define('Eway.view.machine.device.module.PropertyNFC', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertynfc',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.RFCInfo,
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
						labelWidth: 180,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.variant,
						name : 'variant',
						style : 'margin-top:2px'
					}]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});