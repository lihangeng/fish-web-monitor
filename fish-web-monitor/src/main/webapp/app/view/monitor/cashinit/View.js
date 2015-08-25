Ext.define('Eway.view.monitor.cashinit.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.monitor_cashinit_view',
	requires : [ 'Eway.view.monitor.cashinit.Grid',
			'Eway.view.monitor.cashinit.FilterForm' ],

	title : '加钞信息查询',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'monitor_cashinit_filterform'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'monitor_cashinit_grid'
			} ]
		});
		this.callParent(arguments);
	}
});