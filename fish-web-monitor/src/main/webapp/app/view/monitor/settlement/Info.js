Ext.define('Eway.view.monitor.settlement.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_settlement_info',
	title : Eway.locale.monitor.business.settlement.deTitle,
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
							fieldLabel : Eway.locale.monitor.business.settlement.settleId,
							name : 'uuId'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.cimNum,
							name : 'deposit'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.cdmNum,
							name : 'withdrawal'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.totalNum,
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
							fieldLabel : Eway.locale.monitor.business.settlement.endAmt,
							name : 'leftAmt'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.leftDate,
							name : 'date'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.cimAmt,
							name : 'depositAmt'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.cdmAmt,
							name : 'withdrawalAmt'
						}, {
							fieldLabel : Eway.locale.monitor.business.settlement.tranAmt,
							name : 'transactionAmt'
						} ]
					} ]
				}, {
					title : Eway.locale.commen.info,
					itemid : 'detailItemId',
					xtype : 'grid',
					store : 'monitor.settlement.SettlementDetail',
					border : true,
					columns : [ Ext.create('Ext.grid.RowNumberer'), {
						header : Eway.locale.monitor.business.cashInit.boxId,
						dataIndex : 'boxId'
					}, {
						header : Eway.locale.monitor.business.cashInit.boxCurrency,
						dataIndex : 'boxCurrency'
					}, {
						header : Eway.locale.monitor.business.cashInit.lastAmt,
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