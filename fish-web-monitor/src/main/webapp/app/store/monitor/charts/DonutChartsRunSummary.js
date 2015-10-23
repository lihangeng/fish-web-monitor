
Ext.define('Eway.store.monitor.charts.DonutChartsRunSummary', {
	extend: 'Eway.store.base.Store',
	model : 'Eway.model.monitor.charts.DonutCharts',
    proxy: {
        type: 'ajax',
        url : 'api/monitor/summaryInfo/runSummary',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: true
});