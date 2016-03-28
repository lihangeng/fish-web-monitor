Ext.define('Eway.controller.parameter.classify.Classify', {
	extend : 'Eway.controller.base.FishController',

	stores : ['parameter.classify.Classify'],
	models : ['parameter.classify.Classify'],

	views : ['parameter.classify.View',
	            'parameter.classify.Form'],

	refs : [{
		ref : 'ewayView',
		selector : 'classify_View',
		autoCreate : true,
		xtype : 'classify_View'
	},{
		ref : 'classifyFilterForm',
		selector : 'classify_FilterForm'
	},{
		ref : 'classifyGrid',
		selector : 'classify_Grid'
	}],

	formConfig : {
		form : 'Eway.view.parameter.classify.Form',
		xtype : 'parameter_classify_form',
		width: 500,
		height: 280,
		title : EwayLocale.machine.param.paramClassify
	},

	init : function() {
		this.initBaseControl();
		this.control({
			'classify_View button[action=query]' : {
					click : this.onQuery
			},
			'classify_View button[action=add]' : {
					click : this.onAdd,
					scope : this
			},
			'classify_View button[action=update]' : {
					click : this.onUpdate,
					scope : this
			},
			'classify_View button[action=remove]' : {
					click : this.onRemove
			}
		});
	},
});