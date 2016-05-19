Ext.define('Eway.model.parameter.paramMonitor.TaskStatus', {
	extend : 'Ext.data.Model',
	fields : ['appSystem','reason','status'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/downloadMonitor/task/status',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

