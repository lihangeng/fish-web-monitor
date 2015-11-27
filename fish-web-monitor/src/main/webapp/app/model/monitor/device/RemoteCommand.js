Ext.define('Eway.model.monitor.device.RemoteCommand', {
	extend : 'Ext.data.Model',
	
	fields : [ 'id', 'datetime', 'terminalId', 'commandType', 'commandResult', 'handlePerson', 'orgName' ],
	
	proxy : {
		type : 'rest',
		url : 'api/monitor/remoteCommand',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});