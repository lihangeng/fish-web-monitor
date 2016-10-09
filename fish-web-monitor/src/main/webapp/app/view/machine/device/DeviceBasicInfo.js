Ext.define("Eway.view.machine.device.DeviceBasicInfo", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.device.deviceBasicInfo',
	title : EwayLocale.machine.device.deviceBasicInfo,
	bodyStyle : 'padding: 10px 10px 10px 10px',

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
					fieldLabel : EwayLocale.machine.atmGroup.terminalId,
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
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
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
					fieldLabel : EwayLocale.machine.atmGroup.address,
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
					fieldLabel : EwayLocale.machine.atmGroup.devTypeName,
					name : 'model'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.device.lineLogo,
					name : 'lineLogo'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.atmGroup.devServiceName,
					name : 'maintainer'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.device.alarmRateRMB,
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
					fieldLabel : EwayLocale.machine.device.operation,
					name : 'operation'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.device.ipAddress,
					name : 'ipAddress'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.device.swallowCard,
					name : 'swallowCard'
				}, {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.device.alarmRateHKD,
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
					fieldLabel : EwayLocale.machine.device.adminPhone,
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
					fieldLabel : EwayLocale.machine.device.maintainPhone,
					name : 'maintainPhone'
				}
			} ]
		});

		this.callParent(arguments);
	}

});