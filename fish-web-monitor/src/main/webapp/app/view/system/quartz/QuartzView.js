
Ext.define('Eway.view.system.quartz.QuartzView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.quartz_QuartzView',

	requires: ['Eway.view.system.quartz.QuartzGrid',
	           'Eway.view.system.quartz.QuartzFilterForm'
	           ],

	title: 'quartz管理',
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterFormItemId',
				region: 'north',
				xtype: 'quartz_QuartzFilterForm',
				height: 40
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'quartz_QuartzGrid'
			}]
		});

		this.callParent(arguments);
	}
});