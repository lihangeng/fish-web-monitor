
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.FaultTrendByDay','Eway.view.index.VersionDistributePie',
	           'Eway.view.index.RetainCardByDay','Eway.view.index.StatusDonutCharts',
	           'Ext.ux.layout.ResponsiveColumn'],
	uses : [ 'Ext.XTemplate'],
	layout: 'responsivecolumn',
    scrollable : 'y',
    defaults: {
    	frame: true
    },
    
	initComponent: function() {
		Ext.apply(this, {
		    title: EwayLocale.index.indexPage,
		    items:[{
		    	xtype:'statusDonutCharts',
		    	responsiveCls: 'big-50 small-100'
		    },{
		    	xtype:'faultTrendByDay',
		    	responsiveCls: 'big-50 small-100'
		    },{
		    	xtype:'versionDistributePie',
		    	responsiveCls: 'big-50 small-100'
		    },{
		    	xtype:'retainCardByDay',
		    	responsiveCls: 'big-50 small-100'
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