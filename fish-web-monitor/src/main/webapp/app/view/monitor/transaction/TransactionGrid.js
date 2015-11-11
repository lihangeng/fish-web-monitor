Ext.define('Eway.view.monitor.transaction.TransactionGrid', {

	alias : 'widget.monitor_transaction_transactionGrid',
	extend : 'Eway.view.base.Grid',

	store : 'monitor.transaction.Transaction',

	border : false,

	/**
	 * 主机返回码不正常显示红色
	 *
	 * @type
	 */
	viewConfig : {
		forceFit : true,
		getRowClass : function(record, index) {
			var hostRet = record.get('hostRet');
			if (hostRet != '00') {
				return 'user-online';
			}
			return '';
		}
	},

	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar : [ '->', {
				xtype : 'button',
				text : Eway.locale.monitor.business.transaction.transactionMonitor.begin,
				action : 'start',
				glyph : 0xf144
			}, {
				xtype : 'button',
				text : Eway.locale.monitor.business.transaction.transactionMonitor.stop,
				glyph : 0xf04c,
				action : 'stop',
				disabled : true
			}, {
                xtype : 'button',
                text : Eway.locale.monitor.business.transaction.transactionMonitor.clear,
                glyph : 0xf014,
                action : 'clear'
            } ],
			columns : [ {
				header : Eway.locale.monitor.business.transaction.dateTime,
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : Eway.locale.monitor.business.transaction.transCode,
				width : 80,
				dataIndex : 'transCode'
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
				header : Eway.locale.monitor.business.transaction.debitAccount,
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : Eway.locale.monitor.business.transaction.creditAccount,
				dataIndex : 'creditAccount',
				minWidth : 180
			}, {
				header : Eway.locale.monitor.business.transaction.localRet,
				dataIndex : 'localRet'
			}, {
				header : Eway.locale.monitor.business.transaction.hostRet,
				dataIndex : 'hostRet',/*
				renderer : function(value){
					var box = Ext.create('Eway.view.field.monitor.HostRetComboBox',{});
					box.setValue(value);
					return box.getDisplayValue();
				},*/
				flex :1
			} ]
		});

		this.callParent(arguments);
	}

});