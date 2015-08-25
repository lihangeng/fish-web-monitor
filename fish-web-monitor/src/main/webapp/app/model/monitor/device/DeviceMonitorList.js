Ext.define('Eway.model.monitor.device.DeviceMonitorList',{

	extend : 'Ext.data.Model',
	fields : [
		{name : 'id'},
		{name : 'code'},
		{name : 'org'},
		{name : 'type'},
		{name : 'seviceMode'},
		{name : 'insideOutside'},
		{name : 'ip'},
		{name : 'address'},
		{name : 'appRelease'},
		{name : 'runStatus'},
		{name : 'run'},
		{name : 'boxInitCount'},
		{name : 'modStatus'},
		{name : 'mod'},
		{name : 'boxCurrentCount'},
		{name : 'cashboxLimit'},
		{name : 'boxStatus'},
		{name : 'box'},
		{name : 'retainCardCount'},
		{name : 'netStatus'},
		{name : 'net'},
		{name : 'registerStatus'},
		{name : 'idcStatus'},
		{name : 'cimStatus'},
		{name : 'cdmStatus'},
		{name : 'rprStatus'},
		{name : 'jprStatus'},
		{name : 'pinStatus'},
		{name : 'siuStatus'},
		{name : 'ttuStatus'},
		{name : 'iscStatus'},
		{name : 'iccStatus'},
		{name : 'fgpStatus'}
	],

	proxy : {
		type : 'rest',
		url : 'api/monitor/device/matrixData',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProerty : 'total'
		}
	}
});