Ext.define('Eway.model.monitor.device.DeviceMonitorMapOrg',{

	extend : 'Ext.data.Model',
	fields : [
		{name : 'latitude'},
		{name : 'longtitude'},
		{name : 'zoom'},
		{name : 'id'},
		{name : 'address'},
		{name : 'code'},
		{name : 'description'},
		{name : 'name'},
		{name : 'orgFlag'},
		{name : 'orgType'},
		{name : 'state'}
	],
	proxy : {
		type : 'rest',
		url : 'api/monitor/device/mapviewOrg',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProerty : 'total'
		}
	}
});