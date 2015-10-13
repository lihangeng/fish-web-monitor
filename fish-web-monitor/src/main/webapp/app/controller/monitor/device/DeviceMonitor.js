Ext.define('Eway.controller.monitor.device.DeviceMonitor',{

	extend : 'Eway.controller.base.Controller',

	views : [
		'monitor.device.View',
		'monitor.device.MonitorStateConfig',
		'monitor.device.FilterConfig',
		'monitor.device.BaseWindow',
		'monitor.device.DeviceInfoStatus'
	],

	stores : [
		'monitor.device.DeviceMonitorList'
	],

	models : [
		'monitor.device.DeviceMonitorList',
		'monitor.device.FilterItem',
		'monitor.device.DeviceBox'
	],

	refs : [{
		ref : 'ewayView',
		selector : '#monitor_device_view',
		autoCreate : true,
		xtype : 'monitor_device_view',
		id : 'monitor_device_view_id'
	}],

	showSort :true,

	init : function(){
		this.control({
			'monitor_device_view button[action=monitorOK]' : {//开始/暂停监控
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onChangeMonitorState,
				scope : this
			},
			'monitor_device_view button[action=region]' : {
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onReginBtnClick,
				scope : this
			},
			'monitor_device_view button[action=query]' : {//查询按钮
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onQueryBtnClick,
				scope : this
			},
			'monitor_device_view button[action="previous"]' : {//上一页
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onPreviousBtnClick,
				scope : this
			},
			'monitor_device_view button[action="next"]' : {//下一页
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onNextBtnClick,
				scope : this
			},
			'monitor_device_view button[action=monitorState]' : {//设置监控状态
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onMonitorStateBtnClick,
				scope : this
			},
			'monitor_device_view button[action=filter]' : {//设置过滤条件
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onFilterBtnClick
			},
			'monitor_device_view button[action=exportToExcel]' : {//导出为excel
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this.test
			},
			'monitor_device_view button[action=pattern]' : {
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onPatternBtnClick
			},
			'monitor_device_view menuitem[action=listPattern]' : {//打开"列表方式"
				click : this._onListPatternBtnClick
			},
			'monitor_device_view menuitem[action=matrixPattern]' : {//打开"矩形方式"
				click : this._onMatrixPatternBtnClick
			},
			'monitor_device_view menuitem[action=maxIconPattern]' : {//打开"矩形方式"
				click : this._onMaxIconPatternBtnClick
			},
			'monitor_device_view menuitem[action=boxPattern]' : {//打开"钱箱方式"
				click : this._onBoxPatternBtnClick
			},
			'monitor_device_view dataview[name=matrix]' : {
				itemmouseenter : this._onDataViewItemMouseEnter2,
				itemclick : this._onDataViewItemMouseClick,
				itemcontextmenu : this._onDataViewItemContextMenu
			},
			'monitor_device_view gridpanel[name=list]' : {//列表方式显示的时候，行右键菜单
				cellclick : function(table,td,cellIndex,reocrd,tr,rowIndex){
					if(cellIndex != 2){
						return ;
					}
					this._onDataViewItemMouseClick(table,reocrd);
				},
				itemcontextmenu : this._onDataViewItemContextMenu,
				scope : this
			},
			'monitor_device_view gridpanel[name=box]' : {//列表方式显示的时候，行右键菜单
				cellclick : function(table,td,cellIndex,record,tr,rowIndex){
					if(cellIndex == 2){
						this._onDataViewItemMouseClick(table,record);
					} else if(cellIndex == 3) {
						this._onDataViewItemBoxStatusClick(table, record)
					}
				},
				itemcontextmenu : this._onDataViewItemContextMenu,
				scope : this
			},
			'monitor_device_baseWindow button[action=save]' : {
				click : this._onBaseWindowOK,
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut
			},
			'monitor_device_view textfield[action="number"]' : {//监控台数enter事件
				scope : this,
				specialkey : this._addEnter
			},
			'monitor_device_view textfield[action="deviceNumber"]' : {//设备号enter事件
				scope : this,
				specialkey : this._addEnter
			},
			'monitor_device_view button[action="sort"]' : {
				scope : this,
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onSort
			}
		});

	},

	//矩形显示时，鼠标进入事件
	_onDataViewItemMouseEnter2 : function(view,record,itemHtml,index,e,eOpts){
	    var winEl = Ext.get(itemHtml);
	    var codeHtml = winEl.down('span');//只在code部分提示
	    Ext.create('Ext.tip.ToolTip',{
			target:codeHtml,
			dismissDelay:10000,
			trackMouse :false,
			html: '<li style="list-style-type :none">'+Eway.locale.commen.devTypeName+':'+record.data.type+'</li>'+
				  '<li style="list-style-type :none">'+Eway.locale.commen.orgNameBelongs+':'+record.data.org+'</li>'+
				  '<li style="list-style-type :none">'+Eway.locale.commen.appVersion+':'+record.data.appRelease+'</li>'+
				  '<li style="list-style-type :none">'+Eway.locale.monitor.devMonitor.boxCurrentCount+':'+record.data.boxCurrentCount+'</li>'+
				  '<li style="list-style-type :none">'+Eway.locale.monitor.devMonitor.retainCardCount+':'+record.data.retainCardCount+'</li>'
		});
	},

	//监控项右键菜单
	_onDataViewItemContextMenu : function(view,record,itemHtml,index,e,eOpts){
		e.preventDefault();
        e.stopEvent();
        var ip = record.data.ip,
        	code = record.data.code,
        	terminalId  = code;
        var winEl = Ext.get(itemHtml);
		var menu = Ext.create("Ext.menu.Menu",{
			 		floating : true,
                    items : [{
                    		text:Eway.locale.monitor.devMonitor.remote.screen,
                    		code : 'remoteScreen',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                        		'click' : function(){

								var store = Ext.create('Eway.store.agent.Image');
								var imageTpl = new Ext.XTemplate(
								    '<tpl for=".">',
								        '<div style="margin-bottom: 10px;" class="thumb-wrap" >',
								          '<img src="{src}" style="height:380px;width:470px;"/>',
								          '<br/><span>{caption}</span>',
								        '</div>',
								    '</tpl>'
								);
								var view  = Ext.create('Ext.view.View', {
								    store: store,
								    tpl: imageTpl,
								    layout:'fit',
								    itemSelector: 'div.thumb-wrap',
								    emptyText: 'No images available',
								    renderTo: Ext.getBody()
								});
								var win = Ext.create('Ext.window.Window',{
											modal: true,
											height: 450,
										    width: 500,
										    title : Eway.locale.monitor.devMonitor.remote.screen,
										    autoScroll : true,
										    maximizable: true,
										    items:[{
										    	xtype : view
										    }]

								}).show();

								store.load({
									params : {ip : ip ,code: code},
									callback: function(records, operation, success) {
								        if(success == false){
											Eway.alert(operation.getError());
								        }
 									}
								});




                        		},
                        		single: true,
                        		scope : this
                        	}
                    	},{
                    		text:Eway.locale.monitor.devMonitor.remote.log,
                    		code : 'takeLog',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                        		'click' : function(){
									winEl.mask(Eway.locale.tip.nowLink);
									Ext.Ajax.request({
										method : 'POST',
										url : 'api/agent/downLogs/download',
										params : {
											ip : ip,
											code : code
										},
										success : function(response) {
											var object = Ext.decode(response.responseText);
											if (object.success == true) {
												winEl.unmask();
												Ext.MessageBox.confirm(Eway.locale.tip.tips,
														Eway.locale.tip.business.device.logLoadConfirm, function(button, text) {
														if (button == "yes") {
															var url = 'api/agent/downLogs/downloadFile?path=' + object.path + '&fileName=' + object.fileName;
															var iframe = document.getElementById('downloadFileFromWeb');
															iframe.src = url;
														}
													});
											} else {
												winEl.unmask();
												if(undefined==object.errors||''==object.errors){
													Eway.alert(Eway.locale.tip.business.device.logFail);
												}
												else{
													Eway.alert(Eway.locale.tip.business.device.logFail + object.errors);
												}
											}
										},
										failure : function() {
											winEl.unmask();
											Eway.alert(Eway.locale.tip.business.device.logFail);
										}
									});
                        		},
                        		single: true,
                        		scope : this
                        	}
                    	},{
                        	text : Eway.locale.monitor.devMonitor.remote.remoteBrowser,
                        	code : 'remoteBrowser',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                        		'click' : function(){
                        			var controller = this.getController('agent.remote.RemoteBrowse');
									controller.display(ip);
                        		},
                        		single: true,
                        		scope : this
                        	}
                    	},{
                    		text :Eway.locale.monitor.devMonitor.remote.restart,
                    		code : 'restart',
                    		listeners:{
                    			'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                    			'click' : function(){
	                    			var dialog = Ext.create('Ext.window.MessageBox', {
						            buttons: [{
						                text: Eway.locale.tip.business.device.reboot,
						                iconCls: 'icon-add',
						                handler: function() {
						                	Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.rebootConfirm,callBack);
						                	function callBack(id){
						                		if(id == 'yes'){
								            		dialog.close();
													winEl.mask(Eway.locale.tip.business.device.rebooting);
													Ext.Ajax.request({
														method : 'POST',
														url : 'api/agent/normalReboot',
														params : {
															restartType : "OS",
															ip : ip,
															terminalId : terminalId
														},
														success : function(response) {
															var object = Ext.decode(response.responseText);
															if (object.appRet == 00) {
																winEl.unmask();
																Eway.alert(Eway.locale.tip.business.device.rebootSucess);
															} else {
																winEl.unmask();
																Eway.alert(Eway.locale.tip.business.device.rebootFail);
															}
														},
														failure: function(){
															winEl.unmask();
															Eway.alert(Eway.locale.tip.business.device.rebootSendFail);
														}
													})
						                		}
						                	}
						            	}
						            },{
						            	text: Eway.locale.tip.business.device.forceReboot,
						            	iconCls: 'icon-add',
						            	handler: function(){
						            		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.forceRebootConfirm,callBack);
						            		function callBack(id){
						            			if(id == 'yes'){
						            				dialog.close();
													winEl.mask(Eway.locale.tip.business.device.forceRebooting);
													Ext.Ajax.request({
														method : 'POST',
														url : 'api/agent/reboot',
														params : {
															restartType : "OS",
															ip : ip,
															terminalId : terminalId
														},
														success : function(response) {
															var object = Ext.decode(response.responseText);
															if (object.appRet == 00) {
																winEl.unmask();
																Eway.alert(Eway.locale.tip.business.device.forceRebootSuccess);
															} else {
																winEl.unmask();
																Eway.alert(Eway.locale.tip.business.device.forceRebootFail);
															}
														},
														failure: function(){
															winEl.unmask();
															Eway.alert(Eway.locale.tip.business.device.forceRebootSendFail);
														}
													})
						            			}
						            		}
						            	}
						            },{
						            	text: Eway.locale.button.back,
						            	iconCls: 'icon-add',
						            	handler: function(){
						            		dialog.close();
						            	}
						            }]
						        });

						        dialog.show({
						            title: Eway.locale.monitor.devMonitor.remote.restart,
						            msg: Eway.locale.monitor.devMonitor.remote.restartWay,
						            icon: Ext.MessageBox.WARNING
						        });

                    			},
                        		single: true,
                        		scope : this

                    		}
                    	},{
                    		text: Eway.locale.monitor.devMonitor.remote.processList,
                    		listeners:{
                        		click : function(){
									var controller = this.getController('agent.remote.RemoteBrowseProcess');
									controller.display(ip);
                        		},
                        		single: true,
                        		scope : this
                        	}
                    	}
					]
		});
		menu.showAt(e.getXY());
	},

	test : function() {
		var controller = this.getController('agent.remote.RemoteBrowse') ;
		controller.init();
		controller.display();
	},

	_addEnter : function(field, e){
		 if (e.getKey() == e.ENTER) {
		 	var view = Ext.ComponentQuery.query('monitor_device_view')[0];
		 	var btn = view.down('button[action="query"]');
//		 	var btn = field.nextSibling('button[action="query"]');
            this._onQueryBtnClick(btn);
        }
	},

	_onChangeMonitorState : function(btn){
		var view = this.getEwayView();
		// 获得当前监视状态
		monitor = view.config.monitor;
		btn.setDisabled(true);
		var text = "";
		if (monitor) {
			text = Eway.locale.monitor.business.transaction.transactionMonitor.begin;
			btn.glyph = 0xf04b;
			view.config.monitor = false;
			this.getEwayView().closeMatrixMonitor();
		} else {
			text = Eway.locale.monitor.business.transaction.transactionMonitor.stop;
			btn.glyph = 0xf04c;
			view.config.monitor = true;
			var ewayView = this.getEwayView();
			ewayView.activatePanel();
		}
		Ext.defer(function(){
				btn.setDisabled(false);
				btn.setText(text);
			},2000);
	},

	_closeMonitorDeviceWin : function(panel){
		var win = Ext.WindowManager.get('monitor_device_detail_window_id');
		if(win){
			win.close();
		}
	},
	_onMouseOver : function(button){
		button.setText("<font color='yellow'>"+button.getText()+"</font>");
	},
	_onMouseOut : function(button){
		button.setText(Ext.util.Format.stripTags(button.getText()));
	},

	//左侧树节点单击事件
	_onReginBtnClick : function(button){
		var win = Ext.WindowManager.get('monitor_device_baseWindow');
		if(!win){
			win = Ext.widget('monitor_device_baseWindow');
		}

		var treepanel = win.down('treepanel[action="monitor_decice_reginConfig"]');
		if(!treepanel){
			var store = Ext.create('Eway.store.OrganizationTree',{});
			treepanel = Ext.create('Ext.tree.Panel',{
				frame : true,
				border : 0,
				store : store,
				action : 'monitor_decice_reginConfig'
			});
		}
		win.setTitle(Eway.locale.tip.business.device.chooseOrg);
		win.display(treepanel,button);
	},

	//执行‘查询’
	_onQueryBtnClick : function(btn){
		var orgField = btn.previousNode('hiddenfield[name="orgId"]');
		var deviceCodeField = btn.previousNode('textfield[action="deviceNumber"]');

		var ewayView = this.getEwayView();
		var numberField = ewayView.down('numberfield[action="number"]');

		if(!Ext.isNumeric(numberField.getValue()) || numberField.getValue()<=0 || numberField.getValue()>1000){
			numberField.setValue(120);
		}
		var orgId = orgField.getValue();
		var number = numberField.getValue();
		var deviceCode = deviceCodeField.getValue();
		var params = {
			number : number
		};
		if(orgId !== ''){
			params.orgId = orgId;
		}else{
			params.orgId = ewayUser.getOrgId();
		}
		if(deviceCode){
			params.deviceCode = deviceCode;
		}

		this._reload(params);
	},

	_onPreviousBtnClick : function(btn){
		var ewayView = this.getEwayView();
		var nextBtn = ewayView.down('button[action="next"]');
		var perBtn = ewayView.down('button[action="previous"]');
		nextBtn.enable();

		var numberField = ewayView.down('textfield[action="number"]');
		var number = numberField.getValue();
		var total = ewayView.getTotal();
		var currentPage = ewayView.getCurrentPage();

		currentPage--;
		if(currentPage*number >= total ){
			currentPage = 1;
		}
		if(currentPage == 1){
			perBtn.disable();
		}
		ewayView.setCurrentPage(currentPage);
		this._onChangePage(number,currentPage);
	},

	_onNextBtnClick : function(btn){
		var ewayView = this.getEwayView();
		var nextBtn = ewayView.down('button[action="next"]');
		var perBtn = ewayView.down('button[action="previous"]');
		perBtn.enable();
		var numberField = ewayView.down('textfield[action="number"]');
		var number = numberField.getValue();
		var total = ewayView.getTotal();
		var currentPage = ewayView.getCurrentPage();

		currentPage++;
		if(currentPage*number >= total ){
			currentPage = parseInt(total / number);
			if (total % number >= 1) {
				currentPage++;
			}
			nextBtn.disable();
		}
		ewayView.setCurrentPage(currentPage);
		this._onChangePage(number,currentPage);
	},

	_onChangePage : function(number,currentPage){
		var start = number*(currentPage-1);
		var limit = number;
		this._reload({
			number: number,
			startP : start,
			limitP : number,
			page : currentPage
		});
	},

	//打开'设置监控状态'页面
	_onMonitorStateBtnClick : function(button){
		var win = Ext.WindowManager.get('monitor_device_baseWindow');
		if(!win){
			win = Ext.widget('monitor_device_baseWindow');
		}
		win.setWidth(600);
		win.setHeight(600);
		var panel = win.down('panel[action="monitor_device_stateConfig"]');
		if(!panel){
			panel = Ext.widget('monitor_device_stateConfig');
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/monitor/device/getFilter',
				params : {
					userId : ewayUser.getId()
				},
				success : function(response){
					var object = Ext.decode(response.responseText);
					panel.getForm().setValues(object.filter);
					panel.setMemoryRecord(object.filter);
				},
				failure : function(){
				}
			});
		}
		win.setTitle(Eway.locale.tip.business.device.stateSet);
		win.display(panel,button);
	},

	//打开'设置过滤条件'页面
	_onFilterBtnClick : function(button){
		var win = Ext.WindowManager.get('monitor_device_baseWindow');
		if(!win){
			win = Ext.widget('monitor_device_baseWindow');
		}
		var panel = win.down('panel[action="monitor_device_filterConfig"]');
		if(!panel){
			panel = Ext.widget('monitor_device_filterConfig');
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/monitor/device/getMonitorFilter',
				params : {
					userId : ewayUser.getId()
				},
				success : function(response){
					var object = Ext.decode(response.responseText);
					var form = panel.getForm();
					var brand = form.findField("brandItem");
					brand.setValue(object.filter.brandItem);
					var classify = form.findField("classifyItem");
					classify.setValue(object.filter.classifyItem);
					var awayFlag = form.findField("ingItem");
					awayFlag.setValue(object.filter.ingItem);
					var sellItem = form.findField("sellItem");
					sellItem.setValue(object.filter.sellItem);
					var atmGroup = form.findField("atmGroup");
					atmGroup.setValue(object.filter.atmGroup);
				}
			});
		}
		win.setTitle(Eway.locale.tip.business.device.filterSet);
		win.height = 240;
		win.width =400;
		win.display(panel,button);

	},

	_onBaseWindowOK : function(btn){
		var win = btn.up('window');
		var panel = win.getLayout().getActiveItem();
		var view = this.getEwayView();
		var numberField = view.down('textfield[action="number"]');
		var orgIdField = view.down('hiddenfield[name="orgId"]');
		var deviceCodeField = view.down('textfield[action="deviceNumber"]');
		var params = {
			number : numberField.getValue(),
			orgId : orgIdField.getValue(),
			deviceCode : deviceCodeField.getValue()
		};

		if(panel.action == 'monitor_device_stateConfig'){
			this._onMonitorStateWinOK(panel, params);
		}
		else if(panel.action == 'monitor_decice_orderConfig'){

		}
		else if(panel.action == 'monitor_device_filterConfig'){
			this._onFilterWinOK(panel, params);
		}
		win.hide();
	},

	_onMonitorStateWinOK : function(panel,params){
		var form = panel.getForm();
		var values = form.getValues();
		panel.setMemoryRecord(values);
		params.stateItems = form.getFieldValues()
		this._reload(params);
	},

	_onFilterWinOK : function(panel,params){
		var form = panel.getForm();
		params.filterItems = form.getFieldValues();
		this._reload(params);
	},

	_reload : function(params){
		var view = this.getEwayView();
		var button = view.down('button[action="monitorOK"]');
		if(button.getText() == Eway.locale.commen.begin){
			Eway.alert(Eway.locale.tip.business.device.connFirst);
			return;
		}
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		view.changeFilterLoad(currentPanel,params);
	},

	_onPatternBtnClick : function(button){
		var panel = button.up('panel').ownerCt;
		var card = panel.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		var currentStore = currentPanel.getStore();
		var itemId = currentPanel.itemId;
		if(itemId == 'list'){
			layout.setActiveItem('dataview');
		}
		else if(itemId == 'dataview'){
			layout.setActiveItem('list');
		}
		currentPanel = layout.getActiveItem();
		itemId = currentPanel.itemId;
		if(itemId == 'list'){
			button.setText(Eway.locale.tip.business.device.matrixPattern);
			button.setIconCls('matrixPattern');
		}
		else if(itemId == 'dataview'){
			button.setText(Eway.locale.tip.business.device.listPattern);
			button.setIconCls('listPattern');
		}
		var view = this.getEwayView();
		view.loadPanelData(currentPanel,currentStore);
	},
	_onListPatternBtnClick : function() {
		var view = this.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('list');
		var btn = view.down('button[action="query"]');
	    this._onQueryBtnClick(btn);
	},
	_onBoxPatternBtnClick : function() {
		var view = this.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('box');
		var btn = view.down('button[action="query"]');
	    this._onQueryBtnClick(btn);
	},
	_onMatrixPatternBtnClick : function() {
		var view = this.getEwayView();
		view.screen = "";
		// ExtJs4.2删除class样式的接口变更,由removeClass更新为removeCls
		view.down('#images-view').removeCls('maxicon');

		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('dataview');
		var btn = view.down('button[action="query"]');
	    this._onQueryBtnClick(btn);
	},
	_onMaxIconPatternBtnClick : function() {
		this._onMatrixPatternBtnClick();
		var view = this.getEwayView();
		view.screen = 64;
		view.down('#images-view').addCls('maxicon');
	},

	//矩形显示时，鼠标左键单击事件
	_onDataViewItemMouseClick : function(view,record,htmlItem,index,e){
		var controller = this.getController('monitor.device.DeviceInfoStatus');
//		controller.init();
		controller.displayWin(record);
	},

	// 钞箱显示方式时，单击状态事件
	_onDataViewItemBoxStatusClick : function(view, record, htmlItem, index, e) {

		var deviceBoxModel = this.getMonitorDeviceDeviceBoxModel();
		var ip = record.get('ip');
		var controller = this.getController('monitor.device.DeviceBox');
		var view = this.getEwayView();
		var winEl = view.getEl();
		winEl.mask(Eway.locale.tip.nowLink);
		deviceBoxModel.load(ip, {
			success : function(record) {
				winEl.unmask();
				controller.init();
				controller.displayWin(record);
			},
			failure: function(){
				winEl.unmask();
				Eway.alert(Eway.locale.tip.business.device.getCashInfoFail);
			}
		});
	}

});