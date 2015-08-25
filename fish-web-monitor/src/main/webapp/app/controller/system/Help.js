Ext.define('Eway.controller.system.Help',{
	extend : 'Ext.app.Controller',
	
	stores : [],
	models : [],
	views : [
		'system.HelpView'
	],
	
	refs : [{
		ref : 'ewayView',
		selector : '#system_helpView',
		autoCreate : true,
		xtype : 'system_helpView'
	}],
	
	init : function(){
		
	}
	
});