Ext.define('Eway.controller.machine.detail.Detail', {
	extend : 'Eway.controller.base.FishController',
	views : [ 'machine.detail.View' ,
	          'machine.detail.ControllerInfo'],
	          
	          
	requires : ['Eway.view.monitor.device.remote.Grid'],
	          
	refs : [ {
		ref : 'ewayView',
		selector : '#detail',
		autoCreate : true,
		xtype : 'detail.view',
		id : 'deviceDetail'
	}, {
		ref : 'deviceInfo',
		selector : 'detail_basic_deviceInfo'
	}, {
		ref : 'versionInfo',
		selector : 'detail_versionInfo'
	}],


	init : function() {
		this.control({
			'#deviceDetail displayfield[name=deviceBasicInfo]' : {
				click : this.onBasicInfo
			},
			'#deviceDetail displayfield[name=deviceRunInfo]' : {
				click : this.onRunInfo
			}
			,
			'#deviceDetail displayfield[name=deviceControllerInfo]' : {
				click : this.onControllerInfo
			},
			'detail_ControllerInfo displayfield[name="remoteScreenAction"]' : {
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
			'detail_ControllerInfo displayfield[name="logAction"]' : {
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
			'detail_ControllerInfo displayfield[name="netAction"]' : {
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
			'detail_ControllerInfo displayfield[name="softwareListAction"]' : {
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
			'detail_ControllerInfo displayfield[name="logicOpenAction"]' : {
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
			'detail_ControllerInfo displayfield[name="logicCloseAction"]' : {
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
			'detail_ControllerInfo displayfield[name="processListAction"]' : {
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
			'detail_ControllerInfo displayfield[name="remoteBrowserAction"]' : {
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
			'detail_ControllerInfo displayfield[name="remoteLookAction"]' : {
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
			'detail_ControllerInfo displayfield[name="remoteCheckATMAction"]' : {
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
			'detail_ControllerInfo displayfield[name="screenCameraAction"]' : {
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
			'detail_ControllerInfo displayfield[name="resetAction"]' : {
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
			'detail_ControllerInfo displayfield[name="closeAction"]' : {			
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
			'detail_ControllerInfo displayfield[name="restartAction"]' : {
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
			'detail_ControllerInfo displayfield[name="remoteCommHist"]' : {
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
			
			'detail_basic_otherInfo displayfield[name="appRelease"]' : {
				change : {
					fn : function(field) {
						var text = field.getEl().down('a.link');
						if (text) {
							text.on('click', this.showAppRelease, this, field);
						}
					},
					scope : this
				},
				scope : this
			}
		});
	},
	onBasicInfo:function(){
		var view = this.getEwayView();
		view.getLayout().setActiveItem("detail_basicInfo");
	},
	onRunInfo:function(){
		var view = this.getEwayView();
		view.getLayout().setActiveItem("detail_runInfo");
		
	},
	onControllerInfo:function(){
		var view = this.getEwayView();
		view.getLayout().setActiveItem("detail_ControllerInfo");
		
	},
	
	//查看远程命令结果
    onRemoteCommHist :  function(ev, target, field) {
		
	    var view = this.getDeviceInfo();
	    var codes = view.down('displayfield[name="terminalId"]').getValue();
		var code = Ext.util.Format.stripTags(codes);
		
		var win = Ext.create('Ext.window.Window',{
			modal: true,
			height: 520,
		    width: 850,
		    title : EwayLocale.monitor.remoteCommand.titile,
//		    autoScroll : true,
//		    maximizable: true,
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
		var view = this.getDeviceInfo();
		console.log(view);
		var ip = view.down('displayfield[name="ip"]').getValue();
		console.log(ip);
		//var form = field.up('panel');
		//var ip = form.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var codes = view.down('displayfield[name="terminalId"]').getValue();
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
					Eway.alert(EwayLocale.monitor.devMonitor.remote.screenFailed);
		        }
				}
		});
	},
	//提取电子日志
	onLogAction : function(ev, target, field){
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		var codes = view.down('displayfield[name="terminalId"]').getValue();
		code = Ext.util.Format.stripTags(code);
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
									var url = encodeURI('api/agent/downLogs/downloadFile?path=' + object.path + '&fileName=' + object.fileName);
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
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseInstation');
		controller.init();
		controller.display(ip);
	},

	//逻辑开
	onLogicOpenAction : function(ev, target, field){
		var view = this.getDeviceInfo();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.logicOpen,
			function(button,text){
				if(button == 'yes'){					
					var ip =view.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var codes = view.down('displayfield[name="terminalId"]').getValue();
					code = Ext.util.Format.stripTags(codes);
					var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
//							Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
							var object = Ext.decode(response.responseText);
							if (object.success && object.success == true) {
								winEl.unmask();
								Eway.alert(EwayLocale.tip.business.device.openSuccess);
							} else {
								winEl.unmask();
								 Eway.alert(EwayLocale.tip.business.device.openFail);
							}
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
		var view = this.getDeviceInfo();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.closeConfirm,
			function(button,text){
				if(button == 'yes'){
					var ip =view.down('displayfield[name="ip"]').getValue();
					ip = Ext.util.Format.stripTags(ip);
					var codes = view.down('displayfield[name="terminalId"]').getValue();
					code = Ext.util.Format.stripTags(codes);
					var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
//							Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
							var object = Ext.decode(response.responseText);
							if (object.success && object.success == true) {
								winEl.unmask();
								Eway.alert(EwayLocale.tip.business.device.closeServiceSuccess);
							} else {
								winEl.unmask();
								Eway.alert(EwayLocale.tip.business.device.closeServiceFail);
							}
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
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowseProcess');
		controller.display(ip);
	},

	//打开远程浏览功能
	onRemoteBrowserAction : function(ev, target, field){
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteBrowse');
		controller.display(ip);
	},

	//执行关机命令
	onCloseAction : function(ev, target, field){
		var view = this.getDeviceInfo();
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: EwayLocale.tip.business.device.closeNormal,
                handler: function() {
            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.closeNorml,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
		            		var ip =view.down('displayfield[name="ip"]').getValue();
		            		var codes = view.down('displayfield[name="terminalId"]').getValue();
		            		ip = Ext.util.Format.stripTags(ip);
		            		terminalId = Ext.util.Format.stripTags(codes);
		            		var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
		            		var view = this.getDeviceInfo();
		            		var ip =view.down('displayfield[name="ip"]').getValue();
		            		var codes = view.down('displayfield[name="terminalId"]').getValue();
		            		terminalId = Ext.util.Format.stripTags(codes);
		            		ip = Ext.util.Format.stripTags(ip);
		            		var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
		var view = this.getDeviceInfo();
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: EwayLocale.tip.business.device.reboot,
                iconCls: 'icon-add',
                handler: function() {
                	Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
                	function callBack(id){
                		if(id == 'yes'){
		            		dialog.close();
							var ip =view.down('displayfield[name="ip"]').getValue();
							var codes = view.down('displayfield[name="terminalId"]').getValue();
							terminalId = Ext.util.Format.stripTags(codes);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
//									Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
									var object = Ext.decode(response.responseText);
									if (object.success && object.success == true) {
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
            		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
            		function callBack(id){
            			if(id == 'yes'){
            				dialog.close();
							var ip =view.down('displayfield[name="ip"]').getValue();
							var codes = view.down('displayfield[name="terminalId"]').getValue();
							terminalId = Ext.util.Format.stripTags(codes);
							ip = Ext.util.Format.stripTags(ip);
							var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
//									Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
									var object = Ext.decode(response.responseText);
									if (object.success && object.success == true) {
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

	//复位命令，需要C端配合
	onResetAction : function(ev, target, field){
		var view = this.getDeviceInfo();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.resetConfirm,callBack);
		function callBack(id){
			if(id == 'yes'){
				var ip =view.down('displayfield[name="ip"]').getValue();
				ip = Ext.util.Format.stripTags(ip);
				var codes = view.down('displayfield[name="terminalId"]').getValue();
				code = Ext.util.Format.stripTags(codes);
				var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
//						Eway.alert(EwayLocale.tip.business.device.remoteCommandMsg);
						
						var object = Ext.decode(response.responseText);
						if (object.success && object.success == true) {
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.resetSuccess);
						} else {
							winEl.unmask();
							Eway.alert(EwayLocale.tip.business.device.resetFail);
						}
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
		var view = this.getDeviceInfo();
		var codes = view.down('displayfield[name="terminalId"]').getValue();
		var ip =view.down('displayfield[name="ip"]').getValue();
		terminalId = Ext.util.Format.stripTags(codes);
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
		var view = this.getDeviceInfo();
		var codes = view.down('displayfield[name="terminalId"]').getValue();
		var ip =view.down('displayfield[name="ip"]').getValue();
		terminalId = Ext.util.Format.stripTags(codes);
		ip = Ext.util.Format.stripTags(ip);
		var controller = this.getController('agent.remote.RemoteCheckInfo');
		controller.display(ip,terminalId);
	},

	// 查看应用版本
	onRemoteLookAction : function(ev, target, field) {
		var view = this.getDeviceInfo();
		var ip = view.down('displayfield[name="ip"]').getValue();
		var codes = view.down('displayfield[name="terminalId"]').getValue();
		code = Ext.util.Format.stripTags(codes);
		var controller = this.getController('agent.remote.RemoteLookVesion');
		controller.display(code, ip);

		var win = controller.win;
		var statusView = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
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
   	   					
   	   					if (object.data.currentPatches.length > 0) {
   	   						var formVersion = win.down('form');
   	   						
	   	   					for(var index=0; index<object.data.currentPatches.length; index++) {
	   	   						var data = object.data.currentPatches[index];
	   	   						
	   	   						formVersion.add({
	   	   							xtype : 'displayfield',
									fieldLabel: data.typeName,
									labelWidth: 150,
									width: 380,
									
									value : data.versionNo,
									margin : '0 0 0 10',
									style : {
										'text-align': 'center',
										 'word-wrap': 'break-word'
									}
	   	   						});
	   	   					}
   	   					}
   	   					
   	   					win.show();

   	   					//statusView.record.set('appRelease', object.data.atmcVersion);
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
		var win = Ext.widget('detail_ControllerInfo');
		
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
	
	showAppRelease : function(ev, target, field){
		Ext.getCmp('appRelease').show();
	}

});
