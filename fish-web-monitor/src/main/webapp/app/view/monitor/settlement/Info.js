Ext.define('Eway.view.monitor.settlement.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_settlement_info',
	title : '清机详细信息',
	modal : true,
	constrainHeader : true,
	width : 700,
	maximizable : true,
	height : 450,
	layout : 'fit',
	autoScroll : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding:10px 10px 10px 10px',
				height : 440,
				defaults : {
					anchor : '100%'
				},
				items : [ {
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaultType : 'textfield',
						style : 'padding-top:10px',
						defaults : {
							anchor : '90%',
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							fieldLabel : Eway.locale.commen.terminalId,
							name : 'termId'
						}, {
							fieldLabel : '清机ID',
							name : 'uuId'
						}, {
							fieldLabel : '存款笔数',
							name : 'deposit'
						}, {
							fieldLabel : '取款笔数',
							name : 'withdrawal'
						}, {
							fieldLabel : '交易总笔数',
							name : 'transaction'
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						style : 'padding-top:10px',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							fieldLabel : '尾箱余额',
							name : 'leftAmt'
						}, {
							fieldLabel : '结账日期',
							name : 'date'
						}, {
							fieldLabel : '存款金额',
							name : 'depositAmt'
						}, {
							fieldLabel : '取款金额',
							name : 'withdrawalAmt'
						}, {
							fieldLabel : '交易总金额',
							name : 'transactionAmt'
						} ]
					} ]
				}, {
					title : '详细信息',
					itemid : 'detailItemId',
					xtype : 'grid',
					store : 'monitor.settlement.SettlementDetail',
					border : true,
					columns : [ Ext.create('Ext.grid.RowNumberer'), {
						header : '钞箱ID',
						dataIndex : 'boxId'
					}, {
						header : '币种',
						dataIndex : 'boxCurrency'
					}, {
						header : '剩余金额',
						dataIndex : 'boxLeftAmt',
						flex:1
					} ]
				} ]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});