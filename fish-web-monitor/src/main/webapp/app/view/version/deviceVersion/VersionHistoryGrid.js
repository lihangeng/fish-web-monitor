
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
				header:Eway.locale.refs.terminalId,
				dataIndex:'terminalId'
			},{
				header : Eway.locale.refs.ip,//'设备IP',
				dataIndex : 'ip'
			},/*{
				header : '软件分类',
				dataIndex : 'versionType'
			},*/{
				header : Eway.locale.version.View.versionNo,//'版本号',
				dataIndex : 'versionNo'
			},{
				header: Eway.locale.version.View.versionFile,//'版本文件',
				dataIndex:'fullName'
			},{
				header : Eway.locale.version.task.downloadUser,//'下发人',
				dataIndex : 'userName'
			},{
				header : Eway.locale.version.task.downloadTime,//'下发时间',
				dataIndex : 'downTime'
			},{
				header: Eway.locale.version.task.downloadResult,//'下发结果',
				dataIndex : 'status'
			},{
				header: Eway.locale.version.View.remark,
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