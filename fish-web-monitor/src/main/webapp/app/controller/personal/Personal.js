
Ext.define('Eway.controller.personal.Personal', {
	extend: 'Eway.controller.base.Controller',

	stores: [],

	models: [],

	views: ['personal.PersonalSettingsModel','personal.PersonalSettings',
            'personal.UpdatePwd','personal.PersonalInfo'],

	refs: [{
		ref: 'ewayView',
		selector: '#personalSettings',
		autoCreate: true,
		xtype: 'personalSettings',
		id: 'personalSettings'
	}],

	init: function() {
		this.control({
			
		});
	}

	
});