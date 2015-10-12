
Ext.define('Eway.view.monitor.business.BlackListCardGrid', {
	alias: 'widget.business_BlackListCardGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.monitor.business.BlackListCard');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:'查询',
				glyph : 0xf002,
				action:'query'
			},{
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'blackCardAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'blackCardUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'blackCardDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '批量导入',
				iconCls :'importBtn',
				action: 'import',
				code : 'blackCardImportBtn',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : '交易卡号',
				dataIndex : 'cardNo',
				width : 200
			}, {
				header : '用户姓名',
				dataIndex : 'userName'
			}, {
				header : '所属银行',
				dataIndex : 'organization'
			}, {
				header : '添加日期',
				dataIndex : 'addDate',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true,
				displayMsg : Eway.locale.commen.toolbar
			})
		});
		
		this.callParent(arguments);
	},
	
	onReload: function() {
		this.getStore().load();
	}
});