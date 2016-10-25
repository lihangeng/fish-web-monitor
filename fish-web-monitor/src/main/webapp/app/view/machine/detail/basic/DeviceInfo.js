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
				labelAlign : 'left',
				xtype : 'displayfield',
				labelWidth : 80,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.terminalId,
				name : 'terminalId'
			},{
				columnWidth : .25,
				fieldLabel : 'IP地址',
				name : 'ip'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.commen.devStatus,
				name : 'statusName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.devTypeName,
				name : 'devTypeName'
			},{
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
