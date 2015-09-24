Ext.define('Eway.view.version.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionView',

	requires : [ 'Eway.view.version.Grid', 'Eway.view.version.ChartsGrid',
	             'Eway.view.version.charts.Bar3dBasic','Eway.view.version.FilterForm','Eway.view.version.VersionInstallInfo' ],

	title : '版本管理',
	scrollable : 'y',
	
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'version_filterForm',
			    	region:"north"
			}, {
				xtype : 'version_grid',
		    	region:"center"
			}, {
				xtype : 'panel',
				layout : 'border',
				height:350,
		    	region:"south",
			    items:[{
			    	xtype:'bar_3d',
			    	region:"west",
			    	rowField:'title',
			    	columnField:'value'
			    },{//当选择一个图形的内容时，现实对应的设备信息（Grid）
			    	xtype:'version_charts_grid',
			    	region: "center" 
			    }]
			} ],
			listeners : {
				activate : function(panel) {
					if (panel.isLoad) {
						return;
					}
					panel.isLoad = true;
					panel.down('version_grid').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});