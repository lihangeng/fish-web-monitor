Ext.define('Eway.controller.system.register.Register', {
	extend : 'Ext.app.Controller',
	views : [ 'Eway.view.system.register.RegisterView' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#system_RegisterView ',
		autoCreate : true,
		xtype : 'system_RegisterView'
	}, {
		ref : 'displayForm',
		selector : 'register_form'
	} ],

	init : function() {
		var me = this;
	}

});