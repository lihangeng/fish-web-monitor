Ext.define('Eway.view.machine.device.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.device_filterform',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	             'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	             'Eway.lib.Util'],
	height : 122,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .28,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : Eway.locale.machine.atmGroup.terminalId,
					name : 'terminalId',
					vtype : 'terminalId',
					allowBlank : true,
					maxLength:20,
					msgTarget : 'side'
				}, {

					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : ewayUser.getOrgId()
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : Eway.locale.machine.atmGroup.orgName,
					emptyText : Eway.locale.combox.select,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				}, {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : Eway.locale.machine.atmGroup.devVendorName,
				}, {
					xtype : 'field_device_deviceatmtype',
					emptyText :  Eway.locale.combox.select,
				} ]
			}, {
				columnWidth : .28,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel :  Eway.locale.machine.atmGroup.ip,
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : Eway.locale.vtype.ip,
					msgTarget : 'side'
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'devService'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : Eway.locale.machine.atmGroup.devServiceName,
					emptyText : Eway.locale.combox.select,
					name : 'devServiceName',
					hiddenValue : 'devService',
					editable : false,
					filters : '{"type" : "1"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
				}, {


				}, {
					xtype : 'field_atmType_DeviceAtmCatalogComboBox',
					fieldLabel : Eway.locale.machine.atmGroup.devCatalogName
				},{
					xtype : 'combobox',
					fieldLabel : Eway.locale.machine.device.onBankSignal,
					emptyText :  Eway.locale.combox.select,
					name : 'awayFlag',
					hiddenName : 'awayFlag',
					editable : false,
					store: 'machine.DeviceAwayFlagComboBox',
					valueField : 'value',
					displayField : 'display',
					queryMode : 'local'
				} ]
			}, {
				columnWidth : .44,
				defaults : {
					labelAlign : 'right',
					labelWidth : 90
				},
				items : [ {
					xtype : 'fieldcontainer',
					fieldLabel : Eway.locale.machine.atmGroup.cashboxLimit,
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
						name : 'startCashboxLimit',
						lessThan : 'endCashboxLimit',
						regex : /^(0|[1-9]\d{0,5})$/,
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
						value : Eway.locale.machine.device.to
					}, {
						fieldLabel : 'endAmt',
						displayField : 'display',
						valueField : 'value',
						xtype : 'numberfield',
		        		hideTrigger:true,
		        		regex : /^(0|[1-9]\d{0,5})$/,
						greaterThan : 'startCashboxLimit',
						width : 100,
						name : 'endCashboxLimit',
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
					fieldLabel :Eway.locale.machine.atmGroup.installDate,
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
						name : 'startInstallDate',
						vtype : 'daterange',
						width : 150,
						endDateField : 'endInstallDate',
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
						value : Eway.locale.machine.device.to,
					}, {
						fieldLabel : 'minute',
						displayField : 'display',
						valueField : 'value',
						width : 150,
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'endInstallDate',
						editable : false,
						vtype : 'daterange',
						startDateField : 'startInstallDate',
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
				}, {
					xtype : 'textfield',
					width : '100%',
					fieldLabel : Eway.locale.machine.device.devAddress,
					maxLength : 50,
					name : 'address',
					msgTarget : 'side'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});