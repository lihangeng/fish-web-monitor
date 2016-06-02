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
			'report_faultRateReport_typeView button[action=back]' : {
				click : this.onTypeBack
			},
			'report_faultRateReport_typeView button[action=next]' : {
				click : this.nextVendor
			},
			'report_faultRateReport_typeView button[action=pref]' : {
				click : this.prefVendor
			},
			'report_faultRateReport_moduleView  button[action=back]' : {
				click : this.onModuleBack
			},
			'report_faultRateReport_moduleView button[action=next]' : {
				click : this.nextType
			},
			'report_faultRateReport_moduleView button[action=pref]' : {
				click : this.prefType
			}
		})
		this.onQuery();
	},


	onQuery : function() {
		var view = this.getEwayView();
		var time = view.down('datefield').getValue();
		var year = time.getFullYear();
		var month = time.getMonth() + 1;
		var date = year;
		if (month < 10) {
			date = date + '0' + month;
		} else {
			date = date + month;
		}
		var brandStore = this.getBrandGrid().getStore();
		var brandCharts = this.getBrandCharts().down('cartesian').getStore();
		brandStore.setBaseParam("dateTime", date);
		brandStore.loadPage(1);
		brandCharts.loadPage(1);

	},

	brandQuery : function() {
		var brandGrid = this.getBrandGrid();
		var typeGrid = this.getTypeGrid();
		var store = typeGrid.getStore();
		var tabpanel = this.getEwayView().down("panel[name=groupPanel]").up(
				"panel");
		var typeView = this.getEwayView().down(
				"report_faultRateReport_typeView");
		tabpanel.setActiveItem(typeView);
		var typeCharts = this.getTypeCharts().down('cartesian').getStore();
		var record = brandGrid.getSelectionModel().getLastSelected();
		if (record != null) {
			store.setBaseParam("name", record.get('name'));
			store.loadPage(1);
			typeCharts.loadPage(1);
		}
	},

	typeQuery : function() {
		var typeGrid = this.getTypeGrid();
		var moduleGrid = this.getModuleGrid();
		var store = moduleGrid.getStore();
		var record = typeGrid.getSelectionModel().getLastSelected();
		var tabpanel = this.getEwayView().down("panel[name=groupPanel]").up(
				"panel");
		var moduleView = this.getEwayView().down(
				"report_faultRateReport_moduleView");
		tabpanel.setActiveItem(moduleView);
		var moduleCharts = this.getModuleCharts().down('cartesian').getStore();
		if (record != null) {
			store.setBaseParam("name", record.get('name'));
			store.loadPage(1);
			moduleCharts.loadPage(1);
		}
	},

	onTypeBack : function(_this, opt) {
		var panel = _this.up("report_faultRateReport_typeView");
		var layout = panel.up("panel").getLayout();
		var groupPanel = this.getEwayView().down("panel[name=groupPanel]");
		layout.setActiveItem(groupPanel);
	},

	onModuleBack : function(_this, opt) {
		var panel = _this.up("report_faultRateReport_moduleView");
		var layout = panel.up("panel").getLayout();
		var groupPanel = this.getEwayView().down("panel[name=groupPanel]");
		layout.setActiveItem(groupPanel);
	},

});