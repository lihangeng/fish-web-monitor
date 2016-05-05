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
			}
		});
	},
	
	onTabChange:function( tabPanel, newCard, oldCard, eOpts ){
		if(newCard.name=='taskPanel'){
			var tabpanel = this.getEwayView().down("tabpanel");
			var paramDetailPanel = this.getEwayView().down("panel[name='paramMonitorDetails']");
			paramDetailPanel.setTitle(EwayLocale.param.paramDownloadMonitor.downloadDetail);
			tabpanel.remove(paramDetailPanel,true);
		}
	},
	
	onDetail:function(){
		var jobGrid=this.getJobGrid();
		var sm=jobGrid.getSelectionModel();
		if(sm.getCount() == 1){
			var record=sm.getLastSelected();
			var tabpanel = this.getEwayView().down("tabpanel");
			var paramDetailPanel = Ext.create("Eway.view.parameter.paramMonitor.TaskView");
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
		var store = view.down('parameter_paramMonitor_TaskGrid').getStore();
		store.setUrlParamsByObject(values);
		var JobRecord = view.down('parameter_paramMonitor_JobGrid').getSelectionModel().getLastSelected();
		if(JobRecord!=null){
			store.setBaseParam('publishId',JobRecord.data.id);
			store.loadPage(1);
		}else {
			Eway.alert(EwayLocale.param.paramDownloadMonitor.chooseRecord);
		}
	},
	autoJobDetail:function(jobId){
		if(jobId !=null){
			var tabpanel = this.getEwayView().down("tabpanel");
			var paramDetailPanel = Ext.create("Eway.view.parameter.paramMonitor.TaskView");
			tabpanel.add(paramDetailPanel);
			paramDetailPanel.setTitle(EwayLocale.param.paramDownloadMonitor.job+jobId+EwayLocale.param.paramDownloadMonitor.downloadDetail);
			tabpanel.setActiveItem(paramDetailPanel);
			var view = this.getEwayView();
			var store = view.down('parameter_paramMonitor_TaskGrid').getStore();
			store.setBaseParam('publishId',jobId);
			store.loadPage(1);
		}
		this.onTaskQuery();
	}
});
	