/**
 *  报表例子--controller
 */

Ext.define('Eway.controller.report.baseReport.SettlementReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['machine.DeviceAtmType'],

	models : [],

	views : ['report.baseReport.SettlementReportView','report.baseReport.SettlementReportFilter'],

	refs : [ {
		ref : 'ewayView',
		selector : 'settlementReportView',
		autoCreate : true,
		xtype : 'settlementReportView'
	} ],

	init : function() {
		this.control({
			'settlementReportView button[action=query]':{
				click : this.onShowHtml
			},
			'settlementReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'settlementReportView button[action=exportHtml]':{
				click : this.onExportHtml
				},
			'settlementReportView button[action=exportXls]':{
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
//		var fileName = '设备清加钞统计表.html';
		var fileName = 'Settlement';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/settlement',
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
			Eway.alert(EwayLocale.vtype.inputCorrect);
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
//		var fileName = '设备清加钞统计表.'+type;
		var fileName = 'Settlement';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/settlement',
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
			Eway.alert(EwayLocale.vtype.inputCorrect);
		}
	}
});