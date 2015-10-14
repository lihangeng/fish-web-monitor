Ext.define('Eway.controller.case.NotifyMould', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'case.NotifyMould' ],
	models : [ 'case.NotifyMould' ],

	views : [ 'Eway.view.case.notifyMould.NotifyMouldView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'notifyMould_NotifyMouldView',
		autoCreate : true,
		xtype : 'notifyMould_NotifyMouldView'
	}, {
		ref : 'notifyMouldGrid',
		selector : 'notifyMould_NotifyMouldGrid'
	}, {
		ref : 'updateWin',
		selector : 'notifyMould_UpdateWin'
	} ],

	init : function() {
		this.control({
			'notifyMould_NotifyMouldGrid button[action=update]' : {
				click : this.onUpdate
			}
		});
	},

	onUpdate : function() {
		var grid = this.getNotifyMouldGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var win = Ext.create('Eway.view.case.notifyMould.UpdateWin');
			var form = win.down('form').getForm();
			form.loadRecord(record);
			var notifySet = record.data.notifySet;
			var start = notifySet.indexOf('{');
			var end = notifySet.lastIndexOf('}');
			var str = notifySet.substring(start, end + 1).replace(/}{/g, '|');// 将所有的'}{'替换成'|'
			var result = str.substring(1, str.length - 1);
			var array = result.split('|');
			var checkboxgroup = win.down('checkboxgroup');
			var boxes = checkboxgroup.getBoxes();

			for ( var j = 0; j < boxes.length; j++) {
				for ( var i = 0; i < array.length; i++) {
					if (array[i] == boxes[j].inputValue) {
						boxes[j].setValue(true);
					}
				}
			}
			var button = win.down('button[action="confirm"]');
			button.on('click', this.onUpdateConfirm, this);
			win.show();
		} else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	onUpdateConfirm : function() {
		var win = this.getUpdateWin();
		var grid = this.getNotifyMouldGrid();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		var updateForm = this.getUpdateWin().down('form').getForm();
		var data = updateForm.getValues();
		var start = Eway.locale.cases.concern;
		var end = Eway.locale.cases.SRCBView;
		var checkboxgroup = win.down('checkboxgroup');
		var checked = checkboxgroup.getChecked();
		for ( var i = 0; i < checked.length; i++) {
			start = start + '{' + checked[i].inputValue + '}';
		}
		var result = start + end;

		var notifyType = record.data.notifyType;
		var notifyWay = record.data.notifyWay;
		var notifySet = record.data.notifySet;

		if (updateForm.isValid()) {
			record.set('notifyType', data.notifyType);
			record.set('notifyWay', data.notifyWay);
			record.set('notifySet', result);
			record.save({
				success : function(record, operation) {
					Eway.alert(Eway.updateSuccess);
					win.close();
					grid.onReload();
				},
				failure : function(record, operation) {
					Eway.alert(operation.getError());
					record.set('notifyType', notifyType);
					record.set('notifyWay', notifyWay);
					record.set('notifySet', notifySet);
				}
			});
		}
	}
});