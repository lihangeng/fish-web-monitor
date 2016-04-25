
Ext.define('Eway.view.parameter.template.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.template_View',

	requires: ['Eway.view.parameter.template.FilterForm',
	           'Eway.view.parameter.template.Grid'
	           ],

	title: '参数模板管理',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterFormItemId',
				region: 'north',
				xtype: 'template_FilterForm',
				height: 50
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'template_Grid'
			}]
		});

		this.callParent(arguments);
	}
});