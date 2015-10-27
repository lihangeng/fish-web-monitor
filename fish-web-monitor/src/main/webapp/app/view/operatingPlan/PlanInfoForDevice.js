Ext.define('Eway.view.operatingPlan.PlanInfoForDevice', {
	alias: 'widget.planInfo_for_device',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.operatingPlan.PlanInfoForDeviceGrid'],
	
	width: 700,
	height: 500,
	layout: 'fit',
	
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				padding : 1,
				xtype : 'planInfo_device_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});