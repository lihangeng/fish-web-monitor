Ext.define('Eway.controller.machine.detail.Detail', {
	extend : 'Eway.controller.base.FishController',
	views : [ 'machine.detail.View' ,
	          'machine.detail.ControllerInfo'],
	          
    stores: ['case.Fault','case.DevMod','case.FaultStatus','case.FaultClassify'],          
	requires : ['Eway.view.monitor.device.remote.Grid'],
	          
	refs : [{
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
	}, {
		ref : 'appReleaseInfo',
		selector : 'detail_basic_appReleaseInfo'
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
			
			'detail_ControllerInfo displayfield' : {
				afterrender : function(field){
					var text = field.getEl().down('a.link');
					var name=field.name;
					this.controllerInfo(field,text,name);
					scope : this
				},
				scope : this
			},
			'detail_basic_appReleaseInfo displayfield[name="updateVersion"]' : {
				change : {
					fn : function(field) {
						var  text= field.getEl().down('a.link');
						if (text) {
							text.on('mouseenter', this.showAppRelease, this, field);
						}
						
					},
					scope : this
				},
				scope : this
			},
			'detail_personInfo' : {
				beforeexpand:function(){
				   this.getPersonInfo();
				}
			},
			//刷新按钮
			'detail_personInfo [itemId="refreshPersonInfo"]' : {
				afterrender : {
					fn : function(field) {
						field.on('click', this.getPersonInfo, this, field);
					},
					scope : this
				},
				scope : this
			},
			
			'detail_basic_otherInfo' : {
				 beforeexpand:function(){
					   this.getOtherInfo();
					 }
				},
				
				//刷新按钮
				'detail_basic_otherInfo [itemId="refreshOtherInfo"]' : {
					afterrender : {
						fn : function(field) {
							field.on('click', this.getOtherInfo, this, field);
						},
						scope : this
					},
					scope : this
				},
				//刷新按钮
				'detail_basic_deviceInfo [itemId="refreshDeviceInfo"]' : {
					click : this.getDeviceDetailInfo
				},
				
				'detail_basic_appReleaseInfo [itemId="refreshVersion"]' : {
					click : this.getVersionInfo
				},
				
				//刷新按钮
				'detail_basic_hardwareInfo [itemId="refreshHardwareInfo"]' : {
					click : this.getHardwareInfo
				},
				
		});
	},
	
	controllerInfo : function(field,text,name) {
		if (text&&name=="remoteCommHist") {
			text.on('click', this.onRemoteCommHist, this, field);
		}else if(text&&name=="remoteScreenAction") {
			text.on('click', this.onRemoteScreenAction, this, field);
		}else if(text&&name=="netAction") {
			text.on('click', this.onNetAction, this, field);
		}else if(text&&name=="remoteBrowserAction") {
			text.on('click', this.onRemoteBrowserAction, this, field);
		}else if(text&&name=="restartAction") {
			text.on('click', this.onRestartAction, this, field);
		}else if(text&&name=="logicOpenAction") {
			text.on('click', this.onLogicOpenAction, this, field);
		}else if(text&&name=="logicCloseAction") {
			text.on('click', this.onLogicCloseAction, this, field);
		}else if(text&&name=="resetAction") {
			text.on('click', this.onResetAction, this, field);
		}else if(text&&name=="softwareListAction") {
			text.on('click', this.onSoftwareListAction, this, field);
		}else if(text&&name=="processListAction") {
			text.on('click', this.onProcessListAction, this, field);
		}else if(text&&name=="remoteLookAction") {
			text.on('click', this.onRemoteLookAction, this, field);
		}else if(text&&name=="remoteCheckATMAction") {
			text.on('click', this.onRemoteCheckATMAction, this, field);
		}else if(text&&name=="closeAction") {
			text.on('click', this.onCloseAction, this, field);
		}else if(text&&name=="logAction") {
			text.on('click', this.onLogAction, this, field);
		}else if(text&&name=="screenCameraAction") {
			text.on('click', this.onScreenCameraAction, this, field);
		}
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
    	var deviceInfo=this.getParam();
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
			terminalId : deviceInfo.code
		});
		store.loadPage(1);
		
		win.down('button[action=query]').on('click', function() {
			store.loadPage(1);
		});
	},

	
	//打开远程抓屏
	onRemoteScreenAction : function(ev, target, field) {
		var deviceInfo=this.getParam();
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
			params : {ip : deviceInfo.ip ,code: deviceInfo.code},
			callback: function(records, operation, success) {
		        if(success == false){
					Eway.alert(EwayLocale.monitor.devMonitor.remote.screenFailed);
		        }
				}
		});
	},
	//提取电子日志
	onLogAction : function(ev, target, field){
		var deviceInfo=this.getParam();
		var winEl=deviceInfo.winEl;
		winEl.mask(EwayLocale.tip.nowLink);
		Ext.Ajax.request({
			method : 'POST',
			url : 'api/agent/downLogs/download',
			params : {
				ip : deviceInfo.ip,
				code : deviceInfo.code
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
		var deviceInfo=this.getParam();
		var winEl=deviceInfo.winEl;
		var me=this;
		winEl.mask(EwayLocale.tip.nowLink);
		Ext.Ajax.request({
			method: 'POST',
			url: 'api/agent/netWork/getNetWork',
			params: {
				ip : deviceInfo.ip
			},
			success: function(response) {
				winEl.unmask();
				var object = Ext.decode(response.responseText);
				if (object.success && object.success == true) {
					var controller = me.getController('agent.remote.RemoteBrowseNetWork');
					var netInfoObj = Ext.decode(response.responseText);
					controller.init();
					controller.display(deviceInfo.ip,netInfoObj.sendByte,netInfoObj.receivedByte,netInfoObj.conenctRate);
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
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.RemoteBrowseInstation');
		controller.init();
		controller.display(deviceInfo.ip);
	},

	//逻辑开
	onLogicOpenAction : function(ev, target, field){
		var deviceInfo=this.getParam();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.logicOpen,
			function(button,text){
				if(button == 'yes'){					
					var winEl=deviceInfo.winEl;
					winEl.mask(EwayLocale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/open',
						params : {
							ip : deviceInfo.ip,
							terminalId : deviceInfo.code
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
		var deviceInfo=this.getParam();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.closeConfirm,
			function(button,text){
				if(button == 'yes'){
					var winEl=deviceInfo.winEl;
					winEl.mask(EwayLocale.tip.business.device.operating);
					Ext.Ajax.request({
						method : 'POST',
						url : 'api/agent/logic/close',
						params : {
							ip : deviceInfo.ip,
							terminalId : deviceInfo.code
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
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.RemoteBrowseProcess');
		controller.display(deviceInfo.ip);
	},

	//打开远程浏览功能
	onRemoteBrowserAction : function(ev, target, field){
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.RemoteBrowse');
		controller.display(deviceInfo.ip);
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
            				var deviceInfo=this.getParam();
            				var winEl=deviceInfo.winEl;
							winEl.mask(EwayLocale.tip.business.device.closing);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/normalShutdown',
								params : {
									shutdownType : "OS",
									ip : deviceInfo.ip,
									terminalId : deviceInfo.code
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
            				var deviceInfo=this.getParam();
            				winE1=deviceInfo.winEl
							winEl.mask(EwayLocale.tip.business.device.forceClosing);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/shutdown',
								params : {
									shutdownType : "OS",
									ip : deviceInfo.ip,
									terminalId : deviceInfo.code
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
		var deviceInfo=this.getParam();
		var dialog = Ext.create('Ext.window.MessageBox', {
            buttons: [{
                text: EwayLocale.tip.business.device.reboot,
                iconCls: 'icon-add',
                handler: function() {
                	Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.rebootConfirm,callBack);
                	function callBack(id){
                		if(id == 'yes'){
		            		dialog.close();
		            		var winEl=deviceInfo.winEl;
		            		deviceInfo.winEl.mask(EwayLocale.tip.business.device.rebooting);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/normalReboot',
								params : {
									restartType : "OS",
									ip : deviceInfo.ip,
									terminalId : deviceInfo.code
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
		            		var winEl=deviceInfo.winEl;
							winEl.mask(EwayLocale.tip.business.device.forceReboot);
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/agent/reboot',
								params : {
									restartType : "OS",
									ip : deviceInfo.ip,
									terminalId : deviceInfo.code
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
		var deviceInfo=this.getParam();
		Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.business.device.resetConfirm,callBack);
		function callBack(id){
			if(id == 'yes'){
        		var winEl=deviceInfo.winEl;
				winEl.mask(EwayLocale.tip.business.device.operating);
				Ext.Ajax.timeout = 120000;
				Ext.Ajax.request({
					method : 'POST',
					url : 'api/agent/logic/reset',
					params : {
						ip : deviceInfo.ip,
						terminalId : deviceInfo.code
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
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.screen.Camera');
		if (!this.cameaController) {
			controller.init();
			this.cameaController = controller;
		}
		controller.display(deviceInfo.code, deviceInfo.ip);
	},

	//设备体检功能
	onRemoteCheckATMAction : function(ev, target, field){
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.RemoteCheckInfo');
		controller.display(deviceInfo.ip,deviceInfo.code);
	},

	// 查看应用版本
	onRemoteLookAction : function(ev, target, field) {
		var deviceInfo=this.getParam();
		var controller = this.getController('agent.remote.RemoteLookVesion');
		controller.display(deviceInfo.code,deviceInfo.ip);
		var win = controller.win;
		var winEl=deviceInfo.winEl;
		winEl.mask(EwayLocale.tip.business.device.operating);

		Ext.Ajax.request({
   			method : 'POST',
   			url : 'api/agent/atmVersion/versioninfo',
   			params :{
   				terminalId: deviceInfo.code,
   				ip: deviceInfo.ip
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
	
	getParam:function(){
		var view = this.getDeviceInfo();
		var ip =view.down('displayfield[name="ip"]').getValue();
		var codes = view.down('displayfield[name="terminalId"]').getValue();
		code = Ext.util.Format.stripTags(codes);
		ip = Ext.util.Format.stripTags(ip);
		var win = Ext.ComponentQuery.query('detail_ControllerInfo')[0];
		var winEl = win.getEl();
		var deviceInfo={};
		deviceInfo.ip = ip;
		deviceInfo.code = code;
		deviceInfo.winEl = winEl;
		return deviceInfo;
	},
	
	showAppRelease : function(ev, target, field){
		if(Ext.getCmp('tooltip')!=null){
			Ext.getCmp('tooltip').destroy( );
		}
		var view = this.getEwayView();
		var veisionNo=this.getAppReleaseInfo().down("displayfield[name=versionNo]").getValue();
		var termianlId = this.getDeviceInfo().down("displayfield[name=terminalId]").getValue();
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/devicedetail/updateVersion',
			params:{'termianlId':termianlId,'veisionNo':veisionNo},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				var params="";
				if(object.data.length>0){
				Ext.Array.forEach(object.data,function(item,index,items){
					var param=item.fullName+"</br>";
				    params=params+param;
				});
				}else{
					params="当前无可升级版本"
						}
				if(tooltip!=null){
					tooltip.destroy( );
				}
				var tooltip=Ext.create("Ext.tip.ToolTip",{
		            //style: 'display:inline-block;background:#A2C841;padding:7px;cursor:pointer;',
					style: 'display:inline-block;background:#3892D4;padding:7px;cursor:pointer;',
					showDelay: 0,
		            width:200,
		            height:'auto',
		            dismissDelay: 0,
		            target:field.id,
		            hideDelay: 0,
		            id:'tooltip',
		            html:'<font color="white">可升级版本信息：</br>'+params+'</font>'
				});
				view.unmask();	
			}
		});
	},
	
	getPersonInfo : function(){
			var view = this.getEwayView();
			var personInfoView = view.down("detail_personInfo");
			personInfoView.mask();
			var termianlId = this.getDeviceInfo().down("displayfield[name=terminalId]").getValue();
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/machine/devicedetail/personInfo',
				params:{'termianlId':termianlId},
				success : function(response) {
					var object = Ext.decode(response.responseText);
					view.down("detail_personInfo").getStore().removeAll();
					if(object.data!=null){
						Ext.Array.forEach(object.data,function(item,index,items){
							view.down("detail_personInfo").getStore().add(Ext.create('Eway.model.person.person.BankPerson',item))
						});
					}
					personInfoView.unmask();
				},
				failure:function(){
					personInfoView.unmask();
				}
		});
	},
	
	getOtherInfo : function(){
			var view = this.getEwayView();
			var otherInfoView = view.down("detail_basic_otherInfo");
			otherInfoView.mask();
			var termianlId = this.getDeviceInfo().down("displayfield[name=terminalId]").getValue();
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/machine/devicedetail/boxAndRetainInfo',
				params:{'termianlId':termianlId},
				success : function(response) {
					var object = Ext.decode(response.responseText);
					view.down("form").loadRecord(Ext.create('Eway.model.monitor.device.DeviceMonitorList',object.data));
					otherInfoView.unmask();	
				},
				failure:function(){
					otherInfoView.unmask();
				}
		});
	},

	getDeviceDetailInfo : function(){
		var viewAll = this.getEwayView();
		var terminalId = viewAll.getTerminalId();
		var deviceView = viewAll.down("detail_basic_deviceInfo");
		deviceView.mask();
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/devicedetail/basicInfo',
			params:{'termianlId':terminalId},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					deviceView.down("form").loadRecord(Ext.create('Eway.model.machine.Device',object.data));
					}else{
						Eway.alert(object.errorMsg);
					}
				deviceView.unmask();
				},
				failure:function(){
					deviceView.unmask();
				}
		});
	},

	getHardwareInfo : function(){
		var viewAll = this.getEwayView();
		var terminalId = viewAll.getTerminalId();
		var hardwareInfo = viewAll.down("detail_basic_hardwareInfo");
		hardwareInfo.mask();
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/devicedetail/hardwareInfo',
			params:{'termianlId':terminalId},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					viewAll.down("form").loadRecord(Ext.create('Eway.model.monitor.device.DeviceMonitorList',object.data.statusReport));
					var displayfields = Ext.ComponentQuery.query("detail_basic_statusInfo displayfield");
					Ext.Array.forEach(displayfields,function(displayfield,index,items){
						displayfield.setHidden(!hardwareInfo.down("detail_basic_statusInfo").isHidden(displayfield));
					});					
					}else{
						Eway.alert(object.errorMsg);
					}
				hardwareInfo.unmask();
				},
				failure:function(){
					hardwareInfo.unmask();
				}
		});
	},

	getVersionInfo : function(){
		var viewAll = this.getEwayView();
		var terminalId = viewAll.getTerminalId();
		var versionInfoView = viewAll.down("detail_basic_appReleaseInfo");
		versionInfoView.mask();
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/machine/devicedetail/versionInfo',
			params:{'termianlId':terminalId},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					var versionInfo={};
					if(object.data.maxVersion==undefined){
						versionInfo.updateVersion="无";
					}
					else{
						versionInfo.updateVersion=object.data.maxVersion.versionNo;
					}
					if(object.data.currentVersion==undefined){
						versionInfo.versionNo="无";
					}
					else{
						versionInfo.versionNo=object.data.currentVersion.versionNo;
					}
					versionInfo.lastUpdateTime=object.data.lastUpdateTime;
					viewAll.down("form").loadRecord(Ext.create('Eway.model.version.VersionInfo',versionInfo));
					}else{
						Eway.alert(object.errorMsg);
					}
					versionInfoView.unmask();
				},
				failure:function(){
					versionInfoView.unmask();
				}
		});
	}
});
