Ext.define('Eway.model.monitor.newTransaction.NewTransaction', {
    extend : 'Ext.data.Model',
    fields : [ 'termId', 'transId', 'debitAccount', 'creditAccount','cardType',
            'transCode', 'amt', 'currency', 'dateTime','hostRetName','hostRet', 'localRet',
            'orgCode', 'orgName','connectCostTime', 'costTime','errorText','localRetText', 'tipFee',
            'branchOrgName', 'branchOrgId', 'cardCatalog','virtualTeller','oldTransId','remark' ],
    proxy : {
		type : 'rest',
		url : 'api/msg/transaction',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});