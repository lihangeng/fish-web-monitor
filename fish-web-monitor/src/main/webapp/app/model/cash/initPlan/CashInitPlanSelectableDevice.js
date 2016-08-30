Ext.define('Eway.model.cash.initPlan.CashInitPlanSelectableDevice', {
	extend: 'Ext.data.Model',
	fields: ['actualAmt','cashInitPlanInfoId','flag','address','billAmt','cashInAmt',
		'id','terminalId','devType','awayFlag','orgName','lastAmt','lastDate','adviceAmt','maxAmt'
	],
	proxy : {
		type : 'rest',
		url : 'api/cashInitPlanDevice/selectableDevice',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
