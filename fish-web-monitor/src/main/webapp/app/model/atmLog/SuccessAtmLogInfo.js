Ext.define('Eway.model.atmLog.SuccessAtmLogInfo',{
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
		url : 'api/machine/atmLog/getSuccessDayBackup',
		reader : {
			type : 'json',
			rootProperty : 'data'/*,
			totalProerty : 'total'*/
		}
	}
});