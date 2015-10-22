
Ext.define('Eway.view.version.deviceVersion.Grid', {
	alias: 'widget.deviceVersion_grid',
	extend: 'Eway.view.base.Grid',

	store: 'version.DeviceVersion',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: Eway.locale.button.search,//'查询',
				glyph : 0xf002,
				action: 'query'
			},{
				text: Eway.locale.version.task.deviceVersionHis,//'查看设备历史版本',
				tooltip:Eway.locale.version.task.deviceVersionHisTip,//'查看设备历史版本信息',
				glyph : 0xf129,
				action:'history'
			}],
			columns : [{
				header:Eway.locale.refs.terminalId,
				dataIndex:'terminalId'
			},{
				header : Eway.locale.refs.ip,//'设备IP地址',
				dataIndex : 'ip',
				width : 120
			},{
				header : Eway.locale.refs.orgName,
				dataIndex : 'orgName',
				width : 200
			},{
				header : Eway.locale.machine.atmGroup.devVendorName,
				dataIndex : 'devBrand'
			},{
				header : Eway.locale.refs.devType,
				dataIndex : 'devType'
			},{
				header: Eway.locale.machine.atmGroup.devCatalogName,
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