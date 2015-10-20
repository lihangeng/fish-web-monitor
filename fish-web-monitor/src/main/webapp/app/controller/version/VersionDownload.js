Ext.define('Eway.controller.version.VersionDownload', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'version.VersionDownload','version.JobType','version.JobStatus','version.JobPriority','version.TaskStatus','version.Task' ],
	models : [ 'version.VersionDownload','version.Task' ],
	views : [ 'version.download.View'],

	refs : [ {
		ref : 'ewayView',
		selector : '#versionDownload',
		autoCreate : true,
		xtype : 'versionDownloadView',
		id : 'versionDownload'
	}, {
		ref : 'grid',
		selector : 'version_download_grid'
	},{
		ref : 'filterForm',
		selector: 'version_download_filterForm'
	},{
		ref:'taskGrid',
		selector: 'version_download_taskGrid'
	}],

	init : function() {
		this.control({
			'#versionDownload':{
//				activate : this.onViewActivate,
				deactivate : this.onViewBeforeDeactivate
			},
			'#versionDownload button[action=query]' : {
				click : this.onQuery
			},
			'#versionDownload button[action=start]' : {
				click : this.onStart
			},
			'#versionDownload button[action=pause]' : {
				click : this.onPause
			},
			'#versionDownload button[action=remove]' : {
				click : this.onRemove
			},
			'#versionDownload version_download_grid' :{
				afterrender : this.onSelectFirst,
				select : this.onTask
			},
			'#versionDownload version_download_taskGrid pagingtoolbar':{
				beforechange :this.onTaskFresh
			},
			'#versionDownload version_download_grid pagingtoolbar':{
				beforechange :this.onJobFresh
			},
			'#versionDownload version_download_taskGrid button[action=taskquery]':{
				click :this.onTaskQuery
			},
			'#versionDownload version_download_taskGrid button[action=export]':{
				click :this.onTaskExport
			},
			'#versionDownload version_download_taskGrid button[action=rebootAll]':{
				click :this.onTaskRebootAll
			},
			'#versionDownload version_download_taskGrid button[action=autoRefresh]':{
				click :this.onAutoRefresh
			},
			'#versionDownload version_download_taskGrid button[action=resetStatus]':{
				click :this.onResetStatus
			}
		});
	},
	//任务处于某种状态无法继续需要重置任务状态
	onResetStatus:function(){
		var grid = this.getTaskGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get("taskStatus")==Eway.locale.version.taskStatus.checked||
					record.get("taskStatus")==Eway.locale.version.taskStatus.noticedFail||
					record.get("taskStatus")==Eway.locale.version.taskStatus.downloadedFail||
					record.get("taskStatus")==Eway.locale.version.taskStatus.deployedFail||
					record.get("taskStatus")==Eway.locale.version.taskStatus.noticeFail||
					record.get("taskStatus")==Eway.locale.version.taskStatus.checked||
					record.get("taskStatus")==Eway.locale.version.taskStatus.checked){
				Eway.alert(Eway.locale.version.download.checkedTaskCantResetTips);
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
			        Eway.alert(Eway.locale.version.taskStatus.taskResetSuccessTips);
			        grid.getStore().load();
			    }
			});
		}
		else{
			Eway.alert(Eway.locale.version.download.selectTask);
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
			btn.setText(Eway.locale.version.download.autoRefresh);//"开启自动刷新");
			btn.started = false;
			this.currentTask = null;
		}
	},

	//查询
	onQuery: function(){
		 this.onJobFresh();

		 var grid = this.getGrid();
		 var store = grid.getStore();
		 var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		 store.setUrlParamsByObject(data);
		 store.loadPage(1);
	},

	//刷新任务列表
	onTaskFresh : function(pagingtoolbar){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			//增加请求参数
			this.setTaskSearchFilter(record.get("id"));
		}
	},

	//刷新作业列表，清空任务列表
	onJobFresh : function(pagingtoolbar){
		 var taskStore = this.getTaskGrid().getStore();
		 this.clearTaskTip();
		 this.onViewBeforeDeactivate();
	},

	//选择一个作业时，刷新任务列表
	onTask: function(grid,record){
		var taskGrid = this.getTaskGrid();
		taskGrid.refresh(record.get('id'));
		this.clearTaskTip();
		this.onViewBeforeDeactivate();
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
			Eway.alert(Eway.locale.version.task.selectAJob);//"请选择一个作业.");
		}
	},

	hasSelectOne : function(){
		var grid = this.getGrid();
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			return sm.getLastSelected();;
		}
		return null;
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
				Eway.alert(Eway.locale.version.task.cantCancelCompleteJob);//'不能撤销"完成"状态的作业.');
			}else{
//					Ext.MessageBox.confirm("提示", "是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)", function(button,text) {
					Ext.MessageBox.confirm(Eway.locale.confirm.title, Eway.locale.version.task.doSureCancelTheJob, function(button,text) {
						
						if (button == "yes") {
							var winEl = grid.getEl();
							winEl.mask(Eway.locale.version.task.deleting);//'正在删除......');
							record.erase({
								success : function() {
									winEl.unmask();
									if(status  == 'RUN'){
										Eway.alert(Eway.locale.version.task.cancelSuccessBut);//'已经成功撤销作业中还没有运行的任务,此时作业的状态仍然是"运行中",请稍等后刷新作业列表.');
										//同时刷新任务列表页面
										Ext.StoreManager.get("version.Task").load();
									}else{
										store.remove(record);
										Eway.alert(Eway.locale.version.task.cancelJobSuccess);//"成功撤销作业.");
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
			Eway.alert(Eway.locale.version.task.selectAJob);//"请选择一个作业.");
		}
	},

	setTaskSearchFilter  : function(jobId){
		var extraParams = {};//{jobId : jobId};
		var taskGrid = this.getTaskGrid();
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
		var form = this.getFilterForm().getForm();
		if(!form.isValid()){
			return;
		}
		var data = this.getFilterForm().getForm().getValues();
		var store = this.getTaskGrid().getStore();
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},

	currentTask : null,

	//自动刷新任务列表
	onAutoRefresh : function(btn,e,options){
//		var grid = this.getTaskGrid();
//		var sm = grid.getSelectionModel();
//		if (sm.getCount() == 1) {
//			if(this.currentTask == null){
				this.currentTask = {
				   run : function() {
//				   		var record = sm.getLastSelected();
//						this.setTaskSearchFilter(record.get('id'));
						this.getTaskGrid().getStore().loadPage(1);
				    },
				   interval : 60000, //60秒刷新一次
				   scope : this
				   };
//			}
			if(btn.started){
				btn.setText(Eway.locale.version.download.autoRefresh);//"开启自动刷新");
				btn.started = false;
				Ext.TaskManager.stop(this.currentTask);
				this.currentTask = null;
			}else{
				btn.setText(Eway.locale.version.download.stopAutoRefresh);//"停止自动刷新");
				btn.started = true;
				Ext.TaskManager.start(this.currentTask);
			}
//		}else{
//			this.setTaskTip(Eway.locale.version.task.selectJobStartRefresh);//'请选择一个作业,再开启定时刷新！');
//		}
	},

	//导出升级报告
	onTaskExport : function(){
		var form = this.getFilterForm().getForm();
		if(!form.isValid()){
			return;
		}
		var data = form.getValues();
		var url = 'api/version/download/exportToExcel?terminalId='+data.terminalId+
		'&updateResult='+data.updateResult+'&taskType='+data.taskType+
		'&jobName='+data.jobName+'&versionTypeId='+data.versionTypeId+'&versionNo='+data.versionNo+'' ;
		var iframe = document.getElementById('downloadFileFromWeb');
		iframe.src = url;
	},

	//重启所有设备
	onTaskRebootAll : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.Ajax.request({
				method : 'POST',
				url : 'api/version/download/rebootAll' ,
				params : {
					'jobId' : record.get("id")
				},
				success : function(response){
					var text = Ext.decode(response.responseText);
					if(text.success){
						this.setTaskSearchFilter(record.get('id'));
						this.getTaskGrid().getStore().loadPage(1);
						Eway.alert(Eway.locale.version.task.sendRestartCmd);//"已发送重启命令.");
					}else{
						Eway.alert(text.errors);
					}
				}
			});
		}else{
			this.setTaskTip();
		}
	},

	setTaskTip : function(tip){
		var actionTip = this.getEwayView().down("tbtext[action=tip]");
		if(Ext.isEmpty(tip)){
	    	actionTip.setText('<font color="red">'+Eway.locale.version.task.selectAJob+'</font>');
//    	actionTip.setText('<font color="red">请选择一个作业.</font>');
		}else{
			actionTip.setText('<font color="red">'+tip+'</font>');
		}
	},

	clearTaskTip : function(){
		var actionTip = this.getEwayView().down("tbtext[action=tip]");
	    actionTip.setText('');
	}

});
