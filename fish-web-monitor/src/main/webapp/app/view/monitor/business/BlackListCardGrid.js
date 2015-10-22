
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
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'blackCardAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'blackCardUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'blackCardDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: Eway.locale.monitor.business.blackList.importData,
				iconCls :'importBtn',
				action: 'import',
				code : 'blackCardImportBtn',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : Eway.locale.monitor.business.transaction.card,
				dataIndex : 'cardNo',
				width : 200
			}, {
				header : Eway.locale.monitor.business.transaction.userName,
				dataIndex : 'userName'
			}, {
				header : Eway.locale.monitor.business.blackList.cardBank,
				dataIndex : 'organization'
			}, {
				header : Eway.locale.monitor.business.blackList.addDate,
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