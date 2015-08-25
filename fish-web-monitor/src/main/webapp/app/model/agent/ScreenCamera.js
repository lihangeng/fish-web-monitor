Ext.define('Eway.model.agent.ScreenCamera', {
	extend : 'Ext.data.Model',
	fields : [ 'startCameraDate', 'stopCameraDate', 'monitorType',
			'filePathClient', 'fileNameClient', 'terminalId', 'status',
			'groupField', 'code', 'currUserId', 'userId' ],
	proxy : {
		type : 'rest',
		url : 'api/agent/screenshot/query',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});