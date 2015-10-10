Ext.define("Eway.view.machine.device.DeviceBasicInfo", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.device.deviceBasicInfo',

//	id : 'deviceBasicInfoId',
	title : Eway.locale.commen.devInfo,
	bodyStyle : 'padding: 10px 10px 30px 10px',

	layout : 'column',

	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .5,
				layout : 'anchor',
				bodyStyle : 'padding-top: 10px',
				defaults : {
					anchor : '90%',
					labelAlign : 'right',
					readOnly : true
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.terminalId,
					name : 'number',
					msgTarget : 'side'
				} ]
			}, {
				columnWidth : .5,
				layout : 'anchor',
				bodyStyle : 'padding-top: 10px',
				defaults : {
					labelAlign : 'right',
					anchor : '90%',
					readOnly : true
				},
				items : {
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.orgNameBelongs,
					name : 'affiliation'
				}
			}, {
				columnWidth : 1,
				layout : 'anchor',
				bodyStyle : 'padding-top: 10px',
				defaults : {
					labelAlign : 'right',
					anchor : '95%',
					readOnly : true
				},
				items : {
					xtype : 'textfield',
					fieldLabel : '设备地址',
					maxLength : 24,
					name : 'address',
					msgTarget : 'side'
				}
			}, {
				columnWidth : .5,
				layout : 'anchor',
				defaults : {
					labelAlign : 'right',
					anchor : '90%',
					style : 'padding-top: 10px',
					readOnly : true
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.devTypeName,
					name : 'model'
				}, {
					xtype : 'textfield',
					fieldLabel : '在行标志',
					name : 'lineLogo'
				}, {
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.devServiceName,
					name : 'maintainer'
				}, {
					xtype : 'textfield',
					fieldLabel : '钞箱报警金额(人民币)',
					name : 'alarmRateRMB'
				} ]
			}, {
				columnWidth : .5,
				layout : 'anchor',
				defaults : {
					labelAlign : 'right',
					anchor : '90%',
					style : 'padding-top: 10px',
					readOnly : true
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.seviceMode,
					name : 'operation'
				}, {
					xtype : 'textfield',
					fieldLabel : 'IP地址',
					name : 'ipAddress'
				}, {
					xtype : 'textfield',
					fieldLabel : '吞卡张数',
					name : 'swallowCard'
				}, {
					xtype : 'textfield',
					fieldLabel : '钞箱报警金额(港币)',
					name : 'alarmRateHKD'
				} ]
			}, {
				columnWidth : 1,
				layout : 'anchor',
				bodyStyle : 'padding-top: 10px',
				defaults : {
					labelAlign : 'right',
					anchor : '95%',
					readOnly : true
				},
				items : {
					xtype : 'textfield',
					fieldLabel : '管理员(手机号)',
					name : 'adminPhone'
				}
			}, {
				columnWidth : 1,
				layout : 'anchor',
				bodyStyle : 'padding-top: 10px',
				defaults : {
					labelAlign : 'right',
					anchor : '95%',
					readOnly : true
				},
				items : {
					xtype : 'textfield',
					fieldLabel : '维护员(手机号)',
					name : 'maintainPhone'
				}
			} ]
		});

		this.callParent(arguments);
	}

});