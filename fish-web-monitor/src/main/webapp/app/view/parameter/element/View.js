
Ext.define('Eway.view.parameter.element.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.element_View',

	requires: ['Eway.view.parameter.element.FilterForm',
	           'Eway.view.parameter.element.Grid'
	           ],

	title: '参数元数据管理test',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterFormItemId',
				region: 'north',
				xtype: 'element_FilterForm',
				height: 40
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'element_Grid'
			}]
		});

		this.callParent(arguments);
	}
});