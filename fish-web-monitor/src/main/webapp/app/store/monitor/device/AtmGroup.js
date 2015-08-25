Ext.define('Eway.store.monitor.device.AtmGroup',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	proxy : {
		type : 'rest',
		method : 'GET',
		url : 'api/machine/atmGroup/combo',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});