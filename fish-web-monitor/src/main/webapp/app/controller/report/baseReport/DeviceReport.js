
Ext.define('Eway.controller.report.baseReport.DeviceReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	views : ['Eway.view.report.baseReport.DeviceReportView'],

	stores : ['monitor.card.DeviceAtmVendor','monitor.card.DeviceType'],

	models : ['Dict'],

	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_DeviceReportView',
		autoCreate : true,
		xtype : 'baseReport_DeviceReportView'
	} ],

	init : function() {
		this.control({
			'baseReport_DeviceReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'baseReport_DeviceReportView button[action=exportHtml]':{
				click : this.onExportHtml
			},
			'baseReport_DeviceReportView button[action=exportXls]':{
				click : this.onexportXls
			},
			'baseReport_DeviceReportView button[action=query]':{
				click : this.onShowHtml
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
//		var fileName = '设备明细报表.html';
		var fileName = 'Device';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/device',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	centerPanel.add({
			        		style : 'overflow:auto;',
			        		border : 0,
						    loader:{
						        url:'api/report/device/downloadFile?path='+ object.path + '&reportTitle=' + fileName,
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

	onexportXls : function(){
		var type = 'xls';
		this.onExport(type);
	},

	onExport : function(type){
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		values.exportType = type;
//		var fileName = '设备明细报表.' + type;
		var fileName = 'Device';
		Ext.Ajax.request({
		    url: 'api/report/device',
		    method:'GET',
		    params: values,
		    success: function(response){
		        var text = response.responseText;
		        var object = Ext.decode(text);
		        if(object.success){
		        	var url = ('api/report/device/downloadFile?path='+ object.path + '&reportTitle=' + fileName);
		        	var iframe = document.getElementById('downloadFileFromWeb');
		        	iframe.src = url;
		        }else{
		        	Eway.alert(object.message);
		        }
		    }
			});
		}
});