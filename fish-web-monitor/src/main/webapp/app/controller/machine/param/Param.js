Ext.define('Eway.controller.machine.param.Param', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.param.Param' ],
	models : [ 'machine.param.Param' ],

	views : [ 'Eway.view.machine.param.ParamView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'param_ParamView',
		autoCreate : true,
		xtype : 'param_ParamView'
	}, {
		ref : 'paramFilterForm',
		selector : 'param_ParamFilterForm'
	}, {
		ref : 'paramGrid',
		selector : 'param_ParamGrid'
	}, {
		ref : 'updateWin',
		selector : 'param_update'
	} ],

	updateView : 'Eway.view.machine.param.Update',

	init : function() {
		this.control({
			'param_ParamView button[action=query]' : {
				click : this.onQuery
			},
			'param_ParamView button[action=update]' : {
				click : this.onUpdate
			}
		});
	},

	onUpdate : function() {
		var grid = this.getParamGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/machine/param/unique',
				params : {
					paramKey : record.data.paramKey
				},
				success : function(response) {
					var object = Ext.decode(response.responseText);
					if (object.success == false) {
						Eway.alert(EwayLocale.tip.update.two);

					} else {
						var win = Ext.create(this.updateView);
						var form = win.down('form');
						form.getForm().loadRecord(record);
						var button = win.down('button[action="confirm"]');
						button.on('click', this.onUpdateConfirm, this);
						win.show();
					}
				},
				scope : this
			});
		} else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	onUpdateConfirm : function() {
		var sm = this.getParamGrid().getSelectionModel()
		var record = sm.getLastSelected();
		var win = this.getUpdateWin();
		var updateForm = win.down('form').getForm();
		var store = this.getParamGrid().getStore();
		var paramValue = record.data.paramValue;
		var description = record.data.description;
		if (updateForm.isValid()) {
			var data = updateForm.getValues();
			data.id = record.get("id");
			record.set('paramValue', data.paramValue);
			record.set('description', data.description);
			record.save({
				success : function(record, operation) {
					Eway.alert(EwayLocale.updateSuccess);
					win.close();
					store.applyModel(record);
				},
				failure : function(record, operation) {
					Eway.alert(operation.getError());
					// 解决失败之后grid里脏数据的问题
					record.set('paramValue', paramValue);
					record.set('description', description);

					store.commitChanges();
				}
			});
		}
	}
});