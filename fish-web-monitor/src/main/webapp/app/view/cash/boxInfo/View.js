Ext.define('Eway.view.cash.boxInfo.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.boxInfo_View',

	requires : [ 'Eway.view.cash.boxInfo.Grid',
			'Eway.view.cash.boxInfo.FilterForm' ],

	title : EwayLocale.boxInfo.title,//'软件分类管理',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype : 'boxInfo_filterForm'
				}, {
					region : 'center',
					xtype : 'boxInfo_grid'
			} ]
		});

		this.callParent(arguments);
	}
});