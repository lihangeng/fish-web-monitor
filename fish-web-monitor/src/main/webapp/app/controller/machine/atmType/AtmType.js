
Ext.define('Eway.controller.machine.atmType.AtmType', {
	extend: 'Eway.controller.base.FishController',

	stores: ['machine.atmType.AtmType',
	         'machine.atmType.CashtypeComboBox',
	         'machine.atmType.DeviceAtmVendor',
	         'machine.atmType.DeviceAtmCatalog'],
	models: ['machine.atmType.AtmType',
	         'Dict'],

	views: ['Eway.view.machine.atmType.AtmTypeView'],

	refs: [{
		ref: 'ewayView',
		selector: '#atmTypeViewId',
		autoCreate: true,
		xtype: 'atmType_AtmTypeView',
		id: 'atmTypeViewId'
	},{
		ref : 'atmTypeFilterForm',
		selector : 'atmType_AtmTypeFilterForm'
	},{
		ref : 'atmTypeGrid',
		selector : 'atmType_AtmTypeGrid'
	},{
		ref : 'addWin',
		selector : 'atmType_add'
	},{
		ref : 'updateWin',
		selector : 'atmType_update'
	}],


	formConfig : {
		form : 'Eway.view.machine.atmType.Form',
		xtype : 'machine_atmType_form',
		height: 300,
		title : '设备型号信息'
	},

	init: function() {
		this.initBaseControl();
		this.control({
			'#atmTypeViewId button[action=query]': {
				click : this.onQuery
			},
			'#atmTypeViewId button[action=add]': {
				click : this.onAdd
			},
			'#atmTypeViewId button[action=update]' : {
				click : this.onUpdate
			},
			'#atmTypeViewId button[action=remove]' : {
				click : this.onRemove
			}
		});
	}


});