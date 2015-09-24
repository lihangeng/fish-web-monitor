Ext.define('Eway.view.version.autoUpdate.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionAutoUpdateView',

	requires : [ 'Eway.view.version.autoUpdate.Grid', 'Eway.view.version.autoUpdate.FilterForm'],

	title : '自动更新信息',
	layout : 'border',
	
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'versionupdate_filterForm',
		    	region:"north"
			}, {
				xtype : 'versionupdate_grid',
		    	region:"center"
			}],
			listeners : {
//				activate : function(panel) {
//					if (panel.isLoad) {
//						return;
//					}
//					panel.isLoad = true;
//					panel.down('versionupdate_grid').getStore().load();
//				}
			}
		});

		this.callParent(arguments);
	}
});