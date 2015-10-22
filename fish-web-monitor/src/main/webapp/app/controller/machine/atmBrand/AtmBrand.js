Ext.define('Eway.controller.machine.atmBrand.AtmBrand', {
			extend : 'Eway.controller.base.FishController',

			stores : ['machine.atmBrand.AtmBrand',
					'machine.atmBrand.StatusComboBox'],
			models : ['machine.atmBrand.AtmBrand', 'Dict'],

			views : ['Eway.view.machine.atmBrand.AtmBrandView'],

			refs : [{
						ref : 'ewayView',
						selector : 'atmBrand_AtmBrandView',
						autoCreate : true,
						xtype : 'atmBrand_AtmBrandView'
					}, {
						ref : 'atmBrandFilterForm',
						selector : 'atmBrand_AtmBrandFilterForm'
					}, {
						ref : 'atmBrandGrid',
						selector : 'atmBrand_AtmBrandGrid'
					}, {
						ref : 'addWin',
						selector : 'atmBrand_add'
					}, {
						ref : 'updateWin',
						selector : 'atmBrand_update'
					}],

			formConfig : {
				form : 'Eway.view.machine.atmBrand.Form',
				xtype : 'machine_atmBrand_form',
				width: 500,
				height:280,
				title : Eway.locale.machine.atmBrand.devBrandInfo
			},

			init : function() {
				this.initBaseControl();
				this.control({
							'atmBrand_AtmBrandView button[action=query]' : {
								click : this.onQuery
							},
							'atmBrand_AtmBrandView button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'atmBrand_AtmBrandView button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'atmBrand_AtmBrandView button[action=remove]' : {
								click : this.onRemove
							}
						});
			}
});