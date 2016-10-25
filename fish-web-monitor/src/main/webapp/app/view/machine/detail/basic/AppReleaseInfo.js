Ext.define('Eway.view.machine.detail.basic.AppReleaseInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.detail_appRelease',
	title : EwayLocale.deviceInfo.appReleaseInfo,
    
    initComponent : function() {
    	var store = Ext.create("Eway.store.version.AppReleaseList")
		Ext.apply(this, {
			store:store,
			columns: [{
			              text: EwayLocale.version.View.versionNo,
			              dataIndex: 'versionNo',
			              width: 200
			          },{
			              text: EwayLocale.version.View.dependVersion,
			              dataIndex: 'dependVersion',
			              width: 440
			          },{
			              text: EwayLocale.version.View.versionName,
			              dataIndex: 'fullName',
			              width: 440
			          }
			      ],
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
