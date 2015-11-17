
Ext.define('Eway.controller.machine.atmType.AtmType', {
	extend: 'Eway.controller.base.FishController',

	stores: ['machine.atmType.AtmType',
	         'machine.atmType.CashtypeComboBox',
	         'machine.atmType.DeviceAtmVendor',
	         'machine.atmType.DeviceAtmCatalog'],
	models: ['machine.atmType.AtmType',
	         'Dict'],

	views: ['Eway.view.machine.atmType.AtmTypeView',
	        'machine.atmType.Form'],

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
		width:550,
		title : EwayLocale.machine.device.devTypeInfo
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
	},
	//在打开增加页面之前
	beforeShowAddWin: function(win,grid){
		var checkGroup = win.down('form').down("checkboxgroup");
		checkGroup.getLoader().load({
			params:{
				atmTypeId : 0
			}
		});
	},
	//在增加之前
	beforeAddSave: function(win,grid){
		var addForm = win.down('form').getForm();
		data = addForm.getValues();
		var atmModules = data.atmModules;
		if(atmModules && !Ext.isArray(atmModules)){
			data.atmModules = [atmModules];
		}
		return data;
	},
	//在打开修改页面之前
	boforeShowUpdateWin : function(updateWin,grid,record){
		var checkGroup = updateWin.down('form').down("checkboxgroup");
		checkGroup.getLoader().load({
			params:{
				atmTypeId : record.getId()
			}
		});
	},
	//在修改之前
	beforeUpdateSave : function(win,grid,record){
		var values = win.down('form').getForm().getValues();
		var atmTypes = values.atmTypes;
		if(atmTypes && !Ext.isArray(atmModules)){
			values.atmModules = [atmModules];
		}
		record.data.atmModules = values.atmModules;
	}


});