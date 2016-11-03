Ext.define('Eway.view.machine.detail.BasicInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basicInfo',

	requires : ['Eway.view.machine.detail.basic.DeviceInfo',
	            'Eway.view.machine.detail.basic.StatusInfo',
	            'Eway.view.machine.detail.basic.ControllerInfo',
	            'Eway.view.machine.detail.basic.AllControllerInfo',
	            'Eway.view.machine.detail.basic.OtherInfo',
	            'Eway.view.machine.detail.basic.PersonInfo',
	            'Eway.view.machine.detail.basic.AppReleaseInfo'
	            ],
	title : EwayLocale.deviceInfo.deviceBasicInfo,
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
			            title: EwayLocale.deviceInfo.basicInfo,
					},{
						xtype: 'detail_basic_statusInfo',
			            title: EwayLocale.deviceInfo.statusInfo,
			            collapsible :false
					},{
						xtype : 'allControllerInfo',
					    hidden:true
					},{
						name : 'deviceControllerInfo',
						xtype : 'detail_ControllerInfo',
						autoScroll :true,
						collapsible :false,
						title : EwayLocale.deviceInfo.controllerInfo		
					},{
						xtype: 'detail_basic_appReleaseInfo',
			            title: EwayLocale.statics.versionInfo,
			            collapsible :true
					},{
						xtype: 'detail_basic_otherInfo',
						collapsed: true 
					}
				]},{
					xtype: 'detail_personInfo',
		            title: EwayLocale.deviceInfo.personInfo,
		            collapsed: true ,
			        frame: true,
			        margin: 10,
			        collapsible :true,
			        id:'personInfo'
				}]
		});

		this.callParent(arguments);
	}
});
