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
				fieldLabel : EwayLocale.monitor.devMonitor.mod.idc,
				name : 'statusIdc',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.jpr,
				name : 'statusJpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cdm,
				name : 'statusCdm',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.siu,
				name : 'statusSiu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cim,
				name : 'statusCim',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.ttu,
				name : 'statusTtu',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.rpr,
				name : 'statusRpr',
				minHeight : 20
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pin,
				name : 'statusPin',
				minHeight : 20
			} ]
		});
		this.callParent(arguments);
	}
});