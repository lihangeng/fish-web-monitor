
Ext.define('Eway.controller.machine.atmCatalog.AtmCatalog', {
	extend: 'Eway.controller.base.FishController',

	stores: ['machine.atmCatalog.AtmCatalog'],
	models: ['machine.atmCatalog.AtmCatalog'],

	views: ['Eway.view.machine.atmCatalog.AtmCatalogView'],

	refs: [{
		ref: 'ewayView',
		selector: '#atmCatalogViewId',
		autoCreate: true,
		xtype: 'atmCatalog_AtmCatalogView',
		id: 'atmCatalogViewId'
	},{
		ref : 'atmCatalogFilterForm',
		selector : 'atmCatalog_AtmCatalogFilterForm'
	},{
		ref : 'atmCatalogGrid',
		selector : 'atmCatalog_AtmCatalogGrid'
	},{
		ref : 'addWin',
		selector : 'atmCatalog_add'
	},{
		ref : 'updateWin',
		selector : 'atmCatalog_update'
	}],

	addView : 'Eway.view.machine.atmCatalog.Add',
	updateView : 'Eway.view.machine.atmCatalog.Update',

	init: function() {
		this.control({
			'#atmCatalogViewId button[action=query]': {
				click : this.onQuery
			},
			'#atmCatalogViewId button[action=add]': {
				click : this.onAdd
			},
			'#atmCatalogViewId button[action=update]' : {
				click : this.onUpdate
			},
			'#atmCatalogViewId button[action=remove]' : {
				click : this.onRemove
			}
		});
	},

	onAdd : function() {
		var win = Ext.create('Eway.view.machine.atmCatalog.Add');
		var no = win.down('form').getForm().findField('no');
		this.win = win;
		win.down('button[action="confirm"]').on('click',
				this.onAddConfirm, this);
		win.down('field[name="no"]').on('blur',
				Ext.bind(this.onCheckCatalogNoUnique, this, [0, no]),
				this);
		win.show();
	},

	onAddConfirm : function() {
		var form = this.getAddWin().down('form');
		data = this.getAddWin().down('form').getForm().getValues();
		var bool = this.getAddWin().down('form').getForm().isValid();
		if (bool == true) {
			this.addRecord(data);
			this.getAddWin().close();
		} else {
			Eway.alert(Eway.locale.tip.inputError);
		}
	},

	addRecord : function(data) {
		var record = Ext.create('Eway.model.machine.atmCatalog.AtmCatalog',data);
		this.getAtmCatalogGrid().getStore().add(record);
	},

	onUpdate: function() {
		var grid = this.getAtmCatalogGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create(this.updateView);
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			var button = win.query('button[action=confirm]')[0];
			button.on('click', this.onUpdateConfirm, this);
			win.down('field[name="no"]').on(
					'blur',
					Ext.bind(this.onCheckCatalogNoUnique, this, [
									record.getId(),
									this.getUpdateFormFiled('no')]),
					this);
			win.show();
		}
		else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},


	onUpdateConfirm: function() {
		var sm = this.getAtmCatalogGrid().getSelectionModel();
		var record = sm.getLastSelected();
		this.getUpdateWin().down('form').getForm().updateRecord(record);
		this.getUpdateWin().close();
	},

	onCheckCatalogNoUnique : function(id, no) {
		Ext.Ajax.request({
					method : 'GET',
					url : 'api/machine/atmCatalog/unique',
					params : {
						id : id,
						no : no.value
					},
					success : function(response) {
						var object = Ext.decode(response.responseText);
						if (object.success == true) {
							no.markInvalid(Eway.locale.tip.alreadyExist);
						}
					}
				});
	},

	onRemove: function() {
		var grid = this.getAtmCatalogGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(Eway.locale.tip.remove.confirm.title,
					Eway.locale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							this.removeRecord(record);
						}
					}, this);
		}
		else {
			Eway.alert(Eway.choiceDeleteMsg);
		}
	},

	removeRecord : function(record) {
		this.getAtmCatalogGrid().getStore().remove(record);
	},

//	onQuery : function(){
//		var form = this.getAtmCatalogFilterForm().getForm();
//		var values = form.getValues();
//		var store = this.getAtmCatalogGrid().getStore();
//		store.load({
//			start : 0,
//			params : values
//		});
//	},

	getUpdateFormFiled : function(name) {
		return this.getUpdateForm().findField(name);
	},

	getUpdateForm : function() {
		return this.getUpdateWin().down("form").getForm();
	}


});