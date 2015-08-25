
Ext.define('Eway.view.permission.role.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.permission_role_filterform',

	requires: ['Eway.view.field.RoleName',
	           'Eway.view.permission.role.ComboBox'],

	height: 40,

	initComponent: function() {
		Ext.apply(this,{
			items:[{
				fieldLabel :'角色名称',
				xtype:'field_roleName',
				width: 300,
				maxLength : 40,
				allowBlank : true
			}]
		});
		this.callParent(arguments);
	},

	onReset:function(){
		this.up('form').getForm().reset();
	}
});