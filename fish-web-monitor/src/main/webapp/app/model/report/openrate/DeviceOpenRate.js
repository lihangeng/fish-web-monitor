Ext.define('Eway.model.report.openrate.DeviceOpenRate', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'terminalId', 'statDate', 'openTimes', 'healthyTimeReal',
			'unknownTimeReal', 'maintainTimeReal', 'faultTimeReal',
			'atmpTimeReal', 'stopTimeReal', 'programOpenTime',
			'programCloseTime', 'programTimes','orgName','devCatalogName','programTimeReal', {
				name : 'programOpenRate'
			}, {
				name : 'openRate'
			} ],
	proxy : {
		type : 'rest',
		url : 'api/report/openrate/deviceOpenRate',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});