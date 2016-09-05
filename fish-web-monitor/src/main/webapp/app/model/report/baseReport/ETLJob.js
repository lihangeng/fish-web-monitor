Ext.define('Eway.model.report.baseReport.ETLJob', {
	extend : 'Ext.data.Model',
//	idProperty : 'id',
	fields : ['id','jobName','startTime','endTime','tradeTime','operaResult'],
	proxy : {
		type : 'rest',
		url : 'api/report/ETLJob',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});