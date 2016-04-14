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
			'parameter_devParameter_paramGrid button[action=query]' : {
				click :this.onParamQuery
			},
			'parameter_devParameter_devGrid' : {
				itemclick :this.onItemclick
			},
			'tabpanel':{
				tabchange:this.onTabChange
			},
			'parameter_devParameter_paramGrid button[action=update]':{
				click:this.onUpdate
			},
			'parameter_devParameter_paramGrid button[action=save]':{
				click:this.onSave
			}
		});
	},
	
	
	onParamQuery : function(){
		var view = this.getEwayView();
		var form = view.down('parameter_devParameter_paramFilterForm').getForm();
		var bool = form.isValid();
		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = view.down('parameter_devParameter_paramGrid').getStore();
		store.setUrlParamsByObject(values);
		var devGrid = this.getGrid();
		var sm = devGrid.getSelectionModel();
		var record = sm.getLastSelected();
		store.setBaseParam('deviceId',record.get('id'));
		store.setBaseParam('tabId',tabPanelId);
		store.loadPage(1);
	},
	
	tabPanelId : 1,
	onTabChange:function(tabPanel, newCard, oldCard, eOpts ){
		var tabId=tabPanel.activeTab.appType;
		tabPanelId=tabId;
		var me = this;
		var view = this.getEwayView();
		var grid=view.down('parameter_devParameter_devGrid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var tab = tabPanel.activeTab;
			var tabGrid = tab.down('grid');
			var store = tabGrid.getStore();
			var record = sm.getLastSelected();
			store.setBaseParam('deviceId',record.get('id'));
			store.setBaseParam('tabId',tabId);
			store.loadPage(1);		
		}
	},
	
	
	onItemclick:function(me, record, item){
		var view = this.getEwayView();
		var grid = view.down('tabpanel').activeTab.down('grid');
		var store = grid.getStore();
		store.setBaseParam('deviceId',record.get('id'));
		store.setBaseParam('tabId',tabPanelId);
		store.loadPage(1);		
	},
	
	
	onUpdate:function(){
		var view=this.getEwayView();
		var grid = view.down('tabpanel').activeTab.down('grid');
		var cellEditor=grid.plugins[0];
		if(cellEditor.clicksToEdit == 1){
			cellEditor.clicksToEdit=2;
		}
		grid.columns[1].setText('参数值 <font color="red">(可更改)</font>');
	},
	
	onSave:function(){
		var view=this.getEwayView();
		var grid = view.down('tabpanel').activeTab.down('grid');
		grid.columns[1].setText('参数值 ');
		var cellEditor=grid.plugins[0];
		if(cellEditor.clicksToEdit == 2){
			cellEditor.clicksToEdit=1;
		}
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
			 success: function(ed) {
				Eway.alert('更改成功');
				store.loadPage(1);
			 },
			 failure: function(ed){
				Eway.alert('更改失败');
			 },
			 scope : this
		});
		
	}

	
	
});