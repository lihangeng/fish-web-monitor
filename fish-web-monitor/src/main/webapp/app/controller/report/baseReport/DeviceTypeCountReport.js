
Ext.define('Eway.controller.report.baseReport.DeviceTypeCountReport', {
	extend : 'Eway.controller.base.FishController',

	stores : [],

	models : ['Dict'],



	views : ['Eway.view.report.baseReport.DeviceTypeCountReportView'],

	stores : ['monitor.card.DeviceAtmVendor','monitor.card.DeviceType'],


	refs : [ {
		ref : 'ewayView',
		selector : 'baseReport_DeviceTypeCountReportView',
		autoCreate : true,
		xtype : 'baseReport_DeviceTypeCountReportView'
	} ],

	init : function() {
		this.control({
			'baseReport_DeviceTypeCountReportView button[action=exportPdf]':{
				click : this.onExportPdf
			},
			'baseReport_DeviceTypeCountReportView button[action=exportHtml]':{
				click : this.onExportHtml
			},
			'baseReport_DeviceTypeCountReportView button[action=exportXls]':{
				click : this.onexportXls
			},
			'baseReport_DeviceTypeCountReportView button[action=query]':{
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
//		var fileName = '设备品牌统计表.html';
		var fileName = 'DeviceTypeCount';
		if(form.isValid() == true){
			Ext.Ajax.request({
			    url: 'api/report/deviceTypeCount',
			    method:'GET',
			    params: values,
			    success: function(response){
			        var object = Ext.decode(response.responseText);
			        if(object.success){
			        	centerPanel.add({
			        		style : 'overflow:auto;',
			        		border : 0,
						    loader:{
						        url:'api/report/deviceTypeCount/downloadFile?path='+ object.path + '&reportTitle=' + fileName,
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
//		var fileName = '设备品牌统计表.' + type;
		var fileName = 'DeviceTypeCount';
		Ext.Ajax.request({
		    url: 'api/report/deviceTypeCount',
		    method:'GET',
		    params: values,
		    success: function(response){
		        var text = response.responseText;
		        var object = Ext.decode(text);
		        if(object.success){
		        	var url = 'api/report/deviceTypeCount/downloadFile?path='+ object.path + '&reportTitle=' + fileName;
		        	var iframe = document.getElementById('downloadFileFromWeb');
		        	iframe.src = url;
		        }else{
		        	Eway.alert(object.message);
		        }
		    }
			});
		}
});