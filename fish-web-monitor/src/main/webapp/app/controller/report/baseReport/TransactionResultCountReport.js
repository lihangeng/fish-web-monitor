
Ext.define('Eway.controller.report.baseReport.TransactionResultCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['monitor.transaction.TransType'],

	models : ['monitor.transaction.TransType'],



	views : ['report.baseReport.TransactionResultCountReportFilter','report.baseReport.TransactionResultCountReportView'],

	refs : [ {
		ref : 'ewayView',
		selector : 'transactionResultCountReportView',
		autoCreate : true,
		xtype : 'transactionResultCountReportView'
	} ],

	init : function() {
		this.control({
			'transactionResultCountReportView button[action=query]':{
				click : this.onShowHtml
			},
			'transactionResultCountReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'transactionResultCountReportView button[action=exportHtml]':{
				click : this.onExportHtml
				},
			'transactionResultCountReportView button[action=exportXls]':{
				click : this.onExportXls
			}
		});
		this.onShowHtml();
	},

	onShowHtml : function(){
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var centerPanel = view.down('reportDownloadBody');
		centerPanel.removeAll(true);
		var values = form.getValues();
		values.exportType = 'html';
//		var fileName = '交易结果统计表.html';
		var fileName = 'TransactionResultCount';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/transactionResultCount',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	centerPanel.add({
			        		style : 'overflow:auto;',
			        		border : 0,
			        		loader:{
						        url:'api/report/downloadFile?path='+ object.path + '&reportTitle=' + fileName,
						        autoLoad :true,
						        scripts:true//如果你想在html中能执行js的话.就true
						    }
						});
			        }else{
			        	Eway.alert(object.message);
			        }
			    }
			});
		}else{
			Eway.alert('请正确输入.');
		}
	},

	onExportPdf : function(){
		var type = 'pdf';
		this.onExport(type);
	},

	onExportHtml : function(){
		var type = 'html';
		this.onExport(type);
	},

	onExportXls : function(){
		var type = 'xls';
		this.onExport(type);
	},

	onExport : function(type){
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		values.exportType = type;
//		var fileName = '交易结果统计表.'+type;
		var fileName = 'TransactionResultCount';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/transactionResultCount',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	var url = 'api/report/downloadFile?path='+ object.path + '&reportTitle=' + fileName;
						var iframe = document.getElementById('downloadFileFromWeb');
						iframe.src = url;
			        }else{
			        	Eway.alert('导出报表出错，请重新操作!');
			        }
			    }
			});
		}else{
			Eway.alert('请正确输入!');
		}
	}
});