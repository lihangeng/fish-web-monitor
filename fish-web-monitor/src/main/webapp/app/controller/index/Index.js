
Ext.define('Eway.controller.index.Index', {
	extend: 'Ext.app.Controller',

	views: ['index.Index','monitor.charts.View'],

	refs: [{
		ref: 'ewayView',
		selector: 'appindex',
		autoCreate: true,
		xtype: 'appindex'
	}
	/*{
		ref: 'ewayView',
		selector: 'appindex',
		autoCreate: true,
		xtype: 'monitor_view'
	}*/],

	init: function() {
		this.control({});
	}
});