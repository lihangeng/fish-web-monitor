/**
 * 人员设备关联显示窗口：
 */
Ext.define('Eway.view.report.plan.PlanDevice', {
	alias: 'widget.plan_planDevice',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	requires: ['Eway.view.report.plan.LinkedDeviceGrid',
	           'Eway.view.report.plan.LinkingDeviceGrid'],
	
	width: 940,
	height: 400,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : EwayLocale.report.plan.perToDev,
			items : [{
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'plan_linkedDeviceGrid',
				autoLoadStore : true
			},{
				region: 'center',
				padding : 1,
				xtype : 'plan_linkingDeviceGrid',
				autoLoadStore : true
			}]
		});
		this.callParent(arguments);
	}
	
});