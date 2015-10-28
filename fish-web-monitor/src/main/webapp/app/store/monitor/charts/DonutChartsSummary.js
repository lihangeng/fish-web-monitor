
Ext.define('Eway.store.monitor.charts.DonutChartsSummary', {
	extend: 'Eway.store.base.Store',
	model : 'Eway.model.monitor.charts.DonutCharts',
    proxy: {
        type: 'ajax',
        url : 'api/monitor/summaryInfo/summary',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: false
});