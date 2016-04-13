Ext.define('Eway.model.parameter.devParameter.ParamInfo', {
	extend : 'Ext.data.Model',
	fields : ['id','classifyId','paramClassify','paramName','paramValue'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/devParameter/paramInfo',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

