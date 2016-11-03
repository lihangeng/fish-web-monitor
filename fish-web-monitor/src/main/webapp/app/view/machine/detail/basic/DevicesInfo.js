Ext.define('Eway.view.machine.detail.basic.DevicesInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_DevicesInfo',
	

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
				fieldLabel : '序列号',
				name : 'serial'
			},{
				columnWidth : .25,
				fieldLabel :'维护商',
				name : 'devServiceName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.virtual,
				name : 'virtual'
			},{
				columnWidth : .25,
				fieldLabel : '安装日期',
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
				fieldLabel : '注册状态',
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
