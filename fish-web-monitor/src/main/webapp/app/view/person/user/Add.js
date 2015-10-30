
Ext.define('Eway.view.person.user.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.user_add',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.UserState',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.UserType',
	           'Eway.view.person.user.Role'],

	title: Eway.locale.person.user.addUserTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,
	layout: 'border',
	width: 700,
	height: 450,
	initComponent: function() {
		Ext.apply(this, {
			items :[ {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region: 'north',
        		height: 100,
				defaults: {
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
			        xtype: 'fieldcontainer',
			        fieldLabel: '<font color="red">*</font>'+Eway.locale.commen.name,
			        combineErrors: true,
			        layout: 'hbox',
			        defaults: {
			            hideLabel: true
			        },
			        items: [{
						xtype : 'field.username',
						fieldLabel : '<font color="red">*</font> '+Eway.locale.commen.name,
						emptyText:Eway.locale.person.bankPer.clickToUser,
						allowBlank : false,
						readOnly : true
					},{
				        xtype: 'button',
						glyph : 0xf002,
				        name: 'personAddButton',
				        width: 30,
				        margin: '0 0 0 5'
					}]
				},{
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.user.code,
					xtype : 'field.code',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText: Eway.locale.vtype.bankOrgCode,
					allowBlank : false
				},{
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.user.userType,
					xtype : 'field_userType',
					name:'userType',
					allowBlank : false
				},{
				    xtype : 'field.userState',
				    fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankPer.status,
				    allowBlank : false,
				    value : '0',
				    hidden : true,
					editable : false
				},{
					xtype : 'field.gender',
					allowBlank : false,
					editable : false,
					readOnly: true,
					hidden : true
				},{
					xtype : 'textfield',
					name : 'userGuid',
					hidden : true
				}]
			},{
				title:Eway.locale.person.user.roleGiven,
				xtype: 'user_role',
				region: 'center',
				autoLoadStore : false
			}],
			buttonAlign : 'center',
			buttons: [{
					text: Eway.locale.button.confirm,
					action: 'confirm'
				}, {
					text: Eway.locale.button.cancle,
					handler: this.onOver
			}]
		});

		this.callParent(arguments);
	},

	onOver: function() {
		this.up('window').close();
	}
});