Ext.define('Eway.view.monitor.cashinit.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_cashinit_info',
	title : Eway.locale.monitor.business.cashInit.info,
	modal : true,
	constrainHeader : true,
	resizable : false,
	maximizable : true,
	width : 700,
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
							fieldLabel : Eway.locale.monitor.business.cashInit.uuId,
							name : 'uuId'
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
							fieldLabel : Eway.locale.monitor.devMonitor.cash.initAmount,
							name : 'amt'
						}, {
							fieldLabel : Eway.locale.monitor.business.cashInit.date,
							name : 'date'
						} ]
					} ]
				}, {
					title : Eway.locale.commen.info,
					itemid : 'detailItemId',
					xtype : 'grid',
					store : 'monitor.cashinit.CashInitDetail',
					border:true,
					columns : [ Ext.create('Ext.grid.RowNumberer'), {
						header : Eway.locale.monitor.business.cashInit.boxId,
						dataIndex : 'boxId'
					}, {
						header : Eway.locale.monitor.business.cashInit.boxCurrency,
						dataIndex : 'boxCurrency'
					}, {
						header : Eway.locale.monitor.business.cashInit.boxInitAmt,
						dataIndex : 'boxInitAmt',
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