Ext.define('Eway.model.machine.detail.Trading', {
	extend : 'Ext.data.Model',
	fields : ['transName','transCode','transCount','transAmt'],
	proxy : {
		type : 'rest',
		url : 'api/machine/devicedetailrun/transType',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});