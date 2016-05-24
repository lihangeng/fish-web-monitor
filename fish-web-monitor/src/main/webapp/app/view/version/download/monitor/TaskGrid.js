Ext.define('Eway.view.version.download.monitor.TaskGrid', {
	alias : 'widget.version_download_monitor_taskgrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util'],

	name : 'taskDetails',
	store : 'version.monitor.Task',
	border : false,
	autoScroll : true,
	closable : true ,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		loadMask : true,
        enableTextSelection : true
	},
	config:{
		jobId:0
	},
	firstIn : true,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			initRegion : true,
			tbar: [{text:  EwayLocale.button.search,//'查询',
				iconCls : 'queryBtn',
				action: 'taskquery',
				tooltip:EwayLocale.version.download.taskQueryTips,//'根据条件查询选中作业下的详情信息'
				code:'taskQuery'
					},'->',{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},{
				text:EwayLocale.version.download.autoRefresh,//'开启自动刷新',
				action:'autoRefresh',
				glyph : 0xf021,
				tooltip:EwayLocale.version.download.autoRefreshTips,//'刷新周期60秒',
				started:false,
				code:'taskAutoRefresh',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},'-',{
				xtype : 'combo',
				fieldLabel : EwayLocale.version.task.updateResult,//'升级结果',
				name : 'updateResult',
				store : Ext.create('Ext.data.Store',{
					fields : ['value','name'],
					data : [
							{'value':'2','name':EwayLocale.version.task.updateResultRunning},
							{'value':'1','name':EwayLocale.version.task.updateResultSuccess},//'成功'},
						{'value':'0','name':EwayLocale.version.task.updateResultFailed}//'失败'}
					]
				}),
				queryMode : 'local',
				valueField : 'value',
				displayField : 'name',
				value:'',
				width: 250
			},{
				xtype:'textfield',
				fieldLabel:EwayLocale.refs.terminalId,//'设备编号',
				name:'terminalId',
				width: 250
			},{
				text:  EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'taskquery',
				tooltip:EwayLocale.version.download.taskQueryTips,//'根据条件查询选中作业下的详情信息'
				code:'taskQuery',
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
				text: '导出',
				iconCls : 'exportToExcel',
				action: 'export',
				tooltip:'导出选中作业下的全部下发结果',
				code : 'exportJobToExcel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
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
				hidden: true
			}/*,{
				xtype:'actioncolumn',
				width: 40,
				items : [{
					icon : 'resources/images/accept.png',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == '新建'){
							return '';
						}else {
							return 'hiddenComp';
						}
					}
				}]
			}*/,{
				header: EwayLocale.refs.terminalId,//'设备编号',
				dataIndex:'terminalId'
			},{
				header: EwayLocale.refs.ip,//'设备IP',
				dataIndex:'deviceIp',
				width:120,
				sortable: true
			},{
				header: EwayLocale.refs.orgName,//'所属机构',
				dataIndex:'orgName',
				sortable: true
			},{
				header:EwayLocale.version.task.beforeUpdate,//'分发前的版本',
				dataIndex:'versionBeforeUpdate'
			},{
//				header:'分发版本',
//				dataIndex:'version'
//			},{
				header:EwayLocale.version.task.exceptVersion,//'预期版本',
				dataIndex:'exceptVersion'
			},{
				header :EwayLocale.version.task.taskStatus,//'任务状态',
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
//				renderer:function(value,meta,record){
//					if(value == '部署已确认'){
//						return value + "&nbsp;<img src='resources/images/accept.png'>";
//					}else{
//						return value;
//					}
//				},
				width: 150
			},{
				header :EwayLocale.version.task.actionTime,//'执行时间',
				dataIndex:'excuteTime',
				width: 140
			},{
				header: EwayLocale.version.View.remark,//'备注',
				dataIndex:'reason',
				flex:1,
				renderer: function(value,metadata,record){
					if(value == 'NULL'){
		                	 return "";
		             }else{
		            	 return value;
		             }
				}
			},{
				xtype:'actioncolumn',
				width: 80,
				header: EwayLocale.version.task.restartATM,//'重启ATM',
				items : [{
					icon : 'resources/images/version/reboot.png',
					tooltip: EwayLocale.version.task.restartATM,//'重启ATM',
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
								'taskId' : taskId,
								'jobId' : me.getJobId()
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
			}),
			listeners:{
				activate:function( _this, eOpts ){
					var jobId = _this.getConfig().jobId;
					_this.getStore().setBaseParam("jobId",jobId);
					_this.getStore().load();
				}
			}
		});

		this.callParent(arguments);
	},

	refresh: function(jobId){
		var store = this.getStore();
		store.setBaseParam("jobId",jobId);
		store.loadPage(1);
	}
});