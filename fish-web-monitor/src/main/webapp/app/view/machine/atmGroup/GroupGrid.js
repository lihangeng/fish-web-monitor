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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'atmGroupAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'atmGroupUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
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
				text: EwayLocale.monitor.devMonitor.atmGroupTip,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
		} ],
			columns : [ {
				header : EwayLocale.machine.atmGroup.groupName,
				dataIndex : 'name'
			}, {
				header : EwayLocale.machine.atmGroup.note,
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