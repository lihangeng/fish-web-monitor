Ext.define("Eway.view.machine.device.module.Status", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.machine_device_module_status',
	height : 140,
	layout : 'column',
	defaults : {
		border : false
	},
	// title:'设备模块状态',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				anchor : '90%',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : '读卡器模块(IDC)',
				name : 'statusIdc',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '日志打印机模块(JPR)',
				name : 'statusJpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '取款模块(CDM)',
				name : 'statusCdm',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '传感器模块(SIU)',
				name : 'statusSiu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '存款模块(CIM)',
				name : 'statusCim',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '文本终端单元(TTU)',
				name : 'statusTtu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '凭条打印机模块(RPR)',
				name : 'statusRpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '密码键盘模块(PIN)',
				name : 'statusPin',
				minHeight : 20
			} ]
		});
		this.callParent(arguments);
	}
});