/**
 * 文本终端单元（TTU--Text Terminal Unit） 视图页面
 * 
 * @See
 */
Ext.define("Eway.view.machine.device.module.PropertyTTU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyttu',
	items : [ {
		title : EwayLocale.machine.device.TTUInfo,
		titleAlign: 'center',
		layout : 'column',
		items : [ {
			columnWidth : .5,
			defaultType : 'textfield',
			border : false,
			defaults : {
				anchor : '90%',
				readOnly : true,
				labelAlign : 'right',
				labelWidth: 150
			},
			items : [ {
				fieldLabel : EwayLocale.machine.device.alphanumericKeysPresent,
				name : 'alphanumericKeysPresent',
				style : 'margin-top:2px'
			}, {
				fieldLabel : EwayLocale.machine.device.numericKeysPresent,
				name : 'numericKeysPresent'
			}, {
				fieldLabel : EwayLocale.machine.device.displayLightPresent,
				name : 'displayLightPresent'
			}, {
				fieldLabel : EwayLocale.machine.device.cursorSupported,
				name : 'cursorSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.resolutionX,
				name : 'resolutionX'
			} ]
		}, {
			columnWidth : .5,
			defaultType : 'textfield',
			border : false,
			defaults : {
				anchor : '90%',
				readOnly : true,
				labelAlign : 'right',
				labelWidth : 150
			},
			items : [ {
				fieldLabel : EwayLocale.machine.device.hexadecimalKeysPresent,
				name : 'hexadecimalKeysPresent',
				style : 'margin-top:2px'
			}, {
				fieldLabel : EwayLocale.machine.device.keyboardLockPresent,
				name : 'keyboardLockPresent'
			}, {
				fieldLabel : EwayLocale.machine.device.formsSupported,
				name : 'formsSupported'
			}, {
				fieldLabel : EwayLocale.machine.device.resolutionY,
				name : 'resolutionY'
			} ]
		} ]
	} ]
});
