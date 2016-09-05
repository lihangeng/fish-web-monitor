Ext.define('Eway.model.report.baseReport.ETLJobCount', {
	extend : 'Ext.data.Model',
//	idProperty : 'id',
	fields : ['id','tradeTime'],
	proxy : {
		type : 'rest',
		url : 'api/report/ETLJob/check',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});