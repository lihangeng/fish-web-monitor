
Ext.define('Eway.model.cash.initRule.CashInitRule', {
	extend: 'Ext.data.Model',
	fields: [
		'id','name','ruleDesc','startUsing'
	],
	proxy : {
		type : 'rest',
		url : 'api/cashboxInitRule',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
