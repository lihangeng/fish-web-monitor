Ext.define("Eway.view.machine.device.module.PropertyBCR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertybcr',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.BCRInfo,
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
						fieldLabel : EwayLocale.machine.device.BCRCanCompound,//'是否为合成设备',
						name : 'canCompound',
						style : 'margin-top:2px'
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
						fieldLabel : EwayLocale.machine.device.BCRCanFilterSymbologies,//'能否辨别制定的条码',
						name : 'canFilterSymbologies',
						style : 'margin-top:2px'
					}]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});