Ext.define('Eway.controller.report.openrate.TypeOpenRate', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'Eway.store.report.openrate.TypeOpenRate' ],

	models : [ 'Eway.model.report.openrate.TypeOpenRate' ],

	views : [ 'Eway.view.report.openrate.type.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#openrateView',
		autoCreate : true,
		xtype : 'report_openrate_type_view'
	} ],

	init : function() {
		this.onQuery();
		this.control({
			'report_openrate_type_view button[action=query]' : {
				click : this.onQuery
			},
			'report_openrate_type_view button[action=importStat]' : {
				click : this.onImport
			}
		});
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
		var columns = this.getEwayView().down('grid').getColumns();
		params = this.getToExcel(columns,params);
		window.location.href = 'api/report/openrate/type/importStat?_dc' + params;
	}
});