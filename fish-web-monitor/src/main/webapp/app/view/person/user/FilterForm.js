
Ext.define('Eway.view.person.user.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.user_filterform',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.UserStateFilter'
	           ],


	height: 40,
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .3,
				items : [{
					labelWidth: 50,
					xtype : 'field.code',
					labelAlign : 'right',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:Eway.locale.vtype.bankOrgCode,
					fieldLabel : Eway.locale.person.user.code,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .3,
				items : [{
					labelWidth: 50,
					xtype : 'field.userStateFilter',
					labelAlign : 'right',
					editable : false,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .3,
				items : [{
					xtype : 'field.username',
					labelWidth: 50,
					maxLength : 20,
					msgTarget : 'side'
				}]
			}]
		});

		this.callParent(arguments);
	}
});