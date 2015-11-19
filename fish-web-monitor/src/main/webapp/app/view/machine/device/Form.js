Ext.define('Eway.view.machine.device.Form',{
	extend : 'Eway.view.base.Form',
	alias : 'widget.machine_device_form',

	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType','Eway.lib.Util' ],

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
					columnWidth : .45,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '95%',
						style : 'padding-top:5px',
						labelAlign : 'right'
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
						xtype : 'textfield',
						style : 'padding-top:5px',
						labelWidth: 150,
						labelAlign : 'right'
					},
					items : [ {
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'orgId'
					}, {
						xtype : 'common_orgComboOrgTree',
						fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.orgName,
						allowBlank : false,
						emptyText : EwayLocale.combox.select,
						name : 'orgName',
						hiddenValue : 'orgId',
						editable : false,
						filters : '{"type" : "0"}',
						rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
					}, {
						xtype : 'field_device_deviceatmtype',
						fieldLabel : '<font color="red">*</font> '+EwayLocale.machine.atmGroup.devTypeName,
						value: 1,
						allowBlank : false
					},{
						xtype : 'common_orgComboOrgTree',
						fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.devServiceName,
						allowBlank : false,
						emptyText : EwayLocale.combox.select,
						name : 'devServiceName',
						hiddenValue : 'devServiceId',
//						value: "深圳怡化",
						editable : false,
						filters : '{"type" : "1"}',
						rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
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
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.devAddress,
						maxLength : 100,
						name : 'address'
					}]
				}]
			},{
				xtype : 'tabpanel',
				defaults : {
					style : 'padding:5px 0px 0px 10px'
				},
				items : [ {
					title : EwayLocale.machine.device.basicInfo,
					layout : 'fit',
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
				                    labelWidth:140,
				                    labelAlign:'right',
				                    value : new Date()
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
								inputValue : 1
							}, {
								boxLabel : EwayLocale.machine.device.mainRoom,
								name : 'setupType',								
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
								inputValue : 1
							}, {
								boxLabel : EwayLocale.machine.device.wireless,
								name : 'netType',
								inputValue : 2
							}, {
								boxLabel : EwayLocale.machine.device.wiredAndWireless,
								name : 'netType',
								inputValue : 3
							}]
						},{
							fieldLabel : EwayLocale.machine.device.onBankSignal,
							xtype : 'radiogroup',
							anchor : '69%',
							items : [ {
								boxLabel : EwayLocale.machine.device.inBank,
								name : 'awayFlag',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : EwayLocale.machine.device.outBank,
								name : 'awayFlag',
								inputValue : 2
							}/*, {
								boxLabel :  EwayLocale.machine.device.clickBank,
								name : 'awayFlag',
								inputValue : 3
							}*/ ]
						}, {
							fieldLabel : EwayLocale.machine.device.operation,
							xtype : 'radiogroup',
							items : [ {
									boxLabel : EwayLocale.machine.device.operationSelf,
									name : 'workType',
									checked : true,
									inputValue : 1
								}, {
									boxLabel : EwayLocale.machine.device.cooperation,
									name : 'workType',
									inputValue : 2
								}, {
									boxLabel :  EwayLocale.machine.device.epiboly,
									name : 'workType',
									inputValue : 3
							}  ]
						}]
					}]
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
