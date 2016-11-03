Ext.define('Eway.view.machine.detail.BasicInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basicInfo',

	requires : ['Eway.view.machine.detail.basic.DeviceInfo',
	            'Eway.view.machine.detail.basic.StatusInfo',
	            'Eway.view.machine.detail.basic.OtherInfo',
	            'Eway.view.machine.detail.basic.PersonInfo',
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
						collapsible :false,
			            title: '基础信息',
					},{
						xtype: 'detail_basic_statusInfo',
			            title: '状态信息',
			            collapsible :false
					},{
						name : 'deviceControllerInfo',
						xtype : 'detail_ControllerInfo',
						autoScroll :true,
						collapsible :false,
						title : EwayLocale.deviceInfo.controllerInfo		
					},{
						xtype: 'detail_basic_appReleaseInfo',
			            title: '版本信息',
			            collapsible :true
					},{
						xtype: 'detail_basic_otherInfo',
						collapsed: true 
					}
				]},{
					xtype: 'detail_personInfo',
		            title: '人员信息',
		            collapsed: true ,
			        frame: true,
			        margin: 10,
			        collapsible :true,
			        id:'personInfo'
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
