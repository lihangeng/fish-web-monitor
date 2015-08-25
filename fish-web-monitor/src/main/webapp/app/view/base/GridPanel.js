Ext.define('Eway.view.base.GridPanel',{

	extend : 'Ext.grid.Panel',

	constructor : function(config){
		Eway.view.base.GridPanel.superclass.constructor.call(this,Ext.apply({
			 // paging bar on the bottom
	        bbar: Ext.create('Ext.PagingToolbar', {
	            store: config.store,
	            displayInfo: true
	        })
		},config));
	}
});