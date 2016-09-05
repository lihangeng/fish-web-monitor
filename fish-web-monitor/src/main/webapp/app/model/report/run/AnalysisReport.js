Ext.define('Eway.model.report.run.AnalysisReport', {
	extend : 'Ext.data.Model',
	idProperty : 'month',
	fields : [ 'createDate','fileName','fileSize','reportType'],
	proxy : {
		type : 'rest',
		url : 'api/report/runAnalysisReport',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});