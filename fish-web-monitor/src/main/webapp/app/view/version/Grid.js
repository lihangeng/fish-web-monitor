
Ext.define('Eway.view.version.Grid', {
	alias: 'widget.version_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],
	store: 'version.Version',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'query'
			}/*,{
				text: '自动下发',
				tooltip:'设置自动下发标志',
				action:'autoDown'
			}*/,{
				text: EwayLocale.button.download,//'下发',
				tooltip: EwayLocale.button.downloadToolTip,//'配置下发作业',
//				iconCls : 'versionDown',
				glyph : 0xf0ed,
				action: 'down',
				code : 'versionExportBtn',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.add,//'增加',
				glyph : 0xf067,
				action: 'add',
				code : 'versionAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,//'更改',
				glyph : 0xf040,
				action: 'update',
				code : 'versionUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,//'删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'versionDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.version.View.versionTypeName,//'软件分类名称',
				dataIndex : 'versionTypeDesc',
				width: 200
			},{
				header: EwayLocale.version.View.versionNo,//'版本号',
				dataIndex:'versionNo'
			},{
				header :  EwayLocale.version.View.versionTypeId,//'版本类型ID',
				dataIndex : 'versionTypeId',
				hidden : true
			},{
				header : EwayLocale.version.View.versionPath,//'版本路径',
				dataIndex : 'versionPath',
				hidden : true,
				width: 200
			},{
				header : EwayLocale.version.View.versionTime,//'创建时间',
				dataIndex : 'createdTime',
				hidden : true,
				width: 140
			},{
				header:EwayLocale.version.View.versionPerson,//'创建人',
				hidden : true,
				dataIndex:'userName'
			},{
				header: EwayLocale.version.View.dependVersion,//'依赖版本',
				dataIndex : 'dependVersion',
				width: 300
			},{
				header: EwayLocale.version.View.versionFile,//'版本文件',
				dataIndex: 'serverPath',
				renderer:function(value,meta,record){
					if(value != null){
						var fileName = record.get("serverPath");
						var typeName =  record.get("versionType");
						fileName = fileName.replace("&","%26");
						var url = 'api/version/version/download?typeName=' + typeName + '&fileName=' + fileName
						return "<a class='link' href='"+url+"'>"+ value + "</a>";
					}else{
						return value;
					}
				},
				width: 300
			},{
				header: EwayLocale.version.View.versionStatus,//'版本状态',
				dataIndex :'versionStatus',
				renderer: function(value){
					if(value == 'NEW'){
						return EwayLocale.version.View.newCreate;//新建
					}else if(value == "WAITING"){
						return EwayLocale.version.View.waitting;//等待下发
					}else{
						return EwayLocale.version.View.downLoaded;//已下发
					}
				},
				width: 100
			},{
				header: EwayLocale.version.View.autoUpdate,//'允许自动更新',
				dataIndex : 'autoDown',
				renderer: function(value){
					if(value == true){
						return EwayLocale.version.View.autoUpdateYes;//"是";
					}else{
						return EwayLocale.version.View.autoUpdateNo;//"否";
					}
				},
				flex : 1
			}/*,{
				header: '自动解压缩',
				dataIndex : 'uncompress',
				renderer: function(value){
					if(value == true){
						return "是";
					}else{
						return "否";
					}
				},
				width: 100
			}*/,{
				header : EwayLocale.version.View.remark,
				dataIndex : 'desc',
				hidden : true
			}],
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