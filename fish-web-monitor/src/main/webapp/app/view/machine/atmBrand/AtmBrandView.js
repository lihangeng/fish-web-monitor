
Ext.define('Eway.view.machine.atmBrand.AtmBrandView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmBrand_AtmBrandView',

	requires: ['Eway.view.machine.atmBrand.AtmBrandGrid',
	           'Eway.view.machine.atmBrand.AtmBrandFilterForm'
	           ],

	title: '设备品牌',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterFormItemId',
				region: 'north',
				xtype: 'atmBrand_AtmBrandFilterForm',
				height: 40
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'atmBrand_AtmBrandGrid'
			}]
		});

		this.callParent(arguments);
	}
});