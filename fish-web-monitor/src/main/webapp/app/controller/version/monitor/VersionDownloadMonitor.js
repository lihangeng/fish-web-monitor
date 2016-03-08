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
			},
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=rebootAll]':{
				click :this.onTaskRebootAll
			},
			'#versionDownloadMonitorView version_download_monitor_taskgrid button[action=autoRefresh]':{
				click :this.onAutoRefresh
			}
		});
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
			Ext.Msg.alert("提示", "请选择一个作业.");
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
				Ext.Msg.alert("提示", '不能撤销"完成"状态的作业.');
			}else{
					Ext.MessageBox.confirm("提示", "是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)", function(button,text) {
						if (button == "yes") {
							var winEl = grid.getEl();
							winEl.mask('正在删除......');
							record.erase({
								success : function() {
									winEl.unmask();
									if(status  == 'RUN'){
										Ext.Msg.alert("提示", '已经成功撤销作业中还没有运行的任务,此时作业的状态仍然是"运行中",请稍等后刷新作业列表.');
										//同时刷新任务列表页面
										Ext.StoreManager.get("version.Task").load();
									}else{
										store.remove(record);
										Ext.Msg.alert("提示", "成功撤销作业.");
										this.onQuery();
									}
								},
								failure:  function(record,operation){
									winEl.unmask();
									Ext.Msg.alert("提示", operation.request.scope.reader.jsonData.errors);
								},
								scope : this
							});
						}
					}, this);
				}
		} else {
			Ext.Msg.alert("提示", "请选择一个作业.");
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
			var activeId = "job_"+record.get("jobName");
			var activePanel = tabpanel.setActiveTab(activeId);
			if(activePanel.id==activeId){
				return;
			}
			var jobDetailPanel = Ext.create("Eway.view.version.download.monitor.TaskGrid",{"jobId":record.get("id")});
			jobDetailPanel.id=activeId;
			tabpanel.add(jobDetailPanel);
			jobDetailPanel.setTitle(record.get("jobName"));
			tabpanel.setActiveItem(jobDetailPanel);
		} else {
			Ext.Msg.alert("提示", "请选择一个作业.");
		}
		var autoRefreshButton = jobDetailPanel.down("button[action=autoRefresh]");
		Ext.Function.defer(this.onAutoRefresh,500,this,[autoRefreshButton]);
	},
	
	autoJobDetail:function(jobId,jobName){
		var tabpanel = this.getEwayView().down("tabpanel");
		var activeId = "job_"+jobName;
		var activePanel = tabpanel.setActiveTab(activeId);
		if(activePanel.id==activeId){
			return;
		}
		var jobDetailPanel = Ext.create("Eway.view.version.download.monitor.TaskGrid",{"jobId":jobId});
		jobDetailPanel.id=activeId;
		tabpanel.add(jobDetailPanel);
		jobDetailPanel.setTitle(jobName);
		tabpanel.setActiveItem(jobDetailPanel);
	},
	
	
	//---------------------------------------------------------
	
	//任务处于某种状态无法继续需要重置任务状态
	onResetStatus:function(){
		var grid = this.getTaskGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get("taskStatus")==EwayLocale.version.taskStatus.checked||
					record.get("taskStatus")==EwayLocale.version.taskStatus.noticedFail||
					record.get("taskStatus")==EwayLocale.version.taskStatus.downloadedFail||
					record.get("taskStatus")==EwayLocale.version.taskStatus.deployedFail||
					record.get("taskStatus")==EwayLocale.version.taskStatus.noticeFail||
					record.get("taskStatus")==EwayLocale.version.taskStatus.checked||
					record.get("taskStatus")==EwayLocale.version.taskStatus.checked){
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
	//刷新任务列表
	onTaskFresh : function(pagingtoolbar){

		var taskGrid = this.getActiveTask()
		var jobId = taskGrid.getConfig().jobId;
//		var sm = grid.getSelectionModel();
//		if (sm.getCount() == 1) {
//			var record = sm.getLastSelected();
			//增加请求参数
			this.setTaskSearchFilter(jobId);
			taskGrid.getStore().loadPage(1);
//		}
	},
	//选择一个作业时，刷新任务列表
	onTask: function(grid,record){
		var taskGrid = this.getTaskGrid();
		taskGrid.refresh(record.get('id'));
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
			Eway.alert(EwayLocale.vtype.choseTask);//"请选择一个作业.");
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
//		Eway.alert(grid.getTitle());
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
<<<<<<< HEAD
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
				btn.setText("开启自动刷新");
				btn.started = false;
				Ext.TaskManager.stop(this.currentTask);
				this.currentTask = null;
			}else{
				btn.setText("停止自动刷新");
				btn.started = true;
				Ext.TaskManager.start(this.currentTask);
			}
	}/*,
=======
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
			btn.setText("开启自动刷新");
			btn.started = false;
			Ext.TaskManager.stop(this.currentTask);
			this.currentTask = null;
		}else{
			btn.setText("停止自动刷新");
			btn.started = true;
			Ext.TaskManager.start(this.currentTask);
		}
	},
>>>>>>> refs/remotes/origin/master
	//导出升级报告
	onTaskExport : function(){
		var taskGrid = this.getActiveTask()
		var jobId = taskGrid.getConfig().jobId;
		var url = 'api/version/download/exportToExcel?jobId=' + jobId ;
		var iframe = document.getElementById('downloadFileFromWeb');
		iframe.src = url;
	}*/


});
