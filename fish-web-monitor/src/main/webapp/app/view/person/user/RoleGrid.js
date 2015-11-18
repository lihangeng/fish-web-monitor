Ext.define('Eway.view.person.user.RoleGrid', {
	alias : 'widget.user_roleGrid',
	extend : 'Eway.view.base.Grid',

	store : 'person.user.AddingRole',
	border : false,
	autoFit : true,
	id : 'roleGridId',

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'roleGridId',
			dropGroup : 'addedRoleGridId',
			enableDrop : true
		}
	},

	/*
	 * multiSelect:true, selModel:{selType:'checkboxmodel'},
	 */

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
			} ]

		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});