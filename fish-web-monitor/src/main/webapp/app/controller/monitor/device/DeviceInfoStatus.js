Ext.define('Eway.controller.monitor.device.DeviceInfoStatus', {

	extend : 'Ext.app.Controller',

	views : ['monitor.device.DeviceInfoStatus'],
	stores : [ 'monitor.device.DeviceStatus' ],
	models : [ 'monitor.device.DeviceStatus', 'monitor.device.DeviceBox' ],

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
									var form = field.up('form');
									var code = form.down('displayfield[name="code"]').getValue();
									var ip = form.down('displayfield[name="ip"]').getValue();
									code = Ext.util.Format.stripTags(code);
									ip = Ext.util.Format.stripTags(ip);

									var controller = this.getController('machine.device.DevicePropertyStatusDetail');
									controller.display(code, ip);
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

									var tabPanel = win.down('tabpanel');

									// 维护员
									var maintain = tabPanel.query('[itemid=maintainItemID]')[0];

									// 管机员
									var tubeMachine = tabPanel.query('[itemid=tubeMachineItemID]')[0];

									maintain.on('render', function(tab) {
										// 加载维护员的数据
										var params = {
											terminalId : code,
											type : 1
										};
										tab.onReload(params);
									}, this);
									tubeMachine.on('render', function(tab) {
										// 加载管机员的数据
										var params = {
											terminalId : code,
											type : 0
										};
										tab.onReload(params);
									}, this);

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
									winEl.mask(Eway.locale.vtype.dataLoad);
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
												Eway.alert(Eway.locale.vtype.devLinkNormal);
												return;
											}
											if(records.length == 0){
												Eway.alert(Eway.locale.vtype.hardwayInitialize);
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
									winEl.mask(Eway.locale.tip.nowLink);
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
											Eway.alert(Eway.locale.tip.business.device.getCashInfoFail);
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
				'monitor_device_DeviceInfoStatus button[name="remoteScreenAction"]' : {
					click : this.onRemoteScreenAction
				},
				//提取日志
				'monitor_device_DeviceInfoStatus button[name="logAction"]' : {
					click : this.onLogAction
				},
				//网络连接
				'monitor_device_DeviceInfoStatus button[name="netAction"]' : {
					click : this.onNetAction
				},
				//获取软件列表
				'monitor_device_DeviceInfoStatus button[name="softwareListAction"]' : {
					click : this.onSoftwareListAction
				},
				//逻辑开
				'monitor_device_DeviceInfoStatus button[name="logicOpenAction"]' : {
					click: this.onLogicOpenAction
				},
				// 逻辑关
				'monitor_device_DeviceInfoStatus button[name="logicCloseAction"]' : {
					click : this.onLogicCloseAction
				},
				//获取进程列表
				'monitor_device_DeviceInfoStatus button[name="processListAction"]' : {
					click : this.onProcessListAction
				},
				//远程浏览
				'monitor_device_DeviceInfoStatus button[name="remoteBrowserAction"]' : {
					click : this.onRemoteBrowserAction
				},
				//远程查看ATMC应用版本和监控客户端版本
				'monitor_device_DeviceInfoStatus displayfield[name="remoteLookAction"]' : {
					scope : this,
					afterrender : {
						fn : function(comp) {
							var text = comp.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var form = comp.up('form');
									var ip = form.down('displayfield[name="ip"]').getValue();
									var code = form.down('displayfield[name="code"]').getValue();
									code = Ext.util.Format.stripTags(code);
									var controller = this.getController('agent.remote.RemoteLookVesion');
									controller.display(code, ip);

									var win = controller.win;
									var statusView = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
									var winEl = statusView.getEl();
									winEl.mask(Eway.locale.tip.business.device.operating);

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
							   						win.down('form').down('displayfield[name="atmcVersion"]').setValue(object.data.atmcVersion);
							   	   					win.down('form').down('displayfield[name="agentVersion"]').setValue(object.data.agentVersion);
							   	   					win.show();

							   	   					statusView.record.set('appRelease', object.data.atmcVersion);
							   	   					form.down('displayfield[name="appRelease"]').setValue(object.data.atmcVersion);

							   					}else{
							   						win.close();
							   						Eway.alert(Eway.locale.tip.business.device.reviewFail);
							   					}
							   				}else{
							   					win.close();
							   					Eway.alert(Eway.locale.tip.business.device.reviewFail);
							   				}
							   			},
							   			failure : function(){
							   				winEl.unmask();
							   				win.close();
							   				Eway.alert(Eway.locale.tip.business.device.reviewFail);
							   			}
									});

								}, this);
							}
						},
//						single : true,
						scope : this
					}
				},
				//ATM体检
				'monitor_device_DeviceInfoStatus button[name="remoteCheckATMAction"]' : {
					click : this.onRemoteCheckATMAction
				},
				//屏幕录制
				'monitor_device_DeviceInfoStatus button[name="screenCameraAction"]' : {
					click : this.onScreenCameraAction
				},
				// 复位命令
				'monitor_device_DeviceInfoStatus button[name="resetAction"]' : {
					click : this.onResetAction
				},
				//关机命令
				'monitor_device_DeviceInfoStatus button[name="closeAction"]' : {
					click : this.onCloseAction
				},
				//重启设备命令
				'monitor_device_DeviceInfoStatus button[name="restartAction"]' : {
					click : this.onRestartAction
				},
				'monitor_device_DeviceInfoStatus displayfield[name="idcStatus"]' : {
					change : {
						fn : function(field) {
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field.up('form').down('displayfield[name="code"]').getValue());
									var win = Ext.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'IDC');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'JPR');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'CDM');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'PIN');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'CIM');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'SIU');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'RPR');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'TTU');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'ISC');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'ICC');
								}, this);
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
							var text = field.getEl().down('a.link');
							if (text) {
								text.on('click', function(e, htmlEl) {
									var code = Ext.util.Format.stripTags(field
											.up('form')
											.down('displayfield[name="code"]')
											.getValue());
									var win = Ext
											.create('Eway.view.monitor.device.ModuleInfoWin');
									win.display(code, 'FGP');
								}, this);
							}
						},
//						single : true,
						scope : this
					},
					scope : this
				}
			});
	},

	//打开远程抓屏
	onRemoteScreenAction : function(btn){
		var form = btn.up('form');
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

	//提取电子日志
	onLogAction : function(btn){
		var form = btn.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		var code = form .down('displayfield[name="code"]').getValue();
		code = Ext.util.Format.stripTags(code);
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		var winEl = win.getEl();
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
					Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.logLoadConfirm,
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
						Eway.alert(Eway.locale.tip.business.device.logPullFail);
					}
					else{
						Eway.alert(Eway.locale.tip.business.device.logPullFail + object.errors);
					}
				}
			},
			failure : function() {
				winEl.unmask();
				Eway.alert(Eway.locale.tip.business.device.logFail);
			}
		});
	},

	//查看网络连接
	onNetAction : function(btn){
		var me = this;
		var form = btn.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		var winEl = win.getEl();
		winEl.mask(Eway.locale.tip.nowLink);
		Ext.Ajax.request({
			method: 'POST',
			url: 'api/agent/netWork/getNetWork',
			params: {
				ip : ip
			},
			success: function(response) {
				winEl.unmask();
				var controller = me.getController('agent.remote.RemoteBrowseNetWork');
				var netInfoObj = Ext.decode(response.responseText);
				controller.init();
				controller.display(ip,netInfoObj.sendByte,netInfoObj.receivedByte,netInfoObj.conenctRate);
			},
			failure: function(form, action) {
					winEl.unmask();
					Eway.alert(Eway.locale.tip.business.device.linkServerFail);
			}
		});
	},

	//获取软件列表
	onSoftwareListAction : function(btn){
		var form = btn.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseInstation');
		controller.init();
		controller.display(ip);
	},

	//逻辑开
	onLogicOpenAction : function(btn){
		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.logicOpen,
			function(button,text){
				if(button == 'yes'){
					var form = btn.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask(Eway.locale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/open',
						params : {
							ip : ip
						},
						success : function(response) {
							var object = Ext.decode(response.responseText);
							if (object.appRet == 00) {
								winEl.unmask();
								Eway.alert(Eway.locale.tip.business.device.openSuccess);
							} else {
								winEl.unmask();
								 Eway.alert(Eway.locale.tip.business.device.openFail);
							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert(Eway.locale.tip.business.device.openFail);
						}
					});
			}
		});
	},

	//逻辑关
	onLogicCloseAction : function(btn){
		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.closeConfirm,
			function(button,text){
				if(button == 'yes'){
					var form = btn.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask(Eway.locale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/close',
						params : {
							ip : ip
						},
						success : function(response) {
							var object = Ext.decode(response.responseText);
							if (object.appRet == 00) {
								winEl.unmask();
								Eway.alert(Eway.locale.tip.business.device.openSuccess);
							} else {
								winEl.unmask();
								Eway.alert(Eway.locale.tip.business.device.openFail);
							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert(Eway.locale.tip.business.device.openFail);
						}
					});
				}
			});
	},

	//获取进程列表
	onProcessListAction : function(btn){
		var form = btn.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseProcess');
		controller.display(ip);
	},

	//打开远程浏览功能
	onRemoteBrowserAction : function(btn){
		var form = btn.up('form');
		var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowse');
		controller.display(ip);
	},

	//执行关机命令
	onCloseAction : function(btn){
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: Eway.locale.tip.business.device.closeNormal,
                handler: function() {
            		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.closeNorml,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
            				var form = btn.up('form');
		            		var ip = form.down('displayfield[name="ip"]').getValue();
		            		var terminalId = form.down('displayfield[name="code"]').getValue();
		            		ip = Ext.util.Format.stripTags(ip);
		            		terminalId = Ext.util.Format.stripTags(terminalId);
		            		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		            		var winEl = win.getEl();
							winEl.mask(Eway.locale.tip.business.device.closing);
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
										Eway.alert(Eway.locale.tip.business.device.closeSucess);
									} else {
										winEl.unmask();
										Eway.alert(Eway.locale.tip.business.device.closeFail);
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert(Eway.locale.tip.business.device.closeSentFail);
								}
							})
            			}
            		}
            	}
            },{
            	text: Eway.locale.tip.business.device.forceClose,
            	iconCls: 'icon-add',
            	handler: function(){
            		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.forceCloseComfirm,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
		            		var form = btn.up('form');
		            		var ip = form.down('displayfield[name="ip"]').getValue();
		            		var terminalId = form.down('displayfield[name="code"]').getValue();
		            		terminalId = Ext.util.Format.stripTags(terminalId);
		            		ip = Ext.util.Format.stripTags(ip);
		            		var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
		            		var winEl = win.getEl();
							winEl.mask(Eway.locale.tip.business.device.forceClosing);
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
										Eway.alert(Eway.locale.tip.business.device.forceCloseSucess);
									} else {
										winEl.unmask();
										Eway.alert(Eway.locale.tip.business.device.forceCloseFail);
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert(Eway.locale.tip.business.device.ForceCloseSentFail);
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
            title: Eway.locale.monitor.devMonitor.remote.powerOff,
            msg: Eway.locale.monitor.devMonitor.remote.closeWays,
            icon: Ext.MessageBox.WARNING
        });

	},

	//重启命令
	onRestartAction : function(btn){
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: Eway.locale.tip.business.device.reboot,
                iconCls: 'icon-add',
                handler: function() {
                	Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.rebootConfirm,callBack);
                	function callBack(id){
                		if(id == 'yes'){
		            		dialog.close();
		            		var form = btn.up('form');
							var ip = form.down('displayfield[name="ip"]').getValue();
							var terminalId = form.down('displayfield[name="code"]').getValue();
							terminalId = Ext.util.Format.stripTags(terminalId);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
							var winEl = win.getEl();
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
            		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.rebootConfirm,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
		            		var form = btn.up('form');
							var ip = form.down('displayfield[name="ip"]').getValue();
							var terminalId = form.down('displayfield[name="code"]').getValue();
							terminalId = Ext.util.Format.stripTags(terminalId);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
							var winEl = win.getEl();
							winEl.mask(Eway.locale.tip.business.device.forceReboot);
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

	//复位命令，需要C端配合
	onResetAction : function(btn){
		Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.business.device.resetConfirm,callBack);
		function callBack(id){
			if(id == 'yes'){
				var form = btn.up('form');
				var ip = form.down('displayfield[name="ip"]').getValue();
				ip = Ext.util.Format.stripTags(ip);
				var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
				var winEl = win.getEl();
				winEl.mask(Eway.locale.tip.business.device.operating);
				Ext.Ajax.timeout = 120000;
				Ext.Ajax.request({
					method : 'POST',
					url : 'api/agent/logic/reset',
					params : {
						ip : ip
					},
					success : function(response) {
						var object = Ext.decode(response.responseText);
						if (object.appRet == 00) {
							winEl.unmask();
							Eway.alert(Eway.locale.tip.business.device.resetSuccess);
						} else {
							winEl.unmask();
							Eway.alert(Eway.locale.tip.business.device.resetFail);
						}
					},
					failure: function(){
						winEl.unmask();
						Eway.alert(Eway.locale.tip.business.device.resetSendFail);
					}
				});
			}
		}
	},

	//屏幕录像功能
	onScreenCameraAction : function(btn){
		var form = btn.up('form');
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
	onRemoteCheckATMAction : function(btn){
		var form = btn.up('form');
		var terminalId = form.down('displayfield[name="code"]').getValue();
		var ip = form.down('displayfield[name="ip"]').getValue();
		terminalId = Ext.util.Format.stripTags(terminalId);
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteCheckInfo');
		controller.display(ip,terminalId);
	},

	//显示设备详情页面
	displayWin : function(record) {
		var win = Ext.widget('monitor_device_DeviceInfoStatus');
		win.setTitle(Eway.locale.tip.business.device.term + record.data.code + Eway.locale.tip.business.device.detail);
		win.on('afterrender',function(){
			win.fillForm(record);
		},this)
		win.show();

		var me = this; // 保留当前对象的作用域/
		win.query('tool[action="refresh"]')[0].on('click', function() {
			var el = win.getEl();
			el.mask(Eway.locale.tip.business.device.refresh);
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