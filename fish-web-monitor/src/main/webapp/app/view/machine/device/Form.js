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
						fieldLabel : '<font color="red">*</font> 设备号',
						name : 'terminalId',
						allowBlank : false,
						maxLength:20,
						vtype : "terminalId"
					}, {
						fieldLabel : '<font color="red">*</font> 设备IP地址',
						name : 'ip',
						allowBlank : false,
						vtype :'ip'
					}, {
						fieldLabel : '<font color="red">*</font> 设备状态',
						allowBlank : false,
						xtype : 'radiogroup',
						items : [ {
							boxLabel : '开通',
							name : 'status',
							checked : true,
							inputValue : 1
						}, {
							boxLabel : '停用',
							name : 'status',
							inputValue : 2
						} ]
					}, {
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'devServiceId'
					}, {
						xtype : 'common_orgComboOrgTree',
						fieldLabel : '<font color="red">*</font> 设备维护商',
						allowBlank : false,
						emptyText : '--请选择--',
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
						fieldLabel : '<font color="red">*</font> 所属机构',
						allowBlank : false,
						emptyText : '--请选择--',
						name : 'orgName',
						hiddenValue : 'orgId',
						editable : false,
						filters : '{"type" : "0"}',
						rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
					}, {
						xtype : 'field_device_deviceatmtype',
						fieldLabel : '<font color="red">*</font> 设备型号',
						value: 1,
						allowBlank : false
					}, {
						fieldLabel : '钞箱报警金额',
						xtype : 'numberfield',
						regex : /^(0|[1-9]\d{0,5})$/,
		        		hideTrigger:true,
						maxLength : 6,
						name : 'cashboxLimit'
					}, {
						fieldLabel : '设备地址',
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
					title : '基本信息',
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
							fieldLabel : '虚拟设备号',
							regex : /^\w+[\w-\.]*$/,
							regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
							maxLength : 25,
							name : 'virtual'
						},{
							fieldLabel : '设备序列号',
							regex : /^\w+[\w-\.]*$/,
							regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
							maxLength : 40,
							name : 'serial'
						}, {
							fieldLabel : '运营商',
							maxLength : 20,
							name : 'carrier'
						}, {
							fieldLabel : '加钞机构',
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
							fieldLabel : '资金成本利率',
							maxLength : 50,
							name : 'costInterest'
						}, {
							fieldLabel : 'atmc软件',
							maxLength : 50,
							name : 'atmcSoft'
						}, {
							fieldLabel : '厂商sp类型',
							maxLength : 50,
							name : 'sp'
						} ]
					} ]
				}, {
					title : '日期信息',
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
							fieldLabel : '设备购买日期',
							xtype : 'datefield',
							name : 'buyDate',
							editable : false,
							format : 'Y-m-d'
						}, {
							fieldLabel : '设备安装日期',
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'installDate'
						}, {
							fieldLabel : '设备启用日期',
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
							fieldLabel : '设备停用日期',
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
							fieldLabel : '保修到期日期',
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
							fieldLabel : '每日开机时间',
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
								value : '时'

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
								value : '分'
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
								value : '秒'
							} ]
						}, {
							xtype : 'fieldcontainer',
							fieldLabel : '每日关机时间',
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
								value : '时'
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
								value : '分'
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
								value : '秒'
							} ]
						}, {
							fieldLabel : '上次巡检日期',
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'lastPmDate'
						}, {
							fieldLabel : '巡检到期日期',
							xtype : 'datefield',
							format : 'Y-m-d',
							editable : false,
							name : 'expirePmDate'
						} ]
					} ]
				}, {
					title : '费用信息',
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
							fieldLabel : '入账成本(元)',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'price',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : '折旧年限(年)',
							xtype : 'numberfield',
		        			hideTrigger:true,
		        			regex : /^(0|[1-9]\d{0,3})$/,
							name : 'depreciationLife',
							maxValue : 100,
							maxText : '范围1－－100年'
						}, {
							fieldLabel : '装修费用',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'decoration',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : '装修摊销年限(年)',
							xtype : 'numberfield',
		        			hideTrigger:true,
		        			regex : /^(0|[1-9]\d{0,3})$/,
							name : 'decorationCost',
							maxValue : 100,
							maxText : '范围1－－100年'
						}, {
							fieldLabel : '物业租赁费(元/月)',
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
							fieldLabel : '物业管理费用(元/月)',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'governanceCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : '通讯线路费用(元/月)',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'netCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : '电费(元/月)',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'powerCost',
							regex : /^\d{0,9}(\.\d*)?$/
						}, {
							fieldLabel : '加钞维护费用(元/次)',
							xtype : 'numberfield',
		        			hideTrigger:true,
							name : 'moneyCost',
							regex : /^\d{0,9}(\.\d*)?$/
						} ]
					} ]
				}, {
					title : '状态信息',
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
							fieldLabel : '设备关注程度',
							xtype : 'radiogroup',
							items : [ {
								boxLabel : '重点',
								name : 'careLevel',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : '中等',
								name : 'careLevel',
								inputValue : 2
							}, {
								boxLabel : '一般',
								name : 'careLevel',
								inputValue : 3
							} ]
						}, {
							fieldLabel : '非现金标志',
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : '现金',
								name : 'cashType',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : '非现金',
								name : 'cashType',
								inputValue : 2
							} ]
						}, {
							fieldLabel : '安装方式',
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : '穿墙',
								name : 'setupType',
								checked : true,
								inputValue : 0
							}, {
								boxLabel : '大堂',
								name : 'setupType',
								inputValue : 1
							} ]
						}, {
							fieldLabel : '网络类型',
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : '有线',
								name : 'netType',
								checked : true,
								inputValue : 0
							}, {
								boxLabel : '无线',
								name : 'netType',
								inputValue : 1
							}, {
								boxLabel : '有线无线',
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
							fieldLabel : '在行离行标志',
							xtype : 'radiogroup',
							items : [ {
								boxLabel : '在行自助服务区',
								name : 'awayFlag',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : '离行自助银行',
								name : 'awayFlag',
								inputValue : 2
							}, {
								boxLabel : '单机离行自助服务点',
								name : 'awayFlag',
								inputValue : 3
							} ]
						}, {
							fieldLabel : '经营方式',
							xtype : 'radiogroup',
							items : [ {
								boxLabel : '自营',
								name : 'workType',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : '合作',
								name : 'workType',
								inputValue : 2
							}, {
								boxLabel : '外包',
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