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
//					, {
//						ref : 'addWin',
//						selector : 'atmBrand_add'
//					}, {
//						ref : 'updateWin',
//						selector : 'atmBrand_update'
//					}
					],

//			formConfig : {
//				form : 'Eway.view.machine.atmBrand.Form',
//				xtype : 'machine_atmBrand_form',
//				width: 500,
//				height:280,
//				title : EwayLocale.machine.atmBrand.title
//			},

//			init : function() {
//				this.initBaseControl();
//				this.control({
//							'atmBrand_AtmBrandView button[action=query]' : {
//								click : this.onQuery
//							},
//							'atmBrand_AtmBrandView button[action=add]' : {
//								click : this.onAdd,
//								scope : this
//							},
//							'atmBrand_AtmBrandView button[action=update]' : {
//								click : this.onUpdate,
//								scope : this
//							},
//							'atmBrand_AtmBrandView button[action=remove]' : {
//								click : this.onRemove
//							}
//						});
//			}
});