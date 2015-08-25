Ext.define('Eway.model.report.openrate.OrgOpenRate', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'terminalId', 'statDate', 'openTimes', 'healthyTimeReal',
			'unknownTimeReal', 'maintainTimeReal', 'faultTimeReal',
			'atmpTimeReal', 'stopTimeReal', {
				name : 'openRate'
			} ]
});