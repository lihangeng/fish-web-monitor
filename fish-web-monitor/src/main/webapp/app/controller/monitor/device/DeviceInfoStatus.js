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
									winEl.mask("正在加载数据......");
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
												Eway.alert('请检查与设备的连接是否正常.');
												return;
											}
											if(records.length == 0){
												Eway.alert('硬件模块正在初始化......');
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
									winEl.mask('正在连接');
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
											Eway.alert('获取钞箱信息失败.');
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
									winEl.mask('正在执行');

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
							   						Eway.alert("查看失败.");
							   					}
							   				}else{
							   					win.close();
							   					Eway.alert("查看失败.");
							   				}
							   			},
							   			failure : function(){
							   				winEl.unmask();
							   				win.close();
							   				Eway.alert("查看失败.");
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
				    title : '远程抓屏浏览',
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
		winEl.mask('正在连接');
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
					Ext.MessageBox.confirm('提示','应用日志提取成功,是否下载?',
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
						Eway.alert('提取应用电子日志失败.');
					}
					else{
						Eway.alert('提取应用电子日志失败:' + object.errors);
					}
				}
			},
			failure : function() {
				winEl.unmask();
				Eway.alert('log处理失败.');
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
		winEl.mask('正在连接');
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
					Eway.alert('服务器连接失败.');
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
		Ext.MessageBox.confirm('提示','确认要执行开启服务命令?',
			function(button,text){
				if(button == 'yes'){
					var form = btn.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask('正在执行');
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
								Eway.alert('执行开启服务命令成功.');
							} else {
								winEl.unmask();
								 Eway.alert("执行开启服务命令失败.");
							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert("执行开启服务命令失败.");
						}
					});
			}
		});
	},

	//逻辑关
	onLogicCloseAction : function(btn){
		Ext.MessageBox.confirm('提示','确认要执行暂停服务命令?',
			function(button,text){
				if(button == 'yes'){
					var form = btn.up('form');
					var ip = form.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
					var winEl = win.getEl();
					winEl.mask('正在执行');
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
								Eway.alert('执行暂停服务命令成功.');
							} else {
								winEl.unmask();
								Eway.alert("执行暂停服务命令失败.");
							}
						},
						failure: function(){
							winEl.unmask();
							Eway.alert("执行暂停服务命令失败.");
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
                text: '正常关机',
                handler: function() {
            		Ext.MessageBox.confirm('提示','确认要执行正常关机命令么,可能会存在风险?',callBack);
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
							winEl.mask('正在执行正常关机');
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
										Eway.alert("正常关机成功.");
									} else {
										winEl.unmask();
										Eway.alert("正常关机失败.");
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert("正常关机命令发送失败.");
								}
							})
            			}
            		}
            	}
            },{
            	text: '强制关机',
            	iconCls: 'icon-add',
            	handler: function(){
            		Ext.MessageBox.confirm('提示','确认要执行强制关机命令么,可能会存在严重风险?',callBack);
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
							winEl.mask('正在执行强制关机');
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
										Eway.alert("强制关机成功.");
									} else {
										winEl.unmask();
										Eway.alert("强制关机失败.");
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert("强制关机命令发送失败.");
								}
							})
            			}
            		}
            	}
            },{
            	text: '返回',
            	iconCls: 'icon-add',
            	handler: function(){
            		dialog.close();
            	}
            }]
        });
        dialog.show({
            title: '关机',
            msg: '请选择关机方式',
            icon: Ext.MessageBox.WARNING
        });

	},

	//重启命令
	onRestartAction : function(btn){
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: '正常重启',
                iconCls: 'icon-add',
                handler: function() {
                	Ext.MessageBox.confirm('提示','确认要执行正常重启命令么,可能会存在风险?',callBack);
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
							winEl.mask('正在执行正常重启');
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
										Eway.alert("正常重启成功.");
									} else {
										winEl.unmask();
										Eway.alert("正常重启失败.");
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert("正常重启命令发送失败.");
								}
							})
                		}
                	}
            	}
            },{
            	text: '强制重启',
            	iconCls: 'icon-add',
            	handler: function(){
            		Ext.MessageBox.confirm('提示','确认要执行强制重启命令么,可能会存在严重风险?',callBack);
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
							winEl.mask('正在执行强制重启');
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
										Eway.alert("强制重启成功.");
									} else {
										winEl.unmask();
										Eway.alert("强制重启失败.");
									}
								},
								failure: function(){
									winEl.unmask();
									Eway.alert("强制重启命令发送失败.");
								}
							})
            			}
            		}
            	}
            },{
            	text: '返回',
            	iconCls: 'icon-add',
            	handler: function(){
            		dialog.close();
            	}
            }]
        });

        dialog.show({
            title: '重启',
            msg: '请选择重启方式',
            icon: Ext.MessageBox.WARNING
        });
	},

	//复位命令，需要C端配合
	onResetAction : function(btn){
		Ext.MessageBox.confirm('提示','确认要执行强制复位?',callBack);
		function callBack(id){
			if(id == 'yes'){
				var form = btn.up('form');
				var ip = form.down('displayfield[name="ip"]').getValue();
				ip = Ext.util.Format.stripTags(ip);
				var win = Ext.ComponentQuery.query('monitor_device_DeviceInfoStatus')[0];
				var winEl = win.getEl();
				winEl.mask('正在执行');
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
							Eway.alert('强制复位成功.');
						} else {
							winEl.unmask();
							Eway.alert('强制复位失败.');
						}
					},
					failure: function(){
						winEl.unmask();
						Eway.alert("强制复位命令发送失败.");
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
		win.setTitle("设备" + record.data.code + "详情");
		win.on('afterrender',function(){
			win.fillForm(record);
		},this)
		win.show();

		var me = this; // 保留当前对象的作用域/
		win.query('tool[action="refresh"]')[0].on('click', function() {
			var el = win.getEl();
			el.mask('正在刷新......');
			setTimeout(function() {
				me.refreshView(win, win.record.get('code'));
				el.unmask();
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
		var currentStore = currentPanel.getStore();

		// 获取最新store的数据
		var newRecord = currentStore.findRecord('code', code);
		win.destroy(); // window销毁

		this.displayWin(newRecord);
	}
});