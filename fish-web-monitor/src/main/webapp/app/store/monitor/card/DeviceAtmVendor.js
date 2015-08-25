Ext.define('Eway.store.monitor.card.DeviceAtmVendor', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmBrand/queryAtmVendor',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});