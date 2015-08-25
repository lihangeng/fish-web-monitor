
Ext.define('Eway.view.report.plan.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.plan_view',
	
	requires: ['Eway.view.report.plan.FilterForm',
	           'Eway.view.report.plan.Grid'],
	
	title: '开机方案',
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
					region: 'north',
					xtype: 'plan_filterForm'
				}, {
					region: 'center',
					xtype: 'plan_grid'
			}]
		});
		
		this.callParent(arguments);
	}
});