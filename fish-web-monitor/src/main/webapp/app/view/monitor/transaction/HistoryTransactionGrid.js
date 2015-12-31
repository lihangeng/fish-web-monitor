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
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			} ],
			columns : [ {
				header : EwayLocale.monitor.business.transaction.dateTime,
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : EwayLocale.monitor.business.transaction.transCode,
				width : 130,
				dataIndex : 'transCode',
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.TransTypeComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				}
			}, {
				header : EwayLocale.monitor.business.transaction.amt,
				dataIndex : 'amt',
				width : 160
			}, {
				header : EwayLocale.monitor.business.transaction.currency,
				width : 100,
				hidden : true,
				dataIndex : 'currency'
			}, {
				header : EwayLocale.commen.terminalId,
				width : 120,
				dataIndex : 'termId'
			}, {
				header : EwayLocale.monitor.business.transaction.transId,
				dataIndex : 'transId'
			}, {
				header : EwayLocale.monitor.business.transaction.debitAccountOrCard,
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : EwayLocale.monitor.business.transaction.creditAccountOrCard,
				dataIndex : 'creditAccount',
				width : 180
			}, {
				header : EwayLocale.monitor.business.transaction.localRet,
				dataIndex : 'localRet'
			}, {
				header : EwayLocale.monitor.business.transaction.hostRet,
				dataIndex : 'hostRet',
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.HostRetComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				}
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