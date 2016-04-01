Ext.define('Eway.store.parameter.devParameter.ParamClassify', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'name' ],
	proxy : {
		type : 'ajax',
		url : 'api/parameter/devParameter/devParamClassify',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : true
});