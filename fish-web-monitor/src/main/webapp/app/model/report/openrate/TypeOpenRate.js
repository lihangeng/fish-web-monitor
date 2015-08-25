Ext.define('Eway.model.report.openrate.TypeOpenRate', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'terminalId', 'statDate', 'openTimes', 'healthyTimeReal',
			'unknownTimeReal', 'maintainTimeReal', 'faultTimeReal',
			'atmpTimeReal', 'stopTimeReal', 'programOpenTime',
			'programCloseTime', 'programTimes', 'programTimeReal', {
				name : 'programOpenRate'
			}, {
				name : 'openRate'
			} ],
	proxy : {
		type : 'rest',
		url : 'api/report/openrate/typeOpenRate',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});