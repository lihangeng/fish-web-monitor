Ext.define('Eway.model.monitor.settlement.Settlement', {
	extend : 'Ext.data.Model',
	fields : [ 'termId', 'uuId', 'date', 'leftAmt', 'boxDetail', 'deposit',
			'depositAmt', 'withdrawal', 'withdrawalAmt', 'transaction',
			'transactionAmt' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/settlement',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});