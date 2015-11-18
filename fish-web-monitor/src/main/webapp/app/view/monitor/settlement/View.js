Ext.define('Eway.view.monitor.settlement.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.monitor_settlement_view',
	requires : [ 'Eway.view.monitor.settlement.Grid',
			'Eway.view.monitor.settlement.FilterForm' ],

	title : EwayLocale.monitor.business.settlement.title,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'monitor_settlement_filterform'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'monitor_settlement_grid'
			} ]
		});
		this.callParent(arguments);
	}
});