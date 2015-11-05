Ext.define('Eway.view.operatingPlan.LinkedDevicePanel', {
	alias: 'widget.operatingPlan_linkedDevicePanel',
	extend: 'Ext.panel.Panel',
	
	modal: true,
	resizable: false,
//	constrainHeader: true,
	
	requires: ['Eway.view.operatingPlan.LinkedDeviceFilter',
	           'Eway.view.operatingPlan.LinkedDeviceGrid'],
	
	width: 520,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			isWindow:true,
			items : [
			         {
						region: 'north',
						xtype : 'operatingPlan_linkedDeviceFilter'
					},{
						region: 'center',
						xtype : 'operatingPlan_linkedDeviceGrid',
						autoLoadStore : true
					}
			]
		});
		this.callParent(arguments);
	}
	
});