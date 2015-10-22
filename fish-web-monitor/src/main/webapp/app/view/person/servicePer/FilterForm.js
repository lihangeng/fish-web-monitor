
Ext.define('Eway.view.person.servicePer.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.ser_person_filterform',

	requires: ['Eway.view.field.person.UserName',
	           'Eway.view.field.person.GenderFilter',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.StateFilter'
	           ],


	height: 80,
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .5,
				items : [{
					xtype : 'field.username',
					labelWidth: 50,
					maxLength : 20,
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					fieldLabel: Eway.locale.commen.mobile,
					labelWidth: 50,
					regex: /^\d{8,11}$/,
					regexText:Eway.locale.vtype.mobile,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .5,
				items : [{
					fieldLabel : Eway.locale.commen.jobNum,
					xtype : 'textfield',
					labelWidth: 50,
					maxLength : 20,
					editable : true,
					name:'jobNum',
					value:'',
					msgTarget : 'side'
				},  {
					labelWidth: 50,
					xtype : 'field.stateFilter',
					editable : false,

				}]
			}]
		});

		this.callParent(arguments);
	}
});