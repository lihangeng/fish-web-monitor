Ext.define('Eway.view.machine.atmGroup.GroupGrid', {
	alias : 'widget.atmGroup_groupGrid',
	extend : 'Eway.view.base.Grid',
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.machine.atmGroup.DeviceGroup');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '增加',
				glyph : 0xf067,
				action : 'add',
				code : 'atmGroupAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '更改',
				glyph : 0xf040,
				action : 'update',
				code : 'atmGroupUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '删除',
				glyph : 0xf014,
				action : 'remove',
				code : 'atmGroupDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			columns : [ {
				header : '组名',
				dataIndex : 'name'
			}, {
				header : Eway.locale.commen.remark,
				dataIndex : 'note',
				flex : 1
			} ],
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