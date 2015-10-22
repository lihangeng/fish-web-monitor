
Ext.define('Eway.view.permission.role.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.permission_role_view',
	
	requires: ['Eway.view.permission.role.Grid',
	           'Eway.view.permission.role.FilterForm'],
	
	title: Eway.locale.permission.role.title,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterItemId',
				region: 'north',
				xtype: 'permission_role_filterform'
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'permission_role_grid'
			}]
		});
		
		this.callParent(arguments);
	}
});