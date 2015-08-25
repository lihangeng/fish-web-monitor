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
				text : '开始监控',
				action : 'start',
				iconCls : 'monitorStart',
				disabled : true
			}, {
				xtype : 'button',
				text : '停止监控',
				iconCls : 'monitorStop',
				action : 'stop'
			} ],
			columns : [ {
				header : '交易时间',
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : '交易码',
				width : 80,
				dataIndex : 'transCode'
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
				minWidth : 180
			}, {
				header : 'ATMC本地代码',
				dataIndex : 'localRet'
			}, {
				header : '主机返回码',
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