
Ext.define('Eway.view.permission.role.Add', {
	extend: 'Ext.form.Panel',
	alias: 'widget.permission_role_Add',

	requires: ['Eway.view.permission.role.Tree',
	           'Eway.view.field.RoleDescription',
	           'Eway.view.field.RoleName'],

	initComponent: function() {
		Ext.apply(this, {
			items : {
				width : 700,
				height: 540,
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaultType: 'textfield',
				bodyPadding: 8,
				defaults: {
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side',
					style : 'padding-top:10px'
				},
				items: [{
					xtype:'field_roleName'
				},{
					xtype:'field_roleDescription'
				},{
					xtype:'permission_role_tree',
					header : false,
					height:400
				}],
				buttonAlign : 'center',
				buttons: [{
					text: '确认',
					action: 'confirm'
				},{
					text: '取消',
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});