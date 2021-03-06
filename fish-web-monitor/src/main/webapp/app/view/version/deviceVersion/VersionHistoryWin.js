
Ext.define('Eway.view.version.deviceVersion.VersionHistoryWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.deviceVersion_history',
	requires : ['Eway.view.version.deviceVersion.VersionHistoryGrid'],

	title : EwayLocale.version.task.deviceVersionHisTitle,//'设备历史版本信息',
	modal : true,
	resizable : true,
	constrainHeader : true,
	width : 820,
	height: 500,
	layout: 'border',
	
	initComponent : function() {
		Ext.apply(this, {
			items :[{
				region : 'center',
				xtype : 'versionHistory_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});