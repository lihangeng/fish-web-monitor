
Ext.define('Eway.view.monitor.charts.View', {
	alias: 'widget.monitor_view',
	extend: 'Ext.panel.Panel',
	requires: ['Eway.view.monitor.charts.DonutCharts',
				'Ext.ux.layout.ResponsiveColumn'],
	layout: 'responsivecolumn',
    scrollable : 'y',
    closable:true,
    
	initComponent: function() {
		var me = this;
		Ext.apply(this, {
		    title: EwayLocale.monitor.summary.title,//'监控总览',
			refresh:function(){
				var _this = this;
	    		var item1 = _this.getComponent("DonutChartsNetSummary");
	    		item1.down("polar").getStore().load({
    			 	scope: this,
    			    callback: function(records, operation, success) {
    			    	if(success){
    			    		var dount = this.getComponent("DonutChartsNetSummary");
    			    		var colors = new Array();
    			    		Ext.Array.forEach(records,function(item,index,allItems){
    			    			colors.push(item.data.color);
//    			    			dount.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
    			    		},this);
    			    		dount.down("polar series[type='pie']").setColors(colors);
    			    	}
    			    }
        		});
	    		var item2 = _this.getComponent("DonutChartsSummary");
	    		item2.down("polar").getStore().load({
    			 	scope: this,
    			    callback: function(records, operation, success) {
    			    	if(success){
    			    		var dount = this.getComponent("DonutChartsSummary");
    			    		var colors = new Array();
    			    		Ext.Array.forEach(records,function(item,index,allItems){
    			    			colors.push(item.data.color);	
//    			    			dount.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
    			    		},this);
    			    		dount.down("polar series[type='pie']").setColors(colors);
    			    	}
    			    }
        		});
	    		var item3 = _this.getComponent("DonutChartsModSummary");
	    		item3.down("polar").getStore().load({
    			 	scope: this,
    			    callback: function(records, operation, success) {
    			    	if(success){
    			    		var dount = this.getComponent("DonutChartsModSummary");
    			    		var colors = new Array();
    			    		Ext.Array.forEach(records,function(item,index,allItems){
    			    			colors.push(item.data.color);	
//    			    			dount.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
    			    		},this);
    			    		dount.down("polar series[type='pie']").setColors(colors);
    			    	}
    			    }
        		});
	    		var item4 = _this.getComponent("DonutChartsRunSummary");
	    		item4.down("polar").getStore().load({
    			 	scope: this,
    			    callback: function(records, operation, success) {
    			    	if(success){
    			    		var dount = this.getComponent("DonutChartsRunSummary");
    			    		var colors = new Array();
    			    		Ext.Array.forEach(records,function(item,index,allItems){
    			    			colors.push(item.data.color);
//    			    			dount.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
    			    		},this);
    			    		dount.down("polar series[type='pie']").setColors(colors);
    			    	}
    			    }
        		});
	    		var item5 = _this.getComponent("DonutChartsBoxSummary");
	    		item5.down("polar").getStore().load({
    			 	scope: this,
    			    callback: function(records, operation, success) {
    			    	if(success){
    			    		var dount = this.getComponent("DonutChartsBoxSummary");
    			    		var colors = new Array();
    			    		Ext.Array.forEach(records,function(item,index,allItems){
    			    			colors.push(item.data.color);
//    			    			dount.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
    			    		},this);
    			    		dount.down("polar series[type='pie']").setColors(colors);
    			    	}
    			    }
        		});
	    		
	    	
			},
		    defaults: { 
		    	frame: true,
		    	xtype:'pie-donut',
	            angleField:'displayName',
	            labelField:'numberInfo',
	            tools:[{
	                type:'refresh',
	                tooltip: EwayLocale.button.refresh,
	                marginLeft: 10,
	                handler: function(event, toolEl, panelHeader) {
	                	me.refresh();
	                }
	            }]
		    },
		    items:[{
		    	title:EwayLocale.monitor.summary.allSummary,//'概况',
		    	itemId:'DonutChartsSummary',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsSummary"),
		    	responsiveCls: 'big-50 small-100'
		    },{
		    	title:EwayLocale.monitor.summary.appSummary,//'Run概况',
		    	itemId:'DonutChartsRunSummary',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsRunSummary"),
		    	responsiveCls: 'big-50 small-100'
//		    },{
//		    	xtype : 'monitor_device_grid',
//		    	tools:[{
//	                type:'maximize',
//	                handler: function(event, toolEl, panelHeader) {
//	                	var grid = this.up("monitor_device_grid");
//	                	if(grid.getStore().data.length==0){
//	                		Eway.alert("无数据");
//	                		return;
//	                	}
//                		var win = Ext.create('Eway.view.monitor.charts.MonitorDeviceDetailWindow', {
//                			title:grid.getTitle()
//                		});
//                		win.show();
//                		win.down("monitor_device_grid").getStore().load();
//	                }
//	            }]
		    },{
		    	title:EwayLocale.monitor.summary.modSummary,//'Mod概况',
		    	itemId:'DonutChartsModSummary',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsModSummary"),
		    	responsiveCls: 'big-33 small-100'
		    },{
		    	title:EwayLocale.monitor.summary.boxSummary,//'Box概况',
		    	itemId:'DonutChartsBoxSummary',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsBoxSummary"),
		    	responsiveCls: 'big-33 small-100'
		    },{
		    	title:EwayLocale.monitor.summary.netSummary,//'网络概况',
		    	itemId:'DonutChartsNetSummary',
	            stores:Ext.create("Eway.store.monitor.charts.DonutChartsNetSummary"),
		    	responsiveCls: 'big-33 small-100'
		    }],
		    listeners:{
		    	beforeactivate:function( _this, eOpts ){
		    		me.refresh();
		    	}
		    }

		});

		this.callParent(arguments);
	}
});