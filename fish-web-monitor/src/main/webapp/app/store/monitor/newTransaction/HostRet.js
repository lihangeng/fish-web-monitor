
Ext.define('Eway.store.monitor.newTransaction.HostRet', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.monitor.transaction.HostRet',
	autoLoad : true,
	initComponent : function(){
		this.callParent(arguments);
	}
});