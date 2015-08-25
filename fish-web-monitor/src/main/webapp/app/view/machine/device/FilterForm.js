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
					fieldLabel : '设备号',
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
					fieldLabel : '所属机构',
					emptyText : '--请选择--',
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				}, {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : '设备品牌'
				}, {
					xtype : 'combobox',
					fieldLabel : '在离行标志',
					emptyText : '--请选择--',
					name : 'awayFlag',
					hiddenName : 'awayFlag',
					editable : false,
					store: 'machine.DeviceAwayFlagComboBox',
					valueField : 'value',
					displayField : 'display',
					queryMode : 'local'
				} ]
			}, {
				columnWidth : .28,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : '设备IP地址',
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : '请输入正确的IP地址',
					msgTarget : 'side'
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'devService'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '设备维护商',
					emptyText : '--请选择--',
					name : 'devServiceName',
					hiddenValue : 'devService',
					editable : false,
					filters : '{"type" : "1"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
				}, {
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				}, {
					xtype : 'field_atmType_DeviceAtmCatalogComboBox',
					fieldLabel : '设备类型'
				} ]
			}, {
				columnWidth : .44,
				defaults : {
					labelAlign : 'right',
					labelWidth : 90
				},
				items : [ {
					xtype : 'fieldcontainer',
					fieldLabel : '钞箱报警金额',
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
						value : '至'
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
					fieldLabel : '安装日期',
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
						value : '至'
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
					fieldLabel : '设备地址',
					maxLength : 50,
					name : 'address',
					msgTarget : 'side'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});