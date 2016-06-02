Ext.define('Eway.controller.report.faultRate.FaultRateReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['Eway.store.report.faultRateReport.Brand','Eway.store.report.faultRateReport.Type',
			'Eway.store.report.faultRateReport.Module' ],
	models : ['Eway.model.report.faultRateReport.Brand','Eway.model.report.faultRateReport.Type',
			'Eway.model.report.faultRateReport.Module' ],
	views : ['Eway.view.report.faultRateReport.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#report_faultRateReport_view',
		xtype : 'report_faultRateReport_view',
		autoCreate : true,
		id : 'report_faultRateReport_view'
	}, {
		ref : 'brandGrid',
		selector : 'report_faultRateReport_BrandGrid'
	}, {
		ref : 'typeGrid',
		selector : 'report_faultRateReport_TypeGrid'
	}, {
		ref : 'moduleGrid',
		selector : 'report_faultRateReport_ModuleGrid'
	},{
		ref : 'brandCharts',
		selector : 'report_faultRateReport_BrandCharts'
	},{
		ref : 'typeCharts',
		selector : 'report_faultRateReport_TypeCharts'
	},{
		ref : 'moduleCharts',
		selector : 'report_faultRateReport_ModuleCharts'
	} ],

	init : function() {
		this.control({
			'#report_faultRateReport_view button[action=query]' : {
				click : this.onQuery
			},
			'report_faultRateReport_BrandGrid ' : {
				itemclick : this.brandQuery
			},
			'report_faultRateReport_TypeGrid' : {
				itemclick : this.typeQuery
			},
			'#report_faultRateReport_view report_faultRateReport_typeView button[action=next]':{
				click :this.nextVendor
			},
			'#report_faultRateReport_view report_faultRateReport_typeView button[action=pref]':{
				click :this.prefVendor
			},
			'#report_faultRateReport_view report_faultRateReport_moduleView button[action=next]':{
				click :this.nextType
			},
			'#report_faultRateReport_view report_faultRateReport_moduleView button[action=pref]':{
				click :this.prefType
			}
		});
	},
	
	nextVendor:function(){
		this.jobPageChange("1");
	},
	nextVendor:function(){
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
		    		Eway.alert(EwayLocale.version.download.queryJobFailed);	
		    }

		});
	},
	
	onQuery:function(){
		var view =  this.getEwayView();
		var time = view.down('datefield').getValue();
		var year = time.getFullYear();
		var month = time.getMonth()+1;
		var date = year;
		if(month < 10){
			date = date + '0' + month;
		}else{
			date = date + month;
		}
		var brandStore = this.getBrandGrid().getStore();
		var typeStore = this.getTypeGrid().getStore();
		var monduleStore = this.getModuleGrid().getStore();
		var brandCharts = this.getBrandCharts().down('cartesian').getStore();
		var typeCharts = this.getTypeCharts().down('cartesian').getStore();
		var moduleCharts = this.getModuleCharts().down('cartesian').getStore();
		brandStore.setBaseParam("dateTime",date);
		typeStore.setBaseParam("dateTime",date);
		monduleStore.setBaseParam("dateTime",date);
		brandStore.loadPage(1);
		typeStore.loadPage(1);
		monduleStore.loadPage(1);
		brandCharts.loadPage(1);
		typeCharts.loadPage(1);
		moduleCharts.loadPage(1);
	},
	
	brandQuery:function(){
		var brandGrid = this.getBrandGrid();
		var typeGrid = this.getTypeGrid();
		var store = typeGrid.getStore();
		var typeCharts = this.getTypeCharts().down('cartesian').getStore();
		var record = brandGrid.getSelectionModel().getLastSelected();
		if(record != null){
			store.setBaseParam("name",record.get('name'));
			store.loadPage(1);
			typeCharts.loadPage(1);
		}
	},
	
	typeQuery:function(){
		var typeGrid = this.getTypeGrid();
		var moduleGrid = this.getModuleGrid();
		var store = moduleGrid.getStore();
		var record = typeGrid.getSelectionModel().getLastSelected();
		var moduleCharts = this.getModuleCharts().down('cartesian').getStore();
		if(record !=null){
			store.setBaseParam("name",record.get('name'));
			store.loadPage(1);
			moduleCharts.loadPage(1);
		}
	}

});