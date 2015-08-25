/**
 *  报表例子--controller
 */

Ext.define('Eway.controller.report.baseReport.RetainCardReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['monitor.card.CardStatusComboBox',
			  'monitor.card.DeviceAtmVendor',
			  'monitor.card.DeviceType',
			  'monitor.card.InoutComboBox'],

	models : ['monitor.card.CardInfo',
			  'Dict'],

	views : ['report.baseReport.RetainCardReportView','report.baseReport.RetainCardReportFilter'],

	refs : [ {
		ref : 'ewayView',
		selector : 'retainCardReportView',
		autoCreate : true,
		xtype : 'retainCardReportView'
	} ],

	init : function() {
		this.control({
			'retainCardReportView button[action=query]':{
				click : this.onShowHtml
			},
			'retainCardReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'retainCardReportView button[action=exportHtml]':{
				click : this.onExportHtml
				},
			'retainCardReportView button[action=exportXls]':{
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
//		var fileName = '吞卡明细表.html';
		var fileName = 'RetainCard';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/retainCard',
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
//		var fileName = '吞卡明细表.'+type;
		var fileName = 'RetainCard';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/retainCard',
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
			Eway.alert('请正确输入!');
		}
	}
});