Ext.define('Eway.view.operatingPlan.LinkingDevicePanel', {
	alias: 'widget.operatingPlan_linkingDevicePanel',
	extend: 'Ext.panel.Panel',
	
	modal: true,
	resizable: false,
//	constrainHeader: true,
	
	requires: ['Eway.view.operatingPlan.LinkingDeviceFilter',
	           'Eway.view.operatingPlan.LinkingDeviceGrid'],
	
	width: 520,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			isWindow:true,
			items : [
			         {
						region: 'north',
						xtype : 'operatingPlan_linkingDeviceFilter'
					},{
						region: 'center',
						xtype : 'operatingPlan_linkingDeviceGrid',
					}
			]
		});
		this.callParent(arguments);
	}
	
});