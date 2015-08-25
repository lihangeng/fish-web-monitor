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
				text:'查询',
				glyph : 0xf002,
				action:'query'
			} ],
			columns : [ {
				header : '交易时间',
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : '交易类型',
				width : 100,
				dataIndex : 'transCode',
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.TransTypeComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				}
			}, {
				header : '交易金额',
				dataIndex : 'amt'
			}, {
				header : '交易币种',
				width : 80,
				hidden : true,
				dataIndex : 'currency'
			}, {
				header : '设备号',
				width : 80,
				dataIndex : 'termId'
			}, {
				header : '交易流水号',
				dataIndex : 'transId'
			}, {
				header : '客户账号或者卡号',
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : '对方账号或者卡号',
				dataIndex : 'creditAccount',
				width : 180
			}, {
				header : 'ATMC本地代码',
				dataIndex : 'localRet'
			}, {
				header : '主机返回码',
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