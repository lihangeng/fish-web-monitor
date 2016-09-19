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
		this.onQuery();
		this.control({
			'report_openrate_org_view button[action=query]' : {
				click : this.onQuery
			},
			'report_openrate_org_view button[action=importStat]' : {
				click : this.onImport
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
		var grid = view.down('report_openrate_org_treegrid');
		if(undefined!=values.orgId&&values.orgId!=""){
			var model = Ext.create("Eway.model.report.openrate.OrgOpenRate",{defaultRootId:values.orgId,expanded : true,node:values.orgId,id:values.orgId,terminalId:values.orgName,orgName:values.orgName});
			grid.setRootNode(model);
		}
		var store = view.down('report_openrate_org_treegrid').getStore();
		store.proxy.extraParams = values;
		store.load();
	},
	onImport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		var param = 'statType='+values.statType+'&day='+values.day+'&month='+values.month+'&year='+values.year+'&orgId='+values.orgId+'&orgName'+values.orgName+'&awayFlag='+values.awayFlag;
		window.location.href = 'api/report/openrate/org/importStat?' + param;
	}
});