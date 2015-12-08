/**
 * 文本终端单元（TTU--Text Terminal Unit） 视图页面
 * 
 * @See
 */
Ext.define("Eway.view.machine.device.module.PropertyFGP", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyfgp',
	items : [ {
		title : EwayLocale.machine.device.FGPInfo,
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
				fieldLabel : EwayLocale.machine.device.fgp_variant,
				name : 'variant',
				style : 'margin-top:2px'
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
				fieldLabel : EwayLocale.machine.device.canCompare,//是否具有比较特值功能
				name : 'canCompare',
				style : 'margin-top:2px'
			} ]
		} ]
	} ]
});
