Ext.define('Eway.view.version.download.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionDownloadView',

	requires : ['Eway.view.version.download.TaskGrid','Eway.view.version.download.FilterForm'],

	title : '分发监控',
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						region : 'north',
						xtype : 'version_download_filterForm',
						height: 80
					},{
						region:'center',
						xtype:'version_download_taskGrid',
						action:'taskGrid'
				} ]
		});

		this.callParent(arguments);
	}
});