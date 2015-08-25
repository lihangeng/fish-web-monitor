
Ext.define('Eway.model.case.Fault', {
	extend: 'Ext.data.Model',
	fields: [
	       'id', 'terminalId','devMod', 'faultClassify','faultTime','org','bankPer','serPer',
	       'vendorHwCode','faultCode','closedTime','duration','faultStatus','upgrade'
	],
	
	idProperty : 'id',  
	proxy : {
		type : 'rest',
		url : 'api/case/caseFault',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
