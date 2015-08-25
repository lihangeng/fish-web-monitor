Ext.define('Eway.view.person.user.Update', {
	extend : 'Ext.window.Window',
	alias : 'widget.user_update',

	requires : [ 'Eway.view.person.user.RoleGrid',
			'Eway.view.person.user.AddedRoleGrid' ],

	title : '更改用户角色（使用拖拽的方式）',
	modal : true,
	resizable : true,
	constrainHeader : true,
	layout : 'border',
	width : 800,
	height : 500,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'panel',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region : 'center',
				layout : 'border',
				items : [ {
					title : '可添加的角色列表',
					region : 'west',
					width : 400,
					xtype : 'user_roleGrid',
					margin : '0 5px 0 0',
					autoLoadStore : true
				}, {
					title : '已添加的角色列表',
					region : 'center',
					xtype : 'user_addedRoleGrid',
					autoLoadStore : true
				} ]
			} ],
			buttonAlign : 'center',
			fbar : [ {
				text : '返回',
				// iconCls :'returnBtn',
				handler : this.onOver
			} ]
		});

		this.callParent(arguments);
	},

	onOver : function() {
		this.up('window').close();
	}
});