Ext.define('Eway.view.report.baseReport.SettlementReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_SettlementReportFilter',

	requires : ['Eway.view.common.OrgComboOrgTree','Eway.view.field.device.DeviceAtmType',
	            'Eway.lib.Util'],

	height : 80,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						columnWidth : .3,
						items : [{
									style : 'padding-top:0px',
									xtype : 'hiddenfield',
									name : 'orgId'
								}, {
									//只带出银行机构
									xtype : 'common_orgComboOrgTree',
									fieldLabel : Eway.locale.commen.orgNameBelongs,
									labelAlign : 'right',
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								},{
									xtype : 'textfield',
									fieldLabel : Eway.locale.commen.terminalId,
									labelAlign : 'right',
									name : 'terminalId',
									regex : /^\w+[\w-\.]*$/,
									regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
									maxLength : 20
								}]
					}, {
						columnWidth : .4,
						layout : 'anchor',
						defaults : {
							labelAlign : 'right',
							labelWidth : 70
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
								width : 100,
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
							} ]
						}, {
							xtype : 'fieldcontainer',
							fieldLabel : '清机日期',
							labelWidth : 70,
							layout : 'hbox',
							defaults : {
								hideLabel : true
							},
							items : [ {
								fieldLabel : 'endDate',
								displayField : 'display',
								valueField : 'value',
								xtype : 'datefield',
								editable : false,
								format : 'Y-m-d',
								name : 'startDate',
								vtype : 'daterange',
								width : 100,
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
								width : 100,
								editable : false,
								xtype : 'datefield',
								format : 'Y-m-d',
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
					}, {
						columnWidth : .3,
						items : [{
							xtype : 'field_device_deviceatmtype',
							name : 'devType',
							hiddenName : 'devType',
							width : 280
						}]
					}]
		});

		this.callParent(arguments);
	}
});