
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.FaultTrendByDay','Eway.view.index.VersionDistributePie',
	           'Eway.view.index.RetainCardByDay','Eway.view.index.StatusDonutCharts'],
	uses : [ 'Ext.XTemplate'],
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
		    	xtype:'statusDonutCharts'
		    },{
		    	xtype:'faultTrendByDay'
		    },{
		    	xtype:'versionDistributePie'
		    },{
		    	xtype:'retainCardByDay'
		    }],
		    listeners:{
		    	activate:function(_this,eOpt){
		    		_this.removeAll();
		    		var chart1 = Ext.create("Eway.view.index.VersionDistributePie");
		    		var chart2 = Ext.create("Eway.view.index.FaultTrendByDay");
		    		var chart3 = Ext.create("Eway.view.index.RetainCardByDay");
		    		var chart4 = Ext.create("Eway.view.index.StatusDonutCharts");
		    		_this.add(chart4);
		    		_this.add(chart2);
		    		_this.add(chart1);
		    		_this.add(chart3);
		    	}
		    }
		});

		this.callParent(arguments);
	}
});