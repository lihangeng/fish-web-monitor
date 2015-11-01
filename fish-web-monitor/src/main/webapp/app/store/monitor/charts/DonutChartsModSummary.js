
Ext.define('Eway.store.monitor.charts.DonutChartsModSummary', {
	extend: 'Eway.store.base.Store',
	model : 'Eway.model.monitor.charts.DonutCharts',
    proxy: {
        type: 'ajax',
        url : 'api/monitor/summaryInfo/modSummary',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: false
});