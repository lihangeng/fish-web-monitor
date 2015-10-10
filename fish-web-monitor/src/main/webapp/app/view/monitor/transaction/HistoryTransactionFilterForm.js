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
						regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’，只能以字母或数字开头。',
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
						fieldLabel : '流水号',
						xtype : 'textfield',
						name : 'transId',
						labelAlign : 'right'
					}, {
						labelWidth : 60,
						fieldLabel : '客户帐号',
						xtype : 'textfield',
						name : 'debitAccount',
						labelAlign : 'right'
					}, {
						labelWidth : 60,
						fieldLabel : '交易币种',
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
						fieldLabel : '金额范围',
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
							value : '至'
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
						fieldLabel : '交易时间段',
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
							value : '至'
						}, {
							fieldLabel : 'minute',
							displayField : 'display',
							valueField : 'value',
							fieldLabel : '金额',
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