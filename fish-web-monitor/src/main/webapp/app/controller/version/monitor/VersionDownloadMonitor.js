Ext.define('Eway.controller.version.monitor.VersionDownloadMonitor', {
	extend : 'Eway.controller.base.FishController',
	stores : [ 'version.VersionDownload','version.JobType','version.JobStatus','version.JobPriority','version.TaskStatus','version.monitor.Task','version.monitor.Job' ],
	models : [ 'version.VersionDownload','version.monitor.Task','version.monitor.Job' ],
	views : [ 'version.download.monitor.View'],

	refs : [ {
		ref : 'ewayView',
		selector : '#versionDownloadMonitorView',
		autoCreate : true,
		xtype : 'versionDownloadMonitorView',
		id : 'versionDownloadMonitorView'
	},{
		ref : 'grid',
		selector : 'version_download_monitor_jobgrid'
	},{
		ref : 'filterForm',
		selector: 'version_download_monitor_jobfilterForm'
	},{
		ref:'taskGrid',
		selector: 'version_download_monitor_taskgrid'
	}],

	init : function() {
		this.control({
			'#versionDownloadMonitorView button[action=query]' : {
				click : this.onQuery
			},
			'#versionDownloadMonitorView button[action=start]' : {
				click : this.onStart
			},
			'#versionDownloadMonitorView button[action=pause]' : {
				click : this.onPause
			},
			'#versionDownloadMonitorView button[action=remove]' : {
				click : this.onRemove
			},
			'#versionDownloadMonitorView button[action=detail]' : {
				click : this.onJobDetail
			},
			'#versionDownloadMonitorView version_download_grid' :{
				afterrender : this.onSelectFirst
			},
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=taskquery]':{
				click :this.onTaskQuery
			},
			/*'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=export]':{
				click :this.onTaskExport
			},*/
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=rebootAll]':{
				click :this.onTaskRebootAll
			},
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=autoRefresh]':{
				click :this.onAutoRefresh
			},
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=resetStatus]':{
				click :this.onResetStatus
			}
		});
	},
	onResetStatus:function(){
		var grid = this.getEwayView().down("tabpanel").getActiveTab();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var taskStatus = record.get("taskStatusText");
			if(taskStatus==EwayLocale.version.taskStatus.checked||
					taskStatus==EwayLocale.version.taskStatus.noticedFail||
					taskStatus==EwayLocale.version.taskStatus.downloadedFail||
					taskStatus==EwayLocale.version.taskStatus.deployedFail||
					taskStatus==EwayLocale.version.taskStatus.noticeFail||
					taskStatus==EwayLocale.version.taskStatus.checked||
					taskStatus==EwayLocale.version.taskStatus.checked){
				Eway.alert(EwayLocale.version.download.checkedTaskCantResetTips);
				return;
			}
			Ext.Ajax.request({
			    url: 'api/version/download/resetTaskStatus',
			    method:'GET',
			    params: {
			        id: record.get('id')
			    },
			    success: function(response){
			        var text = response.responseText;
			        var object = Ext.decode(text);
			        if(object.success){
				        Eway.alert(EwayLocale.version.taskStatus.taskResetSuccessTips);
				        grid.getStore().load();
			        }
			        else{
			        	Eway.alert(object.errorMsg);
			        }
			    },
			    failure:function(){
					Eway.alert(EwayLocale.version.taskStatus.taskResetFailTips);
			    }
			    
			});
		}
		else{
			Eway.alert(EwayLocale.version.download.selectTask);
		}
	},

	//选中第一条记录
	onSelectFirst : function(grid){
		if(grid.getStore().getCount() > 0){
			grid.getSelectionModel().select(0);
		}
	},

	//查询
	onQuery: function(){
		 var grid = this.getGrid();
		 var store = grid.getStore();
		 var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		 store.setUrlParamsByObject(data);
		 store.loadPage(1);
	},


	//开始运行一个作业
	onStart : function(){
	},

	//暂停一个作业
	onPause: function(){
		var grid = this.getGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.Ajax.request({
			    url: 'api/version/download/pause',
			    params: {
			        id: record.get('id')
			    },
			    success: function(response){
			        var text = response.responseText;
			    }
			});
		}else{
			Eway.alert(EwayLocale.version.task.selectAJob);
		}
	},


	//删除一个作业
	onRemove: function(){
		var grid = this.getGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var status = record.get('jobStatus');
			if(status == 'COMPLETE'){
				Eway.alert(EwayLocale.version.task.cantCancelCompleteJob);
			}else{
					Ext.MessageBox.confirm(EwayLocale.confirm.title, EwayLocale.version.task.doSureCancelTheJob, function(button,text) {
						if (button == "yes") {
							var winEl = grid.getEl();
							winEl.mask(EwayLocale.version.task.deleting);
							record.erase({
								success : function() {
									winEl.unmask();
									if(status  == 'RUN'){
										Eway.alert(EwayLocale.version.task.cancelSuccessBut);
									}else{
										store.remove(record);
										Eway.alert(EwayLocale.version.task.cancelJobSuccess);
										this.onQuery();
									}
								},
								failure:  function(record,operation){
									winEl.unmask();
									Eway.alert(operation.getError());
								},
								scope : this
							});
						}
					}, this);
				}
		} else {
			Eway.alert(EwayLocale.version.task.selectAJob);
		}
	},
	currentTask : null,

	onJobDetail:function(){
		var grid = this.getGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var tabpanel = this.getEwayView().down("tabpanel");
//			var activeId = "job_"+record.get("id");

//			var activePanel = tabpanel.setActiveItem(activeId);
//			if(activePanel.id==activeId){
//				return;
//			}
			var jobDetailPanel = Ext.create("Eway.view.version.download.monitor.TaskGrid",{"jobId":record.get("id")});
//			jobDetailPanel.itemId="'"+activeId+"'";
			tabpanel.add(jobDetailPanel);
			jobDetailPanel.setTitle(record.get("jobName"));
			tabpanel.setActiveItem(jobDetailPanel);
//			jobDetailPanel.id="'"+activeId+"'";
		} else {
			Eway.alert(EwayLocale.version.task.selectAJob);
		}
		var autoRefreshButton = jobDetailPanel.down("button[action=autoRefresh]");
		Ext.Function.defer(this.onAutoRefresh,500,this,[autoRefreshButton]);
	},
	
	autoJobDetail:function(jobId){
		var tabpanel = this.getEwayView().down("tabpanel");
//		var activeId = "job_"+jobId;
//		var activePanel = tabpanel.setActiveTab(activeId);
//		if(activePanel.id==activeId){
//			return;
//		}
		
		Ext.Ajax.request({
		    url: 'api/version/download/getJobInfo',
		    method:'POST',
		    params: {
		        'jobId':jobId
		    },
		    success: function(response){
		        var object = Ext.decode(response.responseText);
		        var jobDetailPanel = Ext.create("Eway.view.version.download.monitor.TaskGrid",{"jobId":jobId});
//		        jobDetailPanel.itemId="job_"+jobId;
//				jobDetailPanel.id="'"+activeId+"'";
				tabpanel.add(jobDetailPanel);
				jobDetailPanel.setTitle(object.total.jobName);
				tabpanel.setActiveItem(jobDetailPanel);
		    }
		});
	},
	
	
	//---------------------------------------------------------
	
	
	//选中第一条记录
	onSelectFirst : function(grid){
		if(grid.getStore().getCount() > 0){
			grid.getSelectionModel().select(0);
		}
	},
	onViewActivate : function(){
		 //清空查询条件
		 this.getFilterForm().getForm().reset();
		 var store =  this.getGrid().getStore();
		 store.loadPage(1);
	},
	onViewBeforeDeactivate : function(){
		if(this.currentTask != null){
			Ext.TaskManager.stop(this.currentTask);
			var btn = this.getTaskGrid().down("button[action=autoRefresh]");
			btn.setText(EwayLocale.version.download.autoRefresh);//"开启自动刷新");
			btn.started = false;
			this.currentTask = null;
		}
	},
	

	
	//暂停一个作业
	onCancelBatch: function(){
		var grid = this.getTaskGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.Ajax.request({
			    url: 'api/version/download/pause',
			    params: {
			        id: record.get('id')
			    },
			    success: function(response){
			        var result = Ext.decode(response.responseText);
			        if(result.success){
			        	Eway.alert(EwayLocale.version.task.cancelDownloadSuccess);
			        }
			    }
			});
		}else{
			Eway.alert(EwayLocale.version.task.selectAJob);//"请选择一个作业.");
		}
	},




	setTaskSearchFilter  : function(jobId){
		var extraParams = ""||{"jobId" : jobId};
		var taskGrid = this.getActiveTask()
		var jobId = taskGrid.getConfig().jobId;
		var fields = taskGrid.query('field');
		Ext.each(fields,function(field){
			if(field.name == 'updateResult' || field.name == 'terminalId'){
				extraParams[field.name] = field.getValue();
			}
		});
		taskGrid.getStore().proxy.extraParams = extraParams;
	},

	//查找任务
	onTaskQuery : function(){
		var grid = this.getActiveTask()
		var jobId = grid.getConfig().jobId;
		this.setTaskSearchFilter(jobId);
		grid.getStore().loadPage(1);
	},
	currentTask : null,
	
	getActiveTask:function(){
		return this.getEwayView().down("tabpanel").getActiveTab();
	},
	
	onAutoRefresh : function(btn,e,options){
		var grid = this.getActiveTask()
		var jobId = grid.getConfig().jobId;
			if(this.currentTask == null){
				this.currentTask = {
				   run : function() {
						this.setTaskSearchFilter(jobId);
						grid.getStore().loadPage(1);
				    },
				   interval : 60000, //60秒刷新一次
				   scope : this
				   };
			}
			if(btn.started){
				btn.setText(EwayLocale.version.download.autoRefresh);
				btn.started = false;
				Ext.TaskManager.stop(this.currentTask);
				this.currentTask = null;
			}else{
				btn.setText(EwayLocale.version.download.stopAutoRefresh);
				btn.started = true;
				Ext.TaskManager.start(this.currentTask);
			}
	}


});
