
Ext.define('Eway.view.version.versionType.Grid', {
	alias: 'widget.versionType_grid',
	extend: 'Eway.view.base.Grid',
	
//	store: 'version.VersionType',
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.version.VersionType');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				glyph : 0xf002
			}, {
				text: EwayLocale.button.add,//'增加',
				action: 'add',
				glyph : 0xf067,
				code : 'versionTypeQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,//'更改',
				action: 'update',
				glyph : 0xf040,
				code : 'versionTypeUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,//'删除',
				action: 'remove',
				glyph : 0xf014,
				code : 'versionTypeDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.version.View.versionTypeCode,//'软件分类编码',
				dataIndex : 'typeName',
				width: '20%'
			},{
				header : EwayLocale.version.View.versionTypeName,//'软件分类名称',
				dataIndex : 'desc',
				width: '30%'
			},{
				header:EwayLocale.versionType.defaultInstallPath,//'默认安装路径',
				dataIndex:'defaultInstallPath',
				width: '30%'
			},{
				header:EwayLocale.versionType.needRestart,//'需要重启设备完成升级',
				dataIndex:'autoDeploy',
				renderer: function(value){
					if(value == '1'){
						return EwayLocale.tip.right.yes;//"是";
					}else{
						return EwayLocale.tip.right.no//"否"
					}
				},
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});