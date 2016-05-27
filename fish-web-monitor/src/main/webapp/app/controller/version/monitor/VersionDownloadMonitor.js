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
		ref:'taskPanel',
		selector: 'version_download_monitor_taskpanel'
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
			'#versionDownloadMonitorView version_download_monitor_taskpanel':{
				close :this.onDestory
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=taskquery]':{
				click :this.onTaskQuery
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=rebootAll]':{
				click :this.onTaskRebootAll
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=autoRefresh]':{
				click :this.onAutoRefresh
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=resetStatus]':{
				click :this.onResetStatus
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=toJob]':{
				click :this.taskClose
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=next]':{
				click :this.nextJob
			},
			'#versionDownloadMonitorView version_download_monitor_taskpanel button[action=pref]':{
				click :this.prefJob
			}
		});
	},

	nextJob:function(){
		this.jobPageChange("1");
	},
	prefJob:function(){
		this.jobPageChange("-1");
	},
	jobPageChange:function(flag){
		var me = this;
		var jobId = this.getActiveTask().getConfig().jobId;
		var panel = this.getTaskPanel().down("fieldset[name='jobDetailInfo']");
		Ext.Ajax.request({
		    url: 'api/version/download/searchJobDetailInfo',
		    method:'GET',
		    params: {
		    	jobId: jobId,
		        nextRecord:flag
		    },
		    success: function(response){
		        var text = response.responseText;
		        var object = Ext.decode(text);
		    	me.getActiveTask().setJobId(object.displayJobId);
				panel.removeAll();
		        var length = object.data.length;
		        for(var index=0;index<length;index++){
		        	var data = object.data[index];
		        	var display = undefined;
		        	if((index+1)%4==1){
		        		display = Ext.create("Ext.form.field.Display",{margin:'0 0 0 20',labelWidth : 105,columnWidth : .25,fieldLabel:data.frist,value: data.second});
		        	}else{
		        		display = Ext.create("Ext.form.field.Display",{labelWidth : 105,columnWidth : .25,fieldLabel:data.frist,value: data.second});
		        	}
		        		
		        	panel.add(display);
		        }
	    		me.onTaskQuery();
		    },
		    failure:function(){
		    		Eway.alert('查看作业失败');	
		    }

		});
	},
	onResetStatus:function(){
		var layout = this.getEwayView().down("panel[name=groupPanel]").up("panel").getLayout();
		var grid = layout.getActiveItem().down("version_download_monitor_taskgrid");
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
			    method:'POST',
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
	lastTaskGrid :null,
	onDestory:function(){
		if(this.currentTask != null){
			Ext.TaskManager.stop(this.currentTask);
			this.currentTask = null;
		}
	},
	
	
	onJobDetail:function(){
		var grid = this.getGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			this.onViewBeforeDeactivate();
			var record = sm.getLastSelected();
			var tabpanel = this.getEwayView().down("panel[name=groupPanel]").up("panel");
			var jobDetailPanel = this.getEwayView().down("version_download_monitor_taskpanel");
			jobDetailPanel.setJobId(record.get("id"));
//			tabpanel.add(jobDetailPanel);
			tabpanel.setActiveItem(jobDetailPanel);
			this.lastTaskGrid = jobDetailPanel;
			this.jobPageChange("0");
		} else {
			Eway.alert(EwayLocale.version.task.selectAJob);
		}
	},

	autoJobDetail:function(jobId){
		var me =this;
		var tabpanel = this.getEwayView().down("panel[name=groupPanel]").up("panel");
		Ext.Ajax.request({
		    url: 'api/version/download/getJobInfo',
		    method:'POST',
		    params: {
		        'jobId':jobId
		    },
		    success: function(response){
		        var object = Ext.decode(response.responseText);
				me.onViewBeforeDeactivate();
				var jobDetailPanel = this.getEwayView().down("version_download_monitor_taskpanel");
				jobDetailPanel.setJobId(record.get("id"));
				tabpanel.setActiveItem(jobDetailPanel);
				me.lastTaskGrid = jobDetailPanel;
				this.jobPageChange("0");
		    }
		});
	},

	taskClose:function(_this,opt){
		var taskPanel = _this.up("version_download_monitor_taskpanel");
		var layout = taskPanel.up("panel").getLayout();
		var groupPanel = this.getEwayView().down("panel[name=groupPanel]");
//		taskPanel.close();
		layout.setActiveItem(groupPanel);
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
			this.currentTask = null;
			var btn = this.lastTaskGrid.down("button[action=autoRefresh]");
			btn.setText(EwayLocale.version.download.autoRefresh);//"开启自动刷新");
			btn.started = false;
			this.currentTask = null;
		}
	},



	//暂停一个作业
	onCancelBatch: function(){
		var grid = this.getTaskPanel().down("version_download_monitor_taskgrid");
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
		var taskGrid = this.getActiveTask().down("version_download_monitor_taskgrid");
		var jobId = this.getActiveTask().getConfig().jobId;
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
		var grid = this.getActiveTask().down("version_download_monitor_taskgrid");
		var jobId = this.getActiveTask().getConfig().jobId;
		this.setTaskSearchFilter(jobId);
		grid.getStore().loadPage(1);
	},

	getActiveTask:function(){
		var layout = this.getEwayView().down("panel[name=groupPanel]").up("panel").getLayout();
		return layout.getActiveItem( );
	},

	onAutoRefresh : function(btn,e,options){
		var grid = this.getActiveTask().down("version_download_monitor_taskgrid");
		var jobId = this.getActiveTask().getConfig().jobId;
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
