/**
 * 文本终端单元（TTU--Text Terminal Unit） 视图页面
 * 
 * @See
 */
Ext.define("Eway.view.machine.device.module.PropertyPBK", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertypbk',
	items : [ {
		title : EwayLocale.machine.device.PBKInfo,
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
				fieldLabel : EwayLocale.machine.device.canEjectCard,
				name : 'canEject',
				style : 'margin-top:2px'
			}, {
				fieldLabel : EwayLocale.machine.device.canCapture,
				name : 'canCapture'
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
				fieldLabel : EwayLocale.machine.device.canStack,
				name : 'canStack',
				style : 'margin-top:2px'
			} ]
		} ]
	} ]
});
