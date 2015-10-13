
Ext.define('Eway.view.version.distribute.VersionStatusDetailGrid', {
	alias: 'widget.version_distribute_grid',
	extend: 'Eway.view.base.Grid',
	store: 'version.DistributeStatusDetail',
	border : false,

	initComponent: function() {
		var me =this;
		var store = Ext.create('Eway.store.version.DistributeStatusDetail');
		Ext.apply(this, {
			store : store,
			columns : [{
				header : Eway.locale.refs.orgName,
				dataIndex : 'orgName'
			},{
				header : '设备编号',
				dataIndex : 'terminalId',
				width: 100
			},{
				header:'IP地址',
				dataIndex:'ip',
				width: 100
			},{
				header : Eway.locale.refs.devType,
				dataIndex : 'deviceType',
				width: 100
			},{
				header : '维护商',
				dataIndex : 'vendor',
				width: 100
			},{
				header : '升级前版本号',
				dataIndex : 'beforeUpdateVersionNo',
				width: 200
			},{
				header : '目标版本号',
				dataIndex : 'afterUpdateVersionNo',
				width: 200
			},{
				header : '升级方式',
				dataIndex : 'updateType',
				flex:1
			}],
			dockedItems: [{  //分页栏
		        xtype: 'pagingtoolbar',
		        store: store ,   // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
   			 }]
		});
		this.callParent(arguments);
		
	}
});