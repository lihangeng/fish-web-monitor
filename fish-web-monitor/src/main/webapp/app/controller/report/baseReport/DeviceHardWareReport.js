
Ext.define('Eway.controller.report.baseReport.DeviceHardWareReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	models : ['Dict'],



	views : ['Eway.view.report.baseReport.DeviceHardWareReportView'],

	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_DeviceHardWareReportView',
		autoCreate : true,
		xtype : 'baseReport_DeviceHardWareReportView'
	} ],

	init : function() {
		this.control({
			'baseReport_DeviceHardWareReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'baseReport_DeviceHardWareReportView button[action=exportHtml]':{
				click : this.onExportHtml
			},
			'baseReport_DeviceHardWareReportView button[action=exportXls]':{
				click : this.onexportXls
			},
			'baseReport_DeviceHardWareReportView button[action=query]':{
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
//		var fileName = '系统硬件配置表.html';
		var fileName = 'DeviceHardware';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/deviceHardware',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	centerPanel.add({
			        		style : 'overflow:auto;',
			        		border : 0,
						    loader:{
						        url:'api/report/deviceHardware/downloadFile?path='+ object.path + '&reportTitle=' + fileName,
			        	        autoLoad :true,
			        	        //如果你想在html中能执行js的话.就true
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
//		var fileName = '系统硬件配置表.' + type;
		var fileName = 'DeviceHardware';
		Ext.Ajax.request({
		    url: 'api/report/deviceHardware',
		    method:'GET',
		    params: values,
		    success: function(response){
		        var text = response.responseText;
		        var object = Ext.decode(text);
		        if(object.success){
		        	var url = 'api/report/deviceHardware/downloadFile?path='+ object.path + '&reportTitle=' + fileName;
		        	var iframe = document.getElementById('downloadFileFromWeb');
		        	iframe.src = url;
		        }else{
		        	Eway.alert(object.message);
		        }
		    }
			});
		}
});