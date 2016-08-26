
Ext.define('Eway.model.cash.initPlan.CashInitPlan', {
	extend: 'Ext.data.Model',
	fields: [
		'id','date','cashInitCode','orgName','amt','orgId'
	],
	proxy : {
		type : 'rest',
		url : 'api/cashInitPlan',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
