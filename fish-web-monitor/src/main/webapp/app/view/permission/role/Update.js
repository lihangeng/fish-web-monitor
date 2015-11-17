
Ext.define('Eway.view.permission.role.Update', {
	extend: 'Ext.form.Panel',
	alias: 'widget.permission_role_update',

	requires: ['Eway.view.field.RoleName',
	           'Eway.view.field.RoleDescription',
	           'Eway.store.permission.ComboBox'],

	initComponent: function() {
		Ext.apply(this, {
			items : {
				trackResetOnLoad : true,
				selectOnFocus : true,
				bodyPadding: 10,
				defaults: {
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side',
					style : 'padding-top:10px'
				},
			items: [{
					xtype:'hidden',
					name:'id'
				},{
					xtype : 'field_roleName'
				},{
					xtype : 'field_roleDescription'
				},{
					xtype:'permission_role_tree',
					header : false,
					height:400
				}],
			fbar: [{
					text: EwayLocale.button.update,
					action: 'confirm'
				},{
					text: EwayLocale.button.back,
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