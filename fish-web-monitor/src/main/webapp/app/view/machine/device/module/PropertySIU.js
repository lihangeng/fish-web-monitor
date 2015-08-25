Ext.define("Eway.view.machine.device.module.PropertySIU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertysiu',
	defaults : {
		anchor : '100%' //
	},
	items : [ {
		title : 'SIU能力属性信息',
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
				fieldLabel : '是否支持操作员开关',
				name : 'operatorSwitchSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : '是否支持后盖门打开传感能力',
				name : 'cabinetSupported'
			}, {
				fieldLabel : '是否支持安全门打开传感能力',
				name : 'safeSupported'
			}, {
				fieldLabel : '是否支持靠近传感能力',
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
				fieldLabel : '是否支持插卡指示灯能力',
				name : 'guidelightIdcSupported',
				style : 'margin-top:2px'
			}, {
				fieldLabel : '是否支持取款指示灯能力',
				name : 'guidelightCdmSupported'
			}, {
				fieldLabel : '是否支持凭条打印指示灯能力',
				name : 'guidelightReceiptSupported'
			}, {
				fieldLabel : '是否支持存款指示灯能力',
				name : 'guidelightCimSupported'
			} ]
		} ]
	} ]
});
