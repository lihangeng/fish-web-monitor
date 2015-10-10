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
					regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
					maxLength : 20
				}, {
					xtype : 'textfield',
					fieldLabel : '加钞ID',
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
					fieldLabel : '加钞金额',
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
						value : '至'
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
					fieldLabel : '加钞日期',
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
						value : '至'
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