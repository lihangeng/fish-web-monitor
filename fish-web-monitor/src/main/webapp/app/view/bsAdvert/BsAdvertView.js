/**
 * 广告组显示Panel
 */
Ext.define('Eway.view.bsAdvert.BsAdvertView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bs_advert_view',

	requires: ['Eway.view.bsAdvert.BsAdvertGrid',
	           'Eway.view.bsAdvert.BsAdvertFilterForm'],

	title: '广告管理',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'center',
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'bs_advert_filterForm'
				}, {
					region: 'center',
					xtype: 'bs_advert_grid'
				}]
			}]
		});
		this.callParent(arguments);
	}
});