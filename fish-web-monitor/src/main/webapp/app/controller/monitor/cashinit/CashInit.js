Ext.define('Eway.controller.monitor.cashinit.CashInit', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.cashinit.CashInit',
	           'monitor.cashinit.CashInitDetail' ],
	models : [ 'monitor.cashinit.CashInit',
	           'monitor.cashinit.CashInitDetail'],

	views : [ 'Eway.view.monitor.cashinit.View' ],
	infoView : 'Eway.view.monitor.cashinit.Info',

	refs : [ {
		ref : 'ewayView',
		selector : 'monitor_cashinit_view',
		autoCreate : true,
		xtype : 'monitor_cashinit_view'
	}, {
		ref : 'grid',
		selector : 'monitor_cashinit_grid'
	} ],
	init : function() {
		this.control({
			'monitor_cashinit_grid button[action=query]' : {
				click : this.onQuery
			},
			'monitor_cashinit_grid button[action=info]' : {
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
			Eway.alert(EwayLocale.tip.search.record);
		}
	}
});