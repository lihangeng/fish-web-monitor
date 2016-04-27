Ext.define('Eway.view.parameter.devParameter.DevGrid', {
	alias : 'widget.parameter_devParameter_devGrid',
	extend : 'Eway.view.base.Grid',
	border : false,
	autoFit : true,
	selModel:{selType:'checkboxmodel'},
	requires : [ 'Eway.lib.Util' ],
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.devParameter.DevInfo');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			selectOnFocus : true,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'devParamDevQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			 },{
				 text:EwayLocale.button.download,
				 action:'release',
				 code : 'devParamDevRelease',
				 listeners:{
					 'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				 }
			 }],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width:150
			},{
				header : EwayLocale.commen.ip,
				dataIndex : 'ip',
				width:150
			},{
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName',
				width:150
			},{
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName',
				width:150
			},{
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName',
				flex : 1
			}],
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