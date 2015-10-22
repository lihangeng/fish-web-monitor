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
				fieldLabel : Eway.locale.machine.deviceIDC,
				name : 'statusIdc',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceJPR,
				name : 'statusJpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceCDM,
				name : 'statusCdm',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceSIU,
				name : 'statusSiu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceCIM,
				name : 'statusCim',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceTTU,
				name : 'statusTtu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.deviceRPR,
				name : 'statusRpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : Eway.locale.machine.devicePIN,
				name : 'statusPin',
				minHeight : 20
			} ]
		});
		this.callParent(arguments);
	}
});