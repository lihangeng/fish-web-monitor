
Ext.define('Eway.model.monitor.transaction.HostRet', {
	extend: 'Ext.data.Model',
	fields : [ 'id','hostRet', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/msg/transaction/queryHostRet',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});