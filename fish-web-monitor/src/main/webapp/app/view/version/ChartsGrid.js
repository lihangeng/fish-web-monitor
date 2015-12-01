
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
				header : EwayLocale.refs.terminalId,//'设备编号',
				dataIndex : 'terminalId',
				width: 150
			},{
				header:EwayLocale.refs.ip,//'IP地址',
				dataIndex:'ip',
				width: 200
			},{
				header : EwayLocale.refs.orgName,//机构
				dataIndex : 'orgName',
				width: 200
			},{
				header : EwayLocale.refs.devType,
				dataIndex : 'devType',
				width: 200
			},{
				header : EwayLocale.version.View.nowVersionNo,//'当前版本号',
				dataIndex : 'versionNo',
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