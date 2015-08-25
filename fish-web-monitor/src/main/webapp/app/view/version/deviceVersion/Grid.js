
Ext.define('Eway.view.version.deviceVersion.Grid', {
	alias: 'widget.deviceVersion_grid',
	extend: 'Eway.view.base.Grid',

	store: 'version.DeviceVersion',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			},{
				text: '查看设备历史版本',
				tooltip:'查看设备历史版本信息',
				glyph : 0xf129,
				action:'history'
			}],
			columns : [{
				header:'设备号',
				dataIndex:'terminalId'
			},{
				header : '设备IP地址',
				dataIndex : 'ip',
				width : 120
			},{
				header : '所属机构',
				dataIndex : 'orgName',
				width : 200
			},{
				header : '设备品牌',
				dataIndex : 'devBrand'
			},{
				header : '设备型号',
				dataIndex : 'devType'
			},{
				header: '设备类型',
				dataIndex : 'devCatalog',
				flex:1
			}/*,{
				header: '应用版本',
				dataIndex : 'appVersion',
				flex:1
			}*/],
			dockedItems: [{  //分页栏
		        xtype: 'pagingtoolbar',
		        store: this.store,   // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
   			 }]
		});
		this.callParent(arguments);

	}
});