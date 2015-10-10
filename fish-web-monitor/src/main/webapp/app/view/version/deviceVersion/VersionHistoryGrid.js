
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
				header:Eway.locale.commen.terminalId,
				dataIndex:'terminalId'
			},{
				header : '设备IP',
				dataIndex : 'ip'
			},/*{
				header : '软件分类',
				dataIndex : 'versionType'
			},*/{
				header : '版本号',
				dataIndex : 'versionNo'
			},{
				header: '版本文件',
				dataIndex:'fullName'
			},{
				header : '下发人',
				dataIndex : 'userName'
			},{
				header : '下发时间',
				dataIndex : 'downTime'
			},{
				header: '下发结果',
				dataIndex : 'status'
			},{
				header: Eway.locale.commen.remark,
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