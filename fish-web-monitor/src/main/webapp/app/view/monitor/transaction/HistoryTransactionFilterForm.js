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
					columnWidth : .30,
					items : [ {
						fieldLabel : EwayLocale.commen.terminalId,
						labelAlign : 'right',
						labelWidth : 150,
						xtype : 'textfield',
						name : 'terminalId',
						maxLength:'MAX_VALUE',
						regex : '',
						regexText : EwayLocale.vtype.terminalId,
						msgTarget : 'side'
					}, /*{
						xtype : 'field_blackList',
						labelAlign : 'right'
					}, */{
						xtype : 'monitor_TransTypeComboBox',
						labelAlign : 'right',
						labelWidth : 150
					} ]
				}, {
					columnWidth : .32,
					items : [ {
						fieldLabel : EwayLocale.monitor.business.transaction.transId,
						xtype : 'textfield',
						name : 'transId',
						labelAlign : 'right',
						labelWidth : 120
					}, {
						fieldLabel : EwayLocale.monitor.business.transaction.debitAccount,
						xtype : 'textfield',
						name : 'debitAccount',
						labelAlign : 'right',
						labelWidth : 120
					}, {
						fieldLabel : EwayLocale.monitor.business.transaction.currency,
						xtype : 'textfield',
						name : 'currency',
						labelAlign : 'right',
						hidden : true
					} , {
						xtype : 'monitor_HostRetComboBox',
						labelAlign : 'right',
						hidden : true
					} ]
				}, {
					columnWidth : .38,
					layout : 'anchor',
					items : [ {
						xtype : 'fieldcontainer',
						fieldLabel : EwayLocale.monitor.business.transaction.amtfield,
						layout : 'hbox',
						labelWidth : 100,
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
							width : 110,
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
							value : EwayLocale.monitor.business.transaction.toNum
						}, {
							fieldLabel : 'endAmt',
							displayField : 'display',
							valueField : 'value',
							xtype : 'numberfield',
		        			hideTrigger:true,
							greaterThan : 'startAmt',
							width : 110,
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
						fieldLabel : EwayLocale.monitor.business.transaction.transContainer,
						labelWidth : 100,
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
							width : 110,
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
							value : EwayLocale.monitor.business.transaction.toNum
						}, {
							fieldLabel : 'minute',
							displayField : 'display',
							valueField : 'value',
							fieldLabel : EwayLocale.monitor.business.transaction.amt,
							width : 110,
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