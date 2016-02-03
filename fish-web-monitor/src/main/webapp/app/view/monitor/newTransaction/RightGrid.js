Ext.define('Eway.view.monitor.newTransaction.RightGrid', {
	alias : 'widget.monitor_new_transaction_rightgrid',
	extend : 'Eway.view.base.Grid',

	border : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.newTransaction.TransactionTPS');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [ {
				header : EwayLocale.monitor.business.newTransaction.type,
				dataIndex : 'text',
				width : 110
			}, {
				header : EwayLocale.monitor.business.newTransaction.result,
				dataIndex : 'successfly',
				width : 80,
				flex: 1
			} ]
		});
		this.callParent(arguments);
	}
});