
Ext.define('Eway.view.version.ChartsGrid', {
	alias: 'widget.version_charts_grid',
	extend: 'Eway.view.base.Grid',
	store: 'version.VersionChartsDetails',
	border : false,

	initComponent: function() {
		var me =this;
		me.store = Ext.create("Eway.store.version.VersionChartsDetails");
		Ext.apply(this, {
			columns : [{
				header : '设备编号',
				dataIndex : 'terminalId',
				width: 150
			},{
				header:'IP地址',
				dataIndex:'ip',
				width: 100
			},{
				header : Eway.locale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			},{
				header : Eway.locale.commen.devTypeName,
				dataIndex : 'devType',
				width: 200
			},{
				header : '当前版本号',
				dataIndex : 'versionNo',
				width: 140,
				flex : 1
			}],
			dockedItems: [{  //分页栏
		        xtype: 'pagingtoolbar',
		        store: me.store,   // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
   			 }]
		});
		this.callParent(arguments);
		
	}
});