Ext.define('Eway.view.machine.device.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.machine_device_form',

	requires : [ 'Eway.view.common.OrgComboOrgTree','Ext.form.field.VTypes', 'Eway.view.field.device.DeviceAtmType','Eway.lib.Util' ],

	initComponent : function(){
		Ext.apply(this,{
			bodyStyle : 'padding:10px 10px 10px 10px',
			defaults : {
				anchor : '100%'
			},
			items : [ {
				layout : 'column',
				border : false,
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						style : 'padding-top:10px'
					},
					items : [ {
						fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.terminalId,
						name : 'terminalId',
						allowBlank : false,
						maxLength:20,
						vtype : "terminalId"
					}, {
						fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.ip,
						name : 'ip',
						allowBlank : false,
						vtype :'ip'
					}, {
						fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.status,
						allowBlank : false,
						xtype : 'radiogroup',
						items : [ {
							boxLabel : Eway.locale.machine.atmGroup.comboxStatus.open,
							name : 'status',
							checked : true,
							inputValue : 1
						}, {
							boxLabel : Eway.locale.machine.atmGroup.comboxStatus.close,
							name : 'status',
							inputValue : 2
						} ]
					}, {
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'devServiceId'
					}, {
						xtype : 'common_orgComboOrgTree',
						fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.devServiceName,
						allowBlank : false,
						emptyText : Eway.locale.combox.select,
						name : 'devServiceName',
						hiddenValue : 'devServiceId',
//						value: "深圳怡化",
						editable : false,
						filters : '{"type" : "1"}',
						rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						xtype : 'textfield',
						style : 'padding-top:10px'
					},
					items : [ {
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'orgId'
					}, {
						xtype : 'common_orgComboOrgTree',
						fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.orgName,
						allowBlank : false,
						emptyText : Eway.locale.combox.select,
						name : 'orgName',
						hiddenValue : 'orgId',
						editable : false,
						filters : '{"type" : "0"}',
						rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
					}, {
						xtype : 'field_device_deviceatmtype',
						fieldLabel : '<font color="red">*</font> '+Eway.locale.machine.atmGroup.devTypeName,
						value: 1,
						allowBlank : false
					}, {
						fieldLabel : Eway.locale.machine.atmGroup.cashboxLimit,
						xtype : 'numberfield',
						regex : /^(0|[1-9]\d{0,5})$/,
		        		hideTrigger:true,
						maxLength : 6,
						name : 'cashboxLimit'
					}, {
						fieldLabel : Eway.locale.machine.device.devAddress,
						maxLength : 50,
						name : 'address'
					} ]
				} ]
			}, {
				xtype : 'tabpanel',
				frame: Ext.themeModel == "new" ? true : false,
				defaults : {
					style : 'padding:10px 0px 10px 5px'
				},

				items : [ {
					title : Eway.locale.machine.device.basicInfo,
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [{
							fieldLabel : Eway.locale.machine.device.virtual,
							regex : /^\w+[\w-\.]*$/,
							regexText : Eway.locale.vtype.numberRule,
							maxLength : 25,
							name : 'virtual'
						},{
							fieldLabel : Eway.locale.machine.device.serial,
							regex : /^\w+[\w-\.]*$/,
							regexText : Eway.locale.vtype.numberRule,
							maxLength : 40,
							name : 'serial'
						}, {
							fieldLabel : Eway.locale.machine.device.carrier,
							maxLength : 20,
							name : 'carrier'
						}, {
							fieldLabel : Eway.locale.machine.device.moneyOrg,
							maxLength : 20,
							name : 'moneyOrg'
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.costInterest,
							maxLength : 50,
							name : 'costInterest'
						}, {
							fieldLabel : Eway.locale.machine.device.atmcSoft,
							maxLength : 50,
							name : 'atmcSoft'
						}, {
							fieldLabel : Eway.locale.machine.device.spType,
							maxLength : 50,
							name : 'sp'
						} ]
					} ]
				}, {
					title :  Eway.locale.machine.device.column,
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.buyDate,
							xtype : 'datefield',
							name : 'buyDate',
							editable : false,
							format : 'Y-m-d'
						}, {
							fieldLabel :  Eway.locale.machine.device.installDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'installDate'
						}, {
							fieldLabel : Eway.locale.machine.device.startDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							vtype : 'daterange',
							endDateField : 'stopDate',
//							id : 'startDate',
							name : 'startDate',
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
							fieldLabel :  Eway.locale.machine.device.stopDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							vtype : 'daterange',
							startDateField : 'startDate',
//							id : 'stopDate',
							name : 'stopDate',
							editable : false,
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
						}, {
							fieldLabel : Eway.locale.machine.device.expireDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'expireDate'
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							xtype : 'fieldcontainer',
							fieldLabel : Eway.locale.machine.device.daliyOpen,
							layout : 'hbox',
							defaults : {
								hideLabel : true
							},
							items : [ {
								xtype : 'combobox',
								fieldLabel : 'hour',
								displayField : 'display',
								store : 'Hour',
								queryMode : 'local',
								valueField : 'value',
								value : '00',
								editable : false,
								name : 'openTimeHour',
								width : 50
							}, {
								xtype : 'displayfield',
								value : Eway.locale.machine.device.openTimeHour

							}, {
								xtype : 'combobox',
								fieldLabel : 'minute',
								displayField : 'display',
								store : 'Minute',
								queryMode : 'local',
								valueField : 'value',
								value : '00',
								editable : false,
								name : 'openTimeMinute',
								width : 50
							}, {
								xtype : 'displayfield',
								value :  Eway.locale.machine.device.openTimeMinute
							}, {
								xtype : 'combobox',
								fieldLabel : 'second',
								displayField : 'display',
								store : 'Minute',
								queryMode : 'local',
								valueField : 'value',
								value : '00',
								editable : false,
								name : 'openTimeSecond',
								width : 50
							}, {
								xtype : 'displayfield',
								value : Eway.locale.machine.device.openTimeSecond
							} ]
						}, {
							xtype : 'fieldcontainer',
							fieldLabel : Eway.locale.machine.device.daliyClose,
							layout : 'hbox',
							defaults : {
								hideLabel : true
							},
							items : [ {
								xtype : 'combobox',
								fieldLabel : 'hour',
								displayField : 'display',
								store : 'Hour',
								queryMode : 'local',
								valueField : 'value',
								value : '23',
								editable : false,
								name : 'closeTimeHour',
								width : 50
							}, {
								xtype : 'displayfield',
								value : Eway.locale.machine.device.openTimeHour
							}, {
								xtype : 'combobox',
								fieldLabel : 'minute',
								displayField : 'display',
								store : 'Minute',
								queryMode : 'local',
								valueField : 'value',
								value : '59',
								editable : false,
								name : 'closeTimeMinute',
								width : 50
							}, {
								xtype : 'displayfield',
								value : Eway.locale.machine.device.openTimeMinute
							}, {
								xtype : 'combobox',
								fieldLabel : 'second',
								displayField : 'display',
								store : 'Minute',
								queryMode : 'local',
								valueField : 'value',
								value : '59',
								editable : false,
								name : 'closeTimeSecond',
								width : 50
							}, {
								xtype : 'displayfield',
								value : Eway.locale.machine.device.openTimeSecond
							} ]
						}, {
							fieldLabel : Eway.locale.machine.device.lastPmDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'lastPmDate'
						}, {
							fieldLabel : Eway.locale.machine.device.expirePmDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'expirePmDate'
						} ]
					} ]
				}, {
					title : Eway.locale.machine.device.costInfo,
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.price,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'price',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : Eway.locale.machine.device.depreciationLife,
							xtype : 'numberfield',
		        			hideTrigger:true,
		        			regex : /^(0|[1-9]\d{0,3})$/,
							name : 'depreciationLife',
							maxValue : 100,
							maxText : Eway.locale.machine.device.range
						}, {
							fieldLabel : Eway.locale.machine.device.decoration,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'decoration',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : Eway.locale.machine.device.decorationCost,
							xtype : 'numberfield',
		        			hideTrigger:true,
		        			regex : /^(0|[1-9]\d{0,3})$/,
							name : 'decorationCost',
							maxValue : 100,
							maxText : Eway.locale.machine.device.range
						}, {
							fieldLabel : Eway.locale.machine.device.governanceRent,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'governanceRent',
							regex : /^\d{0,9}(\.\d*)?$/
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.governanceCost,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'governanceCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel :  Eway.locale.machine.device.netCost,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'netCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : Eway.locale.machine.device.powerCost,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'powerCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : Eway.locale.machine.device.moneyCost,
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'moneyCost',
							regex : /^\d{0,9}(\.\d*)?$/
						} ]
					} ]
				}, {
					title : Eway.locale.machine.device.statusInfo,
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							xtype : 'textfield',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.deviceAttention,
							xtype : 'radiogroup',
							items : [ {
								boxLabel : Eway.locale.machine.device.stress,
								name : 'careLevel',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.device.medium,
								name : 'careLevel',
								inputValue : 2
							}, {
								boxLabel :Eway.locale.machine.device.ordinary,
								name : 'careLevel',
								inputValue : 3
							} ]
						}, {
							fieldLabel : Eway.locale.machine.device.notCashSignal,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : Eway.locale.machine.device.cash,
								name : 'cashType',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.device.notCash,
								name : 'cashType',
								inputValue : 2
							} ]
						}, {
							fieldLabel : Eway.locale.machine.device.installStyle,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : Eway.locale.machine.device.crossWall,
								name : 'setupType',
								checked : true,
								inputValue : 0
							}, {
								boxLabel : Eway.locale.machine.device.mainRoom,
								name : 'setupType',
								inputValue : 1
							} ]
						}, {
							fieldLabel : Eway.locale.machine.device.netType,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : Eway.locale.machine.device.wired,
								name : 'netType',
								checked : true,
								inputValue : 0
							}, {
								boxLabel : Eway.locale.machine.device.wireless,
								name : 'netType',
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.device.wiredAndWireless,
								name : 'netType',
								inputValue : 2
							} ]
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px',
							labelAlign:'right'
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.onBankSignal,
							xtype : 'radiogroup',
							items : [ {
								boxLabel : Eway.locale.machine.device.inBank,
								name : 'awayFlag',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.device.outBank,
								name : 'awayFlag',
								inputValue : 2
							}, {
								boxLabel :  Eway.locale.machine.device.clickBank,
								name : 'awayFlag',
								inputValue : 3
							} ]
						}, {
							fieldLabel : Eway.locale.machine.device.operation,
							xtype : 'radiogroup',
							items : [ {
								boxLabel : Eway.locale.machine.device.operationSelf,
								name : 'workType',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.device.cooperation,
								name : 'workType',
								inputValue : 2
							}, {
								boxLabel :  Eway.locale.machine.device.epiboly,
								name : 'workType',
								inputValue : 3
							} ]
						} ]
					} ]
				}]
			} ]
		});
		this.callParent(arguments);
	},

	// 继承view.base.Form.js的loadCusRecord方法，来实现自己form的一些特定要求。
	// 如此form的设备号不能修改。
	loadCusRecord : function(record) {
		this.getForm().loadRecord(record);

		// 设备号不能修改
		this.getForm().findField('terminalId').setDisabled(true);
	}
});