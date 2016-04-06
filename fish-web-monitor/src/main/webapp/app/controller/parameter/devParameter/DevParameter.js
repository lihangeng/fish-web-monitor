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
	}

	
	
	
});