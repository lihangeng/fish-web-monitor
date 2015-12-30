Ext.define('Eway.view.person.bankPer.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.bank_person_filterform',

	requires : [ 'Eway.view.field.person.UserName',
			'Eway.view.field.person.GenderFilter',
			'Eway.view.field.person.Mobile','Eway.view.common.OrgComboOrgTree',
			'Eway.view.field.person.StateFilter',
			'Eway.view.field.person.PersonJobComboBox' ],

	height : 80,
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent : function() {
		Ext.apply(this,{
			items : [ {
				columnWidth : .3,
				items : [{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
					emptyText: EwayLocale.combox.select,
					editable : false,
					name : 'org',
					filters : '{"type" : "0"}',
					rootVisible : false,
					hiddenValue : 'selectedNode',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					xtype : 'hiddenfield',
					name :'selectedNode'
				}, {
					xtype : 'field_person_personJobComboBox',
					fieldLabel : EwayLocale.commen.personJobName
				}]
			},{
				columnWidth : .3,
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
				columnWidth : .3,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name : 'jobNum'
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});