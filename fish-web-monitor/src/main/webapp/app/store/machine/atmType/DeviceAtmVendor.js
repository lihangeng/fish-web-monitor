Ext.define('Eway.store.machine.atmType.DeviceAtmVendor', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmType/queryAtmVendor',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});