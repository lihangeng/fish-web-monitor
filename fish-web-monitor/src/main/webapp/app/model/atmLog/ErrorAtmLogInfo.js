Ext.define('Eway.model.atmLog.ErrorAtmLogInfo',{
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
		url : 'api/machine/atmLog/getErrorDayBackup',
		reader : {
			type : 'json',
			rootProperty : 'data'/*,
			totalProerty : 'total'*/
		}
	}
});