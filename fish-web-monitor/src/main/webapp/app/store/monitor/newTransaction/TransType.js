
Ext.define('Eway.store.monitor.newTransaction.TransType', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.monitor.transaction.TransType',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});