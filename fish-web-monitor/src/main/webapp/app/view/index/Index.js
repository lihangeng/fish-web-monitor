
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.FaultTrendByDay','Eway.view.index.VersionDistributePie',
	           'Eway.view.index.RetainCardByDay','Eway.view.index.StatusDonutCharts',
	           'Ext.ux.layout.ResponsiveColumn'],
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
		    		var chart1 = Ext.create("Eway.view.index.VersionDistributePie",{
		    			responsiveCls: 'big-50 small-100'
		    		});
		    		var chart2 = Ext.create("Eway.view.index.FaultTrendByDay",{
		    			responsiveCls: 'big-50 small-100'
		    		});
		    		var chart3 = Ext.create("Eway.view.index.RetainCardByDay",{
		    			responsiveCls: 'big-50 small-100'
		    		});
		    		var chart4 = Ext.create("Eway.view.index.StatusDonutCharts",{
		    			responsiveCls: 'big-50 small-100'
		    		});
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