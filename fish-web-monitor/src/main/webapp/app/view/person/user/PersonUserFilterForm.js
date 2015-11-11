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
					labelWidth : 40,
					maxLength : 20,
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					labelWidth : 40,
					regex : /^\d{8,11}$/,
					regexText : Eway.locale.vtype.mobile,
					msgTarget : 'side'
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field.genderFilter',
					labelWidth : 40,
					editable : false,
					msgTarget : 'side'
				}, {
					labelWidth : 40,
					xtype : 'field.stateFilter',
					editable : false
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field_person_personJobComboBox',
					fieldLabel : Eway.locale.commen.personJobName,
					labelWidth : 40,
					msgTarget : 'side'
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});