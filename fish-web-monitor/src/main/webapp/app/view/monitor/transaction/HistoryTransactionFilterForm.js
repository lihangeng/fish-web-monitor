Ext.define('Eway.view.monitor.transaction.HistoryTransactionFilterForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.monitor_transaction_transForm',
	model : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	border : false,

	requires: ['Eway.view.field.monitor.BlackList',
	           'Eway.lib.Util',
	           'Eway.view.field.monitor.TransTypeComboBox',
	           'Eway.view.field.monitor.HostRetComboBox','Eway.lib.Util'],

	initComponent : function() {
		Ext.apply(this, {
			bodyStyle : 'padding:10px 10px 10px 10px',
			defaults : {
				border : false
			},
			items : [ {
				layout : 'column',
				border : false,
				defaults : {
					border : false
				},
				items : [ {
					columnWidth : .28,
					items : [ {
						labelWidth : 60,
						fieldLabel : Eway.locale.commen.terminalId,
						labelAlign : 'right',
						xtype : 'textfield',
						name : 'terminalId',
						regex : /^\w[\w-_\.]{0,19}$/,
						regexText : Eway.locale.vtype.terminalId,
						msgTarget : 'side'
					}, {
						labelWidth : 60,
						xtype : 'field_blackList',
						labelAlign : 'right'
					}, {
						labelWidth : 60,
						xtype : 'monitor_TransTypeComboBox',
						labelAlign : 'right'
					} ]
				}, {
					columnWidth : .28,
					items : [ {
						labelWidth : 60,
						fieldLabel : Eway.locale.monitor.business.transaction.transId,
						xtype : 'textfield',
						name : 'transId',
						labelAlign : 'right'
					}, {
						labelWidth : 60,
						fieldLabel : Eway.locale.monitor.business.transaction.debitAccount,
						xtype : 'textfield',
						name : 'debitAccount',
						labelAlign : 'right'
					}, {
						labelWidth : 60,
						fieldLabel : Eway.locale.monitor.business.transaction.currency,
						xtype : 'textfield',
						name : 'currency',
						labelAlign : 'right',
						hidden : true
					} , {
						labelWidth : 60,
						xtype : 'monitor_HostRetComboBox',
						labelAlign : 'right',
						hidden : true
					} ]
				}, {
					columnWidth : .44,
					layout : 'anchor',
//						defaults : {
//							anchor : '90%',
//							xtype : 'textfield',
//							labelAlign : 'right',
//							style : 'padding-top:10px'
//						},
					items : [ {
						xtype : 'fieldcontainer',
						labelWidth : 70,
						fieldLabel : Eway.locale.monitor.business.transaction.amtfield,
						layout : 'hbox',
						defaults : {
							hideLabel : true
						},
						items : [ {
							xtype : 'numberfield',
		        			hideTrigger:true,
							fieldLabel : 'startAmt',
							displayField : 'display',
							valueField : 'value',
							name : 'startAmt',
							lessThan : 'endAmt',
							width : 100,
							listeners : {
								change : {
						            fn: function(This, options){
						            	var value = this.getValue();
					            		var endField = this.up('form').getForm().findField(this.lessThan);
					            		endField.setMinValue(value);
					            		endField.isValid();
						            }
								}
							}
						}, {
							xtype : 'displayfield',
							value : Eway.locale.monitor.business.transaction.toNum
						}, {
							fieldLabel : 'endAmt',
							displayField : 'display',
							valueField : 'value',
							xtype : 'numberfield',
		        			hideTrigger:true,
							greaterThan : 'startAmt',
							width : 100,
							name : 'endAmt',
							listeners : {
								change : {
						            fn: function(This, options){
						            	var value = this.getValue();
					            		var startField = this.up('form').getForm().findField(this.greaterThan);
					            		startField.setMaxValue(value);
					            		startField.isValid();
						            }
								}
							}
						}]
					}, {
						xtype : 'fieldcontainer',
						fieldLabel : Eway.locale.monitor.business.transaction.transContainer,
						labelWidth : 70,
						layout : 'hbox',
						defaults : {
							hideLabel : true
						},
						items : [ {
							fieldLabel : 'endDateTime',
							displayField : 'display',
							valueField : 'value',
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'startDateTime',
							value : Ext.Date.add(new Date(), Ext.Date.DAY, -2),
							vtype : 'daterange',
							width : 100,
							endDateField : 'endDateTime',
							listeners : {
							blur : {
					            fn: function(This, options){
					            	var value = this.getValue();
					            	if (!value) {
					            		var endField = this.up('form').getForm().findField(this.endDateField);
					            		endField.setMinValue(null);
					            	}
					            }
							}
						}
						}, {
							xtype : 'displayfield',
							value : Eway.locale.monitor.business.transaction.toNum
						}, {
							fieldLabel : 'minute',
							displayField : 'display',
							valueField : 'value',
							fieldLabel : Eway.locale.monitor.business.transaction.amt,
							width : 100,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'endDateTime',
							value : new Date(),
							vtype : 'daterange',
							startDateField : 'startDateTime',
							listeners : {
							blur : {
					            fn: function(This, options){
					            	var value = this.getValue();
					            	if (!value) {
					            		var startField = this.up('form').getForm().findField(this.startDateField);
					            		startField.setMaxValue(null);
					            	}
					            }
							}
						}
						} ]
					}]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});