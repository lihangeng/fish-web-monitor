
Ext.define('Eway.view.operatingPlan.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.operatingPlan_view',
	
	requires: ['Eway.view.operatingPlan.FilterForm',
	           'Eway.view.operatingPlan.Grid'],
	
	title: EwayLocale.button.openPlan,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
					region: 'north',
					xtype: 'operatingPlan_filterForm'
				}, {
					region: 'center',
					xtype: 'operatingPlan_grid'
			}]
		});
		
		this.callParent(arguments);
	}
});