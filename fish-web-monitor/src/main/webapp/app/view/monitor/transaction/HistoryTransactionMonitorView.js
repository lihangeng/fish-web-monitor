Ext.define('Eway.view.monitor.transaction.HistoryTransactionMonitorView', {
	extend: 'Eway.view.base.Panel',
	
	alias: 'widget.monitor_transaction_transview',
	
	requires: [ 'Eway.view.monitor.transaction.HistoryTransactionGrid',
	           'Eway.view.monitor.transaction.HistoryTransactionFilterForm' ],
	            
	title: EwayLocale.monitor.business.transaction.historyTransaction.title,
	layout: 'border',
	modal : true,
	constrainHeader : true,
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'monitor_transaction_transForm'
			}, {
				region: 'center',
				xtype: 'monitor_transaction_transGrid'
			}]
		});
		this.callParent(arguments);
	}
});