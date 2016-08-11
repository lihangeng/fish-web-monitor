
Ext.define('Eway.model.cash.boxInfo.CashBoxInfo', {
	extend: 'Ext.data.Model',
	fields: [
		'id','devType','devTypeName','devCatalogId','devCatalogName','devService',
		'devServiceName','organization','organizationName','awayFlag',
		'awayFlagName','ip','terminalId','maxAlarm','minAlarm'
	],
	
	proxy : {
		type : 'rest',
		url : 'api/cashbox',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
