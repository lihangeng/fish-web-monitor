
Ext.define('Eway.view.version.deviceVersion.Grid', {
	alias: 'widget.deviceVersion_grid',
	extend: 'Eway.view.base.Grid',

	store: 'version.DeviceVersion',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'query'
			},{
				text: EwayLocale.version.task.deviceVersionHis,//'查看设备历史版本',
				tooltip:EwayLocale.version.task.deviceVersionHisTip,//'查看设备历史版本信息',
				glyph : 0xf129,
				action:'history',
				code : 'deviceVersionHisInfo',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header:EwayLocale.refs.terminalId,
				dataIndex:'terminalId'
			},{
				header : EwayLocale.refs.ip,//'设备IP地址',
				dataIndex : 'ip',
				width : 120
			},{
				header : EwayLocale.refs.orgName,
				dataIndex : 'orgName',
				width : 200
			},{
				header : EwayLocale.machine.atmGroup.devVendorName,
				dataIndex : 'devBrand'
			},{
				header : EwayLocale.refs.devType,
				dataIndex : 'devType'
			},{
				header: EwayLocale.machine.atmGroup.devCatalogName,
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