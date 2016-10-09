Ext.define('Eway.view.machine.detail.BasicInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basicInfo',

	requires : ['Eway.view.machine.detail.basic.DeviceInfo',
	            'Eway.view.machine.detail.basic.HardwareInfo',
	            'Eway.view.machine.detail.basic.CashLimitInfo',
	            'Eway.view.version.deviceVersion.VersionHistoryGrid'
	            ],
	title : '设备基本信息',
   layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
    },
    
    bodyPadding: 10,

    defaults: {
        frame: true,
        bodyPadding: 10,
        collapsible :true
    },

	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype:'form',
				items:[{
					xtype: 'detail_basic_deviceInfo',
		            title: '设备基础信息',
				},{
					xtype: 'detail_basic_hardwareInfo',
		            title: '硬件状态'
				},{
					xtype: 'detail_basic_cashLimitInfo'
				}
				]},{
					xtype: 'panel',
		            title: '设备人员信息',
		            html:'设备人员信息'
				},{
					xtype: 'versionHistory_grid',
		            title: '版本信息'
			}],

			listeners : {
				activate : function(panel) {
					var hisGrid = this.down('versionHistory_grid');
					var historyStore = hisGrid.getStore();
					historyStore.cleanUrlParam();
					historyStore.proxy.extraParams = {deviceId:this.up("viewport").down("appheader textfield[name=terminalId]").getValue()};
					historyStore.load();
				}
			}
		});

		this.callParent(arguments);
	}
});
