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
				text : EwayLocale.monitor.business.transaction.transactionMonitor.begin,
				action : 'start',
				glyph : 0xf144,
				code:'TransactionStart',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.monitor.business.transaction.transactionMonitor.stop,
				glyph : 0xf04c,
				action : 'stop',
				disabled : true,
				code:'TransactionStop',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
                xtype : 'button',
                text : EwayLocale.monitor.business.transaction.transactionMonitor.clear,
                glyph : 0xf014,
                action : 'clear',
                code:'TransactionClear',
                listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
            } ],
			columns : [ {
				header : EwayLocale.monitor.business.transaction.dateTime,
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : EwayLocale.monitor.business.transaction.transCode,
				width : 120,
				dataIndex : 'transCode'
			}, {
				header : EwayLocale.monitor.business.transaction.amt,
				dataIndex : 'amt'
			}, {
				header : EwayLocale.monitor.business.transaction.currency,
				width : 80,
				hidden : true,
				dataIndex : 'currency'
			}, {
				header : EwayLocale.commen.terminalId,
				width : 80,
				dataIndex : 'termId'
			}, {
				header : EwayLocale.monitor.business.transaction.transId,
				dataIndex : 'transId'
			}, {
				header : EwayLocale.monitor.business.transaction.debitAccount,
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : EwayLocale.monitor.business.transaction.creditAccount,
				dataIndex : 'creditAccount',
				minWidth : 180
			}, {
				header : EwayLocale.monitor.business.transaction.localRet,
				dataIndex : 'localRet'
			}, {
				header : EwayLocale.monitor.business.transaction.hostRet,
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