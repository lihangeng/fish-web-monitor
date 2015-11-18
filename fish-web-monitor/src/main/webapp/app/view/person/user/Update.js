Ext.define('Eway.view.person.user.Update', {
	extend : 'Ext.window.Window',
	alias : 'widget.user_update',

	requires : [ 'Eway.view.person.user.RoleGrid',
		           'Eway.view.field.person.UserType',
			'Eway.view.person.user.AddedRoleGrid' ],

	title : EwayLocale.person.user.updateUserTitle,
	modal : true,
	resizable : true,
	constrainHeader : true,
	layout : 'border',
	width : 800,
	height : 500,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
//				fieldLabel : '<font color="red">*</font> 用户类型',
//				xtype : 'field_userType',
//				region : 'north',
//				name:'userType',
//				allowBlank : false
//			},{
				xtype : 'panel',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region : 'center',
				layout : 'border',
				items : [ {
					title : EwayLocale.person.user.roleCanBeAdd,
					region : 'west',
					width : 400,
					xtype : 'user_roleGrid',
					margin : '0 5px 0 0',
					autoLoadStore : true
				}, {
					title : EwayLocale.person.user.roleAlreadyBeAdd,
					region : 'center',
					xtype : 'user_addedRoleGrid',
					autoLoadStore : true
				} ]
			} ],
			buttonAlign : 'center',
			fbar : [ {
				text : EwayLocale.button.back,
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