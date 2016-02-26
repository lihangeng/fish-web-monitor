/**
 * 广告组显示Panel
 */
Ext.define('Eway.view.bsAdvert.BsAdvertGroupView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bs_advert_group_view',

	requires: ['Eway.view.bsAdvert.BsAdvertGroupGrid',
	           'Eway.view.bsAdvert.BsAdvertGroupFilterForm'],

	title: EwayLocale.bsAdvertGroup.title,
	layout: 'border',

	initComponent: function() {
//		var store = Ext.create('Eway.store.person.organization.BankOrganizationTree');
		Ext.apply(this, {
			items: [{
				region: 'center',
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'bs_advert_group_filterform'
				}, {
					region: 'center',
					xtype: 'bs_advert_group_grid'
				}]
			}],
			listeners : {
				activate : function(panel) {
//					if (!panel.isLoad) {
//						// 第一次进来主动加载信息
//						panel.isLoad = true;
//						var store = panel.down('bank_organization_grid').getStore();
//						store.setUrlParamsByObject({
//							type : '0'
//						});
//						store.load();
//						return;
//					}
				}
			}
		});
		this.callParent(arguments);
	}
});