Ext.define('Eway.view.machine.detail.basic.DeviceInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_deviceInfo',

	requires : [  ],
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.terminalId,
				name : 'terminalId',
				code : 'terminalId'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.ip,
				name : 'ip',
				code :'ip'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.commen.devStatus,
				name : 'statusName',
				code :'statusName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.serial,
				name : 'serial',
				code :'serial'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.orgName,
				name : 'orgName',
				code :'orgName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.devTypeName,
				name : 'devTypeName',
				code :'devTypeName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.devServiceName,
				name : 'devServiceName',
				code :'devServiceName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.devAddress,
				name : 'installDate',
				code :'installDate'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.virtual,
				name : 'virtual',
				code :'virtual'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.virtual,
				name : 'virtual',
				code :'virtual'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.installStyle,
				name : 'setupTypeName',
				code :'setupTypeName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.netType,
				name : 'netTypeName',
				code :'netTypeName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.onBankSignal,
				name : 'awayFlagName',
				code :'awayFlagName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.device.operation,
				name : 'workTypeName',
				code :'workTypeName'
			}],
			
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
