
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
					regexText:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',
					fieldLabel : '用户名',
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