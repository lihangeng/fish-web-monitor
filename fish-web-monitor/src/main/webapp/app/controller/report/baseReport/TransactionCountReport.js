Ext.define('Eway.controller.report.baseReport.TransactionCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.transaction.TransType',
			'person.organization.OrganizationLevelDict',
			'monitor.report.DeviceComboBox' ],

	models : [ 'monitor.transaction.TransType' ],

	views : [ 'report.baseReport.TransactionCountReportFilter',
			'report.baseReport.TransactionCountReportView' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'transactionCountReportView',
		autoCreate : true,
		xtype : 'transactionCountReportView'
	} ],

	init : function() {
		this.control({
			'transactionCountReportView button[action=query]' : {
				click : this.onShowHtml
			},
			'transactionCountReportView button[action=exportPdf]' : {
				click : this.onExportPdf
			},
			'transactionCountReportView button[action=exportHtml]' : {
				click : this.onExportHtml
			},
			'transactionCountReportView button[action=exportXls]' : {
				click : this.onExportXls
			}
		});
		this.onShowHtml();
	},

	onShowHtml : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var centerPanel = view.down('reportDownloadBody');
		centerPanel.removeAll(true);
		var values = form.getValues();
		values.exportType = 'html';
		// var fileName = '交易情况统计表.html';
		var fileName = 'TransactionCount';
		if (form.isValid() == true) {
			Ext.Ajax.request({
				url : 'api/report/transactionCount',
				method : 'GET',
				params : values,
				success : function(response) {
					var object = Ext.decode(response.responseText);
					if (object.success) {
						centerPanel.add({
			        		style : 'overflow:auto;',
							border : 0,
						    loader : {
								url : 'api/report/downloadFile?path='+ object.path + '&reportTitle='+ fileName,
						        autoLoad :true,
								scripts : true
							}
						});
					} else {
						Eway.alert(object.message);
					}
				}
			});
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