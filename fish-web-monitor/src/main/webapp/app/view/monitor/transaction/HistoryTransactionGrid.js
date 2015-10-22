Ext.define('Eway.view.monitor.transaction.HistoryTransactionGrid', {

	alias : 'widget.monitor_transaction_transGrid',

	extend : 'Eway.view.base.Grid',

	border : false,
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.transaction.HistoryTransaction');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : ['->', {
				xtype : 'button',
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			} ],
			columns : [ {
				header : Eway.locale.monitor.business.transaction.dateTime,
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : Eway.locale.monitor.business.transaction.transCode,
				width : 100,
				dataIndex : 'transCode',
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.TransTypeComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				}
			}, {
				header : Eway.locale.monitor.business.transaction.amt,
				dataIndex : 'amt'
			}, {
				header : Eway.locale.monitor.business.transaction.currency,
				width : 80,
				hidden : true,
				dataIndex : 'currency'
			}, {
				header : Eway.locale.commen.terminalId,
				width : 80,
				dataIndex : 'termId'
			}, {
				header : Eway.locale.monitor.business.transaction.transId,
				dataIndex : 'transId'
			}, {
				header : Eway.locale.monitor.business.transaction.debitAccountOrCard,
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : Eway.locale.monitor.business.transaction.creditAccountOrCard,
				dataIndex : 'creditAccount',
				width : 180
			}, {
				header : Eway.locale.monitor.business.transaction.localRet,
				dataIndex : 'localRet'
			}, {
				header : Eway.locale.monitor.business.transaction.hostRet,
				dataIndex : 'hostRet',
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.HostRetComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				},
				flex: 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});