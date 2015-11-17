Ext.define('Eway.view.monitor.settlement.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_settlement_info',
	title : EwayLocale.monitor.business.settlement.deTitle,
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
							fieldLabel : EwayLocale.commen.terminalId,
							name : 'termId'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.settleId,
							name : 'uuId'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.cimNum,
							name : 'deposit'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.cdmNum,
							name : 'withdrawal'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.totalNum,
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
							fieldLabel : EwayLocale.monitor.business.settlement.endAmt,
							name : 'leftAmt'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.leftDate,
							name : 'date'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.cimAmt,
							name : 'depositAmt'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.cdmAmt,
							name : 'withdrawalAmt'
						}, {
							fieldLabel : EwayLocale.monitor.business.settlement.tranAmt,
							name : 'transactionAmt'
						} ]
					} ]
				}, {
					title : EwayLocale.commen.info,
					itemid : 'detailItemId',
					xtype : 'grid',
					store : 'monitor.settlement.SettlementDetail',
					border : true,
					columns : [ Ext.create('Ext.grid.RowNumberer'), {
						header : EwayLocale.monitor.business.cashInit.boxId,
						dataIndex : 'boxId'
					}, {
						header : EwayLocale.monitor.business.cashInit.boxCurrency,
						dataIndex : 'boxCurrency'
					}, {
						header : EwayLocale.monitor.business.cashInit.lastAmt,
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