
Ext.define('Eway.model.cash.boxInfo.CashBoxInfo', {
	extend: 'Ext.data.Model',
	fields: [
		'id','devType','devTypeName','devCatalogId','devCatalogName','devService',
		'devServiceName','organization','organizationName','awayFlag','flag',
		'awayFlagName','ip','terminalId','maxAlarm','minAlarm','defaultBill','defaultCashIn'
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
