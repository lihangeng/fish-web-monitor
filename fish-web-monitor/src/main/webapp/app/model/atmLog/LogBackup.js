Ext.define('Eway.model.atmLog.LogBackup',{
	extend : 'Ext.data.Model',
	fields : [
		'id',
		'terminalId',
		'dateTime',
		'lastDoDate',
		'size',
		'backupResult'
	],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmLog/getBackup',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProerty : 'total'
		}
	}
});