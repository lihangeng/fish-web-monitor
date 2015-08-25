/**
 * 服务商厂商管理模块显示Panel
 */
Ext.define('Eway.view.person.serviceOrg.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.ser_organization_view',

	requires: ['Eway.view.person.serviceOrg.Grid',
	           'Eway.view.person.serviceOrg.FilterForm'],

	title: '维护商管理',
	layout: 'border',

	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.SerOrganizationTree');
		Ext.apply(this, {
			items: [{
				region: 'east',
				title : '厂商导航',
				header: false,
				xtype : 'treepanel',
				width : 200,
				border : false,
				padding : '0 2 0 0',
				rootVisible : true,
				lines : true,
				store : store
			}, {
				region: 'center',
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'ser_organization_filterform'
				}, {
					region: 'center',
					xtype: 'ser_organization_grid'
				}]
			}]
		});
		this.callParent(arguments);
	}
});