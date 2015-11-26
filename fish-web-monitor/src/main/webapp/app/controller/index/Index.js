
Ext.define('Eway.controller.index.Index', {
	extend: 'Ext.app.Controller',

	views: ['index.Index'],

	refs: [{
		ref: 'ewayView',
		selector: 'appindex',
		autoCreate: true,
		xtype: 'appindex'
	},{
		ref : 'versionDistributePie',
		selector : 'versionDistributePie'
	}],

	init: function() {
		this.control({

		});
	}
});