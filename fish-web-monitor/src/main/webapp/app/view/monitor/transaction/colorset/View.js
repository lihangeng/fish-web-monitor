Ext.define('Eway.view.monitor.transaction.colorset.View', {

	extend : 'Eway.view.base.Panel',
	alias : 'widget.monitor_transaction_colorset_view',
	title : EwayLocale.monitor.business.transactionColor.title,
	layout : 'border',
	requires : [ 'Eway.view.monitor.transaction.colorset.FilterForm',
			'Eway.view.monitor.transaction.colorset.Grid' ],
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'center',
				layout : 'border',
				items : [ {
					region : 'north',
					xtype : 'monitor_transaction_colorset_filterform'
				}, {
					region : 'center',
					xtype : 'monitor_transaction_colorset_grid'
				} ],
				split : true
			} ]
		});
		this.callParent(arguments);
	}
});
