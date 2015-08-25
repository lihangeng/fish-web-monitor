Ext.define('Eway.view.machine.device.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_info',
	title : '设备详细信息',
	modal : true,
	constrainHeader : true,
	width : 700,
	height : 450,
	// maxHeight : 600,.
	layout : 'fit',
	maximizable : true,
	resizable : false,
	autoScroll : true,
//	bodyStyle : 'padding: 10px 10px 30px 10px',
	requires : [ 'Eway.view.common.OrgComboOrgTree',
			'Eway.view.machine.device.person.TGrid',
			'Eway.view.machine.device.person.TmGrid' ],
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding:10px 10px 10px 10px',
				height : 370,
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
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							name : 'id',
							style : 'padding : 0px',
							xtype : 'hiddenfield'
						}, {
							fieldLabel : '<font color="red">*</font> 设备号',
							name : 'terminalId'
						}, {
							fieldLabel : '<font color="red">*</font> 设备IP地址',
							name : 'ip'
						}, {
							fieldLabel : '<font color="red">*</font> 设备状态',
							xtype : 'radiogroup',
							defaults : {
								readOnly : true
							},
							items : [ {
								boxLabel : '开通',
								name : 'status',
								inputValue : 1
							}, {
								boxLabel : '停用',
								name : 'status',
								inputValue : 2
							} ]
						}, {
							fieldLabel : '设备维护商',
							name : 'devServiceName'
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							fieldLabel : '<font color="red">*</font> 所属机构',
							name : 'orgName'
						}, {
							fieldLabel : '<font color="red">*</font> 设备型号',
							name : 'devTypeName'
						}, {
							fieldLabel : '钞箱报警金额',
							xtype : 'numberfield',
							hideTrigger : true,
							name : 'cashboxLimit'
						}, {
							fieldLabel : '设备地址',
							name : 'address'
						} ]
					} ]
				}, {
					xtype : 'tabpanel',
					frame : true,
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
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [{
								fieldLabel : '虚拟设备号',
								name : 'virtual'
							}, {
								fieldLabel : '设备序列号',
								name : 'serial'
							}, {
								fieldLabel : '运营商',
								name : 'carrier'
							}, {
								fieldLabel : '加钞机构',
								name : 'moneyOrg'
							} ]
						}, {
							columnWidth : .5,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '90%',
								xtype : 'textfield',
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [ {
								fieldLabel : '资金成本利率',
								name : 'costInterest'
							}, {
								fieldLabel : 'atmc软件',
								name : 'atmcSoft'
							}, {
								fieldLabel : '厂商sp类型',
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
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [ {
								fieldLabel : '设备购买日期',
								xtype : 'datefield',
								name : 'buyDate',
								format : 'Y-m-d'
							}, {
								fieldLabel : '设备安装日期',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'installDate'
							}, {
								fieldLabel : '设备启用日期',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'startDate'
							}, {
								fieldLabel : '设备停用日期',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'stopDate'
							}, {
								fieldLabel : '保修到期日期',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'expireDate'
							} ]
						}, {
							columnWidth : .5,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '90%',
								xtype : 'textfield',
								style : 'padding-top:10px',
								readOnly : true
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
									readOnly : true,
									fieldLabel : 'hour',
									displayField : 'display',
									store : 'Hour',
									queryMode : 'local',
									valueField : 'value',
									name : 'openTimeHour',
									width : 40
								}, {
									xtype : 'displayfield',
									value : '时'

								}, {
									xtype : 'combobox',
									readOnly : true,
									fieldLabel : 'minute',
									displayField : 'display',
									store : 'Minute',
									queryMode : 'local',
									valueField : 'value',
									name : 'openTimeMinute',
									width : 40
								}, {
									xtype : 'displayfield',
									value : '分'
								}, {
									xtype : 'combobox',
									readOnly : true,
									fieldLabel : 'second',
									displayField : 'display',
									store : 'Minute',
									queryMode : 'local',
									valueField : 'value',
									name : 'openTimeSecond',
									width : 40
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
									readOnly : true,
									fieldLabel : 'hour',
									displayField : 'display',
									store : 'Hour',
									queryMode : 'local',
									valueField : 'value',
									name : 'closeTimeHour',
									width : 40
								}, {
									xtype : 'displayfield',
									value : '时'
								}, {
									xtype : 'combobox',
									readOnly : true,
									fieldLabel : 'minute',
									displayField : 'display',
									store : 'Minute',
									queryMode : 'local',
									valueField : 'value',
									name : 'closeTimeMinute',
									width : 40
								}, {
									xtype : 'displayfield',
									value : '分'
								}, {
									xtype : 'combobox',
									readOnly : true,
									fieldLabel : 'second',
									displayField : 'display',
									store : 'Minute',
									queryMode : 'local',
									valueField : 'value',
									name : 'closeTimeSecond',
									width : 40
								}, {
									xtype : 'displayfield',
									value : '秒'
								} ]
							}, {
								fieldLabel : '上次巡检日期',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'lastPmDate'
							}, {
								fieldLabel : '巡检到期日期',
								xtype : 'datefield',
								format : 'Y-m-d',
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
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [ {
								fieldLabel : '入账成本(元)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'price'
							}, {
								fieldLabel : '折旧年限(年)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'depreciationLife'
							}, {
								fieldLabel : '装修费用',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'decoration'
							}, {
								fieldLabel : '装修摊销年限(年)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'decorationCost'
							}, {
								fieldLabel : '物业租赁费(元/月)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'governanceRent'
							} ]
						}, {
							columnWidth : .5,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '90%',
								xtype : 'textfield',
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [ {
								fieldLabel : '物业管理费用(元/月)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'governanceCost'
							}, {
								fieldLabel : '通讯线路费用(元/月)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'netCost'
							}, {
								fieldLabel : '电费(元/月)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'powerCost'
							}, {
								fieldLabel : '加钞维护费用(元/次)',
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'moneyCost'
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
								anchor : '90%',
								xtype : 'textfield',
								style : 'padding-top:10px'
							},
							items : [ {
								fieldLabel : '设备关注程序',
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '重点',
									name : 'careLevel',
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
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '现金',
									name : 'cashType',
									inputValue : 1
								}, {
									boxLabel : '非现金',
									name : 'cashType',
									inputValue : 2
								} ]
							}, {
								fieldLabel : '安装方式',
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '穿墙',
									name : 'setupType',
									inputValue : 0
								}, {
									boxLabel : '大堂',
									name : 'setupType',
									inputValue : 1
								} ]
							}, {
								fieldLabel : '网络类型',
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '有线',
									name : 'netType',
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
								style : 'padding-top:10px'
							},
							items : [ {
								fieldLabel : '在行离行标志',
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '在行自助服务区',
									name : 'awayFlag',
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
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : '自营',
									name : 'workType',
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
					}, {
						title : '管机员',
						itemid : 'tubeMachineItemID',
						xtype : 'machine_device_person_tmGrid'
					}, {
						title : '维护员',
						itemid : 'maintainItemID',
						xtype : 'machine_device_person_tGrid'
					} ]
				} ]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});