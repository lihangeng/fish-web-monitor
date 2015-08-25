Ext.define('Eway.controller.case.FaultClassify', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'case.FaultClassify' ],
	models : [ 'case.FaultClassify' ],

	views : [ 'Eway.view.case.faultClassify.FaultClassifyView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'faultClassify_FaultClassifyView',
		autoCreate : true,
		xtype : 'faultClassify_FaultClassifyView'
	}, {
		ref : 'faultClassifyGrid',
		selector : 'faultClassify_FaultClassifyGrid'
	}, {
		ref : 'updateWin',
		selector : 'faultClassify_UpdateWin'
	} ],

	updateWin : 'Eway.view.case.faultClassify.UpdateWin',

	init : function() {
		this.control({
			'faultClassify_FaultClassifyGrid button[action=update]' : {
				click : this.onUpdate
			}
		});
	},

	onUpdate : function() {
		var grid = this.getFaultClassifyGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var win = Ext.create('Eway.view.case.faultClassify.UpdateWin');
			var form = win.down('form');
			form.getForm().loadRecord(record);
			var button = win.down('button[action="confirm"]');
			button.on('click', this.onUpdateConfirm, this);
			win.show();
		} else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	onUpdateConfirm : function() {
		var grid = this.getFaultClassifyGrid();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		var win = this.getUpdateWin();
		var updateForm = win.down('form').getForm();
		var data = updateForm.getValues();
		var notifyTimes = record.data.notifyTimes;
		var notifyWay = record.data.notifyWay;
		var resolveHour = record.data.resolveHour;
		var responsorType = record.data.responsorType;
		var upgrade = record.data.upgrade;
		if (updateForm.isValid()) {
			record.set('notifyTimes', data.notifyTimes);
			record.set('notifyWay', data.notifyWay);
			record.set('resolveHour', data.resolveHour);
			record.set('responsorType', data.responsorType);
			record.set('upgrade', data.upgrade);
			record.save({
				success : function(record, operation) {
					Eway.alert(Eway.updateSuccess);
					win.close();
					grid.onReload();
				},
				failure : function(record, operation) {
					Eway.alert(operation.getError());
					record.set('notifyTimes', notifyTimes);
					record.set('notifyWay', notifyWay);
					record.set('resolveHour', resolveHour);
					record.set('responsorType', responsorType);
					record.set('upgrade', upgrade);
				}
			});
		}
	}
});