Ext.define('Eway.controller.monitor.device.DeviceInfoStatus', {

	extend : 'Ext.app.Controller',

	views : ['monitor.device.DeviceInfoStatus'],
	stores : [ 'monitor.device.DeviceStatus' ],
	models : [ 'monitor.device.DeviceStatus', 'monitor.device.DeviceBox' ],

	requires : ['Eway.view.monitor.device.remote.Grid'],
	
	init : function() {
			this.control({
				//打开设备编号的超链接
				'monitor_device_DeviceInfoStatus displayfield[name="code"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var form = field.up('form');
									var code = form.down('displayfield[name="code"]').getValue();
									code = Ext.util.Format.stripTags(code);
									var controller = this.getController('machine.device.DeviceInfo');
									controller.onMonitorToDeviceInfo(code);
								}, this);
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				//模块状态
				'monitor_device_DeviceInfoStatus displayfield[name="modStatus"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var win = field.up('window');
									
									var form = field.up('form');
									var code = form.down('displayfield[name="code"]').getValue();
									var ip = form.down('displayfield[name="ip"]').getValue();
									code = Ext.util.Format.stripTags(code);
									ip = Ext.util.Format.stripTags(ip);

									var controller = this.getController('machine.device.DevicePropertyStatusDetail');
									controller.display(code, ip, win.record);
								}, this);
							}
						},
//						single : true,
						scope:this
					}
				},
				//人员展示
				'monitor_device_DeviceInfoStatus displayfield[name="personnel"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var form = field.up('form');
									var code = form.down('displayfield[name="code"]').getValue();
									code = Ext.util.Format.stripTags(code);

									var win = Ext.create('Eway.view.machine.device.person.PersonInfo');
									win.setTerminalId(code);
									win.show();

								}, this);
							}
						},
//						single : true,
						scope:this
					}
				},

				//图形展示
				'monitor_device_DeviceInfoStatus displayfield[name="modGraphic"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var form = field.up('form');
									var code = form.down('displayfield[name="code"]').getValue();
									var ip = form.down('displayfield[name="ip"]').getValue();
									code = Ext.util.Format.stripTags(code);
									ip = Ext.util.Format.stripTags(ip);
									var controller = this
											.getController('machine.device.graphic.ModuleGraphic');

									// 设备模块状态在渲染的时候加载数据
									var store = this.getMonitorDeviceDeviceStatusStore();
									var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
									var winEl = win.getEl();
									winEl.mask(EwayLocale.vtype.dataLoad);
									store.load({
										params : {
											deviceId : code,
											ip : ip
										},

										// 这里为了测试设备状态的变更(如果需要真实数据,请把url去掉)
										// 更改状态device_status_test.js里的状态，进行测试
	//									url : 'app/controller/machine/device/graphic/device_status_test.js',

										scope : this,
										callback : function(records, operation, success) {// 回调函数
											winEl.unmask();
											if (!success) {
												Eway.alert(EwayLocale.vtype.devLinkNormal);
												return;
											}
											if(records.length == 0){
												Eway.alert(EwayLocale.vtype.hardwayInitialize);
												return ;
											}
											if (records && records.length > 0) { // 判断是否有数据
												controller.display(records[0]);
											}
										}
									});
								}, this);
							}
						},
//						single : true,
						scope:this
					}
				},
				//钞箱状态
				'monitor_device_DeviceInfoStatus displayfield[name="boxStatus"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var form = field.up('form');
									var ip = form.down('displayfield[name="ip"]').getValue();
									ip = Ext.util.Format.stripTags(ip);
									var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
									var winEl = win.getEl();
									//var deviceBoxModel = this.getMonitorDeviceDeviceBoxModel();
									winEl.mask(EwayLocale.tip.nowLink);
									Eway.model.monitor.device.DeviceBox.load(ip,{
										scope:this,
										success : function(record, operation) {
											winEl.unmask();
											var controller = this.getController('monitor.device.DeviceBox');
											controller.init();
											controller.displayWin(record);
										},
										failure: function(record, operation){
											winEl.unmask();
											Eway.alert(EwayLocale.tip.business.device.getCashInfoFail);
										}
									});
								}, this);
							}
						},
//						single : true,
						scope:this
					}
				},
				//远程抓屏
				'monitor_device_DeviceInfoStatus displayfield[name="remoteScreenAction"]' : {
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRemoteScreenAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				
				//提取日志
				'monitor_device_DeviceInfoStatus displayfield[name="logAction"]' : {
//					click : this.onLogAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onLogAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//网络连接
				'monitor_device_DeviceInfoStatus displayfield[name="netAction"]' : {
//					click : this.onNetAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onNetAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//获取软件列表
				'monitor_device_DeviceInfoStatus displayfield[name="softwareListAction"]' : {
//					click : this.onSoftwareListAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onSoftwareListAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//逻辑开
				'monitor_device_DeviceInfoStatus displayfield[name="logicOpenAction"]' : {
//					click: this.onLogicOpenAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onLogicOpenAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				// 逻辑关
				'monitor_device_DeviceInfoStatus displayfield[name="logicCloseAction"]' : {
//					click : this.onLogicCloseAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onLogicCloseAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//获取进程列表
				'monitor_device_DeviceInfoStatus displayfield[name="processListAction"]' : {
//					click : this.onProcessListAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onProcessListAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//远程浏览
				'monitor_device_DeviceInfoStatus displayfield[name="remoteBrowserAction"]' : {
//					click : this.onRemoteBrowserAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRemoteBrowserAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//远程查看ATMC应用版本和监控客户端版本
				'monitor_device_DeviceInfoStatus displayfield[name="remoteLookAction"]' : {
//					click : this.onRemoteLookAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRemoteLookAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//ATM体检
				'monitor_device_DeviceInfoStatus displayfield[name="remoteCheckATMAction"]' : {
//					click : this.onRemoteCheckATMAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRemoteCheckATMAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//屏幕录制
				'monitor_device_DeviceInfoStatus displayfield[name="screenCameraAction"]' : {
//					click : this.onScreenCameraAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onScreenCameraAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				// 复位命令
				'monitor_device_DeviceInfoStatus displayfield[name="resetAction"]' : {
//					click : this.onResetAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onResetAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//关机命令
				'monitor_device_DeviceInfoStatus displayfield[name="closeAction"]' : {
//					click : this.onCloseAction
					
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onCloseAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				//重启设备命令
				'monitor_device_DeviceInfoStatus displayfield[name="restartAction"]' : {
//					click : this.onRestartAction
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRestartAction, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				
				// 命令执行结果
				'monitor_device_DeviceInfoStatus displayfield[name="remoteCommHist"]' : {
					afterrender : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', this.onRemoteCommHist, this, field);
							}
						},
						scope : this
					},
					scope : this
				},
				
				
				'monitor_device_DeviceInfoStatus displayfield[name="idcStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'IDC');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},

				'monitor_device_DeviceInfoStatus displayfield[name="jprStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'JPR');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="cdmStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'CDM');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="pinStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'PIN');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="cimStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'CIM');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="siuStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'SIU');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="rprStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'RPR');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="ttuStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'TTU');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="iscStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'ISC');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="iccStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'ICC');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="fgpStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'FGP');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				},
				'monitor_device_DeviceInfoStatus displayfield[name="pbkStatus"]' : {
					change : {
						fn : function(field) {
							if (field.getEl()) {
								var text = field.getEl().down('a.link');
								if (text) {
									text.on('click', function(e, htmlEl) {
										var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
										var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
										win.display(code, 'PBK');
									}, this);
								}
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				}
			});
	},


	onRemoteCommHist :  function(ev, target, field) {
		
		var form = field.up('form');
		var codes = form.down('displayfield[name="code"]').getValue();
		var code = Ext.util.Format.stripTags(codes);
		
		var win = Ext.create('Ext.window.Window',{
			modal: true,
			height: 520,
		    width: 850,
		    title : EwayLocale.monitor.devMonitor.remote.screen,
		    autoScroll : true,
		    maximizable: true,
		    layout : 'border',
		    items:[ {
		    	region : 'center',
		    	xtype : 'monitor_device_remote_grid'
		    } ]
		}).show();
		
		var store = win.down('grid').getStore();
		store.setUrlParamsByObject({
			terminalId : code
		});
		store.loadPage(1);
		
		win.down('button[action=query]').on('click', function() {
			store.loadPage(1);
		});
	},
	


	//打开远程抓屏
	onRemoteScreenAction : function(ev, target, field) {
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var codes = form.down('displayfield[name="code"]').getValue();
		var code = Ext.util.Format.stripTags(codes);
		var store = Ext.create('Eway.store.agent.Image');
		var imageTpl = new Ext.XTemplate(
		    '<tpl for=".">',
		        '<div style="margin-bottom: 10px;" class="thumb-wrap" >',
		          '<img src="{src}" style="height:545px;width:675px;"/>',
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
					height: 520,
				    width: 700,
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

	//提取电子日志
	onLogAction : function(ev, target, field){
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		var code = form .down('displayfield[name="code"]').getValue();
		code = Ext.util.Format.stripTags(code);
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		var winEl = win.getEl();
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
					Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.logLoadConfirm,
							 function(button, text) {
								if (button == "yes") {
									var url = 'api/agent/downLogs/downloadFile?path=' + object.path + '&fileName=' + object.fileName
									var iframe = document.getElementById('downloadFileFromWeb');
									iframe.src = url;
								}
							});
				} else {
					winEl.unmask();
					if(undefined==object.errors||''==object.errors){
						Eway.alert(EwayLocale.tip.business.device.logPullFail);
					}
					else{
						Eway.alert(EwayLocale.tip.business.device.logPullFail + object.errors);
					}
				}
			},
			failure : function() {
				winEl.unmask();
				Eway.alert(EwayLocale.tip.business.device.logFail);
			}
		});
	},

	//查看网络连接
	onNetAction : function(ev, target, field){
		var me = this;
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		var winEl = win.getEl();
		winEl.mask(EwayLocale.tip.nowLink);
		Ext.Ajax.request({
			method: 'POST',
			url: 'api/agent/netWork/getNetWork',
			params: {
				ip : ip
			},
			success: function(response) {
				winEl.unmask();
				var object = Ext.decode(response.responseText);
				if (object.success && object.success == true) {
					var controller = me.getController('agent.remote.RemoteBrowseNetWork');
					var netInfoObj = Ext.decode(response.responseText);
					controller.init();
					controller.display(ip,netInfoObj.sendByte,netInfoObj.receivedByte,netInfoObj.conenctRate);
				} else {
					Eway.alert(EwayLocale.tip.business.device.linkServerFail);
				}
			},
			failure: function(form, action) {
				winEl.unmask();
				Eway.alert(EwayLocale.tip.business.device.linkServerFail);
			}
		});
	},

	//获取软件列表
	onSoftwareListAction : function(ev, target, field){
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseInstation');
		controller.init();
		controller.display(ip);
	},

	//逻辑开
	onLogicOpenAction : function(ev, target, field){
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.logicOpen,
			function(button,text){
				if(button == 'yes'){
					var form = field.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var code = form.down('displayfield[name="code"]').getValue();
					code = Ext.util.Format.stripTags(code);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask(EwayLocale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/open',
						params : {
							ip : ip,
							terminalId : code
						},
						success : function(response) {
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
//							var object = Ext.decode(response.responseText);
//							if (object.appRet == 00) {
//								winEl.unmask();
//								Eway.alert(EwayLocale.tip.business.device.openSuccess);
//							} else {
//								winEl.unmask();
//								 Eway.alert(EwayLocale.tip.business.device.openFail);
//							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.openFail);
						}
					});
			}
		});
	},

	//逻辑关
	onLogicCloseAction : function(ev, target, field){
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.closeConfirm,
			function(button,text){
				if(button == 'yes'){
					var form = field.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var code = form.down('displayfield[name="code"]').getValue();
					code = Ext.util.Format.stripTags(code);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask(EwayLocale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/close',
						params : {
							ip : ip,
							terminalId : code
						},
						success : function(response) {
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
//							var object = Ext.decode(response.responseText);
//							if (object.appRet == 00) {
//								winEl.unmask();
//								Eway.alert(EwayLocale.tip.business.device.closeServiceSuccess);
//							} else {
//								winEl.unmask();
//								Eway.alert(EwayLocale.tip.business.device.closeServiceFail);
//							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.closeServiceFail);
						}
					});
				}
			});
	},

	//获取进程列表
	onProcessListAction : function(ev, target, field){
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseProcess');
		controller.display(ip);
	},

	//打开远程浏览功能
	onRemoteBrowserAction : function(ev, target, field){
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowse');
		controller.display(ip);
	},

	//执行关机命令
	onCloseAction : function(ev, target, field){
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: EwayLocale.tip.business.device.closeNormal,
                handler: function() {
            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.closeNorml,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
            				var form = field.up('form');
		            		var ip = form.down('displayfield[name="ip"]').getValue();
		            		var terminalId = form.down('displayfield[name="code"]').getValue();
		            		ip = Ext.util.Format.stripTags(ip);
		            		terminalId = Ext.util.Format.stripTags(terminalId);
		            		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		            		var winEl = win.getEl();
							winEl.mask(EwayLocale.tip.business.device.closing);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/normalShutdown',
								params : {
									shutdownType : "OS",
									ip : ip,
									terminalId : terminalId
								},
								success : function(response) {
									var object = Ext.decode(response.responseText);
									if (object.appRet == 00) {
										winEl.unmask();
										Eway.alert(EwayLocale.tip.business.device.closeSucess);
									} else {
										winEl.unmask();
										Eway.alert(EwayLocale.tip.business.device.closeFail);
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert(EwayLocale.tip.business.device.closeSentFail);
								}
							})
            			}
            		}
            	}
            },{
            	text: EwayLocale.tip.business.device.forceClose,
            	iconCls: 'icon-add',
            	handler: function(){
            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.forceCloseComfirm,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
		            		var form = field.up('form');
		            		var ip = form.down('displayfield[name="ip"]').getValue();
		            		var terminalId = form.down('displayfield[name="code"]').getValue();
		            		terminalId = Ext.util.Format.stripTags(terminalId);
		            		ip = Ext.util.Format.stripTags(ip);
		            		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		            		var winEl = win.getEl();
							winEl.mask(EwayLocale.tip.business.device.forceClosing);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/shutdown',
								params : {
									shutdownType : "OS",
									ip : ip,
									terminalId : terminalId
								},
								success : function(response) {
									var object = Ext.decode(response.responseText);
									if (object.appRet == 00) {
										winEl.unmask();
										Eway.alert(EwayLocale.tip.business.device.forceCloseSucess);
									} else {
										winEl.unmask();
										Eway.alert(EwayLocale.tip.business.device.forceCloseFail);
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert(EwayLocale.tip.business.device.ForceCloseSentFail);
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
            title: EwayLocale.monitor.devMonitor.remote.powerOff,
            msg: EwayLocale.monitor.devMonitor.remote.closeWays,
            icon: Ext.MessageBox.WARNING
        });

	},

	//重启命令
	onRestartAction : function(ev, target, field){
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: EwayLocale.tip.business.device.reboot,
                iconCls: 'icon-add',
                handler: function() {
                	Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
                	function callBack(id){
                		if(id == 'yes'){
		            		dialog.close();
		            		var form = field.up('form');
							var ip = form.down('displayfield[name="ip"]').getValue();
							var terminalId = form.down('displayfield[name="code"]').getValue();
							terminalId = Ext.util.Format.stripTags(terminalId);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
							var winEl = win.getEl();
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
									winEl.unmask();
									Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
//									var object = Ext.decode(response.responseText);
//									if (object.appRet == 00) {
//										winEl.unmask();
//										Eway.alert(EwayLocale.tip.business.device.rebootSucess);
//									} else {
//										winEl.unmask();
//										Eway.alert(EwayLocale.tip.business.device.rebootFail);
//									}
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
            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
		            		var form = field.up('form');
							var ip = form.down('displayfield[name="ip"]').getValue();
							var terminalId = form.down('displayfield[name="code"]').getValue();
							terminalId = Ext.util.Format.stripTags(terminalId);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
							var winEl = win.getEl();
							winEl.mask(EwayLocale.tip.business.device.forceReboot);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/reboot',
								params : {
									restartType : "OS",
									ip : ip,
									terminalId : terminalId
								},
								success : function(response) {
									winEl.unmask();
									Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
//									var object = Ext.decode(response.responseText);
//									if (object.appRet == 00) {
//										winEl.unmask();
//										Eway.alert(EwayLocale.tip.business.device.forceRebootSuccess);
//									} else {
//										winEl.unmask();
//										Eway.alert(EwayLocale.tip.business.device.forceRebootFail);
//									}
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

	//复位命令，需要C端配合
	onResetAction : function(ev, target, field){
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.resetConfirm,callBack);
		function callBack(id){
			if(id == 'yes'){
				var form = field.up('form');
				var ip = form.down('displayfield[name="ip"]').getValue();
				ip = Ext.util.Format.stripTags(ip);
				var code = form.down('displayfield[name="code"]').getValue();
				code = Ext.util.Format.stripTags(code);
				var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
				var winEl = win.getEl();
				winEl.mask(EwayLocale.tip.business.device.operating);
				Ext.Ajax.timeout = 120000;
				Ext.Ajax.request({
					method : 'POST',
					url : 'api/agent/logic/reset',
					params : {
						ip : ip,
						terminalId : code
					},
					success : function(response) {
						winEl.unmask();
						Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
						
						
//						var object = Ext.decode(response.responseText);
//						if (object.appRet == 00) {
//							winEl.unmask();
//							Eway.alert(EwayLocale.tip.business.device.resetSuccess);
//						} else {
//							winEl.unmask();
//							Eway.alert(EwayLocale.tip.business.device.resetFail);
//						}
					},
					failure: function(){
						winEl.unmask();
						Eway.alert(EwayLocale.tip.business.device.resetSendFail);
					}
				});
			}
		}
	},

	//屏幕录像功能
	onScreenCameraAction : function(ev, target, field){
		var form = field.up('form');
		var terminalId = form.down('displayfield[name="code"]').getValue();
		var ip = form.down('displayfield[name="ip"]').getValue();
		terminalId = Ext.util.Format.stripTags(terminalId);
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.screen.Camera');
		if (!this.cameaController) {
			controller.init();
			this.cameaController = controller;
		}
		controller.display(terminalId, ip);
	},

	//设备体检功能
	onRemoteCheckATMAction : function(ev, target, field){
		var form = field.up('form');
		var terminalId = form.down('displayfield[name="code"]').getValue();
		var ip = form.down('displayfield[name="ip"]').getValue();
		terminalId = Ext.util.Format.stripTags(terminalId);
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteCheckInfo');
		controller.display(ip,terminalId);
	},

	// 查看应用版本
	onRemoteLookAction : function(ev, target, field) {
		var form = field.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		var code = form.down('displayfield[name="code"]').getValue();
		code = Ext.util.Format.stripTags(code);
		var controller = this.getController('agent.remote.RemoteLookVesion');
		controller.display(code, ip);

		var win = controller.win;
		var statusView = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		var winEl = statusView.getEl();
		winEl.mask(EwayLocale.tip.business.device.operating);

		Ext.Ajax.request({
   			method : 'POST',
   			url : 'api/agent/atmVersion/versioninfo',
   			params :{
   				terminalId: code,
   				ip: ip
   			},
   			success : function(response){
   				winEl.unmask();
   				var object = Ext.decode(response.responseText);
   				if(object.success == true){
   					if(object.data != null){
//   						win.down('form').down('displayfield[name="atmcVersion"]').setValue(object.data.atmcVersion);
//   	   					win.down('form').down('displayfield[name="agentVersion"]').setValue(object.data.agentVersion);
   	   					
   	   					if (object.data.currentPatches.length > 0) {
   	   						var formVersion = win.down('form');
   	   						
	   	   					for(var index=0; index<object.data.currentPatches.length; index++) {
	   	   						var data = object.data.currentPatches[index];
	   	   						
	   	   						formVersion.add({
	   	   							xtype : 'displayfield',
									fieldLabel: data.typeName,
									value : data.versionNo,
									margin : '0 0 0 10',
									style : {
										'text-align' : 'center'
									}
	   	   						});
	   	   					}
   	   					}
   	   					
   	   					win.show();

   	   					statusView.record.set('appRelease', object.data.atmcVersion);
   	   					form.down('displayfield[name="appRelease"]').setValue(object.data.atmcVersion);

   					}else{
   						win.close();
   						Eway.alert(EwayLocale.tip.business.device.reviewFail);
   					}
   				}else{
   					win.close();
   					Eway.alert(EwayLocale.tip.business.device.reviewFail);
   				}
   			},
   			failure : function(){
   				winEl.unmask();
   				win.close();
   				Eway.alert(EwayLocale.tip.business.device.reviewFail);
   			}
		});
	},

	//显示设备详情页面
	displayWin : function(record) {
		var win = Ext.widget('monitor_device_DeviceInfoStatus');
		
		win.setRecord(record);
		
		win.setTitle(EwayLocale.tip.business.device.term + "(" + record.data.code + ")" +EwayLocale.tip.business.device.detail);
		win.on('afterrender',function(){
			win.fillForm(record);
		},this)
		win.show();

		var me = this; // 保留当前对象的作用域/
		win.query('tool[action="refresh"]')[0].on('click', function() {
			var el = win.getEl();
			el.mask(EwayLocale.tip.business.device.refresh);
			setTimeout(function() {
				el.unmask();
				me.refreshView(win, win.record.get('code'));
			}, 100);
		} );

		var deviceStatusIntervalID = setInterval(function() {
			// 判断设备状态详情页面是不是最前面，如果是最前面则刷新，否则不做操作
			var indexStack = win.zIndexManager.getActive();
			if (win === indexStack) {
				me.refreshView(win, record.get('code'));
			}
		}, 120000);
		win.on('beforedestroy', function() {
			clearInterval(deviceStatusIntervalID);
		}, this);
	},

	refreshView : function(win, code) {
		/** 取当前活动面板的store */
		var c = this.getController('monitor.device.DeviceMonitor');
		var view = c.getEwayView();
		var card = view.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		
		var currentStore;
		if (currentPanel.getItemId() == 'martrixPanel') {
			currentStore = currentPanel.down('monitor_device_showtype_dataviewgrid').getStore();
		} else {
			currentStore = currentPanel.getStore();
		}

		// 获取最新store的数据
		var newRecord = currentStore.findRecord('code', code);
		win.destroy(); // window销毁

		this.displayWin(newRecord);
	}
});
