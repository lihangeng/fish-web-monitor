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
		'monitor.device.DeviceMonitorList',
		'monitor.device.DeviceFilter'
	],

	models : [
		'monitor.device.DeviceMonitorList',
		'monitor.device.FilterItem',
		'monitor.device.DeviceBox',
		'monitor.device.DeviceFilter'
	],

	refs : [{
		ref : 'ewayView',
		selector : '#monitor_device_view',
		autoCreate : true,
		xtype : 'monitor_device_view',
		id : 'monitor_device_view_id'
	}, {
		ref : 'filterGrid',
		selector : 'monitor_device_filtermanager_grid'
	}, {
		ref : 'filterWin',
		selector : 'monitor_device_filtermanager_filterwin'
	} ],

	showSort :true,

	init : function(){
		this.control({
			'monitor_device_view button[action=query]' : {//查询按钮
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onQueryBtnClick,
				scope : this
			},
			'monitor_device_view button[action=monitorState]' : {//设置监控状态
				mouseover : this._onMouseOver,
				mouseout : this._onMouseOut,
				click : this._onMonitorStateBtnClick,
				scope : this
			},
			'monitor_device_view button[action=listPattern]' : {//打开"列表方式"
				click : this._onListPatternBtnClick
			},
			'monitor_device_view button[action=matrixPattern]' : {//打开"矩形方式"
				click : this._onMatrixPatternBtnClick
			},
			'monitor_device_view button[action=maxIconPattern]' : {//打开"矩形方式"
				click : this._onMaxIconPatternBtnClick
			},
			'monitor_device_view button[action=boxPattern]' : {//打开"钱箱方式"
				click : this._onBoxPatternBtnClick
			},
			'monitor_device_view menuitem[action=summaryPattern]' : {//打开"全局方式"
				click : this._onSummaryPatternBtnClick
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
			'monitor_device_view textfield[action="deviceNumber"]' : {//设备号enter事件
				scope : this,
				specialkey : this._addEnter
			},
			
			// 选择订阅条件
			'monitor_device_view monitor_device_showtype_tbar combobox[action="filterName"]' : {
				scope : this,
				change : this._onFilterNameChange
			},
			
			
			// 订阅管理
			'monitor_device_filtermanager_grid button[action=query]' : {
				scope : this,
				click : this._onFilterManagerQuery
			},
			'monitor_device_filtermanager_grid button[action=add]' : {
				scope : this,
				click : this._onFilterManagerAdd
			},
			'monitor_device_filtermanager_grid button[action=update]' : {
				scope : this,
				click : this._onFilterManagerUpdate
			},
			'monitor_device_filtermanager_grid button[action=remove]' : {
				scope : this,
				click : this._onFilterManagerRemove
			},
			'monitor_device_filtermanager_add button[action=confirm]' : {
				scope : this,
				click : this._onFilterManagerAddConfirm
			},
			'monitor_device_filtermanager_update button[action=confirm]' : {
				scope : this,
				click : this._onFilterManagerUpdateConfirm
			},
			'monitor_device_filtermanager_filterwin' : {
				scope : this,
				close : this._onFilterWinClose
			}
		});

	},
	
	// 关闭订阅管理窗口时，加载订阅条件选择项
	_onFilterWinClose : function( panel, eOpts ) {
		
		var view = this.getEwayView();
		var filterNameField = view.down('combobox[action="filterName"]');

		var filterId = filterNameField.getValue();
		var store = filterNameField.getStore();
		store.load({
			callback: function(records, operation, success) {
				var del = true;
				if (records && records.length > 0) {
					for(var i=0; i<records.length; i++) {
						var record = records[i];
						if (record.get('id') == filterId) {
							del = false;
							break;	
						}
					}
				}
				if (del) {
					filterNameField.setValue();
				}
    		}
		});
	},
	
	_onFilterNameChange : function( field, newValue, oldValue, eOpts ) {
		
		this._onQueryBtnClick();
	},
	
	// 订阅条件查询
	_onFilterManagerQuery : function(btn) {
		
		var win = this.getFilterWin();
		var form = win.down('form');
		
		var values = form.getValues();
		values.userId = ewayUser.getId();
		
		var grid = this.getFilterGrid();
		var store = grid.getStore();
		
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	
	//打开'订阅条件管理'页面
	_onMonitorStateBtnClick : function(button) {
		
		var filterWin = Ext.create('Eway.view.monitor.device.filterManager.FilterWin');
		var grid = filterWin.down('grid');
		var store = grid.getStore();
		
		store.setUrlParamsByObject({
			userId : ewayUser.getId()
		});
		store.loadPage(1);
		
		filterWin.show();
	},
	
	_onFilterManagerAdd : function() {
		var filterAddWin = Ext.create('Eway.view.monitor.device.filterManager.Add');
		filterAddWin.show();
	},
	
	_addOrUpdate : function(btn, url, succMsg) {
		var me = this;
		var win = btn.up('window');
		var forms = win.query('form');
		
		if (!forms[0].isValid()) {
			return;
		}
		
		var val0 = forms[0].getValues();
		var val1 = forms[1].getForm().getFieldValues();
		
		var values = {};
		
		for(var i in val0) {
			values[i] = val0[i];
		}
		for(var i in val1) {
			values[i] = val1[i];
		}
		values.userId = ewayUser.getId();
		var winEl = win.getEl();
		winEl.mask();
		
		Ext.Ajax.request({
			method : 'POST',
			url : url,
			params : values,
			success : function(response) {
				var object = Ext.decode(response.responseText);
				winEl.unmask();
				if (object.success == true) {
					Eway.alert(succMsg);
					win.close();
					me._onFilterManagerQuery();
				} else {
					Eway.alert(EwayLocale.tip.business.device.logFail);
				}
			},
			failure : function() {
				winEl.unmask();
				Eway.alert(EwayLocale.tip.business.device.logFail);
			}
		});
	},
	
	_onFilterManagerAddConfirm : function(btn) {
		this._addOrUpdate(btn, 'api/monitor/device/monitorFilter/add', EwayLocale.addSuccess);
	},
	_onFilterManagerUpdateConfirm : function(btn) {
		this._addOrUpdate(btn, 'api/monitor/device/monitorFilter/update', EwayLocale.updateSuccess);
	},
	
	// 修改订阅条件
	_onFilterManagerUpdate : function(btn) {
		var me = this;
		
		var grid = this.getFilterGrid();
		
		var sm = grid.getSelectionModel();
		if(sm.getCount() < 1) {
			Eway.alert(EwayLocale.choiceUpdateMsg);
			return;
		}
		var record = sm.getLastSelected();
		
		var win = Ext.create('Eway.view.monitor.device.filterManager.Update');
		
		var forms = win.query('form');
		
		var form0 = forms[0].getForm();
		var form1 = forms[1].getForm();
		
		var brand = form0.findField("brandItem");
		brand.setValue(record.get('brandItem'));
		var classify = form0.findField("classifyItem");
		classify.setValue(record.get('classifyItem'));
		var awayFlag = form0.findField("ingItem");
		awayFlag.setValue(record.get('ingItem'));
		var sellItem = form0.findField("sellItem");
		sellItem.setValue(record.get('sellItem'));
		var atmGroup = form0.findField("atmGroup");
		atmGroup.setValue(record.get('atmGroup'));
		var filterName = form0.findField("filterName");
		filterName.setValue(record.get('filterName'));
		var id = form0.findField("id");
		id.setValue(record.get('id'));
		var orgId = form0.findField("orgId");
		orgId.setValue(record.get('orgId'));
		var orgName = form0.findField("orgName");
		orgName.setValue(record.get('orgName'));
		
		var run = record.get('runStatusFilterForm');
		var mod = record.get('modStatusFilterForm');
		var net = record.get('netStatusFilterForm');
		var box = record.get('boxStatusFilterForm');
		
		var values = {};
		for(var i in run) {
			values[i] = run[i];
		}
		for(var i in mod) {
			values[i] = mod[i];
		}
		for(var i in net) {
			values[i] = net[i];
		}
		for(var i in box) {
			values[i] = box[i];
		}
		form1.setValues(values);
		
		win.show();
	},
	
	// 删除订阅条件
	_onFilterManagerRemove : function(btn) {
		var me = this;
		
		var grid = this.getFilterGrid();
		var sm = grid.getSelectionModel();
		var count = sm.getCount();
		
		if(count == 0){
			Eway.alert(EwayLocale.tip.remove.none);
			return;
		}
		if(count > 1){
			Eway.alert(EwayLocale.tip.remove.one);
			return;
		}
		Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title, EwayLocale.tip.remove.confirm.info,function(button,text){
			if(button == 'yes'){
				var record = sm.getLastSelected();
				record.erase({
					success: function(record,operation) {
						Eway.alert(EwayLocale.deleteSuccess);
						me._onFilterManagerQuery();
					},
					failure: function(record,operation) {
						//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
						record.dropped = false;
						Eway.alert(EwayLocale.tip.remove.error+ operation.getError());
					},
					scope:this
				});
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
			html: '<li style="list-style-type :none">'+EwayLocale.commen.devTypeName+':'+record.data.type+'</li>'+
				  '<li style="list-style-type :none">'+EwayLocale.commen.orgNameBelongs+':'+record.data.org+'</li>'+
				  '<li style="list-style-type :none">'+EwayLocale.commen.appVersion+':'+record.data.appRelease+'</li>'+
				  '<li style="list-style-type :none">'+EwayLocale.monitor.devMonitor.cash.boxCurrentCount+':'+record.data.boxCurrentCount+'</li>'+
				  '<li style="list-style-type :none">'+EwayLocale.monitor.devMonitor.retainCardCount+':'+record.data.retainCardCount+'</li>'
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
                    		text:EwayLocale.monitor.devMonitor.remote.screen,
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
										    title : EwayLocale.monitor.devMonitor.remote.screen,
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
                    		text:EwayLocale.monitor.devMonitor.remote.log,
                    		code : 'takeLog',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                        		'click' : function(){
									winEl.mask(EwayLocale.tip.nowLink);
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
												Ext.MessageBox.confirm(EwayLocale.tip.tips,
														EwayLocale.tip.business.device.logLoadConfirm, function(button, text) {
														if (button == "yes") {
															var url = 'api/agent/downLogs/downloadFile?path=' + object.path + '&fileName=' + object.fileName;
															var iframe = document.getElementById('downloadFileFromWeb');
															iframe.src = url;
														}
													});
											} else {
												winEl.unmask();
												if(undefined==object.errors||''==object.errors){
													Eway.alert(EwayLocale.tip.business.device.logFail);
												}
												else{
													Eway.alert(EwayLocale.tip.business.device.logFail + object.errors);
												}
											}
										},
										failure : function() {
											winEl.unmask();
											Eway.alert(EwayLocale.tip.business.device.logFail);
										}
									});
                        		},
                        		single: true,
                        		scope : this
                        	}
                    	},{
                        	text : EwayLocale.monitor.devMonitor.remote.remoteBrowser,
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
                    		text :EwayLocale.monitor.devMonitor.remote.restart,
                    		code : 'restart',
                    		listeners:{
                    			'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender,
                    			'click' : function(){
	                    			var dialog = Ext.create('Ext.window.MessageBox', {
						            buttons: [{
						                text: EwayLocale.tip.business.device.reboot,
						                iconCls: 'icon-add',
						                handler: function() {
						                	Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
						                	function callBack(id){
						                		if(id == 'yes'){
								            		dialog.close();
													winEl.mask(EwayLocale.tip.business.device.rebooting);
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
																Eway.alert(EwayLocale.tip.business.device.rebootSucess);
															} else {
																winEl.unmask();
																Eway.alert(EwayLocale.tip.business.device.rebootFail);
															}
														},
														failure: function(){
															winEl.unmask();
															Eway.alert(EwayLocale.tip.business.device.rebootSendFail);
														}
													})
						                		}
						                	}
						            	}
						            },{
						            	text: EwayLocale.tip.business.device.forceReboot,
						            	iconCls: 'icon-add',
						            	handler: function(){
						            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.forceRebootConfirm,callBack);
						            		function callBack(id){
						            			if(id == 'yes'){
						            				dialog.close();
													winEl.mask(EwayLocale.tip.business.device.forceRebooting);
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
																Eway.alert(EwayLocale.tip.business.device.forceRebootSuccess);
															} else {
																winEl.unmask();
																Eway.alert(EwayLocale.tip.business.device.forceRebootFail);
															}
														},
														failure: function(){
															winEl.unmask();
															Eway.alert(EwayLocale.tip.business.device.forceRebootSendFail);
														}
													})
						            			}
						            		}
						            	}
						            },{
						            	text: EwayLocale.button.back,
						            	iconCls: 'icon-add',
						            	handler: function(){
						            		dialog.close();
						            	}
						            }]
						        });

						        dialog.show({
						            title: EwayLocale.monitor.devMonitor.remote.restart,
						            msg: EwayLocale.monitor.devMonitor.remote.restartWay,
						            icon: Ext.MessageBox.WARNING
						        });

                    			},
                        		single: true,
                        		scope : this

                    		}
                    	},{
                    		text: EwayLocale.monitor.devMonitor.remote.processList,
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

	_addEnter : function(field, e){
		 if (e.getKey() == e.ENTER) {
		 	var view = this.getEwayView();
		 	var btn = view.down('button[action="query"]');
            this._onQueryBtnClick(btn);
        }
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

	//执行‘查询’
	_onQueryBtnClick : function(btn) {
		
		var params = {};
		
//		// 设备号
//		var deviceCodeField = btn.previousNode('textfield[action="deviceNumber"]');
//		var deviceCode = deviceCodeField.getValue();
//		if(deviceCode) {
//			params.deviceCode = deviceCode;
//		}
//		
		// 监控条件
		var view = this.getEwayView();
		var filterNameField = view.down('combobox[action="filterName"]');
		var filterId = filterNameField.getValue();
		
		var record = filterNameField.getStore().findRecord('id', filterId);
		this._onFilterMessage(record);

		this._reload(params);
	},
	
	_onFilterMessage : function(record) {
		var tLabel = this.getEwayView().down('label');
		var msg = '';
		
		if (!record) {
			tLabel.setText(EwayLocale.commen.all);
			return;
		}
		
		// 所属机构
		if (record.get('orgId') && record.get('orgId') != 0) {
			msg += ' ' + EwayLocale.machine.atmGroup.orgName + '[';
			msg += record.get('orgName');
			msg +=']';
		}
		
		// 品牌
		if (record.get('brandItem') && record.get('brandItem') != 0) {
			msg += ' ' + EwayLocale.commen.devVendorName + '[';
			msg += record.get('brandItemName');
			msg +=']';
		}
		
		// 型号
		if (record.get('classifyItem') && record.get('classifyItem') != 0) {
			msg += ' ' + EwayLocale.commen.devTypeName + '[';
			msg += record.get('classifyItemName');
			msg +=']';
		}
		
		// 经营方式
		if (record.get('sellItem') && record.get('sellItem') != 0) {
			msg += ' ' + EwayLocale.commen.seviceMode + '[';
			if (record.get('sellItem') == 1) {
				msg += EwayLocale.machine.device.operationSelf;
			} else if (record.get('sellItem') == 2) {
				msg += EwayLocale.machine.device.cooperation;
			} else if (record.get('sellItem') == 3) {
				msg += EwayLocale.machine.device.epiboly;
			}
			msg +=']';
		}
		
		// 经营方式
		if (record.get('ingItem') && record.get('ingItem') != 0) {
			msg += ' ' + EwayLocale.commen.insideOutside + '[';
			if (record.get('ingItem') == 1) {
				msg += EwayLocale.machine.atmGroup.comboxAwayFlag.inBank;
			} else if (record.get('ingItem') == 2) {
				msg += EwayLocale.machine.atmGroup.comboxAwayFlag.outBank;
			} else if (record.get('ingItem') == 3) {
				msg += EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank;
			}
			msg +=']';
		}
			
		// 分组
		if (record.get('atmGroup') && record.get('atmGroup') != 0) {
			msg += ' ' + EwayLocale.monitor.devMonitor.atmGroup + '[';
			msg += record.get('atmGroupName');
			msg +=']';
		}	
		
		// 运行状态
		if (!record.get('runStatusFilterForm').run_all) {
			var value = record.get('runStatusFilterForm');
			var str = '';
			if (value.run_unknown) {
				str += '|' + EwayLocale.commen.unknow;
			}
			if (value.run_initial) {
				str += '|' + EwayLocale.monitor.devMonitor.init;
			}
			if (value.run_healthy) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.healthy;
			}
			if (value.run_subHealth) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.halfSer;
			}
			if (value.run_customer) {
				str += '|' + EwayLocale.monitor.devMonitor.accTrans;
			}
			if (value.run_maintain) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.staff;
			}
			if (value.run_vdm) {
				str += '|' + EwayLocale.monitor.devMonitor.factureStaff;
			}
			if (value.run_halt) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.powerOff;
			}
			if (value.run_reboot) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.restart;
			}
			if (value.run_stopAtmp) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.pFault;
			}
			if (value.run_stopManmade) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.stopCash;
			}
			if (value.run_stopMod) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.pauseSer;
			}
			if (value.run_stopUnCashIn) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.pauseCash;
			}
			if (value.run_stopunknown) {
				str += '|' + EwayLocale.monitor.devMonitor.remote.pauseSerUnknow;
			}
			
//			str = str.startsWith('|') ? str.substr(1) : str;
			str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
			msg += ' ' + EwayLocale.monitor.devMonitor.comboxStatus.runStatus + '[';
			msg += str;
			msg +=']';
		} 
		
		// 模块状态
		if (!record.get('modStatusFilterForm').mod_all) {
			var value = record.get('modStatusFilterForm');
			var str = '';
			if (value.mod_healthy) {
				str += '|' + EwayLocale.monitor.devMonitor.mod.healthy;
			}
			if (value.mod_waring) {
				str += '|' + EwayLocale.commen.warn;
			}
			if (value.mod_fatal) {
				str += '|' + EwayLocale.commen.fatal;
			}
			if (value.mod_unknown) {
				str += '|' + EwayLocale.commen.unknow;
			}
			if (value.mod_noDevice) {
				str += '|' + EwayLocale.monitor.devMonitor.noData;
			}
			
//			str = str.startsWith('|') ? str.substr(1) : str;
			str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
			msg += ' ' + EwayLocale.monitor.devMonitor.comboxStatus.modStatus + '[';
			msg += str;
			msg +=']';
		} 
		
		// 模块状态
		if (!record.get('boxStatusFilterForm').box_all) {
			var value = record.get('boxStatusFilterForm');
			var str = '';
			if (value.box_healthy) {
				str += '|' + EwayLocale.commen.stateDict.normal;
			}
			if (value.box_low) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.low;
			}
			if (value.box_empty) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.empty;
			}
			if (value.box_high) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.cimAFull;
			}
			if (value.box_full) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.cimFull;
			}
			if (value.box_fatal) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.cashFault;
			}
			if (value.box_unknown) {
				str += '|' + EwayLocale.monitor.devMonitor.cash.cashUnknow;
			}
			
//			str = str.startsWith('|') ? str.substr(1) : str;
			str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
			msg += ' ' + EwayLocale.monitor.devMonitor.comboxStatus.boxStatus + '[';
			msg += str;
			msg +=']';
		} 
		
		// 网络状态
		if (!record.get('netStatusFilterForm').net_all) {
			var value = record.get('netStatusFilterForm');
			var str = '';
			if (value.net_healthy) {
				str += '|' + EwayLocale.monitor.devMonitor.netHealthy;
			}
			if (value.net_warning) {
				str += '|' + EwayLocale.monitor.devMonitor.netUnStable;
			}
			if (value.net_fatal) {
				str += '|' + EwayLocale.monitor.devMonitor.netFatal;
			}
			if (value.net_unknown) {
				str += '|' + EwayLocale.commen.unknow;
			}
	
//			str = str.startsWith('|') ? str.substr(1) : str;
			str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
			msg += ' ' + EwayLocale.monitor.devMonitor.comboxStatus.netStatus + '[';
			msg += str;
			msg +=']';
		} 
		
		if (msg.length == 0) {
			msg = EwayLocale.commen.all;
		}
		if(msg.length>100){
			tLabel.setText(msg.substring(0,100)+"...");
		}
		else{
			tLabel.setText(msg);
		}
		
		tLabel.value = msg;
	},

	//打开'设置监控状态'页面
	_onMonitorStateBtnClick : function(button){
		
		var filterWin = Ext.create('Eway.view.monitor.device.filterManager.FilterWin');
		var grid = filterWin.down('grid');
		var store = grid.getStore();
		
		store.setUrlParamsByObject({
			userId : ewayUser.getId()
		});
		store.loadPage(1);
		
		filterWin.show();
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

		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		view.changeFilterLoad(currentPanel,params);
	},

	
	_onListPatternBtnClick : function(btn) {
		
		var view = this.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('list');
		var queryBtn = view.down('button[action="query"]');
	    this._onQueryBtnClick(queryBtn);
	    
	    this.setFocusCls(view, btn, 'monitor_focus_color');
	},
	_onBoxPatternBtnClick : function(btn) {
		
		var view = this.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('box');
		var queryBtn = view.down('button[action="query"]');
	    this._onQueryBtnClick(queryBtn);
	    
	    this.setFocusCls(view, btn, 'monitor_focus_color');
	    
	},	
	_onSummaryPatternBtnClick : function() {
		var view = this.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('summary');
//		var btn = view.down('button[action="query"]');
//	    this._onQueryBtnClick(btn);
	},
	_onMatrixPatternBtnClick : function(btn) {
		
		var view = this.getEwayView();
		view.screen = "";
		// ExtJs4.2删除class样式的接口变更,由removeClass更新为removeCls
		view.down('#images-view').removeCls('maxicon');

		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		layout.setActiveItem('martrixPanel');
		var queryBtn = view.down('button[action="query"]');
	    this._onQueryBtnClick(queryBtn);
	    
	    this.setFocusCls(view, btn, 'monitor_focus_color');
	},
	
	_onMaxIconPatternBtnClick : function(btn) {
		this._onMatrixPatternBtnClick(btn);
		var view = this.getEwayView();
		view.screen = 64;
		view.down('#images-view').addCls('maxicon');
	},
	
	setFocusCls : function(view, btn, cls) {
		var btns = view.down('monitor_device_showtype_tbar').query('button');
		Ext.each(btns, function(b) {
			b.removeCls(cls);
		}) ;
		btn.addCls(cls);
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
		winEl.mask(EwayLocale.tip.nowLink);
		deviceBoxModel.load(ip, {
			success : function(record) {
				winEl.unmask();
				controller.init();
				controller.displayWin(record);
			},
			failure: function(){
				winEl.unmask();
				Eway.alert(EwayLocale.tip.business.device.getCashInfoFail);
			}
		});
	}

});