Ext.define('Eway.model.parameter.paramMonitor.JobMonitor', {
	extend : 'Ext.data.Model',
	fields : ['id','date', 'publisher'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/downloadMonior',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

