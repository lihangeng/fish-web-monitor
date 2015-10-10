Ext.define('Eway.view.monitor.device.DeviceInfoStatus', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_DeviceInfoStatus',
	requires:['Ext.ux.TransparentButton'],
	width : 720,
	height :570,
	maximizable : true,
	modal : true,
	border : false,
	layout : 'fit',
	constrain : true,
	constrainHeader : true,
	bodyStyle:'padding:10px',
	tools: [ {
		type:'refresh',
		tooltip: Eway.locale.button.refresh,
		action : 'refresh'
	} ],

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			fieldDefaults : {
				labelAlign : 'left',
				labelWidth : 90,
				msgTarget : 'qtip'
			},
			items : [ {
				xtype : 'fieldset',
				title : Eway.locale.commen.devInfo,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.commen.terminalId,
						name : 'code',
						link : true
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.commen.orgNameBelongs,
						name : 'org'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.commen.devTypeName,
						name : 'type'
					}, {
						columnWidth : .49,
						fieldLabel : '联系人',
						name : 'personnel',
						link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : '在行标志',
						name : 'insideOutside'
					}, {
						columnWidth : .49,
						fieldLabel : 'IP地址',
						name : 'ip'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.commen.installAddr,
						name : 'address',
						tips : true,
						fieldCls : 'text_ellipsis'
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.commen.appVersion,
						name : 'appRelease'
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : '设备状态信息',
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.monitor.devMonitor.runStatus,
						name : 'runStatus',
						link : true
					}, {
						columnWidth : .49,
						fieldLabel : '钞箱初始金额',
						name : 'boxInitCount'
					} ]
				}, {
					layout : 'column',
					defaults : {
						border : false
					},
					items : [ {
						xtype: 'panel',
				        combineErrors: true,
				        columnWidth : .5,
				        layout: 'column',
				        defaultType : 'displayfield',
				        items: [ {
							columnWidth : .5,
							fieldLabel : Eway.locale.monitor.devMonitor.modStatus,
							name : 'modStatus',
							link : true
						}, {
							hideLabel: true,
							columnWidth : .49,
							fieldLabel : '模块状态图示',
							name : 'modGraphic',
							link : true,
							code : 'modStatusGraphic',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						} ]
					}, {
						columnWidth : .49,
						xtype: 'displayfield',
						fieldLabel : Eway.locale.monitor.devMonitor.boxCurrentCount,
						name : 'boxCurrentCount'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.monitor.devMonitor.boxStatus,
						name : 'boxStatus',
						link : true
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.monitor.devMonitor.retainCardCount,
						name : 'retainCardCount'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.monitor.devMonitor.netStatus,
						name : 'netStatus',
						link : true
					}, {
						columnWidth : .49,
						fieldLabel : '注册状态',
						name : 'registerStatus'
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : '设备模块状态',
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .3,
						fieldLabel : '读卡器',
						name : 'idcStatus',
						link : true
					},{
						columnWidth : .3,
						fieldLabel : '日志打印机',
						name : 'jprStatus',
						link : true
					},{
						columnWidth : .3,
						fieldLabel : '取款模块',
						name : 'cdmStatus',
						link : true
					}]
				},{
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .3,
						fieldLabel : '存款模块',
						name : 'cimStatus',
						link : true
					},{
						columnWidth : .3,
						fieldLabel : '传感器',
						name : 'siuStatus',
						link : true
					},{
						columnWidth : .3,
						fieldLabel : '凭条打印机',
						name : 'rprStatus',
						link : true
					}]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .3,
						fieldLabel : '密码键盘',
						name : 'pinStatus',
						link : true
					},{
						columnWidth : .3,
						fieldLabel : '文本终端',
						name : 'ttuStatus',
						link : true
					}, {
						columnWidth : .3,
						fieldLabel : '身份证扫描仪',
						name : 'iscStatus',
						link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .3,
						fieldLabel : '发卡器',
						name : 'iccStatus',
						link : true
					}, {
						columnWidth : .3,
						fieldLabel : '指纹仪',
						name : 'fgpStatus',
						link : true
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : '远程控制',
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					xtype:'toolbar',
					defaultType : 'transparentbutton',
					minHeight : 90,
					items : [ {
						columnWidth : .24,
						name : 'remoteScreenAction',
						text : '远程抓屏',
						code : 'remoteScreen',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logAction',
						text : '提取电子日志',
						code : 'takeLog',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						text : '查看网络连接</a>',
						name : 'netAction'
					}, {
						columnWidth : .24,
						text : '获取软件列表</a>',
						name : 'softwareListAction'
					}, {
						columnWidth : .24,
						name: 'closeAction',
						text : '关机</a>',
						code : 'close',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'restartAction',
						text : '重新启动',
						code : 'restart',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicOpenAction',
						text : '开启服务',
						code : 'logicOpen',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicCloseAction',
						text : '暂停服务',
						code : 'logicClose',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteBrowserAction',
						text : '远程浏览',
						code : 'remoteBrowser',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						text : '查看进程信息',
						name : 'processListAction'
					}, {
						columnWidth : .24,
						name : 'screenCameraAction',
						text : '屏幕录制',
						code : 'screenCamera',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'resetAction',
						text : '强制复位',
						code : 'reset',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteLookAction',
						value : '查看应用版本'
					}, {
						columnWidth : .24,
						name : 'remoteCheckATMAction',
						value : 'ATM体检',
						code : 'remoteCheckATM',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					} ]
				} ]
			} ]
		} ];
		this.callParent(arguments);
	},

	fillForm : function(record) {
		var me = this;
		me.record = record;
		var form = this.down('form').getForm();
		var fields = form.getFields();

		var view = Ext.getCmp('monitor_device_view_id');

		fields.each(function(item){
			var name = item.getName();
			var value = record.get(name);
			if(item.link){
				if(name=='idcStatus' || name=='jprStatus'
					|| name=='cdmStatus' || name=='pinStatus'
					|| name=='cimStatus' || name=='siuStatus'
					|| name=='rprStatus' || name=='ttuStatus'
					|| name=='iccStatus' || name=='iscStatus'
					|| name=='fgpStatus'){
						var text = me._getText(value);
						if(value == 'Warning'){
							item.setValue('<a href="#" class="link warningHighLight">'+text+'</a>');
						}else if(value == 'Fatal'){
							item.setValue('<a href="#" class="link fatalHighLight">'+text+'</a>');
						}else {
							item.setValue(text);
						}
				}else if(name == "runStatus"){
					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getRunPath(record.get("run")) + '"/>&nbsp;&nbsp;</div>';

					var runFatals= ['半功能','维护','关机','重启','交易前置故障','报停','暂停服务-模块故障','暂停服务-未加钞','暂停服务'];
					if(Ext.Array.contains(runFatals,value)){
						item.setValue(img + "<span class='fatalHighLight'>"+ value + "</span>");
					}else{
						item.setValue(img + value);
					}
				}else if(name == "modStatus"){
					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getModulePath(record.get("mod")) + '"/>&nbsp;&nbsp;</div>';

					var className = 'link ';

					if(value == '警告'){
						className += ' warningHighLight ';
					}else if(value == '故障'){
						className += ' fatalHighLight ';
					}
					item.setValue(img + '<a href="#" class="'+className+'">' + value + '</a>');

				}else if(name == "boxStatus"){
					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getBoxPath(record.get("box")) + '"/>&nbsp;&nbsp;</div>';

					var className = 'link';
					var boxFatals= ['存款钞满','取款钞空','存款钞将满','钞箱故障'];
					if(Ext.Array.contains(boxFatals,value)){
						className += ' fatalHighLight ';
					} else if(value == '取款钞少') {
						className += ' warningHighLight ';
					}
					item.setValue(img + '<a href="#" class="'+className+'">'+value+'</a>');

				}else if(name == "netStatus"){
					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getNetPath(record.get("net")) + '"/>&nbsp;&nbsp;</div>';

					if(value =="故障"){
						item.setValue(img + "<span class='fatalHighLight'>"+ value + "</span>");
					}else if(value =="不稳定"){
						item.setValue(img + "<span class='warningHighLight'>"+ value + "</span>");
					}else{
						item.setValue(img + value);
					}
				} else if (name == 'personnel') {
					item.setValue('<a href="#" class="link">管机员和维护员</a>');
				} else if (name == "modGraphic") {
					item.setValue('<a href="#" class="link">模块图示</a>');
				} else {
					item.setValue('<a href="#" class="link">'+value+'</a>');
				}
			}
			else {
				if(!item.onlyText){
					item.setValue(value);
				}
			}
		});
	},

	_getText : function(value){
		if(value=='Healthy'){
			return Eway.locale.commen.stateDict.normal;
		}
		if(value=='Warning'){
			return '警告';
		}
		if(value=='Fatal'){
			return '故障';
		}
		if(value=='Unknown'){
			return '未知';
		}
		if(value=='NoDevice'){
			return '无设备';
		}
	}
});