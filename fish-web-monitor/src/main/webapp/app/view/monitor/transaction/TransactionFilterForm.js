
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
							fieldLabel : '设备号',
							labelAlign : 'right',
							xtype : 'textfield',
							name : 'terminalId',
							listeners : {
								focus : {
						            fn: function(This,event, options){
						            	view = this.up('transactionFilterForm').up('transactionMonitorView');
						            	if(view.down('button[action="start"]').disabled){
						            		Eway.alert("请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！");
						            	}
						            }
								}
							},
							msgTarget : 'side'
						},{
							fieldLabel : '对方账号',
							xtype : 'textfield',
							name : 'creditAccount',
							listeners : {
								focus : {
						            fn: function(This,event, options){
						            	view = this.up('transactionFilterForm').up('transactionMonitorView');
						            	if(view.down('button[action="start"]').disabled){
						            		Eway.alert("请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！");
						            	}
						            }
								}
							},
							labelAlign : 'right'
						} ]
					}, {
						columnWidth : .5,
						items : [ {
							fieldLabel : '客户帐号',
							xtype : 'textfield',
							name : 'debitAccount',
							listeners : {
								focus : {
									fn: function(This,event, options){
										view = this.up('transactionFilterForm').up('transactionMonitorView');
										if(view.down('button[action="start"]').disabled){
											Eway.alert("请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！");
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
						            		Eway.alert("请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！");
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