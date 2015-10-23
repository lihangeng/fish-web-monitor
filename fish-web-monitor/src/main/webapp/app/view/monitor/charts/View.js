
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
		    title: Eway.locale.index.indexPage,
		    items:[{
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsNetSummary")
		    },{
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsModSummary")
		    },{
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsBoxSummary")
		    },{
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsRunSummary")
		    },{
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsSummary")
		    }]
//		,
//		    listeners:{
//		    	activate:function(_this,eOpt){
//		    		_this.removeAll();
//		    		var pieBasic = Ext.create("Eway.view.monitor.charts.DonutCharts",{angleField:'displayName',labelField:'numberInfo'});
//		    		var pieBasic1 = Ext.create("Eway.view.index.PieBasic");
//		    		var LineBasic = Ext.create("Eway.view.index.LineBasic");
//		    		var LineBasic1 = Ext.create("Eway.view.index.LineBasic");
//		    		_this.add(pieBasic);
//		    		_this.add(LineBasic);
//		    		_this.add(pieBasic1);
//		    		_this.add(LineBasic1);
//		    	}
//		    }
		});

		this.callParent(arguments);
	}
});