
Ext.define('Eway.view.version.download.SelectableTaskGrid', {
	alias: 'widget.version_download_selectableTaskGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	store: 'version.Task',
	border : false,
	
	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: EwayLocale.commen.selectAll,
				action: 'selectAll'
			}, {
				text: EwayLocale.commen.selectNon,
				action: 'selectNone'
			}],
			columns : [{
				header : EwayLocale.version.task.jobName,//'作业名称',
				dataIndex : 'jobName',
				sortable : true
			},{
				header : EwayLocale.version.planTime,//'计划执行时间',
				dataIndex : 'planTime',
				sortable : true
			},{
				header: EwayLocale.version.View.downloadVersionName,//'下发的版本',
				dataIndex: 'versionName',
				sortable: true
			},{
				header: EwayLocale.advert.jobType,//'作业类型',
				dataIndex: 'jobType',
				sortable: true
			},{
				header: EwayLocale.advert.jobPriority,//'作业优先级',
				dataIndex: 'jobPriority',
				sortable: true
			},{
				header: EwayLocale.version.task.jobStatus,//'作业状态',
				dataIndex: 'jobStatus',
				sortable: true
			},{
				header : EwayLocale.version.View.remark,
				dataIndex : 'desc',
				sortable : true
			}],
			dockedItems: [{  //分页栏
		        xtype: 'pagingtoolbar',
		        store: this.store,   // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
   			 }]
		});
		
		this.callParent(arguments);
	}
});