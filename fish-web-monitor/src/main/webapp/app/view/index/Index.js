
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.FaultTrendByDay','Eway.view.index.VersionDistributePie',
	           'Eway.view.index.RetainCardByDay'],
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
    	margin: 10/*,
    	collapsible :true*/
    },
    
	initComponent: function() {
		Ext.apply(this, {
		    title: Eway.locale.index.indexPage,
		    items:[{
		    	xtype:'versionDistributePie'
		    },{
		    	xtype:'faultTrendByDay'
		    },{
		    	xtype:'retainCardByDay'
		    },{
		    	xtype:'faultTrendByDay'
		    }],
		    listeners:{
		    	activate:function(_this,eOpt){
		    		_this.removeAll();
		    		var chart1 = Ext.create("Eway.view.index.VersionDistributePie");
		    		var chart2 = Ext.create("Eway.view.index.FaultTrendByDay");
		    		var chart3 = Ext.create("Eway.view.index.RetainCardByDay");
		    		var chart4 = Ext.create("Eway.view.index.FaultTrendByDay");
		    		_this.add(chart1);
		    		_this.add(chart2);
		    		_this.add(chart3);
		    		_this.add(chart4);
		    	}
		    }
		});

		this.callParent(arguments);
	}
});