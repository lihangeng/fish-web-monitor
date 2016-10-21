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
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.terminalId,
				name : 'terminalId'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.ip,
				name : 'ip'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.commen.devStatus,
				name : 'statusName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.serial,
				name : 'serial'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.virtual,
				name : 'virtual'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.devTypeName,
				name : 'devTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.devServiceName,
				name : 'devServiceName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.installDate,
				name : 'installDate'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.orgName,
				name : 'orgName'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.address,
				name : 'address'
			}],
			
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
