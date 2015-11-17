/**
 * 服务商机构管理模块显示Panel
 */
Ext.define('Eway.view.person.bankOrg.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bank_organization_view',

	requires: ['Eway.view.person.bankOrg.Grid',
	           'Eway.view.person.bankOrg.FilterForm'],

	title: EwayLocale.person.bankOrg.title,
	layout: 'border',

	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.BankOrganizationTree');
		Ext.apply(this, {
			items: [{
				region: 'east',
				title : Eway.view.person.bankOrg.orgNavi,
				header: false,
				xtype : 'treepanel',
				width : 200,
				minWidth:200,
				maxWidth:300,
				border : true,
				//padding : '10 0 10 0',
				rootVisible : true,
				lines : true,
				store : store,
				collapsible: false,
             	//collapseMode: 'mini',
             	split:false
			}, {
				region: 'center',
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'bank_organization_filterform'
				}, {
					region: 'center',
					xtype: 'bank_organization_grid'
				}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {

						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}

					panel.down('treepanel').getStore().load();
				}
			}
		});
		this.callParent(arguments);
	}
});