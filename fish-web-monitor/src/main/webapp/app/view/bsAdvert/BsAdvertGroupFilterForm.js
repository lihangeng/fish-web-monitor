/**
 * 机构查询条件Form
 */
Ext.define('Eway.view.bsAdvert.BsAdvertGroupFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.bs_advert_group_filterform',

	requires: ['Eway.view.common.OrgComboOrgTree',
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
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.person.bankOrg.name,
					emptyText: EwayLocale.combox.select,
					editable : false,
					name : 'org',
					filters : '{"type" : "0"}',
					rootVisible : true,
					hiddenValue : 'orgId'
				},{
					xtype : 'hiddenfield',
					name :'orgId'
				}]
			},{
				columnWidth : .3,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'groupName',
					fieldLabel : '广告组名称',
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