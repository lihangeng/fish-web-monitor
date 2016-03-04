Ext.define('Eway.view.version.download.monitor.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionDownloadMonitorView',

	requires : ['Eway.view.version.download.monitor.TaskGrid',
	            'Eway.view.version.download.monitor.JobGrid',
	            'Eway.view.version.download.monitor.JobFilterForm'],

	title : EwayLocale.version.download.title,//'分发监控',
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region: 'center',
				xtype: 'tabpanel',
				plain:true,
				tabPosition : 'bottom',
				headerPosition: 'bottom',
				split: true,
				items : [{
						name:'groupPanel',
						xtype:'panel',
						title: '作业信息',
						layout : 'border',
						items:[{
								xtype:'version_download_monitor_jobfilterForm',
								region: 'north',
								height : 40
							}, {
								region: 'center',
								xtype : 'version_download_monitor_jobgrid'
							}
						]}
				]
			}],
			listeners : {
				activate : function(panel) {
					if (panel.isLoad) {
						return;
					}
					panel.isLoad = true;
					panel.down('version_download_monitor_jobgrid').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});