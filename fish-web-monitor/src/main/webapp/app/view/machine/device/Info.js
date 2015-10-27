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
						items : [{
							columnWidth : 1,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '80%',
								xtype : 'textfield',
								style : 'padding-top:10px',
								readOnly : true
							},
							items : [{
								fieldLabel : Eway.locale.machine.device.installStyle,
								xtype : 'radiogroup',
								allowBlank : false,
								anchor : '59%',
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
							},{
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
								}]}
								,{
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
								},{
								fieldLabel : Eway.locale.machine.device.installDate,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'installDate',
								anchor : '50%',
								}]
						} ]
					},{
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