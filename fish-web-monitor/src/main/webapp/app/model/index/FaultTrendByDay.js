Ext.define('Eway.model.index.FaultTrendByDay', {
	extend : 'Ext.data.Model',
	idProperty : 'month',
	fields : [ 'month','data1'],
	proxy : {
		type : 'rest',
		url : 'api/index/faultTrendByDay',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});