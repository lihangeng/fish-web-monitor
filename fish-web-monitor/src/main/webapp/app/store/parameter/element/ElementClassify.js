Ext.define('Eway.store.parameter.element.ElementClassify', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/parameter/element/queryAppsystem',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});