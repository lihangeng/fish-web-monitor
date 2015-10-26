
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.PieBasic','Eway.view.index.FaultTrendByDay'
	           ,'Eway.view.index.VersionDistributePie'],
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
		    	xtype:'faultTrendByDay',
		    	tools:[{
		    	    type:'refresh',
		    	    tooltip: 'Refresh',
		    	    handler: function(event, toolEl, panelHeader) {
		    	     	this.up('faultTrendByDay').down('cartesian').getStore().load();
		    	    }
		    	}]
		    },{
		    	xtype:'pieBasic'
		    },{
		    	xtype:'faultTrendByDay'
		    }]
		});

		this.callParent(arguments);
	}
});