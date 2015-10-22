/**
 *角色列表
 */
Ext.define('Eway.view.person.user.UserRoleGrid', {
	alias: 'widget.user_role_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	autoFit:true,
	autoScroll : true,
	
	bodyStyle:'background-color:white',
		
	initComponent: function() {
		var store = Ext.create('Eway.store.person.user.AllRole');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [{
					header : Eway.locale.person.user.roleName,
					width : 200,
					dataIndex : 'name'
				},{
					header : Eway.locale.person.user.roleDescription,
					dataIndex : 'description',
					flex:1,
					storable: true
				},{
					header : 'ID',
					dataIndex : 'id',
					hidden : true,
					storable: true
			}]
		
		});
		this.callParent(arguments);
	},
	
	onReload: function() {
		this.getStore().reload();
	}
});