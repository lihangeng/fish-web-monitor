Ext.define('Eway.view.parameter.classify.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.classify_View',

	requires: ['Eway.view.parameter.classify.FilterForm',
	           	   'Eway.view.parameter.classify.Grid'],

	title: '参数元数据分类管理',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'classify_FilterForm',
				height: 40
			}, {
				region: 'center',
				xtype: 'classify_Grid'
			}]
		});
		this.callParent(arguments);
	}
});