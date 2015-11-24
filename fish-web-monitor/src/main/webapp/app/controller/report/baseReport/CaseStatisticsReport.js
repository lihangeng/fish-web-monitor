Ext.define('Eway.controller.report.baseReport.CaseStatisticsReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'report.baseReport.CaseStatisticsReport' ],

	models : [ 'report.baseReport.CaseStatisticsReport' ],

	views : [ 'report.baseReport.CaseStatisticsReportView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_CaseStatisticsReportView',
		autoCreate : true,
		xtype : 'baseReport_CaseStatisticsReportView'
	},{
		ref : 'grid',
		selector : 'baseReport_CaseStatisticsReportGrid',
		autoCreate : true,
		xtype : 'baseReport_CaseStatisticsReportGrid'
	} ],

	init : function() {
		this.control({
			'baseReport_CaseStatisticsReportView button[action=query]' : {
				click : this.onShowHtml
			}
		});
		this.onShowHtml();
	},

	onShowHtml : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		if (form.isValid() == true) {
			var store = this.getGrid().down('cartesian').getStore() ;
			store.setUrlParamsByObject(values) ;
			store.load() ;
		} else {
			Eway.alert(EwayLocale.vtype.inputCorrect);
		}
	},

	onExportPdf : function() {
		var type = 'pdf';
		this.onExport(type);
	},

	onExportHtml : function() {
		var type = 'html';
		this.onExport(type);
	},

	onExportXls : function() {
		var type = 'xls';
		this.onExport(type);
	},

	onExport : function(type) {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		values.exportType = type;
		// var fileName = '交易统计表.'+type;
		var fileName = 'TransactionCount';
		if (form.isValid() == true) {
			Ext.Ajax.request({
				url : 'api/report/transactionCount',
				method : 'GET',
				params : values,
				success : function(response) {
					var object = Ext.decode(response.responseText);
					if (object.success) {
						var url = 'api/report/downloadFile?path=' + object.path
								+ '&reportTitle=' + fileName;
						var iframe = document
								.getElementById('downloadFileFromWeb');
						iframe.src = url;
					} else {
						Eway.alert(EwayLocale.vtype.exportRepError);
					}
				}
			});
		} else {
			Eway.alert(EwayLocale.vtype.inputCorrect);
		}
	}
});