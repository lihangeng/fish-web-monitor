
Ext.define('Eway.view.permission.permission.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.permission.view',
	
	requires: ['Eway.view.permission.permission.Tree'],
	
	title: Eway.locale.permission.permission.menuPermission,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'center',
				xtype: 'permission.tree'
			}]
		});
		
		this.callParent(arguments);
	}
});