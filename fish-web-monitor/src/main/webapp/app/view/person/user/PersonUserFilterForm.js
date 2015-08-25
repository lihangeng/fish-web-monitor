Ext.define('Eway.view.person.user.PersonUserFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.person_user_personUserFilterForm',

	requires : [ 'Eway.view.field.person.UserName',
			'Eway.view.field.person.GenderFilter',
			'Eway.view.field.person.Mobile',/*
			'Eway.view.field.person.TypeFilter',*/
			'Eway.view.field.person.StateFilter',
			'Eway.view.field.person.PersonJobComboBox' ],

	height : 80,
	layout : 'column',
	defaults : {
		border : false
	},

	/* title: '输入您要查询的条件：', */
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .3,
				items : [ {
					xtype : 'field.username',
					labelWidth : 50,
					maxLength : 20,
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					labelWidth : 50,
					regex : /^\d{8,11}$/,
					regexText : '手机电话号码只能输入8到11位数字‘0-9’！',
					msgTarget : 'side'
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field.genderFilter',
					labelWidth : 50,
					editable : false,
					msgTarget : 'side'
				}, {
					labelWidth : 50,
					xtype : 'field.stateFilter',
					editable : false
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field_person_personJobComboBox',
					fieldLabel : '岗位',
					labelWidth : 50,
					msgTarget : 'side'
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});