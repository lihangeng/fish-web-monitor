Ext.define('Eway.view.machine.device.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_info',
	title : Eway.locale.machine.device.devTailMsg,
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.terminalId,
							name : 'terminalId'
						}, {
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.ip,
							name : 'ip'
						}, {
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.status,
							xtype : 'radiogroup',
							defaults : {
								readOnly : true
							},
							items : [ {
								boxLabel : Eway.locale.machine.atmGroup.comboxStatus.open,
								name : 'status',
								inputValue : 1
							}, {
								boxLabel : Eway.locale.machine.atmGroup.comboxStatus.close,
								name : 'status',
								inputValue : 2
							} ]
						}, {
							fieldLabel : Eway.locale.machine.atmGroup.devServiceName,
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.orgName,
							name : 'orgName'
						}, {
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.devTypeName,
							name : 'devTypeName'
						}, {
							fieldLabel : Eway.locale.machine.atmGroup.cashboxLimit,
							xtype : 'numberfield',
							hideTrigger : true,
							name : 'cashboxLimit'
						}, {
							fieldLabel : Eway.locale.machine.device.devAddress,
							name : 'address'
						} ]
					} ]
				}, {
					xtype : 'tabpanel',
					frame : true,
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
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [{
								fieldLabel : Eway.locale.machine.device.virtual,
								name : 'virtual'
							}, {
								fieldLabel : Eway.locale.machine.device.serial,
								name : 'serial'
							}, {
								fieldLabel : Eway.locale.machine.device.carrier,
								name : 'carrier'
							}, {
								fieldLabel : Eway.locale.machine.device.moneyOrg,
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
								fieldLabel : Eway.locale.machine.device.costInterest,
								name : 'costInterest'
							}, {
								fieldLabel : Eway.locale.machine.device.atmcSoft,
								name : 'atmcSoft'
							}, {
								fieldLabel : Eway.locale.machine.device.spType,
								name : 'sp'
							} ]
						} ]
					}, {
						title : Eway.locale.machine.device.column,
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
								fieldLabel : Eway.locale.machine.device.buyDate,
								xtype : 'datefield',
								name : 'buyDate',
								format : 'Y-m-d'
							}, {
								fieldLabel : Eway.locale.machine.device.installDate,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'installDate'
							}, {
								fieldLabel : Eway.locale.machine.device.startDate,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'startDate'
							}, {
								fieldLabel : Eway.locale.machine.device.stopDate,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'stopDate'
							}, {
								fieldLabel : Eway.locale.machine.device.expireDate,
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
								fieldLabel : Eway.locale.machine.device.daliyOpen,
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
									value : Eway.locale.machine.device.openTimeHour

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
									value : Eway.locale.machine.device.openTimeMinute
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
									value : Eway.locale.machine.device.openTimeHour
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
									value : Eway.locale.machine.device.openTimeMinute
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
									value : Eway.locale.machine.device.openTimeSecond
								} ]
							}, {
								fieldLabel : Eway.locale.machine.device.lastPmDate,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'lastPmDate'
							}, {
								fieldLabel : Eway.locale.machine.device.expirePmDate,
								xtype : 'datefield',
								format : 'Y-m-d',
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
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [ {
								fieldLabel : Eway.locale.machine.device.price,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'price'
							}, {
								fieldLabel :Eway.locale.machine.device.depreciationLife,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'depreciationLife'
							}, {
								fieldLabel : Eway.locale.machine.device.decoration,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'decoration'
							}, {
								fieldLabel : Eway.locale.machine.device.decorationCost,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'decorationCost'
							}, {
								fieldLabel : Eway.locale.machine.device.governanceRent,
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
								fieldLabel : Eway.locale.machine.device.governanceCost,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'governanceCost'
							}, {
								fieldLabel : Eway.locale.machine.device.netCost,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'netCost'
							}, {
								fieldLabel :Eway.locale.machine.device.powerCost,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'powerCost'
							}, {
								fieldLabel : Eway.locale.machine.device.moneyCost,
								xtype : 'numberfield',
								hideTrigger : true,
								name : 'moneyCost'
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
								anchor : '90%',
								xtype : 'textfield',
								style : 'padding-top:10px'
							},
							items : [ {
								fieldLabel : Eway.locale.machine.device.deviceAttention,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : Eway.locale.machine.device.stress,
									name : 'careLevel',
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.medium,
									name : 'careLevel',
									inputValue : 2
								}, {
									boxLabel : Eway.locale.machine.device.ordinary,
									name : 'careLevel',
									inputValue : 3
								} ]
							}, {
								fieldLabel : Eway.locale.machine.device.notCashSignal,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : Eway.locale.machine.device.cash,
									name : 'cashType',
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.notCash,
									name : 'cashType',
									inputValue : 2
								} ]
							}, {
								fieldLabel : Eway.locale.machine.device.installStyle,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : Eway.locale.machine.device.crossWall,
									name : 'setupType',
									inputValue : 0
								}, {
									boxLabel : Eway.locale.machine.device.mainRoom,
									name : 'setupType',
									inputValue : 1
								} ]
							}, {
								fieldLabel : Eway.locale.machine.device.netType,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : Eway.locale.machine.device.wired,
									name : 'netType',
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
								style : 'padding-top:10px'
							},
							items : [ {
								fieldLabel : Eway.locale.machine.device.onBankSignal,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel : Eway.locale.machine.device.inBank,
									name : 'awayFlag',
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.outBank,
									name : 'awayFlag',
									inputValue : 2
								}, {
									boxLabel : Eway.locale.machine.device.clickBank,
									name : 'awayFlag',
									inputValue : 3
								} ]
							}, {
								fieldLabel : Eway.locale.machine.device.operation,
								xtype : 'radiogroup',
								defaults : {
									readOnly : true
								},
								items : [ {
									boxLabel: Eway.locale.machine.device.operationSelf,
									name : 'workType',
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.cooperation,
									name : 'workType',
									inputValue : 2
								}, {
									boxLabel : Eway.locale.machine.device.epiboly,
									name : 'workType',
									inputValue : 3
								} ]
							} ]
						} ]
					}, {
						title : Eway.locale.machine.device.managePerson,
						itemid : 'tubeMachineItemID',
						xtype : 'machine_device_person_tmGrid'
					}, {
						title : Eway.locale.machine.device.maintainPerson,
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