
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
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					fieldLabel: EwayLocale.commen.mobile
				}]
			}, {
				columnWidth : .5,
				items : [{
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name:'jobNum',
				},  {
					xtype : 'field.stateFilter'
				}]
			}]
		});

		this.callParent(arguments);
	}
});