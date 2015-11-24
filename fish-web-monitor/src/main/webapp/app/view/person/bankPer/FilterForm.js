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
				columnWidth : .45,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'field.username'
				}, {
					xtype : 'field.mobile',
					fieldLabel : EwayLocale.commen.mobile
				} ]
			}, {
				columnWidth : .45,
				defaults : {
					labelAlign : 'right',
				},
				items : [ {
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name : 'jobNum'
				}, {
					xtype : 'field_person_personJobComboBox',
					fieldLabel : EwayLocale.commen.personJobName
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});