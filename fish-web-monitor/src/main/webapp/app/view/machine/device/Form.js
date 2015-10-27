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
					},{
						fieldLabel : Eway.locale.machine.device.serial,
						regex : /^\w+[\w-\.]*$/,
						regexText : Eway.locale.vtype.numberRule,
						maxLength : 40,
						name : 'serial'
					}]
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
					},{
						fieldLabel : Eway.locale.machine.device.virtual,
						regex : /^\w+[\w-\.]*$/,
						regexText : Eway.locale.vtype.numberRule,
						maxLength : 25,
						name : 'virtual'
					},{
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
					items : [{
						columnWidth : 1,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '80%',
							xtype : 'textfield',
							style : 'padding-top:10px'
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
								}  ]
						} ,{
							fieldLabel : Eway.locale.machine.device.installDate,
							xtype : 'datefield',
							format : 'Y-m-d',
							name : 'installDate',
							anchor : '50%',
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