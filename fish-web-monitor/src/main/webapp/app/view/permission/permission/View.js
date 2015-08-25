
Ext.define('Eway.view.permission.permission.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.permission.view',
	
	requires: ['Eway.view.permission.permission.Tree'],
	
	title: '菜单权限',
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