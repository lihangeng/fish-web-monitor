
Ext.define('Eway.model.cash.initPlan.CashInitPlanDevice', {
	extend: 'Ext.data.Model',
	fields: ['actualAmt','cashInitPlanInfoId','flag','address',
		'id','terminalId','devType','awayFlag','orgName','lastAmt','lastDate','adviceAmt','maxAmt'
	],
	proxy : {
		type : 'rest',
		url : 'api/cashInitPlanDevice',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
