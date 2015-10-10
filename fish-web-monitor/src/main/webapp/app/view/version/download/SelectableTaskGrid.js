
Ext.define('Eway.view.version.download.SelectableTaskGrid', {
	alias: 'widget.version_download_selectableTaskGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	store: 'version.Task',
	border : false,
	
	initComponent: function() {
		Ext.apply(this, {
			tbar: ['->', {
				text: Eway.locale.commen.selectAll,
				action: 'selectAll'
			}, {
				text: Eway.locale.commen.selectNon,
				action: 'selectNone'
			}],
			columns : [{
				header : '作业名称',
				dataIndex : 'jobName',
				sortable : true
			},{
				header : '计划执行时间',
				dataIndex : 'planTime',
				sortable : true
			},{
				header: '下发的版本',
				dataIndex: 'versionName',
				sortable: true
			},{
				header: '作业类型',
				dataIndex: 'jobType',
				sortable: true
			},{
				header: '作业优先级',
				dataIndex: 'jobPriority',
				sortable: true
			},{
				header: '作业状态',
				dataIndex: 'jobStatus',
				sortable: true
			},{
				header : Eway.locale.commen.remark,
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