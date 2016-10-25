Ext.define('Eway.view.machine.detail.BasicInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basicInfo',

	requires : ['Eway.view.machine.detail.basic.DeviceInfo',
	            'Eway.view.machine.detail.basic.HardwareInfo',
	            'Eway.view.machine.detail.basic.OtherInfo',
	            'Eway.view.machine.detail.basic.PersonInfo',
	            'Eway.view.machine.detail.basic.VersionInfo',
	            'Eway.view.machine.detail.basic.AppReleaseInfo'
	            ],
	title : '设备基本信息',
   layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
    },
    closable:false,
    bodyPadding: 10,

  

	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype:'form',
				defaults: {
			        frame: true,
			        bodyPadding: 10,
			        collapsible :true
			    },
				items:[{
						xtype: 'detail_basic_deviceInfo',
			            title: '设备基础信息',
					},{
						xtype: 'detail_basic_hardwareInfo',
			            title: '硬件状态'
					},{
						xtype: 'detail_basic_otherInfo'
					}
				]},{
					xtype: 'detail_appRelease',
		            title: '可升级版本信息',
			        frame: true,
			        margin: 10,
			        collapsible :true,
			        hidden:true,
			        id:'appRelease'
				},{
					xtype: 'detail_personInfo',
		            title: '设备人员信息',
			        frame: true,
			        margin: 10,
			        collapsible :true
				},{
					xtype: 'detail_versionInfo',
		            title: '版本信息',
			        frame: true,
			        margin: 10,
			        collapsible :true
			}],

			listeners : {
				activate : function(panel) {
//					var hisGrid = this.down('versionHistory_grid');
//					var historyStore = hisGrid.getStore();
//					historyStore.cleanUrlParam();
//					historyStore.proxy.extraParams = {deviceId:this.up("viewport").down("appheader textfield[name=terminalId]").getValue()};
//					historyStore.load();
				}
			}
		});

		this.callParent(arguments);
	}
});
