Ext.define('Eway.store.monitor.device.FilterClassifyItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	proxy : {
		type : 'rest',
		method : 'GET',
		url : 'api/machine/atmBrand/getTypeItem2',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
	/*
	data : [{
			name : '-全部-',
			value : '1'
		},{
			name : 'ATM',
			value : '2'
		},{
			name : 'CDM',
			value : '3'
		},{
			name : 'CRS',
			value : '4'
		}]*/
});