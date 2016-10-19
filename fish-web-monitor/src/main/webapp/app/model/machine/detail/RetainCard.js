Ext.define('Eway.model.machine.detail.RetainCard', {
	extend : 'Ext.data.Model',
	fields : ['month', 'data1'],
	proxy : {
		type : 'rest',
		url : 'api/machine/devicedetailrun/retainCard',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});