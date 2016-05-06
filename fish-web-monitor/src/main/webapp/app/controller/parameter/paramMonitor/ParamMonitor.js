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
		ref:'jobGrid',
		selector:'parameter_paramMonitor_JobGrid'
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
			'parameter_paramMonitor_view tabpanel':{
				tabchange:this.onTabChange
			},
			'parameter_paramMonitor_TaskGrid':{
				cellclick:this.onCellClick
			}
		});
	},
	
	onTabChange:function( tabPanel, newCard, oldCard, eOpts ){
		if(newCard.name=='taskPanel'){
			var tabpanel = this.getEwayView().down("tabpanel");
			var paramDetailPanel = this.getEwayView().down("panel[name='paramMonitorDetails']");
			tabpanel.remove(paramDetailPanel,true);
		}
	},
	
	onDetail:function(){
		var jobGrid=this.getJobGrid();
		var sm=jobGrid.getSelectionModel();
		if(sm.getCount() == 1){
			var record=sm.getLastSelected();
			var tabpanel = this.getEwayView().down("tabpanel");
			var paramDetailPanel = Ext.create("Eway.view.parameter.paramMonitor.TaskView",{jobId:record.get("id")});
			tabpanel.add(paramDetailPanel);
			paramDetailPanel.setTitle(EwayLocale.param.paramDownloadMonitor.job+record.get('id')+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			tabpanel.setActiveItem(paramDetailPanel);
			this.onTaskQuery();
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
		store.setUrlParamsByObject(values);
		var JobRecord = view.down('parameter_paramMonitor_JobGrid').getSelectionModel().getLastSelected();
		if(JobRecord!=null){
			store.setBaseParam('publishId',taskGrid.getJobId());
			store.loadPage(1);
		}else {
			Eway.alert(EwayLocale.param.paramDownloadMonitor.chooseRecord);
		}
	},
	autoJobDetail:function(jobId){
		if(jobId !=null){
			var tabpanel = this.getEwayView().down("tabpanel");
			if(tabpanel.items.length>1){
				tabpanel.remove(tabpanel.down("parameter_paramMonitor_TaskView"),true);
			}
			var paramDetailPanel = Ext.create("Eway.view.parameter.paramMonitor.TaskView",{'jobId':jobId});
			tabpanel.add(paramDetailPanel);
			paramDetailPanel.setTitle(EwayLocale.param.paramDownloadMonitor.job+jobId+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			tabpanel.setActiveItem(paramDetailPanel);
			var view = this.getEwayView();
			var store = tabpanel.activeTab.down('parameter_paramMonitor_TaskGrid').getStore();
			store.setBaseParam('publishId',jobId);
			store.loadPage(1);
		}
		Ext.Function.defer(this.onTaskQuery,5000,this);
	},
	
	onCellClick:function( _this, td, cellIndex, record, tr, rowIndex, e, eOpts){
		if(cellIndex==6){
			var form=Ext.create('Eway.view.parameter.paramMonitor.Form');
			form.show();
			var record=this.getTaskGrid().getSelectionModel().getLastSelected();
			if(record){
				var store=form.getStore();
				var publishResultId=record.get('id');
				store.setBaseParam('publishResultId',publishResultId);
				store.loadPage(1);
			}
		}
		
	}
});
	