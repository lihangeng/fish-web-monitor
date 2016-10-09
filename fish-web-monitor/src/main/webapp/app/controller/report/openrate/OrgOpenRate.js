Ext.define('Eway.controller.report.openrate.OrgOpenRate', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Eway.store.report.openrate.OrgOpenRate' ],

	models : [ 'Eway.model.report.openrate.OrgOpenRate' ],

	views : [ 'Eway.view.report.openrate.org.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#openrateView',
		autoCreate : true,
		xtype : 'report_openrate_org_view'
	} ],

	init : function() {
		this.control({
			'report_openrate_org_view button[action=query]' : {
				click : this.onQuery
			},
			'report_openrate_org_view button[action=importStat]' : {
				click : this.onImport
			},
			'report_openrate_org_view':{
				afterrender : this.onQuery
			}
		});
	},
	onQuery : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();

		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = view.down('report_openrate_org_treegrid').getStore();
		store.proxy.extraParams = values;
		store.load();
	},
	onImport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool =  form.isValid();
		if(bool==false){
			return false;
		}
		var values = form.getValues();
		var params = '&statType='+values.statType+'&day='+values.day+'&month='+values.month+'&year='+values.year+'&orgId='+values.orgId+'&orgName'+values.orgName+'&awayFlag='+values.awayFlag;
		var columns = this.getEwayView().getComponent('gridItemId').getColumns();
		params = this.getToExcel(columns,params);
		window.location.href = 'api/report/openrate/org/importStat?_dc' + params;
	}
});