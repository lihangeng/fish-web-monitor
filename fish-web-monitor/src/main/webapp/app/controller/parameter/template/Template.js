Ext.define('Eway.controller.parameter.template.Template', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.template.Template'],
			models : ['parameter.template.Template'],

			views : ['parameter.template.View'],

			refs : [{
						ref : 'ewayView',
						selector : 'template_View',
						autoCreate : true,
						xtype : 'template_View'
					}, {
						ref : 'templateFilterForm',
						selector : 'template_FilterForm'
					}, {
						ref : 'templateGrid',
						selector : 'template_Grid'
					}
					],


			init : function() {
				this.initBaseControl();
				this.control({
							'template_View button[action=query]' : {
								click : this.onQuery
							},
							'template_View button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'template_View button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'template_View button[action=remove]' : {
								click : this.onRemove
							}
						});
			}
});