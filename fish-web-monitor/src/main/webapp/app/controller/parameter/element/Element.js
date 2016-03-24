Ext.define('Eway.controller.parameter.element.Element', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.element.Element'],
			models : ['parameter.element.Element'],

			views : ['parameter.element.View'],

			refs : [{
						ref : 'ewayView',
						selector : 'element_View',
						autoCreate : true,
						xtype : 'element_View'
					}, {
						ref : 'elementFilterForm',
						selector : 'element_FilterForm'
					}, {
						ref : 'elementGrid',
						selector : 'element_Grid'
					}
					, {
						ref : 'addWin',
						selector : 'element_add'
					}, {
						ref : 'updateWin',
						selector : 'element_update'
					}
					],

			formConfig : {
				form : 'Eway.view.parameter.element.Form',
				xtype : 'parameter_element_form',
				width: 500,
				height:280,
				title : EwayLocale.machine.atmBrand.title
			},

			init : function() {
				this.initBaseControl();
				this.control({
							'element_View button[action=query]' : {
								click : this.onQuery
							},
							'element_View button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'element_View button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'element_View button[action=remove]' : {
								click : this.onRemove
							}
						});
			},

});