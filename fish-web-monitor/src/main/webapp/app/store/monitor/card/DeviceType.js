Ext.define('Eway.store.monitor.card.DeviceType', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmType/queryAtmType',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});