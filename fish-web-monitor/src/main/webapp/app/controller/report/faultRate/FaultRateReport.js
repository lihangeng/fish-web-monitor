Ext.define('Eway.controller.report.faultRate.FaultRateReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Eway.store.report.faultRateReport.Brand',
			'Eway.store.report.faultRateReport.Type',
			'Eway.store.report.faultRateReport.Module' ],
	models : [ 'Eway.model.report.faultRateReport.Brand',
			'Eway.model.report.faultRateReport.Type',
			'Eway.model.report.faultRateReport.Module' ],
	views : [ 'Eway.view.report.faultRateReport.View',
			'Eway.view.report.faultRateReport.ModuleView',
			'Eway.view.report.faultRateReport.TypeView' ],

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
	}, {
		ref : 'brandCharts',
		selector : 'report_faultRateReport_BrandCharts'
	}, {
		ref : 'typeCharts',
		selector : 'report_faultRateReport_TypeCharts'
	}, {
		ref : 'moduleCharts',
		selector : 'report_faultRateReport_ModuleCharts'
	}, {
		ref : 'moduleView',
		selector : 'report_faultRateReport_moduleView'
	}, {
		ref : 'typeView',
		selector : 'report_faultRateReport_typeView'
	} , {
		ref : 'brandView',
		selector : 'report_faultRateReport_brandView'
	}],

	init : function() {
		this.control({
			'#report_faultRateReport_view button[action=query]' : {
				click : this.onQuery
			},
			'report_faultRateReport_brandView report_faultRateReport_BrandGrid' : {
				itemmouseenter : this.brandQuery
			},
			'report_faultRateReport_typeView report_faultRateReport_TypeGrid' : {
				itemmouseenter : this.typeQuery
			},
			'report_faultRateReport_typeView button[action=back]' : {
				click : this.onTypeBack
			},
			'report_faultRateReport_typeView button[action=next]' : {
				click : this.nextVendor
			},
			'report_faultRateReport_typeView button[action=pref]' : {
				click : this.preVendor
			},
			'report_faultRateReport_moduleView  button[action=back]' : {
				click : this.onModuleBack
			},
			'report_faultRateReport_moduleView button[action=next]' : {
				click : this.nextType
			},
			'report_faultRateReport_moduleView button[action=pref]' : {
				click : this.preType
			}
		})
	},
	
	dateTime : null,
	onQuery : function() {
		this.dateTime = this.getDateTimeValue();
		var brandStore = this.getBrandGrid().getStore();
		var brandCharts = this.getBrandCharts().down('cartesian').getStore();
		brandStore.setBaseParam("dateTime", this.dateTime);
		brandStore.loadPage(1);
		brandCharts.loadPage(1);
	},
	
	getDateTimeValue : function(){
		var view = this.getBrandView();
		return view.down('form').getForm().getValues().dateMonth;
	},
	
	vendorId:0,
	name : null,
	brandIndex : 0,
	brandQuery : function( _this,  record, itemHtml, index, e, eOpts) {
		this.dateTime = this.getDateTimeValue();
		this.brandIndex = index;
		var winEl = Ext.get(itemHtml);
		this.vendorId=record.get('vendorId');
		this.name = record.get('name');
	    winEl.down('img').on("click",this.turnToType,this);
//	    Ext.bind(this.onSetManagerConfirm,this,[addManagerWin])
	},

	nextVendor:function(_this, e, eOpts){
		var store = this.getBrandGrid().getStore();
		var count = store.getCount();
		if(this.brandIndex + 1 < count){
			var rec = store.getAt(this.brandIndex + 1);
			this.showType(rec);
			this.brandIndex = this.brandIndex + 1;
		}
	},
	
	showType : function(rec){
		this.name = rec.get("name");
		this.vendorId=rec.get('vendorId');
		var typeStore =  this.getTypeGrid().getStore();
		this.getTypeView().setTitle(this.name + " 品牌下的所有型号故障率情况");
		typeStore.setBaseParam("vendorId", this.vendorId);
		typeStore.setBaseParam("dateTime", this.dateTime);
		typeStore.load();
		this.getTypeCharts().down('cartesian').getStore().load();
	},

	preVendor:function(_this, e, eOpts){
		var store = this.getBrandGrid().getStore();
		var count = store.getCount();
		if(this.brandIndex - 1 >=0){
			var rec = store.getAt(this.brandIndex - 1);
			this.showType(rec);
			this.brandIndex = this.brandIndex - 1;
		}
	},
	
	turnToType:function(_this){
		var typeGrid = this.getTypeGrid();
		var store = typeGrid.getStore();
		this.getTypeView().setTitle(this.name + " 品牌下的所有型号故障率情况");
		store.setBaseParam("vendorId", this.vendorId);
		store.setBaseParam("dateTime", this.dateTime);
		store.load();
		this.getTypeCharts().down('cartesian').getStore().load();
		var layout = this.getEwayView().getLayout();
		layout.setActiveItem(1);
	},

	typeId:0,
	typeQuery : function(_this,  record, itemHtml, index, e, eOpts) {
		var winEl = Ext.get(itemHtml);
		this.typeId=record.get('devTypeId');
		this.name = record.get('name');
	    var imgHtml = winEl.down('img').on("click",this.turnToModule,this);
	},
	
	turnToModule:function(_this, e, eOpts){
		var moduleGrid = this.getModuleGrid();
		var store = moduleGrid.getStore();
		this.getModuleView().setTitle(this.name + " 型号下所有模块故障率情况");
		store.setBaseParam("vendorId",this.vendorId);
		store.setBaseParam("devTypeId",this.typeId);
		store.setBaseParam("dateTime", this.dateTime);
		store.load();
		this.getModuleCharts().down('cartesian').getStore().load();
		var layout = this.getEwayView().getLayout();
		layout.setActiveItem(2);
	},
	
	typeIndex:0,
	
	preType:function(_this, e, eOpts){
		var store = this.getTypeGrid().getStore();
		if(this.typeIndex - 1 >=0){
			var rec = store.getAt(this.typeIndex - 1);
			this.showModule(rec);
			this.typeIndex = this.typeIndex - 1;
		}
	},
	
	nextType:function(_this, e, eOpts){
		var store = this.getTypeGrid().getStore();
		var count = store.getCount();
		if(this.typeIndex + 1 < count){
			var rec = store.getAt(this.typeIndex + 1);
			this.showModule(rec);
			this.typeIndex = this.typeIndex + 1;
		}
	},
	
	showModule: function(rec){
		this.name = rec.get("name");
		this.typeId=rec.get('devTypeId');
		this.getModuleView().setTitle(this.name + " 型号下所有模块故障率情况");
		var moduleStore =  this.getModuleGrid().getStore();
		moduleStore.setBaseParam("vendorId",this.vendorId);
		moduleStore.setBaseParam("devTypeId",this.typeId);
		moduleStore.setBaseParam("dateTime", this.dateTime);
		moduleStore.load();
		this.getModuleCharts().down('cartesian').getStore().load();
	},
	
	onTypeBack : function(_this, opt) {
		var layout = this.getEwayView().getLayout();
		layout.setActiveItem(0);
	},

	onModuleBack : function(_this, opt) {
		var layout = this.getEwayView().getLayout();
		layout.setActiveItem(1);
	}
	
});