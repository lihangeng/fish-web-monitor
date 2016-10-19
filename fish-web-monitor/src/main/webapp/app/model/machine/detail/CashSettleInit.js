Ext.define('Eway.model.machine.detail.CashSettleInit', {
	extend : 'Ext.data.Model',
	fields : ['date', 'initAmt','leftAmt', 'deposit'
	          , 'depositAmt','withdrawal', 'withdrawalAmt'],
	
	proxy : {
		type : 'rest',
		url : 'api/machine/devicedetailrun/cashSettleInit',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});