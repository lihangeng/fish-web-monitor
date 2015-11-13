Ext.define('Eway.view.machine.device.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_info',
	title : Eway.locale.machine.device.devTailMsg,
	modal : true,
	constrainHeader : true,
	width : 700,
	height : 450,
	layout : 'fit',
	maximizable : true,
	resizable : false,
	autoScroll : true,
	requires : [ 'Eway.view.common.OrgComboOrgTree',
			'Eway.view.machine.device.person.TGrid',
			'Eway.view.machine.device.person.TmGrid',
			'Eway.view.field.device.DeviceAtmType'],
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
							style : 'padding-top:5px',
							readOnly:true
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.commen.devStatus,
							allowBlank : false,
							disabled:true,
							xtype : 'radiogroup',
							hidden : true,
							items : [ {
								boxLabel : Eway.locale.commen.comboxDevStatus.open,
								name : 'status',
								checked : true,
								readOnly :true,
								inputValue : 1
							}, {
								boxLabel : Eway.locale.commen.comboxDevStatus.stop,
								name : 'status',
								readOnly : true,
								inputValue : 2
							} ]
						},{
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'devServiceId'
						},{
							fieldLabel : Eway.locale.machine.device.virtual,
							regex : /^\w+[\w-\.]*$/,
							regexText : Eway.locale.vtype.numberRule,
							maxLength : 25,
							name : 'virtual'
						}]					
						
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:5px',
							readOnly:true
						},
						items : [ {
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'orgId'
						}, {
							xtype : 'common_orgComboOrgTree',
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.orgName,
							name : 'orgName',
						}, {
							xtype : 'field_device_deviceatmtype',
							fieldLabel : '<font color="red">*</font> '+Eway.locale.machine.atmGroup.devTypeName,
							value: 1,
							allowBlank : false
						},{
							xtype : 'common_orgComboOrgTree',
							fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.devServiceName,
							name : 'devServiceName',
						} ]
					}]
					
				},{				
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : 1,
						border : false,
						layout : 'anchor',
						defaultType : 'textarea',
						defaults : {
							anchor : '95%',
							style : 'padding-top:5px',
							readOnly:true
						},
						items : [ {
							fieldLabel : Eway.locale.machine.device.devAddress,
							maxLength : 100,
							name : 'address'
						}]
					}]
				},{
					xtype : 'tabpanel',
					frame : true,
					items : [ {
						title : Eway.locale.machine.device.basicInfo,
						items:[{
							layout : 'column',
							border : false,
							items:[{
									columnWidth : 0.5,
									border : false,
									layout : 'anchor',
									items:[{
								 		fieldLabel : Eway.locale.machine.device.installDate,
					                    xtype : 'datefield',
					                    format : 'Y-m-d',
					                    name : 'installDate',
					                    readOnly:true
									}]
								},{
									columnWidth : 0.5,
									border : false,
									layout : 'anchor',
									items:[{
										xtype:'textfield',
										fieldLabel : Eway.locale.machine.device.serial,
										regex : /^\w+[\w-\.]*$/,
										regexText : Eway.locale.vtype.numberRule,
										maxLength : 40,
										name : 'serial',
										readOnly:true
									}]
								}]
						
						},{
							columnWidth : 1,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '95%',
								xtype : 'textfield',
								style : 'padding-top:5px'
							},
							items : [{
								fieldLabel : Eway.locale.machine.atmGroup.cashboxLimit,
								xtype : 'numberfield',
								regex : /^(0|[1-9]\d{0,5})$/,
								hideTrigger:true,
								maxLength : 6,
								anchor : '41%',
								name : 'cashboxLimit',
								readOnly:true,
								value:0
							},{  			        
								fieldLabel : Eway.locale.machine.device.installStyle,
								xtype : 'radiogroup',
								allowBlank : false,
								anchor : '69%',
								items : [ {
									boxLabel : Eway.locale.machine.device.crossWall,
									name : 'setupType',	
									checked : true,
									readOnly:true,
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.mainRoom,
									name : 'setupType',								
									readOnly:true,
									inputValue : 2
								} ]
							},{
								fieldLabel : Eway.locale.machine.device.netType,
								xtype : 'radiogroup',
								allowBlank : false,
								items : [ {
									boxLabel : Eway.locale.machine.device.wired,
									name : 'netType',
									checked : true,
									readOnly:true,
									inputValue : 1
								}, {
									boxLabel : Eway.locale.machine.device.wireless,
									name : 'netType',
									readOnly:true,
									inputValue : 2
								}, {
									boxLabel : Eway.locale.machine.device.wiredAndWireless,
									name : 'netType',
									readOnly:true,
									inputValue : 3
								}]}
								,{
									fieldLabel : Eway.locale.machine.device.onBankSignal,
									xtype : 'radiogroup',
									items : [ {
										boxLabel : Eway.locale.machine.device.inBank,
										name : 'awayFlag',
										checked : true,
										readOnly:true,
										inputValue : 1
									}, {
										boxLabel : Eway.locale.machine.device.outBank,
										name : 'awayFlag',
										readOnly:true,
										inputValue : 2
									}, {
										boxLabel :  Eway.locale.machine.device.clickBank,
										name : 'awayFlag',
										readOnly:true,
										inputValue : 3
									} ]
								} ]
						}]
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