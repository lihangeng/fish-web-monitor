
Ext.define('Eway.view.operatingPlan.ViewForDevice', {
	extend: 'Ext.window.Window',
	alias: 'widget.operatingPlan_viewForDevice',
	
	requires: ['Eway.view.operatingPlan.FilterForm',
	           'Eway.view.operatingPlan.GridForDevice'],
	
	title: EwayLocale.report.openplan.selectPlan,
	
	width: 900,
	height: 560,
	layout: 'border',
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				
					region: 'north',
					height: 40,
					xtype: 'operatingPlan_filterForm'
				}, {
					region: 'center',
					xtype: 'operatingPlan_gridForDevice'
			}]
		});
		
		this.callParent(arguments);
	}
});