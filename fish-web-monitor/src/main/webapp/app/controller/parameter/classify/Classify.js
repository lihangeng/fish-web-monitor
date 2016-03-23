Ext.define('Eway.controller.parameter.classify.Classify', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.classify.Classify'],
			models : ['parameter.classify.Classify'],

			views : ['parameter.classify.View',
			            'parameter.classify.FilterForm',
			            'parameter.classify.Grid'],

			refs : [{
						ref : 'ewayView',
						selector : 'classify_View',
						autoCreate : true,
						xtype : 'classify_View'
					}, {
						ref : 'classifyFilterForm',
						selector : 'classify_FilterForm'
					}, {
						ref : 'classifyGrid',
						selector : 'classify_Grid'
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