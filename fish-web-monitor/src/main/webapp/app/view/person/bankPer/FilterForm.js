Ext.define('Eway.view.person.bankPer.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.bank_person_filterform',

	requires : [ 'Eway.view.field.person.UserName',
			'Eway.view.field.person.GenderFilter',
			'Eway.view.field.person.Mobile',
			'Eway.view.field.person.StateFilter',
			'Eway.view.field.person.PersonJobComboBox' ],

	height : 80,
	layout : 'column',
	defaults : {
		border : false
	},

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
					fieldLabel : '手机',
					labelWidth : 50,
					regex : /^\d{8,11}$/,
					regexText : '手机电话号码只能输入8到11位数字‘0-9’！',
					msgTarget : 'side'
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					fieldLabel : '工号',
					xtype : 'textfield',
					labelWidth : 50,
					editable : true,
					maxLength : 20,
					name : 'jobNum',
					value : '',
					msgTarget : 'side'
				}, {
					labelWidth : 50,
					xtype : 'field.stateFilter',
					editable : false

				} ]
			}, {
				columnWidth : .4,
				items : [ {
					labelWidth : 50,
					xtype : 'field_person_personJobComboBox',
					fieldLabel : '岗位',
					msgTarget : 'side'
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});