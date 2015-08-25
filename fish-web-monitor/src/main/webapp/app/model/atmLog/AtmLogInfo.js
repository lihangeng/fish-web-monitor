Ext.define('Eway.model.atmLog.AtmLogInfo',{
	extend : 'Ext.data.Model',
	fields : [
		'orgId',
		'orgName',
		'backupDate',
		'backupSuccessNumber',
		'backupErrorNumber',
		'totalBackupNumber'
	],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmLogInfo',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProerty : 'total'
		}
	}
});