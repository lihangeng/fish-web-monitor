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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'atmGroupAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'atmGroupUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'atmGroupDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
			    
				action : 'showDetail',
				code : 'showDetail',
				glyph : 0xf129,
				text: Eway.locale.monitor.devMonitor.atmGroupTip,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
		} ],
			columns : [ {
				header : Eway.locale.machine.atmGroup.groupName,
				dataIndex : 'name'
			}, {
				header : Eway.locale.machine.atmGroup.note,
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