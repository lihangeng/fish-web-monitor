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
				xtype : 'version_filterForm'
			}, {
				xtype : 'version_grid',
				border: true,
				minHeight:300
			}, {
				xtype : 'panel',
				layout: {
			        type: 'table',
			        columns: 2
			    },
			    defaults: {
			    	frame: true,
			    	border:3,
			    	width: 300
			    },
			    items:[{
			    	xtype:'bar_3d',
			    	rowField:'title',
			    	columnField:'value'
			    },{//当选择一个图形的内容时，现实对应的设备信息（Grid）
			    	xtype:'version_charts_grid',
//			    	xtype:'panel',
			    	minWidth:800,
			    	minHeight:350
			    }]
			} ]
		});

		this.callParent(arguments);
	}
});