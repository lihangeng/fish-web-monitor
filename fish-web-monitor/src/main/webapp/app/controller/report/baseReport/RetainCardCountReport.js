/**
 *  吞卡统计报表--controller
 */

Ext.define('Eway.controller.report.baseReport.RetainCardCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	models : [],



	views : ['report.baseReport.RetainCardCountReportView','report.baseReport.RetainCardCountReportFilter'],

	refs : [ {
		ref : 'ewayView',
		selector : 'retainCardCountReportView',
		autoCreate : true,
		xtype : 'retainCardCountReportView'
	} ],

	init : function() {
		this.control({
			'retainCardCountReportView button[action=query]':{
				click : this.onShowHtml
			},
			'retainCardCountReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'retainCardCountReportView button[action=exportHtml]':{
				click : this.onExportHtml
				},
			'retainCardCountReportView button[action=exportXls]':{
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
//		var fileName = '吞卡统计表.html';
		var fileName = 'RetainCardCount';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/retainCardCount',
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
			Eway.alert(Eway.locale.vtype.inputCorrect);
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
//		var fileName = '吞卡统计表.'+type;
		var fileName = 'RetainCardCount';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/retainCardCount',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	var url = 'api/report/downloadFile?path='+ object.path + '&reportTitle=' + fileName;
						var iframe = document.getElementById('downloadFileFromWeb');
						iframe.src = url;
			        }else{
			        	Eway.alert(Eway.locale.vtype.exportRepError);
			        }

			    }
			});
		}else{
			Eway.alert(Eway.locale.vtype.inputCorrect);
		}
	}
});