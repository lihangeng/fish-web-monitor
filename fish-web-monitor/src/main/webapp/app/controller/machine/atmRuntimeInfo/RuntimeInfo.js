
Ext.define('Eway.controller.machine.atmRuntimeInfo.RuntimeInfo', {
	extend: 'Ext.app.Controller',

	stores: ['machine.atmRuntimeInfo.RuntimeInfo',
				'machine.DeviceAtmType',
				'machine.DeviceAwayFlagComboBox',
				'machine.atmType.DeviceAtmVendor',
				'machine.atmType.DeviceAtmCatalog'],
	models: ['machine.atmRuntimeInfo.RuntimeInfo'],

	views: ['Eway.view.machine.atmRuntimeInfo.View'],

	exportWin : 'Eway.view.machine.atmRuntimeInfo.ExportWin',

	refs: [{
		ref: 'ewayView',
		selector: 'atmRuntimeInfo_View',
		autoCreate: true,
		xtype: 'atmRuntimeInfo_View'
	},{
		ref : 'runtimeInfogrid',
		selector : 'atmRuntimeInfo_Grid'
	},{
		ref : 'exportWin',
		selector : 'machine_atmRuntimeInfo_ExportWin'
	}],
	init: function() {
		this.control({
			'atmRuntimeInfo_Grid button[action=query]': {
				click : this.onQuery
			},
			'atmRuntimeInfo_Grid button[action=export]': {
				click : this.onExport
			},
			'atmRuntimeInfo_Grid button[action=exportLast30]': {
				click : this.onExportLast30
			}
		});
	},

	onQuery: function(){
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();

		// 查询输入验证
		if (bool == false) {
			Eway.alert("查询项中存在不合法的输入,不能提交.");
			return
		}
		var values = form.getValues();
		var store = view.down('gridpanel').getStore();

		store.setUrlParamsByObject(values);
		store.setBaseParam('organizationID',ewayUser.getOrgId());
		store.loadPage(1);
	},

	onExport : function(){
		var grid = this.getRuntimeInfogrid();
		var sm = grid.getSelectionModel();
		var store = grid.getStore();
		if(sm.getCount() == 1){
			var win = Ext.create(this.exportWin);
			var exportFromDateButton = win.query('button[action = exportFromDate]')[0];
			exportFromDateButton.on('click',this.onExportFromDate,this);
			var form = win.down('form').getForm();
			form.loadRecord(sm.getLastSelected());
			win.show();
		}else{
			Eway.alert('请选择要导出信息的设备.');
		}
	},

	onExportFromDate : function(){
		var win = this.getExportWin();
		var form = this.getExportWin().down('form').getForm();
		var grid = this.getRuntimeInfogrid();
		var sm = grid.getSelectionModel();
		var data = form.getValues();
		var ip = sm.getLastSelected().get('ip');
		var terminalId = sm.getLastSelected().get('terminalId');
		var startDate = data.startDate;
		var endDate = data.endDate;

		var winEl = win.getEl();
		winEl.mask('正在连接......');

		Ext.Ajax.request({
			method : 'POST',
			url : 'api/machine/runtimeInfo/exportFromDate/check',
			params : {
					ip : ip,
					startDate : startDate,
					terminalId : terminalId,
					endDate : endDate
					},
			success : function(response){
				winEl.unmask();
				var object = Ext.decode(response.responseText);
				if(object.success == false){
					Eway.alert(object.msg);
				}else{
					var path = object.path;
					window.location.href = 'api/machine/runtimeInfo/exportFromDate/download?path=' + path;
					win.close();
				}
			},
			failure : function(){
				winEl.unmask();
				Eway.alert('连接失败.');
			}
		});
		},

	onExportLast30 : function(){
//		var grid = this.getRuntimeInfogrid();
		var grid = Ext.ComponentQuery.query('atmRuntimeInfo_Grid')[0];
		var sm = grid.getSelectionModel();

		var gridEl = grid.getEl();
		gridEl.mask('正在连接......');
		if(sm.getCount() == 1){
			var record = sm.getLastSelected();
			var ip = record.data.ip;
			var terminalId = record.data.terminalId;
			Ext.Ajax.timeout = 90000;
			Ext.Ajax.request({
				method : 'POST',
				url : 'api/machine/runtimeInfo/exportLast30/check',
				params : {ip : ip, terminalId : terminalId},
				success : function(response){
					gridEl.unmask();
					var object = Ext.decode(response.responseText);
					if(object.success == false){
						Eway.alert(object.msg);
					}else{
						var path = object.path;
/*						var itemEl = grid.getEl();
						var iframe = itemEl.prev();
						if(iframe){
							var action = "";
							for(var i in iframe.dom.attributes){
							var attr = iframe.dom.attributes[i];
							if(attr.nodeValue == "test"){
								action = "test";
								break;
							}
						}
							if(action == 'test'){
								Ext.core.Element.get(iframe).destroy();
							}
						}
						iframe = Ext.core.DomHelper.createDom({
							tag : 'iframe',
							src : 'api/machine/runtimeInfo/exportLast30/download?path=' + path,
							style : "display:none",
							action : 'test'
						});
						itemEl.insertSibling(iframe);*/
						window.location.href = 'api/machine/runtimeInfo/exportLast30/download?path=' + path;
					}
				},
				failure : function(){
					gridEl.unmask();
					Eway.alert('连接失败.');
				}
			});
			/*var itemEl = grid.getEl();
			var iframe = itemEl.prev();
			if(iframe){
				var action = "";
				for(var i in iframe.dom.attributes){
				var attr = iframe.dom.attributes[i];
				if(attr.nodeValue == "test"){
					action = "test";
					break;
				}
			}
			if(action == 'test'){
				Ext.core.Element.get(iframe).destroy();
			}
		}
			iframe = Ext.core.DomHelper.createDom({
				tag : 'iframe',
				src : 'api/machine/runtimeInfo/exportLast30?ip=' + ip +'&terminalId='+terminalId,
				style : "display:none",
				action : 'test'
			});
			itemEl.insertSibling(iframe);*/
/*			Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/runtimeInfo/exportLast30',
			params : {
				ip : ip,
				terminalId: terminalId
			},
			success : function(response){
				var object = Ext.decode(response.responseText);
				if(object.success == false){
					Eway.alert('取到的信息为空!');
				}else{
					var string = 'http://localhost:8085/fish-web/';
					window.location.href = string + object.data;
				}
			}
		});*/
		}else {
			gridEl.unmask();
			Eway.alert('请选择要导出信息的设备.');
		}


	}


});