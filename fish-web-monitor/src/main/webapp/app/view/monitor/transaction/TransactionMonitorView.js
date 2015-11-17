Ext.define('Eway.view.monitor.transaction.TransactionMonitorView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.transactionMonitorView',

	requires: ['Eway.view.monitor.transaction.TransactionGrid',
				'Eway.view.monitor.transaction.TransactionFilterForm'],

	title: EwayLocale.monitor.business.transaction.transactionMonitor.title,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [ {
				region: 'north',
				xtype: 'transactionFilterForm'
			}, {
				region: 'center',
				xtype: 'monitor_transaction_transactionGrid'
			} ],
			listeners : {
			}
		});
		this.callParent(arguments);
	}
});