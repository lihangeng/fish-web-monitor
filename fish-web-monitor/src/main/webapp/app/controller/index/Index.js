
Ext.define('Eway.controller.index.Index', {
	extend: 'Ext.app.Controller',

	views: ['index.Index','monitor.charts.View'],

	refs: [{
		ref: 'ewayView',
		selector: 'appindex',
		autoCreate: true,
		xtype: 'appindex'
	}],

	init: function() {
		this.control({});
	}
});