Ext.define('Eway.model.machine.detail.OpenRate', {
	extend : 'Ext.data.Model',
	fields : ['month', 'data1'],
	proxy : {
		type : 'rest',
		url : 'api/machine/devicedetailrun/openrate',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});