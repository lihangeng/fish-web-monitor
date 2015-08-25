Ext.define('Eway.model.monitor.settlement.SettlementDetail', {
	extend : 'Ext.data.Model',
	fields : [ 'boxId', 'boxCurrency', 'boxLeftAmt' ],
	proxy : {
		type : 'rest',
		url : 'api/msg/checkoutcash',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});