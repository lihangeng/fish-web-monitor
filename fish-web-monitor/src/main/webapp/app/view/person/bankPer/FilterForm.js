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
				columnWidth : .30,
				defaults : {
					labelAlign : 'right',
					labelWidth : 50,
					width:200
				},
				items : [ {
					xtype : 'field.username',
					labelWidth : 50,
					maxLength : 20,					
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					fieldLabel : EwayLocale.commen.mobile,
					labelWidth : 50,
					regex : /^\d{8,11}$/,
					regexText : EwayLocale.vtype.mobile,
					msgTarget : 'side'
				} ]
			}, {
				columnWidth : .30,
				defaults : {
					labelAlign : 'right',
					labelWidth : 50,
					width:200
				},
				items : [ {
					fieldLabel : EwayLocale.commen.jobNum,
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
				columnWidth : .40,
				defaults : {
					labelAlign : 'right',
					width:200,
					labelWidth : 50
				},
				items : [ {
					labelWidth : 50,
					xtype : 'field_person_personJobComboBox',
					fieldLabel : EwayLocale.commen.personJobName,
					msgTarget : 'side'
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});