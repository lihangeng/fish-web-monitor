
Ext.define('Eway.view.monitor.transaction.TransactionFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.transactionFilterForm',

	model : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	border : false,

	requires: ['Eway.view.field.monitor.BlackList'],

	initComponent: function() {
		Ext.apply(this, {
			items : [ {
				layout : 'column',
				border : false,
				defaults : {
					border : false
				},
				items : [ {
						columnWidth : .5,
						items : [ {
							fieldLabel : Eway.locale.commen.terminalId,
							labelAlign : 'right',
							xtype : 'textfield',
							name : 'terminalId',
							listeners : {
								focus : {
						            fn: function(This,event, options){
						            	view = this.up('transactionFilterForm').up('transactionMonitorView');
						            	if(view.down('button[action="start"]').disabled){
						            		Eway.alert(Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor);
						            	}
						            }
								}
							},
							msgTarget : 'side'
						},{
							fieldLabel : Eway.locale.monitor.business.transaction.creditAccount,
							xtype : 'textfield',
							name : 'creditAccount',
							listeners : {
								focus : {
						            fn: function(This,event, options){
						            	view = this.up('transactionFilterForm').up('transactionMonitorView');
						            	if(view.down('button[action="start"]').disabled){
						            		Eway.alert(Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor);
						            	}
						            }
								}
							},
							labelAlign : 'right'
						} ]
					}, {
						columnWidth : .5,
						items : [ {
							fieldLabel : Eway.locale.monitor.business.transaction.debitAccount,
							xtype : 'textfield',
							name : 'debitAccount',
							listeners : {
								focus : {
									fn: function(This,event, options){
										view = this.up('transactionFilterForm').up('transactionMonitorView');
										if(view.down('button[action="start"]').disabled){
											Eway.alert(Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor);
										}
									}
								}
							},
							labelAlign : 'right'
						},{
							xtype : 'field_blackList',
							listeners : {
								focus : {
						            fn: function(This,event, options){
						            	view = this.up('transactionFilterForm').up('transactionMonitorView');
						            	if(view.down('button[action="start"]').disabled){
						            		Eway.alert(Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor);
						            	}
						            }
								}
							},
							editable : false,
							labelAlign : 'right'
						}]
					},{
						items : [ {
							xtype : 'hidden',
							name : 'transType',
							labelAlign : 'right'
						}]
					},{
						items : [ {
							xtype : 'hidden',
							name : 'hostRet',
							labelAlign : 'right'
						}]
					}]
				} ]
		});

		this.callParent(arguments);
	}
});