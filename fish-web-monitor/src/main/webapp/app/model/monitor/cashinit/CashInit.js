Ext.define('Eway.model.monitor.cashinit.CashInit', {
	extend : 'Ext.data.Model',
	fields : [ 'termId', 'date', 'uuId', 'amt', 'boxDetail' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/cashinit',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});