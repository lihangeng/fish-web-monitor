Ext.define('Eway.controller.monitor.settlement.Settlement', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.settlement.Settlement',
	           'monitor.settlement.SettlementDetail' ],
	models : [ 'monitor.settlement.Settlement',
	           'monitor.settlement.SettlementDetail'],

	views : [ 'Eway.view.monitor.settlement.View' ],
	infoView : 'Eway.view.monitor.settlement.Info',

	refs : [ {
		ref : 'ewayView',
		selector : 'monitor_settlement_view',
		autoCreate : true,
		xtype : 'monitor_settlement_view'
	}, {
		ref : 'grid',
		selector : 'monitor_settlement_grid'
	} ],
	init : function() {
		this.control({
			'monitor_settlement_grid button[action=query]' : {
				click : this.onQuery
			},
			'monitor_settlement_grid button[action=info]' : {
				click : this.onInfo
			}
		});
	},

	onInfo : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var win = Ext.create(this.infoView);
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			win.down('grid').getStore().loadData(record.get('boxDetail'));
			win.show();
		} else {
			Eway.alert("请选择您要查看的记录.");
		}
	}
});