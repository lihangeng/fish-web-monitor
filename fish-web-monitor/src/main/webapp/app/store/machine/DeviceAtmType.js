Ext.define('Eway.store.machine.DeviceAtmType', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'ajax',
		url : 'api/machine/device/queryAtmType',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});