
Ext.define('Eway.view.version.deviceVersion.VersionHistoryGrid', {
	alias: 'widget.versionHistory_grid',
	extend: 'Eway.view.base.Grid',
	
	initComponent: function() {
		var store = Ext.create('Eway.store.version.DeviceVersionHistory');
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '{name}'
    	});
		Ext.apply(this, {
			store : store,
			features: [groupingFeature],
			columns : [{
				header:EwayLocale.refs.terminalId,
				dataIndex:'terminalId'
			},{
				header : EwayLocale.refs.ip,//'设备IP',
				dataIndex : 'ip'
			},/*{
				header : '软件分类',
				dataIndex : 'versionType'
			},*/{
				header : EwayLocale.version.View.versionNo,//'版本号',
				dataIndex : 'versionNo'
			},{
				header: EwayLocale.version.View.versionFile,//'版本文件',
				dataIndex:'fullName'
//			},{
//				header : EwayLocale.version.task.downloadUser,//'下发人',
//				dataIndex : 'userName'
			},{
				header : EwayLocale.version.task.downloadTime,//'下发时间',
				dataIndex : 'downTime'
			},{
				header: EwayLocale.version.task.downloadResult,//'下发结果',
				dataIndex : 'status'
			},{
				header: EwayLocale.version.View.remark,
				dataIndex : 'remark',
				flex:1
			}],
			dockedItems: [{  //分页栏
		        xtype: 'pagingtoolbar',
		        store: store,
		        dock: 'bottom',
		        displayInfo: true
   			 }]
		});
		this.callParent(arguments);
	}
});