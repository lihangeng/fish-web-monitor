Ext.define('Eway.controller.parameter.devParameter.DevParameter',{
	extend :'Eway.controller.base.FishController',
	
	stores :['parameter.devParameter.DevInfo','parameter.devParameter.ParamInfo','Eway.store.parameter.devParameter.ParamClassify'],
	models :['parameter.devParameter.DevInfo','parameter.devParameter.ParamInfo'],
	views  :['parameter.devParameter.View','parameter.devParameter.ParamGrid','parameter.devParameter.ParamView'],
	config:{
		appType:''
	},
	refs :[{
		ref:'ewayView',
		selector:'#parameter_devParameter_view',
		xtype :'parameter_devParameter_view',
		autoCreate:true,
		id:'parameter_devParameter_view'
	},{
		ref:'grid',
		selector:'parameter_devParameter_devGrid'
	},{
		ref:'paramGrid',
		selector:'parameter_devParameter_paramGrid'
	}],
	
	
	init : function(){
		this.control({
			'parameter_devParameter_devGrid button[action=query]' : {
				click :this.onQuery
			},
			'parameter_devParameter_devGrid button[action=release]' :{
				click :this.onRelease
			},
			'parameter_devParameter_paramGrid button[action=query]' : {
				click :this.onParamQuery
			},
			'parameter_devParameter_devGrid' : {
				itemclick :this.onItemclick
			},
			'parameter_devParameter_view tabpanel':{
				tabchange:this.onTabChange
			},
			'parameter_devParameter_paramGrid ':{
				itemclick:this.paramItemClick
			},
			'parameter_devParameter_paramGrid button[action=save]':{
				click:this.onSave
			}
		});
	},
	
	onParamQuery : function(){
		var view = this.getEwayView();
		var form = view.down('tabpanel').activeTab.down('parameter_devParameter_paramFilterForm').getForm();
		var bool = form.isValid();
		// 查询输入验证view
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = view.down('tabpanel').activeTab.down('parameter_devParameter_paramGrid').getStore();
		store.setUrlParamsByObject(values);
		var devGrid = this.getGrid();
		var sm = devGrid.getSelectionModel();
		if(sm.getCount()==1){
			var record = sm.getLastSelected();
			store.setBaseParam('deviceId',record.get('id'));
			store.setBaseParam('tabId',tabPanelId);
			store.loadPage(1);
		}
	},
	
	onRelease:function(){
		var grid=this.getGrid();
		var sm=grid.getSelectionModel();
		var records=sm.getSelection();
		var devArrayId='0';
		for(var p in records){
			var id=records[p].get('id');
			devArrayId+='-'+id;
		}
		Ext.Ajax.request({
			method:'POST',
			url:'api/parameter/devParameter/paramInfo/release',
			params:{
				arrayId:devArrayId
			},
			success:function(ed){
				if(Ext.decode(ed.responseText).success==false){
					Eway.alert(Ext.decode(ed.responseText).errorMsg);
					this.onQuery();
				}else{
					if(Ext.decode(ed.responseText).data){
						var jobId=Ext.decode(ed.responseText).data;
						Ext.MessageBox.confirm(EwayLocale.confirm.title,//'提示',
								jobId+EwayLocale.param.paramDownloadMonitor.aotuJump,
					 			function(button, text) {
					 				if (button == "yes") {
					 					var controller = this.parent.activeController('parameter.paramMonitor.ParamMonitor');
					 					controller.autoJobDetail(jobId);
					 				}
					 			},this);
					}
				}
			},
			failure:function(response){
				Eway.alert(EwayLocale.param.deviceParam.downloadFailure);
			},
			scope:this
		})
	},
	
	tabPanelId : 1,
	onTabChange:function(tabPanel, newCard, oldCard, eOpts ){
		var tabId=tabPanel.activeTab.appType;
		tabPanelId=tabId;
		var me = this;
		var view = this.getEwayView();
		var grid=view.down('parameter_devParameter_devGrid');
		var form = view.down('tabpanel').activeTab.down('parameter_devParameter_paramFilterForm');
		var bool = form.isValid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var tab = tabPanel.activeTab;
			var tabGrid = tab.down('grid');
			var store = tabGrid.getStore();
			var record = sm.getLastSelected();
			store.setBaseParam('deviceId',record.get('id'));
			store.setBaseParam('tabId',tabId);
			this.onParamQuery();
		}
	},
	
	
	onItemclick:function(me, record, item){
		var view = this.getEwayView();
		var grid = view.down('tabpanel').activeTab.down('grid');
		var store = grid.getStore();
		if(record!=null){
			store.setBaseParam('deviceId',record.get('id'));
			store.setBaseParam('tabId',tabPanelId);
			store.loadPage(1);	
		}
	},
	
	
	paramItemClick:function(){
		var view=this.getEwayView();
		var paramGrid = view.down('tabpanel').activeTab.down('grid');
		var store=paramGrid.getStore();
		var record=paramGrid.getSelectionModel().getLastSelected();
		var cellEditor=paramGrid.plugins[0];
		if(record.get('eleParamRights') == '1'){
			cellEditor.clicksToEdit=1;
		}else if(record.get('eleParamRights') == '2'){
			cellEditor.clicksToEdit=2;
		}
	},
	
	onSave:function(){
		var view=this.getEwayView();
		var grid = view.down('tabpanel').activeTab.down('grid');
		var store=grid.getStore();
		var data="[";
		for(var index = 0;index < store.count();index++){
			var record=store.getAt(index);
			if(record.dirty){
				var resource="{'id':'"+record.get('id')+"','paramValue':'"+record.get('paramValue')+"'}";
				if(data== "["){
					data=data+resource;
				}else{
					data=data+","+resource;
				}
			}
		}
		data=data+"]";
		
		var deviceGrid=view.down('parameter_devParameter_devGrid');
		var deviceRecord = deviceGrid.getSelectionModel().getLastSelected();
		var record=Ext.create('Eway.model.parameter.devParameter.ParamInfo',{
			id:deviceRecord.get('id'),
			data:data
		});
		
		record.save({
			success:function(response) {
				if(response.data.id){
					var jobId=response.data.id;
					Ext.MessageBox.confirm(EwayLocale.confirm.title,//'提示',
							jobId+EwayLocale.param.paramDownloadMonitor.aotuJump,
				 			function(button, text) {
				 				if (button == "yes") {
				 					var controller = this.parent.activeController('parameter.paramMonitor.ParamMonitor');
				 					controller.autoJobDetail(jobId);
				 				}
				 			},this);
				}
				store.loadPage(1);
			 },
			 failure: function(ed){
				Eway.alert(EwayLocale.param.deviceParam.notExist);
				this.onQuery();
			 },
			 scope : this
		});
		this.onParamQuery();
	}

	
	
});
