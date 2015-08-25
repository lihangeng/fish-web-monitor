/**
 *  报表例子--controller
 */

Ext.define('Eway.controller.report.baseReport.CashInReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	models : [],

	views : ['report.baseReport.CashInReportView'],

	refs : [ {
		ref : 'ewayView',
		selector : 'cashInReportView',
		autoCreate : true,
		xtype : 'cashInReportView'
	} ],

	init : function() {
		this.control({
			'cashInReportView button[action=query]':{
				click : this.onShowHtml
			},
			'cashInReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'cashInReportView button[action=exportHtml]':{
				click : this.onExportHtml
				},
			'cashInReportView button[action=exportXls]':{
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
//		var fileName = '加钞明细表.html';
		var fileName = 'CashIn';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/cashIn',
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
		var centerPanel = view.down('reportDownload');
		var values = form.getValues();
		values.exportType = type;
//		var fileName = '加钞明细表.'+type;
		var fileName = 'CashIn';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/cashIn',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	var url = 'api/report/downloadFile?path='+ object.path + '&reportTitle=' + fileName;
						var iframe = document.getElementById('downloadFileFromWeb');
						iframe.src = url;
			        }else{
			        	Eway.alert(object.message);
			        }
			    }
			});
		}else{
			Eway.alert('请正确输入.');
		}
	}
});