Ext.define('Eway.model.parameter.paramMonitor.JobMonitor', {
	extend : 'Ext.data.Model',
	fields : ['id','date', 'publisher','templateName'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/downloadMonior',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

