
Ext.define('Eway.controller.report.baseReport.ModuleFaultRateReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['report.baseReport.ModuleFaultRateReport'],

	models : ['report.baseReport.ModuleFaultRateReport'],

	views : ['report.baseReport.ModuleFaultRateReportFilter',
	         'report.baseReport.ModuleFaultRateReportView'],

	refs : [ {
		ref : 'ewayView',
		selector : 'moduleFaultRateReportView',
		autoCreate : true,
		xtype : 'moduleFaultRateReportView'
	} ],

	init : function() {
		this.control({
			'moduleFaultRateReportView button[action=query]':{
				click : this.onShowHtml
			},
			'moduleFaultRateReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'moduleFaultRateReportView button[action=exportXls]':{
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
		var fileName = 'ModuleFaultRate';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/moduleFaultRate',
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
						        scripts:true
						    }
						});
			        }else{
			        	Eway.alert(object.message);
			        }
			    }
			});
		}else{
			Eway.alert(EwayLocale.vtype.inputCorrect);
		}
	},

	onExportPdf : function(){
		var type = 'pdf';
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
		var fileName = 'ModuleFaultRate';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/moduleFaultRate',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	var url = encodeURI('api/report/downloadFile?path='+ object.path + '&reportTitle=' + fileName);
						var iframe = document.getElementById('downloadFileFromWeb');
						iframe.src = url;
			        }else{
			        	Eway.alert(EwayLocale.vtype.exportRepError);
			        }
			    }
			});
		}else{
			Eway.alert(EwayLocale.vtype.inputCorrect);
		}
	}
});