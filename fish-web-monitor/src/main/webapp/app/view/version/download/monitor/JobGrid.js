
Ext.define('Eway.view.version.download.monitor.JobGrid', {
	alias: 'widget.version_download_monitor_jobgrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util','Ext.ux.PreviewPlugin'],

	border : false,
	autoScroll : true,

	 viewConfig: {
//            plugins: [{
//                ptype: 'preview',
//                bodyField: 'extraBody',
//                expanded: true,
//                pluginId: 'preview'
//                	
//            }],
            getRowClass: function(record, rowIndex, rowParams, store){
    			if(record.get('jobStatus') == 'RUN'){
    				return "running";
    			}else if(record.get('jobStatus') == 'SCHEDULER'){
    				return "plan";
    			}else if(record.get('runTaskCount') != 0){
    				return "grey";
    			}
            	return "";
       		},
            enableTextSelection : true
        },

	initComponent: function() {
		var gridStore = Ext.create('Eway.store.version.monitor.Job',{
			listeners: {
				load : function(store,records){
							if(records.length > 0){
								var sm = this.getSelectionModel();
								sm.select(records[0]);
								sm.lastSelected = records[0];
							}
					   },
				scope : this
			}
		});
		Ext.apply(this, {
			initRegion : true,
			store: gridStore,
			tbar: ['->',{
				text: EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'query',
				code:'jobQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*, {
				text: '创建新的作业',
				action: 'add'
			}, {
				text:'开始',
				action: 'start'
			},{
				text: '暂停',
				action: 'pause'
			}*/, {
				text: EwayLocale.version.download.cancelJob,//'撤销作业',
				glyph : 0xf014,
				action: 'remove',
				code : 'versionMonitorDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.version.download.jobDetail,//'查看作业明细',
				glyph : 0xf129,
				action: 'detail',
				code : 'jobDetail',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [
			{
				header: EwayLocale.version.task.jobId,//'作业ID',
				dataIndex: 'id',
				width: 80
			},{
				header: EwayLocale.version.View.versionName,//'下发的版本',
				dataIndex: 'versionName',
				sortable: true,
				width: 180
			}/*,{
				header : '作业名称',
				dataIndex : 'jobName',
				sortable : true
			},{
				header: '版本文件',
				dataIndex: 'versionFile',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";;
					}else{
						return value;
					}
				},
				width: 200
			}*//*,{
				header: '作业类型',
				dataIndex: 'jobType',
				renderer: Eway.lib.Util.dictRenderer('version.JobType'),
				sortable: true,
				width: 120
			},{
				header: '作业优先级',
				dataIndex: 'jobPriority',
				renderer: Eway.lib.Util.dictRenderer('version.JobPriority'),
				sortable: true
			}*/,{
				header : EwayLocale.version.planTime,//'计划执行时间',
				dataIndex : 'planTime',
			/*	xtype:'datecolumn',
				format:'Y-m-d H:i:s',*/
				sortable : true,
				width: 150
			},{
				header: EwayLocale.version.task.jobStatus,//'作业状态',
				dataIndex: 'jobStatus',
				renderer: Eway.lib.Util.dictRenderer('version.JobStatus'),
				sortable: true,
				width: 120
			},{
				header : EwayLocale.version.taskType,//'备注',
				dataIndex : 'jobType',
				renderer: Eway.lib.Util.dictRenderer('version.JobType'),
				width: 120,
				sortable : true
			},{
				header: EwayLocale.version.download.allTaskCount,//'总任务数',
				dataIndex: 'allTaskCount',
				sortable: true,
				width: 120
			},{
				header: EwayLocale.version.download.finishTaskCount,//'任务完成数',
				dataIndex:'finishTaskCount',
				sortable: true,
				width: 120
			},{
				header: EwayLocale.version.download.failTaskCount,//'任务失败数',
				dataIndex:'failTaskCount',
				sortable: true,
				width: 120
			},{
				header: EwayLocale.version.download.runTaskCount,//'进行中任务数',
				dataIndex:'runTaskCount',
				sortable: true,
				flex:1
			}],
			bbar : Ext.create('Ext.toolbar.Paging',{
				store : gridStore,
				displayInfo : true
			}),
			listener:{
				
			}
		});

		this.callParent(arguments);
	}
});