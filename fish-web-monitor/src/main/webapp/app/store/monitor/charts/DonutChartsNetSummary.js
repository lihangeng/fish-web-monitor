
Ext.define('Eway.store.monitor.charts.DonutChartsNetSummary', {
	extend: 'Eway.store.base.Store',
	model : 'Eway.model.monitor.charts.DonutCharts',
    proxy: {
        type: 'ajax',
        url : 'api/monitor/summaryInfo/netSummary',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: true
});