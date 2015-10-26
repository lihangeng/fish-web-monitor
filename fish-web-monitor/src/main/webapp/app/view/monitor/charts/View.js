
Ext.define('Eway.view.monitor.charts.View', {
	alias: 'widget.monitor_view',
	extend: 'Ext.panel.Panel',
	requires: ['Eway.view.monitor.charts.DonutCharts'],
	layout: {
        type: 'table',
        columns: 2
    },
    
    scrollable : 'y',
    bodyStyle : 'padding: 10px 10px 0px 10px',
    defaults: {
    	frame: true,
    	width: 550,
    	margin: 10
    },
    
	initComponent: function() {
		Ext.apply(this, {
		    title: '监控总览',
		    items:[{
		    	title:'概况',
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsSummary")
		    },{
		    	title:'Run概况',
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsRunSummary")
		    },{
		    	title:'Mod概况',
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsModSummary")
		    },{
		    	title:'Box概况',
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsBoxSummary")
		    },{
		    	title:'网络概况',
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsNetSummary")
		    }]

		});

		this.callParent(arguments);
	}
});