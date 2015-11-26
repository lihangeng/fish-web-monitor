Ext.define('Eway.model.atmLog.DayBackup',{
	extend : 'Ext.data.Model',
	fields : [
		'date',
		'result',
		'doTime',
		'endTime',
		'deviceSucCount',
		'deviceFailCount'
	],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmLogInfo/getDayBackup',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProerty : 'total'
		}
	}
});