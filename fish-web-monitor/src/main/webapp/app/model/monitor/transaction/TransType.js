
Ext.define('Eway.model.monitor.transaction.TransType', {
	extend: 'Ext.data.Model',
	fields : [ 'id','transCode', 'codeDesc' ],
	proxy : {
		type : 'rest',
		url : 'api/msg/transaction/queryTransType',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});