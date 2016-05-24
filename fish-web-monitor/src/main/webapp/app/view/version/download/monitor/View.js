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
				xtype: 'panel',
				layout : 'card',
				items : [{
						name:'groupPanel',
						xtype:'panel',
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
					panel.down('version_download_monitor_jobgrid').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});