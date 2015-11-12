Ext.define('Eway.controller.report.baseReport.TransactionDaysCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'report.baseReport.TransactionDaysCount' ],

	models : [ 'report.baseReport.TransactionDaysCount' ],

	views : [ 'report.baseReport.TransactionDaysCountView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_TransactionDaysCountView',
		autoCreate : true,
		xtype : 'baseReport_TransactionDaysCountView'
	},{
		ref : 'grid',
		selector : 'baseReport_TransactionDaysCountGrid',
		autoCreate : true,
		xtype : 'baseReport_TransactionDaysCountGrid'
	} ],

	init : function() {
		this.control({
			'baseReport_TransactionDaysCountView button[action=query]' : {
				click : this.onShowHtml
			},
			'baseReport_TransactionDaysCountView button[action=importStat]' : {
				click : this.onExportXls
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
			Eway.alert(Eway.locale.vtype.inputCorrect);
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
						Eway.alert(Eway.locale.vtype.exportRepError);
					}
				}
			});
		} else {
			Eway.alert(Eway.locale.vtype.inputCorrect);
		}
	}
});