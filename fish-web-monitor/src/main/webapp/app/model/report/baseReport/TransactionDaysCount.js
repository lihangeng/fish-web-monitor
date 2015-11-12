Ext.define('Eway.model.report.baseReport.TransactionDaysCount', {
	extend : 'Ext.data.Model',
	idProperty : 'month',
	fields : [ 'month','data1'],
	proxy : {
		type : 'rest',
		url : 'api/report/transactionDaysCount',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});