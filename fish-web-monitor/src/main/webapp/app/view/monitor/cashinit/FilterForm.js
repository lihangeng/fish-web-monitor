Ext.define('Eway.view.monitor.cashinit.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_cashinit_filterform',
	requires : ['Eway.lib.Util'],
	height : 70,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.terminalId,
					name : 'terminalId',
					regex : /^\w+[\w-\.]*$/,
					regexText : Eway.locale.vtype.terminalId,
					maxLength : 20
				}, {
					xtype : 'textfield',
					fieldLabel : Eway.locale.monitor.business.cashInit.uuId,
					name : 'uuId',
					maxLength : 20
				} ]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
			    },
				items : [{
					xtype : 'fieldcontainer',
					fieldLabel : Eway.locale.monitor.business.cashInit.amt,
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
						width : 150,
//						vtype : 'numberrange',
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
//						vtype : 'numberrange',
						greaterThan : 'startAmt',
						width : 150,
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
					} ]
				}, {
					xtype : 'fieldcontainer',
					fieldLabel : Eway.locale.monitor.business.cashInit.date,
					layout : 'hbox',
					defaults : {
						hideLabel : true
					},
					items : [ {
						fieldLabel : 'endDate',
						displayField : 'display',
						valueField : 'value',
						xtype : 'datefield',
						format : 'Y-m-d',
						editable : false,
						name : 'startDate',
						vtype : 'daterange',
						width : 150,
						endDateField : 'endDate',
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
						width : 150,
						xtype : 'datefield',
						format : 'Y-m-d',
						editable : false,
						name : 'endDate',
						vtype : 'daterange',
						startDateField : 'startDate',
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
		});
		this.callParent(arguments);
	}
});