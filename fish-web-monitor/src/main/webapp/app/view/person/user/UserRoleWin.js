
Ext.define('Eway.view.person.user.UserRoleWin', {
	alias: 'widget.user_roleWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.user.UserRoleGrid'],
				
	title: Eway.locale.person.user.roleListTitle,
	width: 750,
	height: 400,
	layout : 'border',
	initComponent: function() {
		Ext.apply(this, {
			items : [ {
				region: 'center',
				xtype : 'user_role_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});