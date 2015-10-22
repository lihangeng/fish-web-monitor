Ext.define("Eway.view.machine.device.module.PropertySIU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertysiu',
	defaults : {
		anchor : '100%' //
	},
	items : [ {
		title : Eway.locale.machine.device.SIUInfo,
		titleAlign:'center',
		layout : 'column',
		items : [ {
			columnWidth : .5,
			defaultType : 'textfield',
			border : false,
			defaults : {
				anchor : '90%',
				readOnly : true,
				labelAlign : 'right',
				labelWidth: 180
			},
			items : [ {
				fieldLabel : Eway.locale.machine.device.operatorSwitchSupported,
				name : 'operatorSwitchSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : Eway.locale.machine.device.cabinetSupported,
				name : 'cabinetSupported'
			}, {
				fieldLabel : Eway.locale.machine.device.safeSupported,
				name : 'safeSupported'
			}, {
				fieldLabel : Eway.locale.machine.device.indicatorSupported,
				name : 'indicatorSupported'
			} ]
		}, {
			columnWidth : .5,
			defaultType : 'textfield',
			border : false,
			defaults : {
				anchor : '90%',
				readOnly : true,
				labelAlign : 'right',
				labelWidth: 180
			},
			items : [ {
				fieldLabel : Eway.locale.machine.device.guidelightIdcSupported,
				name : 'guidelightIdcSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : Eway.locale.machine.device.guidelightCdmSupported,
				name : 'guidelightCdmSupported'
			}, {
				fieldLabel : Eway.locale.machine.device.guidelightReceiptSupported,
				name : 'guidelightReceiptSupported'
			}, {
				fieldLabel : Eway.locale.machine.device.guidelightCimSupported,
				name : 'guidelightCimSupported'
			} ]
		} ]
	} ]
});
