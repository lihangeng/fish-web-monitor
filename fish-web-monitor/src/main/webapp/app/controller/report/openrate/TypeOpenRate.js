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
		var param = '&statType='+values.statType+'&day='+values.day+'&month='+values.month+'&year='+values.year+'&orgId='+values.orgId+'&orgName'+values.orgName+'&awayFlag='+values.awayFlag;
		var columns = this.getEwayView().down('grid').getColumns();
        var headerName = new Array();
		   var colIndex = new Array();
		   var colWidth = new Array();
		   Ext.Array.forEach(columns,function(item,index,opt){
				headerName.push(item.text);
				colWidth.push(item.cellWidth);
				colIndex.push(item.dataIndex);
			},this);
		   param+="&gridInfoHeaderNames="+headerName+"&gridInfoColIndexs="+colIndex+"&gridInfoColWidths="+colWidth;
		window.location.href = 'api/report/openrate/type/importStat?_dc' + param;
	}
});