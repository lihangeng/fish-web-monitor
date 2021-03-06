Ext.define('Eway.view.version.download.TaskGrid', {
	alias : 'widget.version_download_taskGrid',
	extend : 'Ext.grid.Panel',

	requires : [ 'Eway.lib.Util'],

	store : 'version.Task',
	border : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		loadMask : false
	},
	firstIn : true,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar: ['->',{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'-',{
				text: EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'taskquery',
				tooltip:EwayLocale.version.download.taskQueryTips//'根据条件查询选中作业下的详情信息'
			},{
				text: EwayLocale.button.exported,//'导出',
				glyph : 0xf1c3,
				action: 'export',
				tooltip:EwayLocale.version.download.taskExportTips,//'导出选中作业下的全部下发结果',
				code : 'exportJobToExcel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text:EwayLocale.version.download.autoRefresh,//'开启自动刷新',
				glyph : 0xf021,
				action:'autoRefresh',
				tooltip:EwayLocale.version.download.autoRefreshTips,//'刷新周期60秒',
				started:false
			},{
				text:EwayLocale.version.download.cancelBatch,//'批次取消',
				action:'cancelBatch',
				glyph : 0xf014,
				code : 'cancelBatch',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text:EwayLocale.version.download.resetTaskStatus,//'重置任务状态',
				action:'resetStatus',
				glyph : 0xf013,
				code : 'resetStatus',
				tooltip:EwayLocale.version.download.resetTaskStatus,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*,{
				text: '全部重启',
				iconCls : 'exportToExcel',
				action: 'rebootAll',
				tooltip:'重启该作业下的可以重启的全部设备',
				code : 'rebootAll',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}*/],
			columns : [
			{
				header : 'ID',
				dataIndex : 'id',
//	            locked: true,
				hidden: true
			},{
				header: EwayLocale.version.task.jobBatchName,//'作业批次名称',
				width:180,
				dataIndex:'jobName'

			},{
				header: EwayLocale.refs.terminalId,//'设备编号',
				width:100,
				dataIndex:'terminalId'
			},{
				header:EwayLocale.version.task.patchVersion,//'分发版本',
				width:140,
				dataIndex:'version'
			},{
				header :EwayLocale.version.task.taskStatus,//'任务状态',
//	            locked: true,
				dataIndex:'taskStatus',
				renderer: function(value,meta,record) {
					if (value == EwayLocale.version.taskStatus.downloading) {
						 var process = record.get('process');
			            return value + '('+ process + '%)';
					} else if(value == EwayLocale.version.taskStatus.checked){//'部署已确认'){
						return value + "&nbsp;<img src='resources/images/accept.png'>";
					} else {
						return value;
					}
				},
				width: 150
			},{
				header: EwayLocale.refs.ip,//'设备IP',
				dataIndex:'deviceIp',
				width: 120,
				sortable: true
			},{
				header: EwayLocale.refs.orgName,
				dataIndex:'orgName',
				width: 170,
				sortable: true
			},{
				header:EwayLocale.version.task.beforeUpdate,//'分发前的版本',
				width: 170,
				dataIndex:'versionBeforeUpdate'
			},{
				header:EwayLocale.version.task.exceptVersion,//'预期版本',
				width: 120,
				dataIndex:'exceptVersion'
			},{
				header :EwayLocale.version.task.actionTime,//'执行时间',
				dataIndex:'excuteTime',
				width: 140
			},{
				header :EwayLocale.version.task.downSource,//'下载源',
				width: 120,
				dataIndex:'downSource'
			},{
				header :EwayLocale.version.task.planTime,//'计划时间',
				dataIndex:'planTime',
				width: 140
			},{
				header :EwayLocale.version.task.excuteMachine,//'执行服务器',
				dataIndex:'excuteMachine',
				width: 120
			},{
				header: EwayLocale.version.View.remark,
				dataIndex:'reason',
				width: 250
			},{
				xtype:'actioncolumn',
				width: 100,
				header: EwayLocale.version.task.restartATM,//'重启ATM',
				menuText : EwayLocale.version.task.restartATM,
				items : [{
					icon : 'resources/images/version/reboot.png',
					tooltip: '重启ATM',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == EwayLocale.version.taskStatus.deployedWait){//'等待部署'){
							return '';
						}else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var taskId = record.get('id');
//						Ext.MessageBox.confirm('提示','执行重启命令可能存在风险,确认重启?',callBack);
						Ext.MessageBox.confirm(EwayLocale.confirm.title,EwayLocale.version.task.restartATMTips,callBack);

	            		function callBack(id){
	            			if(id == 'yes'){
								Ext.Ajax.request({
									method : 'POST',
									url : 'api/version/download/rebootOne' ,
									params : {
										'taskId' : taskId
									},
									success : function(response){
										var text = Ext.decode(response.responseText);
										if(text.success){
											Eway.alert(EwayLocale.version.task.sendRestartCmd);//"已发送重启命令！");
										}else{
											Eway.alert(text.errorMsg);
										}
										grid.getStore().load();
									}
								});
			            	}
	            		}
					},
					scope : this
				}]
			},{
				xtype:'actioncolumn',
				width: 100,
				header: EwayLocale.version.task.cancelDownload,//'取消下发',
				menuText : EwayLocale.version.task.cancelDownload,
				items : [{
					icon : 'resources/images/remove.png',
					tooltip: EwayLocale.version.task.cancelDownload,//'取消下发',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == EwayLocale.version.View.newCreate){
							return '';
						}else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var taskId = record.get('id');
						Ext.Ajax.request({
							method : 'POST',
							url : 'api/version/download/task/cancel' ,
							params : {
								'taskId' : taskId
							},
							success : function(response){
								var text = Ext.decode(response.responseText);
								if(text.success){
									Eway.alert(EwayLocale.version.task.cancelDownloadSuccess);//"取消下发 成功！");
								}else{
									if(text.errorMsg !== ""){
										Eway.alert(text.errorMsg);
									}
								}
								grid.getStore().load();
							}
						});
					},
					scope : this
				}]
			}
			],
			bbar : Ext.create('Ext.toolbar.Paging',{
				store : this.store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	refresh: function(jobId){
		var store = this.getStore();
		store.setBaseParam("jobId",jobId);
		store.loadPage(1);
	}
});
