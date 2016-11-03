Ext.define('Eway.view.machine.detail.basic.HiddenDeviceInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_hiddenDevicesInfo',
	

	requires : [],
	layout: {
        type: 'column',
    },
    closable:false,
    //bodyPadding: 10,
    

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 105,
				width : '25%'
			},
			items : [{
				columnWidth : .25,
				fieldLabel : EwayLocale.system.serialNum,
				name : 'serial'
			},{
				columnWidth : .25,
				fieldLabel :EwayLocale.person.bankOrg.organizationType.serviceOrg,
				name : 'devServiceName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.virtual,
				name : 'virtual'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.installDate,
				name : 'installDate'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.installStyle,
				name : 'setupTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.netType,
				name : 'netTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.onBankSignal,
				name : 'awayFlagName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.operation,
				name : 'workTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.registerStatus,
				name : 'registerStatus'
			},{
				width : '37%',
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.orgName,
				name : 'orgName'
			},{
				width : '38%',
				columnWidth : .5,
				fieldLabel : EwayLocale.machine.atmGroup.address,
				name : 'address'
			}]
		});
		
		this.callParent(arguments);
	}
});
