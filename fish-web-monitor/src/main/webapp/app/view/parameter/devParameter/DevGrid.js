Ext.define('Eway.view.parameter.devParameter.DevGrid', {
	alias : 'widget.parameter_devParameter_devGrid',
	extend : 'Eway.view.base.Grid',
	border : false,
	autoFit : true,
	selectfirst : true,
	requires : [ 'Eway.lib.Util' ],
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.devParameter.DevInfo');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			 }],
			columns : [ {
				header : '设备号',
				dataIndex : 'terminalId',
				width:150
			},{
				header : 'Ip地址',
				dataIndex : 'ip',
				width:150
			},{
				header : '所属机构',
				dataIndex : 'orgName',
				flex:1
			}/*,{
				header : '设备型号',
				dataIndex : 'devTypeName',
				width:150
			},{
				header : '设备类型',
				dataIndex : 'devCatalogName',
				flex : 1
			}*/],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});