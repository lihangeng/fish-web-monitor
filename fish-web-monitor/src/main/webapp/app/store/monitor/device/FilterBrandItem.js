Ext.define('Eway.store.monitor.device.FilterBrandItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	proxy : {
		type : 'rest',
		method : 'GET',
		url : 'api/machine/atmBrand/getBrandItem',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
	
	/*data : [{
			name : '-全部-',
			value : '1'
		},{
			name : 'HITACHI',
			value : '2'
		},{
			name : 'YH',
			value : '3'
		}]*/
	
	
});