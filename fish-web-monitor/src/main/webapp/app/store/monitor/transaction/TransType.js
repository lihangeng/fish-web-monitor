
Ext.define('Eway.store.monitor.transaction.TransType', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.monitor.transaction.TransType',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});