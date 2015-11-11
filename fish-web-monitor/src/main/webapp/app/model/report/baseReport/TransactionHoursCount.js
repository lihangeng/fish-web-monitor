Ext.define('Eway.model.report.baseReport.TransactionHoursCount', {
	extend : 'Ext.data.Model',
	idProperty : 'month',
	fields : [ 'month','data1'],
	proxy : {
		type : 'rest',
		url : 'api/report/transactionDaysCount/hour',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});