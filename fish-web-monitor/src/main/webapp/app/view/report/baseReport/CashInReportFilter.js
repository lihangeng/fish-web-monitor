Ext.define('Eway.view.report.baseReport.CashInReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_CashInReportFilter',

	requires : ['Eway.view.common.OrgComboOrgTree',
	            'Eway.lib.Util'],

	height : 80,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						columnWidth : .5,
						items : [{
									style : 'padding-top:0px',
									xtype : 'hiddenfield',
									name : 'orgId'
								}, {
									//只带出银行机构
									xtype : 'common_orgComboOrgTree',
									fieldLabel : EwayLocale.commen.orgNameBelongs,
									labelAlign : 'right',
									emptyText : EwayLocale.combox.select,
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								},{
									xtype : 'textfield',
									fieldLabel : EwayLocale.commen.terminalId,
									name : 'terminalId',
									labelAlign : 'right',
									regex : /^\w+[\w-\.]*$/,
									regexText : EwayLocale.vtype.numberRule,
									maxLength : 20
								}]
					}, {
						columnWidth : .5,
						layout : 'anchor',
						defaults : {
							labelAlign : 'right'
						},
						items : [{
							xtype : 'fieldcontainer',
							fieldLabel : EwayLocale.report.baseReport.amt,
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
								value : EwayLocale.machine.device.to
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
							fieldLabel : EwayLocale.report.baseReport.date,
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
								value : EwayLocale.machine.device.to
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
					}]
		});

		this.callParent(arguments);
	}
});