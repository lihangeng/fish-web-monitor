
Ext.define('Eway.model.monitor.transaction.Transaction', {
	extend: 'Ext.data.Model',
	fields : [ 'termId', 'transId', 'debitAccount', 'creditAccount',
			'transCode', 'amt', 'currency', 'dateTime', 'hostRet',
			'localRet' ],
	proxy : {
		type : 'rest',
		url : 'api/msg/transaction',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});