Ext.define('Eway.view.cash.initRule.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.initRule_View',

	requires : [ 'Eway.view.cash.initRule.Grid',
			'Eway.view.cash.initRule.FilterForm' ],

	title : EwayLocale.initRule.title,//'加钞规则管理',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype : 'initRule_filterForm'
				}, {
					region : 'center',
					xtype : 'initRule_grid'
			} ]
		});

		this.callParent(arguments);
	}
});