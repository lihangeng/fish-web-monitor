
Ext.define('Eway.controller.machine.atmModule.AtmModule', {
	extend: 'Eway.controller.base.FishController',
	
	stores: ['machine.atmModule.AtmModule'],
	models: ['machine.atmModule.AtmModule'],
	
	views: ['Eway.view.machine.atmModule.AtmModuleView'],
	
	refs: [{
		ref: 'ewayView',
		selector: 'atmModule_AtmModuleView',
		autoCreate: true,
		xtype: 'atmModule_AtmModuleView',
		id: 'AtmModuleViewId'
	},{
		ref : 'atmModuleFilterForm',
		selector : 'atmModule_AtmModuleFilterForm'
	},{
		ref : 'atmModuleGrid',
		selector : 'atmModule_AtmModuleGrid'
	}],
	init: function() {
		this.control({
			'atmModule_AtmModuleGrid button[action=query]': {
				click : this.onQuery
			}
		});
	}
	

});