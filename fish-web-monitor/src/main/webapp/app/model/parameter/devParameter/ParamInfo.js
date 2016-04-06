Ext.define('Eway.model.parameter.devParameter.ParamInfo', {
	extend : 'Ext.data.Model',
	fields : ['classifyId','paramClassify','paramName','paramValue'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/devParameter/paramInfo',
//		extraParams:{  
//			  total:50000   
//			           }, 
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

