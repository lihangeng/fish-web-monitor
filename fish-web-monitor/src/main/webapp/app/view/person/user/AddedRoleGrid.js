Ext.define('Eway.view.person.user.AddedRoleGrid', {
	alias : 'widget.user_addedRoleGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	store : 'person.user.AddedRole',
	border : false,
	autoFit : true,
	id : 'addedRoleGridId',

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'addedRoleGridId',
			dropGroup : 'roleGridId',
			enableDrop : true
		}
	},

	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			columns : [ {
				header : EwayLocale.person.user.roleName,
				dataIndex : 'name',
				storable : true
			}, {
				header : EwayLocale.person.user.roleDescription,
				dataIndex : 'description',
				flex : 1
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ],

			enableColumnMove : true
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().reload();
	}
});