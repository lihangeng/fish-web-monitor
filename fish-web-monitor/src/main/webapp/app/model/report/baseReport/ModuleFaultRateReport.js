Ext.define('Eway.model.report.baseReport.ModuleFaultRateReport', {
	extend : 'Ext.data.Model',
	fields : [ 'angle','total'],
	proxy : {
		type : 'rest',
		url : 'api/report/moduleFaultRate',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});