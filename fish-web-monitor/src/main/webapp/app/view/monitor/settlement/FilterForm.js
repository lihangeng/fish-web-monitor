Ext.define('Eway.view.monitor.settlement.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_settlement_filterform',

	requires: ['Eway.lib.Util'],
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
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.commen.terminalId,
					name : 'terminalId',
					regex : /^\w+[\w-\.]*$/,
					regexText : EwayLocale.vtype.terminalId,
					maxLength : 20
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.monitor.business.settlement.uuId,
					name : 'uuId',
					maxLength : 20
				} ]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'fieldcontainer',
					fieldLabel : EwayLocale.monitor.business.settlement.endAmt,
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
					            	if (!value) {
					            		var endField = this.up('form').getForm().findField(this.lessThan);
					            		endField.setMinValue(null);
					            	}
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
					fieldLabel : EwayLocale.monitor.business.settlement.endDate,
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
						value : EwayLocale.monitor.business.transaction.toNum
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
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});