Ext.define("Eway.view.machine.device.module.Property", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.machine_device_module_property',
	height : 140,
	layout : 'column',
	defaults : {
		border : false
	},
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
				name : 'propertyIdc',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '日志打印机模块(JPR)',
				name : 'propertyJpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '取款模块(CDM)',
				name : 'propertyCdm',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '传感器模块(SIU)',
				name : 'propertySiu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '存款模块(CIM)',
				name : 'propertyCim',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '文本终端单元(TTU)',
				name : 'propertyTtu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '凭条打印机模块(RPR)',
				name : 'propertyRpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : '密码键盘模块(PIN)',
				name : 'propertyPin',
				minHeight : 20
			} ]
		});
		this.callParent(arguments);
	}
});