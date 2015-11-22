Ext.define('Eway.model.report.baseReport.CaseStatisticsReport', {
	extend : 'Ext.data.Model',
	fields : [ 'angle','total'],
	proxy : {
		type : 'rest',
		url : 'api/report/caseStatisticsReport',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});