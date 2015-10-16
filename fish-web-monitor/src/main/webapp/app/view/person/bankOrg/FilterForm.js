/**
 * 机构查询条件Form
 */
Ext.define('Eway.view.person.bankOrg.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.bank_organization_filterform',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.Name',
	           'Eway.view.field.person.OrganizationLevel'],

	/*title: '输入您的查询条件',*/
	height: 40,
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent: function() {
		var levelStore = Ext.create('Eway.store.person.organization.OrganizationLevelDict');
		Ext.apply(this, {
			items : [{
				columnWidth : .3,
				items : [{
					xtype : 'field.code',
					labelAlign : 'right',
					fieldLabel : Eway.locale.person.bankOrg.code,
					maxLength:20,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .3,
				items : [{
					xtype : 'field.name',
					labelAlign : 'right',
					fieldLabel: Eway.locale.person.bankOrg.name,
					maxLength:40,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .3,
				items : [{
					xtype : 'field.organizationLevel',
					name : 'orgLevel',
					store : levelStore
				}]
			}]
		});

		this.callParent(arguments);
	}
});