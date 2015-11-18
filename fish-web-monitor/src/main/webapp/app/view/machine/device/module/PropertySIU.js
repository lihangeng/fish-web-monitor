Ext.define("Eway.view.machine.device.module.PropertySIU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertysiu',
	defaults : {
		anchor : '100%' //
	},
	items : [ {
		title : EwayLocale.machine.device.SIUInfo,
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
				fieldLabel : EwayLocale.machine.device.operatorSwitchSupported,
				name : 'operatorSwitchSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : EwayLocale.machine.device.cabinetSupported,
				name : 'cabinetSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.safeSupported,
				name : 'safeSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.indicatorSupported,
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
				fieldLabel : EwayLocale.machine.device.guidelightIdcSupported,
				name : 'guidelightIdcSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : EwayLocale.machine.device.guidelightCdmSupported,
				name : 'guidelightCdmSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.guidelightReceiptSupported,
				name : 'guidelightReceiptSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.guidelightCimSupported,
				name : 'guidelightCimSupported'
			} ]
		} ]
	} ]
});
