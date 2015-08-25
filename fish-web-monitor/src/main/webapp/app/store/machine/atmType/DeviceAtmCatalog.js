Ext.define('Eway.store.machine.atmType.DeviceAtmCatalog', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'ajax',
		url : 'api/machine/atmType/queryAtmCatalog',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});