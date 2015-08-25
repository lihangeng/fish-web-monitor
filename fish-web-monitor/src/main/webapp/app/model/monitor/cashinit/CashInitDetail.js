Ext.define('Eway.model.monitor.cashinit.CashInitDetail', {
	extend : 'Ext.data.Model',
	fields : [ 'boxId', 'boxCurrency', 'boxInitAmt' ],
	proxy : {
		type : 'rest',
		url : 'api/msg/checkincash',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});