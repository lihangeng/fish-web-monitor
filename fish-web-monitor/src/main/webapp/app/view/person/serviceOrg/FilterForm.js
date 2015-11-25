/**
 * 厂商查询条件Form
 */
Ext.define('Eway.view.person.serviceOrg.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.ser_organization_filterform',
	
	requires: ['Eway.view.field.person.Code','Eway.view.common.OrgComboOrgTree',
	           'Eway.view.field.person.Name'],
	
	/*title: '输入您的查询条件',*/
	height: 40,
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent: function() {
		Ext.apply(this, {
			items : [{

				columnWidth : .3,
				items : [{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.person.serviceOrg.name,
					emptyText: EwayLocale.combox.select,
					editable : false,
					name : 'org',
					filters : '{"type" : "1"}',
					rootVisible : true,
					hiddenValue : 'selectedNode'
				},{
					xtype : 'hiddenfield',
					name :'selectedNode'
				}]
			
			},{
				columnWidth : .3,
				items : [{
					xtype : 'field.code',
					labelAlign : 'right',
					fieldLabel : EwayLocale.person.serviceOrg.code,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .3,
				items : [{
					xtype : 'field.name',
					labelAlign : 'right',
					fieldLabel: EwayLocale.person.serviceOrg.name,
					msgTarget : 'side'
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});