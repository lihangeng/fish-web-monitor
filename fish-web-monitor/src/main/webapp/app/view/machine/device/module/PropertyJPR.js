Ext.define("Eway.view.machine.device.module.PropertyJPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyjpr',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				title : EwayLocale.machine.device.JPRInfo,
				titleAlign:'center',
				layout : 'column',
				items : [ {
					columnWidth : .8,
					border : false,
					defaultType : 'textfield',
					defaults : {
						anchor : '100%',
						readOnly : true,
						labelWidth: 120,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.canEject,
						name : 'canEject',
						style : 'margin-top:2px'
					}, {
						fieldLabel :  EwayLocale.machine.device.canCapture,
						name : 'canCapture'
					}, {
						fieldLabel : EwayLocale.machine.device.canStack,
						name : 'canStack'
					} ]
				}]
			} ]
		});
		this.callParent(arguments);
	}
});