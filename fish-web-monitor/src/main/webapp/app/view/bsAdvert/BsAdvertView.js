/**
 * 广告组显示Panel
 */
Ext.define('Eway.view.bsAdvert.BsAdvertView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bs_advert_view',

	requires: ['Eway.view.bsAdvert.BsAdvertGrid',
	           'Eway.view.bsAdvert.BsAdvertFilterForm'],
	//'广告管理'
	title: EwayLocale.bsAdvert.advertTitle,
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
			}],
			listeners:{
				activate:function(_this,  eOpts ){
					_this.down("field_advert_advertGroup").getStore().load();
				}
			}
		});
		this.callParent(arguments);
	}
});