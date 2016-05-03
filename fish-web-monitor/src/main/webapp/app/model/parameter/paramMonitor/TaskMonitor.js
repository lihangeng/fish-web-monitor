Ext.define('Eway.model.parameter.paramMonitor.TaskMonitor', {
	extend : 'Ext.data.Model',
	fields : ['id','deviceId','terminalId', 'versionNo','downloadStartTime','downloadFinishTime','success','reason','taskStatus'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/downloadMonior/task',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});

