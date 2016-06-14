Ext.define('Eway.controller.parameter.paramMonitor.ParamMonitor',{
	extend :'Eway.controller.base.FishController',
	
	stores :['parameter.paramMonitor.JobMonitor','Eway.store.parameter.paramMonitor.TaskMonitor'],
	models :['parameter.paramMonitor.JobMonitor','Eway.model.parameter.paramMonitor.TaskMonitor'],
	views  :['Eway.view.parameter.paramMonitor.View','Eway.view.parameter.paramMonitor.JobGrid',
	         'Eway.view.parameter.paramMonitor.TaskView','Eway.view.parameter.paramMonitor.TaskGrid'],
	
	
	refs :[{
		ref:'ewayView',
		selector:'#parameter_paramMonitor_view',
		xtype :'parameter_paramMonitor_view',
		autoCreate:true,
		id:'parameter_paramMonitor_view'
	},{
		ref:'jobView',
		selector:'parameter_paramMonitor_JobView'
	},{
		ref:'jobGrid',
		selector:'parameter_paramMonitor_JobGrid'
	},{
		ref:'taskView',
		selector:'parameter_paramMonitor_TaskView'
	},{
		ref:'taskGrid',
		selector:'parameter_paramMonitor_TaskGrid'
	}],
	
	
	init : function(){
		this.control({
			'parameter_paramMonitor_JobGrid button[action=query]' : {
				click :this.onQuery
			},
			'parameter_paramMonitor_JobGrid button[action=detail]':{
				click:this.onDetail
			},
			'parameter_paramMonitor_TaskGrid button[action=query]':{
				click:this.onTaskQuery
			},
			'parameter_paramMonitor_TaskView button[action=back]':{
				click:this.onBack
			},
			'parameter_paramMonitor_TaskView button[action=pref]':{
				click:this.onPref
			},
			'parameter_paramMonitor_TaskView button[action=next]':{
				click:this.onNext
			},
			'parameter_paramMonitor_TaskGrid':{
				cellclick:this.onCellClick
			}
		});
	},
	
	jobId:0,
	index:0,
	onDetail:function(_this){
		var jobGrid=this.getJobGrid();
		var sm=jobGrid.getSelectionModel();
		if(sm.getCount() == 1){
			var record=sm.getLastSelected();
			this.index = jobGrid.getStore().indexOf(record);
			var layout = this.getEwayView().getLayout();
			layout.setActiveItem(1);
			this.jobId = record.get('id');
			this.getTaskView().setTitle(EwayLocale.param.paramDownloadMonitor.job+record.get('id')+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			this.onTaskQuery();
		}else {
			Eway.alert(EwayLocale.param.paramDownloadMonitor.chooseRecord);
		}
	},
	onTaskQuery : function(){
		var view = this.getEwayView();
		var form = view.down('parameter_paramMonitor_TaskFilterForm').getForm();
		var bool = form.isValid();
		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var taskGrid = view.down('parameter_paramMonitor_TaskGrid');
		var store = taskGrid.getStore();
		if(this.jobId!=0){
			store.setUrlParamsByObject(values);
			store.setBaseParam('publishId',this.jobId);
			store.loadPage(1);
		}
	},
	autoJobDetail:function(autoJobId){
		if(autoJobId !=null){
			var view = this.getEwayView();
			var layout = view.getLayout();
			this.jobId = autoJobId;
			layout.setActiveItem(1);
			this.getTaskView().setTitle(EwayLocale.param.paramDownloadMonitor.job+autoJobId+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			var store = this.getTaskGrid().getStore();
			store.setBaseParam('publishId',autoJobId);
			store.loadPage(1);
		}
		Ext.Function.defer(this.onTaskQuery,5000,this);
	},
	
	onBack:function(){
		var layout = this.getEwayView().getLayout();
		layout.setActiveItem(0);
	},
	
	onNext:function(_this, e, eOpts){
		var store = this.getJobGrid().getStore();
		if(this.index - 1 >=0){
			var rec = store.getAt(this.index - 1);
			this.showJobDetail(rec);
			this.index = this.index - 1;
		}
	},
	
	onPref:function(){
		var store = this.getJobGrid().getStore();
		var count = store.getCount();
		if(this.index + 1 < count){
			var rec = store.getAt(this.index + 1);
			this.showJobDetail(rec);
			this.index = this.index + 1;
		}
	},
	
	showJobDetail:function(rec){
		if(rec){
			this.jobId = rec.get('id');
			this.getTaskView().setTitle(EwayLocale.param.paramDownloadMonitor.job+rec.get('id')+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			this.onTaskQuery();
		}
	},
	
	onCellClick:function( _this, td, cellIndex, record, tr, rowIndex, e, eOpts){
		if(cellIndex==6){
			var win=Ext.create('Eway.view.parameter.paramMonitor.Form');
			win.show();
			
			var grid=win.down('parameter_paramMonitor_AppResultGrid');
			var record=this.getTaskGrid().getSelectionModel().getLastSelected();
			win.setTitle(EwayLocale.param.paramDownloadMonitor.task+record.get('id')+EwayLocale.param.paramDownloadMonitor.StatusDetail);
			if(record){
				var store=grid.getStore();
				var publishResultId=record.get('id');
				store.setBaseParam('publishResultId',publishResultId);
				store.loadPage(1);
			}
		}
		
	}
});
	