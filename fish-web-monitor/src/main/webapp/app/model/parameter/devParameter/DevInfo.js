Ext.define('Eway.model.parameter.devParameter.DevInfo', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','devTypeId','devTypeName','orgId','orgName','devCatalogName'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/devParameter',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

