Ext.define('Eway.controller.system.AboutSystem',{
	extend : 'Ext.app.Controller',
	views : [
		'system.AboutSystemView'
	],
	refs : [{
		ref : 'ewayView',
		selector : '#system_aboutSystemView',
		autoCreate : true,
		xtype : 'system_aboutSystemView'
	}]

});