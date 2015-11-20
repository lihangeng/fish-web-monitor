Ext.define('Eway.view.machine.device.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_info',
	title : EwayLocale.machine.device.devTailMsg,
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
						columnWidth : .45,
						border : false,
						layout : 'anchor',
						defaultType : 'textfield',
						defaults : {
							anchor : '95%',
							style : 'padding-top:5px',
							labelAlign : 'right',
							readOnly:true,
						},
						items : [ {
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.terminalId,
							name : 'terminalId',
							allowBlank : false,
							maxLength:20,
							vtype : "terminalId"
						}, {
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.ip,
							name : 'ip',
							allowBlank : false,
							vtype :'ip'
						}, {
							fieldLabel : '<font color="red">*</font>'+EwayLocale.commen.devStatus,
							allowBlank : false,
							disabled:true,
							xtype : 'radiogroup',
							hidden : true,
							items : [ {
								boxLabel : EwayLocale.commen.comboxDevStatus.open,
								name : 'status',
								checked : true,
								readOnly :true,
								inputValue : 1
							}, {
								boxLabel : EwayLocale.commen.comboxDevStatus.stop,
								name : 'status',
								readOnly : true,
								inputValue : 2
							} ]
						},{
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'devServiceId'
						},{
							fieldLabel : EwayLocale.machine.device.virtual,
							regex : /^\w+[\w-\.]*$/,
							regexText : EwayLocale.vtype.numberRule,
							maxLength : 25,
							name : 'virtual'
						}]					
						
					}, {
						columnWidth : .55,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '95%',
							labelAlign : 'right',
							xtype : 'textfield',
							labelWidth: 150,
							style : 'padding-top:5px',
							readOnly:true
						},
						items : [ {
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'orgId'
						}, {
							xtype : 'common_orgComboOrgTree',
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.orgName,
							name : 'orgName',
						}, {
							xtype : 'field_device_deviceatmtype',
							fieldLabel : '<font color="red">*</font> '+EwayLocale.machine.atmGroup.devTypeName,
							value: 1,
							allowBlank : false
						},{
							xtype : 'common_orgComboOrgTree',
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.devServiceName,
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
							anchor : '97%',
							style : 'padding-top:5px',
							readOnly:true,
							labelAlign:'right'
						},
						items : [ {
							fieldLabel : EwayLocale.machine.device.devAddress,
							maxLength : 100,
							name : 'address'
						}]
					}]
				},{
					xtype : 'tabpanel',
					frame : true,
					items : [ {
						title : EwayLocale.machine.device.basicInfo,
						items:[{
							layout : 'column',
							border : false,
							items:[{
									columnWidth : 0.55,
									border : false,
									layout : 'anchor',
									items:[{
								 		fieldLabel : EwayLocale.machine.device.installDate,
					                    xtype : 'datefield',
					                    format : 'Y-m-d',
					                    name : 'installDate',
					                    readOnly:true,
					                    labelWidth:140,
										labelAlign:'right'
									}]
								},{
									columnWidth : 0.45,
									border : false,
									layout : 'anchor',
									items:[{
										xtype:'textfield',
										fieldLabel : EwayLocale.machine.device.serial,
										regex : /^\w+[\w-\.]*$/,
										regexText : EwayLocale.vtype.numberRule,
										maxLength : 40,
										name : 'serial',
										readOnly:true,
										labelAlign:'right'
									}]
								}]
						
						},{
							columnWidth : 1,
							border : false,
							layout : 'anchor',
							defaults : {
								anchor : '95%',
								xtype : 'textfield',
								style : 'padding-top:5px',
								labelWidth:140,
								labelAlign:'right'
							},
							items : [{
								fieldLabel : EwayLocale.machine.atmGroup.cashboxLimit,
								xtype : 'numberfield',
								regex : /^(0|[1-9]\d{0,5})$/,
								hideTrigger:true,
								maxLength : 6,
								anchor : '47%',
								name : 'cashboxLimit',
								readOnly:true,
								value:0
							},{  			        
								fieldLabel : EwayLocale.machine.device.installStyle,
								xtype : 'radiogroup',
								allowBlank : false,
								anchor : '69%',
								items : [ {
									boxLabel : EwayLocale.machine.device.crossWall,
									name : 'setupType',	
									checked : true,
									readOnly:true,
									inputValue : 1
								}, {
									boxLabel : EwayLocale.machine.device.mainRoom,
									name : 'setupType',								
									readOnly:true,
									inputValue : 2
								} ]
							},{
								fieldLabel : EwayLocale.machine.device.netType,
								xtype : 'radiogroup',
								allowBlank : false,
								anchor : '92%',
								items : [ {
									boxLabel : EwayLocale.machine.device.wired,
									name : 'netType',
									checked : true,
									readOnly:true,
									inputValue : 1
								}, {
									boxLabel : EwayLocale.machine.device.wireless,
									name : 'netType',
									readOnly:true,
									inputValue : 2
								}, {
									boxLabel : EwayLocale.machine.device.wiredAndWireless,
									name : 'netType',
									readOnly:true,
									inputValue : 3
								}]}
								,{
									fieldLabel : EwayLocale.machine.device.onBankSignal,
									xtype : 'radiogroup',
									anchor : '69%',
									items : [ {
										boxLabel : EwayLocale.machine.device.inBank,
										name : 'awayFlag',
										checked : true,
										readOnly:true,
										inputValue : 1
									}, {
										boxLabel : EwayLocale.machine.device.outBank,
										name : 'awayFlag',
										readOnly:true,
										inputValue : 2
									}/*, {
										boxLabel :  EwayLocale.machine.device.clickBank,
										name : 'awayFlag',
										readOnly:true,
										inputValue : 3
									} */]
								} ]
						}]
					},{
						title : EwayLocale.machine.device.managePerson,
						itemid : 'tubeMachineItemID',
						xtype : 'machine_device_person_tmGrid'
					}, {
						title : EwayLocale.machine.device.maintainPerson,
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