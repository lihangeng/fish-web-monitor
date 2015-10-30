
Ext.define('Eway.store.monitor.charts.DonutChartsBoxSummary', {
	extend: 'Eway.store.base.Store',
	model : 'Eway.model.monitor.charts.DonutCharts',
    proxy: {
        type: 'ajax',
        url : 'api/monitor/summaryInfo/boxSummary',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: false
});