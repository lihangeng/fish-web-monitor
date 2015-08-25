/**
 * 文本终端单元（TTU--Text Terminal Unit） 视图页面
 * 
 * @See
 */
Ext.define("Eway.view.machine.device.module.PropertyTTU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyttu',
	items : [ {
		title : '文本终端单元(TTU)属性信息',
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
				fieldLabel : '是否支持字母数字键输入',
				name : 'alphanumericKeysPresent',
				style : 'margin-top:2px'
			}, {
				fieldLabel : '是否支持数字键输入',
				name : 'numericKeysPresent'
			}, {
				fieldLabel : '是否支持屏幕亮度调节',
				name : 'displayLightPresent'
			}, {
				fieldLabel : '是否支持鼠标',
				name : 'cursorSupported'
			}, {
				fieldLabel : '横轴分辨率',
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
				fieldLabel : '是否支持十六进制键输入',
				name : 'hexadecimalKeysPresent',
				style : 'margin-top:2px'
			}, {
				fieldLabel : '是否支持键盘锁定',
				name : 'keyboardLockPresent'
			}, {
				fieldLabel : '是否支持表格',
				name : 'formsSupported'
			}, {
				fieldLabel : '纵轴分辨率',
				name : 'resolutionY'
			} ]
		} ]
	} ]
});
