
Ext.define('Eway.view.index.Index', {
	alias: 'widget.appindex',
	extend: 'Ext.panel.Panel',

	requires: ['Eway.view.index.PieBasic','Eway.view.index.LineBasic'],
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
		    	xtype:'pieBasic'
		    },{
		    	xtype:'lineBasic',
		    	tools:[{
		    	    type:'refresh',
		    	    tooltip: 'Refresh',
		    	    handler: function(event, toolEl, panelHeader) {
		    	     	this.up('lineBasic').down('cartesian').getStore().load();
		    	    }
		    	}]
		    },{
		    	xtype:'pieBasic'
		    },{
		    	xtype:'lineBasic'
		    }],
		    listeners:{
		    	activate:function(_this,eOpt){
		    		_this.removeAll();
		    		var pieBasic = Ext.create("Eway.view.index.PieBasic");
		    		var pieBasic1 = Ext.create("Eway.view.index.PieBasic");
		    		var LineBasic = Ext.create("Eway.view.index.LineBasic");
		    		var LineBasic1 = Ext.create("Eway.view.index.LineBasic");
		    		_this.add(pieBasic);
		    		_this.add(LineBasic);
		    		_this.add(pieBasic1);
		    		_this.add(LineBasic1);
		    	}
		    }
		});

		this.callParent(arguments);
	}
});