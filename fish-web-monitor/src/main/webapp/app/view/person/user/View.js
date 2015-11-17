
Ext.define('Eway.view.person.user.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.user_view',
	
	requires: ['Eway.view.person.user.Grid',
	           'Eway.view.person.user.FilterForm'],
	
	title: EwayLocale.person.user.title,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
					region: 'north',
					xtype: 'user_filterform'
				}, {
					region: 'center',
					xtype: 'user_grid'
			}]
		});
		
		this.callParent(arguments);
	}
});