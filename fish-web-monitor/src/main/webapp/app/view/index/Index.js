
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
		    }]
		});

		this.callParent(arguments);
	}
});