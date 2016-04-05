
Ext.define('Eway.store.parameter.element.ParamBelongs', {
	extend: 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'rest',
		url : 'api/parameter/element/elementClassify',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});
