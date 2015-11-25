
Ext.define('Eway.view.person.servicePer.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.ser_person_filterform',

	requires: ['Eway.view.field.person.UserName',
	           'Eway.view.field.person.GenderFilter','Eway.view.common.OrgComboOrgTree',
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
				columnWidth : .33,
				items : [{
					xtype : 'common_orgComboOrgTree',
					fieldLabel :EwayLocale.commen.devServiceName,
					emptyText: EwayLocale.combox.select,
					labelWidth:140,
					editable : false,
					name : 'org',
					filters : '{"type" : "1"}',
					rootVisible : true,
					hiddenValue : 'selectedNode'
				},{
					xtype : 'hiddenfield',
					name :'selectedNode'
				},  {
					labelWidth:140,
					xtype : 'field.stateFilter'
				}]
			},{
				columnWidth : .3,
				items : [{
					xtype : 'field.username',
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					fieldLabel: EwayLocale.commen.mobile
				}]
			}, {
				columnWidth : .3,
				items : [{
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name:'jobNum',
				}]
			}]
		});

		this.callParent(arguments);
	}
});