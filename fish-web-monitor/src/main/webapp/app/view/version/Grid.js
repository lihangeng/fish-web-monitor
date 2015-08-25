
Ext.define('Eway.view.version.Grid', {
	alias: 'widget.version_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	store: 'version.Version',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}/*,{
				text: '自动下发',
				tooltip:'设置自动下发标志',
				action:'autoDown'
			}*/,{
				text: '下发',
				tooltip:'配置下发作业',
//				iconCls : 'versionDown',
				glyph : 0xf0ed,
				action: 'down',
				code : 'versionExportBtn',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'versionAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'versionUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'versionDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*, {
				text: '下发统计',
				iconCls :'downStatics',
				action: 'downStatics',
				code : 'downStatics',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}*/],
			columns : [{
				header : '软件分类编码',
				dataIndex : 'versionType',
				width: 150
			},{
				header:'版本号',
				dataIndex:'versionNo',
				width: 100
			},{
				header : '版本类型ID',
				dataIndex : 'versionTypeId',
				hidden : true
			},{
				header : '版本路径',
				dataIndex : 'versionPath',
				width: 200
			},{
				header : '创建时间',
				dataIndex : 'createdTime',
				width: 140
			},{
				header:'创建人',
				dataIndex:'userName'
			},{
				header: '依赖版本',
				dataIndex : 'dependVersion',
				width: 140
			},{
				header: '版本文件',
				dataIndex: 'serverPath',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				},
				width: 120
			},{
				header: '版本状态',
				dataIndex :'versionStatus',
				renderer: function(value){
					if(value == 'NEW'){
						return "新建";
					}else if(value == "WAITING"){
						return "等待下发";
					}else{
						return  "已下发";
					}
				}
			},{
				header: '允许自动更新',
				dataIndex : 'autoDown',
				renderer: function(value){
					if(value == true){
						return "是";
					}else{
						return "否";
					}
				},
				width: 100
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
				header : '备注',
				dataIndex : 'desc',
				flex : 1
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