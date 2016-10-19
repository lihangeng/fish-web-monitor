Ext.define('Eway.model.machine.detail.FaultTrend', {
	extend : 'Ext.data.Model',
	fields : ['month', 'data1'],
	proxy : {
		type : 'rest',
		url : 'api/machine/devicedetailrun/faultTrend',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});