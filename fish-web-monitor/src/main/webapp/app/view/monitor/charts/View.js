
Ext.define('Eway.view.monitor.charts.View', {
	alias: 'widget.monitor_view',
	extend: 'Ext.panel.Panel',
	requires: ['Eway.view.monitor.charts.DonutCharts','Eway.view.monitor.charts.MonitorDeviceGrid'],
	layout: {
        type: 'table',
        columns: 2
    },
    
    scrollable : 'y',
    bodyStyle : 'padding: 10px 10px 0px 10px',
 
    
	initComponent: function() {
		var me = this;
		Ext.apply(this, {
		    title: Eway.locale.monitor.summary.title,//'监控总览',
		    defaults: { 
		    	frame: true,
		    	width: 550,
		    	margin: 10,
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            tools:[{
	                type:'refresh',
	                tooltip: 'Refresh form Data',
	                handler: function(event, toolEl, panelHeader) {
	                	
	                	Ext.Array.forEach(me.items.items,function(item,index,allItems){
	                		if(item.down("polar")!=undefined)
	                		item.down("polar").getStore().load({
                			 	scope: this,
                			    callback: function(records, operation, success) {
                			    }
	                		});
	                	},this);
	                }
	            }]
		    },
		    items:[{
		    	title:Eway.locale.monitor.summary.allSummary,//'概况',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsSummary")
		    },{
		    	title:Eway.locale.monitor.summary.appSummary,//'Run概况',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsRunSummary")
		    },{
		    	xtype : 'monitor_device_grid',
		    	tools:[{
	                type:'maximize',
	                handler: function(event, toolEl, panelHeader) {
	                	var grid = this.up("monitor_device_grid");
	                	if(grid.getStore().data.length==0){
	                		Eway.alert("无数据");
	                		return;
	                	}
                		var win = Ext.create('Eway.view.monitor.charts.MonitorDeviceDetailWindow', {
                			title:grid.getTitle()
                		});
                		win.show();
                		win.down("monitor_device_grid").getStore().load();
	                }
	            }]
		    },{
		    	title:Eway.locale.monitor.summary.modSummary,//'Mod概况',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsModSummary")
		    },{
		    	title:Eway.locale.monitor.summary.boxSummary,//'Box概况',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsBoxSummary")
		    },{
		    	title:Eway.locale.monitor.summary.netSummary,//'网络概况',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsNetSummary")
		    }]

		});

		this.callParent(arguments);
	}
});