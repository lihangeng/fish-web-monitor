Ext.define('Eway.model.monitor.charts.DonutChartsDetail', {
	extend : 'Ext.data.Model',
	
	fields : [ 'id', 'code', 'org', 'type', 'seviceMode', 'insideOutside',
	           'modStatus', 'boxCurrentCount', 'cashboxLimit', 'boxStatus', 'retainCardCount', 'netStatus',
			'ip', 'address', 'runStatus', 'boxInitCount' ],
	proxy : {
		type : 'ajax',
		url : 'api/monitor/summaryInfo/detail',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});
