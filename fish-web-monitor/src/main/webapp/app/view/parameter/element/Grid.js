
Ext.define('Eway.view.parameter.element.Grid', {
	alias: 'widget.element_Grid',
	extend: 'Eway.view.base.Grid',

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.parameter.element.Element');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'elementAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'elementUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'elementDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '参数名称',
				dataIndex : 'paramName',
				flex : 1
			},{
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '参数值类型',
				dataIndex : 'paramType',
				flex : 2,
				flex : 2,
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==1){
						return "整型";
					}
					if(value==2){
						return "字符型";
					}
					if(value==3){
						return "布尔型";
					}
					if(value==4){
						return "IP地址型";
					}
				}
			},{
				header : '参数分类',
				dataIndex : 'paramClassify',
				flex : 2
			},{
				header : '参数权限',
				dataIndex : 'paramRights',
				flex : 2,
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==1){
						return "可编辑";
					}
					if(value==2){
						return "不可编辑";
					}
				}
			},{
				header : '归属的应用系统',
				dataIndex : 'paramBelongs',
				flex : 2,
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==1){
						return "ATMC系统";
					}
					if(value==2){
						return "监控客户端";
					}
				}
			},{
				header : '备注',
				dataIndex : 'remark',
				flex : 2
			},{
				header : '创建时间',
				dataIndex : 'createTime',
				flex : 2
			},{
				header : '最后修改时间',
				dataIndex : 'lastModifyTime',
				flex : 2
			}
			],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload: function() {
		this.getStore().load();
	}
});