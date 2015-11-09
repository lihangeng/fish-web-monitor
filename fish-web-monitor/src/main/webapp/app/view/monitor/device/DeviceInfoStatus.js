Ext.define('Eway.view.monitor.device.DeviceInfoStatus', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_DeviceInfoStatus',
	requires:['Ext.ux.TransparentButton'],
	width : 720,
	height :610,
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
						a_link : true
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
						fieldLabel : Eway.locale.commen.personnel,
						name : 'personnel',
						a_link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.commen.insideOutside,
						name : 'insideOutside'
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.commen.ip,
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
				title : Eway.locale.commen.devStatus,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.monitor.devMonitor.comboxStatus.runStatus,
						name : 'runStatus',
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.monitor.devMonitor.cash.boxInitCount,
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
							fieldLabel : Eway.locale.monitor.devMonitor.comboxStatus.modStatus,
							name : 'modStatus',
							a_link : true
						}, {
							hideLabel: true,
							columnWidth : .49,
							fieldLabel : Eway.locale.monitor.devMonitor.modStateGraphic,
							name : 'modGraphic',
							a_link : true,
							code : 'modStatusGraphic',
							listeners:{
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						} ]
					}, {
						columnWidth : .49,
						xtype: 'displayfield',
						fieldLabel : Eway.locale.monitor.devMonitor.cash.boxCurrentCount,
						name : 'boxCurrentCount'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
						name : 'boxStatus',
						a_link : true
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
						fieldLabel : Eway.locale.monitor.devMonitor.comboxStatus.netStatus,
						name : 'netStatus',
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : Eway.locale.monitor.devMonitor.registerStatus,
						name : 'registerStatus'
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : Eway.locale.monitor.devMonitor.devModStatus,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.idc,
						name : 'idcStatus',
						a_link : true
					},{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.jpr,
						name : 'jprStatus',
						a_link : true
					},{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.cdm,
						name : 'cdmStatus',
						a_link : true
					}]
				},{
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.cim,
						name : 'cimStatus',
						a_link : true
					},{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.siu,
						name : 'siuStatus',
						a_link : true
					},{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.rpr,
						name : 'rprStatus',
						a_link : true
					}]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.pin,
						name : 'pinStatus',
						a_link : true
					},{
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.ttu,
						name : 'ttuStatus',
						a_link : true
					}, {
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.isc,
						name : 'iscStatus',
						a_link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.icc,
						name : 'iccStatus',
						a_link : true
					}, {
						columnWidth : .3,
						fieldLabel : Eway.locale.monitor.devMonitor.mod.fgp,
						name : 'fgpStatus',
						a_link : true
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : Eway.locale.monitor.devMonitor.remote.control,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					xtype:'toolbar',
//					defaultType : 'transparentbutton',
					minHeight : 90,
					items : [ {
						columnWidth : .24,
						name : 'remoteScreenAction',
						text : Eway.locale.monitor.devMonitor.remote.screen,
						code : 'remoteScreen',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logAction',
						text : Eway.locale.monitor.devMonitor.remote.log,
						code : 'takeLog',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						text : Eway.locale.monitor.devMonitor.remote.net+'</a>',
						name : 'netAction'
					}, {
						columnWidth : .24,
						text : Eway.locale.monitor.devMonitor.remote.softwareList+'</a>',
						name : 'softwareListAction'
					}, {
						columnWidth : .24,
						name: 'closeAction',
						text : Eway.locale.monitor.devMonitor.remote.powerOff+'</a>',
						code : 'close',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'restartAction',
						text : Eway.locale.monitor.devMonitor.remote.restart,
						code : 'restart',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicOpenAction',
						text : Eway.locale.monitor.devMonitor.remote.logicOpen,
						code : 'logicOpen',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicCloseAction',
						text : Eway.locale.monitor.devMonitor.remote.logicClose,
						code : 'logicClose',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteBrowserAction',
						text : Eway.locale.monitor.devMonitor.remote.remoteBrowser,
						code : 'remoteBrowser',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						text : Eway.locale.monitor.devMonitor.remote.processList,
						name : 'processListAction'
					}, {
						columnWidth : .24,
						name : 'screenCameraAction',
						text : Eway.locale.monitor.devMonitor.remote.screenCamera,
						code : 'screenCamera',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'resetAction',
						text : Eway.locale.monitor.devMonitor.remote.reset,
						code : 'reset',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteLookAction',
						text : Eway.locale.monitor.devMonitor.remote.remoteLook
					}, {
						columnWidth : .24,
						name : 'remoteCheckATMAction',
						text : Eway.locale.monitor.devMonitor.remote.remoteCheckATM,
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
			if(item.a_link){
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
					//var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getRunPath(record.get("run")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-run-'+record.get("run")+'">&nbsp;&nbsp;</div>';
					
					var runFatals= [Eway.locale.monitor.devMonitor.remote.halfSer,Eway.locale.monitor.devMonitor.remote.staff,
					                Eway.locale.monitor.devMonitor.remote.powerOff,Eway.locale.monitor.devMonitor.remote.restart,
					                Eway.locale.monitor.devMonitor.remote.pFault,
					                Eway.locale.monitor.devMonitor.remote.stop,Eway.locale.monitor.devMonitor.remote.pauseFault,
					                Eway.locale.monitor.devMonitor.remote.pauseCash,Eway.locale.monitor.devMonitor.remote.pauseSer];
					if(Ext.Array.contains(runFatals,value)){
						item.setValue(div + "<span class='fatalHighLight'>"+ value + "</span>");
					}else{
						item.setValue(div + value);
					}
				}else if(name == "modStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getModulePath(record.get("mod")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-mod-'+record.get("mod")+'">&nbsp;&nbsp;</div>';
					
					var className = 'link ';

					if(value == Eway.locale.commen.warn){
						className += ' warningHighLight ';
					}else if(value == Eway.locale.commen.fatal){
						className += ' fatalHighLight ';
					}
					item.setValue(div + '<a href="#" class="'+className+'">' + value + '</a>');

				}else if(name == "boxStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getBoxPath(record.get("box")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-box-'+record.get("box")+'">&nbsp;&nbsp;</div>';
					var className = 'link';
					var boxFatals= [Eway.locale.monitor.devMonitor.cash.cimFull,Eway.locale.monitor.devMonitor.cash.cdmEmpty,
					                Eway.locale.monitor.devMonitor.cash.cimAFull,Eway.locale.monitor.devMonitor.cash.cashFault];
					if(Ext.Array.contains(boxFatals,value)){
						className += ' fatalHighLight ';
					} else if(value == Eway.locale.monitor.devMonitor.cash.cdmLow) {
						className += ' warningHighLight ';
					}
					item.setValue(div + '<a href="#" class="'+className+'">'+value+'</a>');

				}else if(name == "netStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getNetPath(record.get("net")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-net-'+record.get("net")+'">&nbsp;&nbsp;</div>';
					if(value ==Eway.locale.commen.fatal){
						item.setValue(div + "<span class='fatalHighLight'>"+ value + "</span>");
					}else if(value ==Eway.locale.commen.unStable){
						item.setValue(div + "<span class='warningHighLight'>"+ value + "</span>");
					}else{
						item.setValue(div + value);
					}
				} else if (name == 'personnel') {
					item.setValue('<a href="#" class="link">'+Eway.locale.monitor.devMonitor.remote.manaAndstaff+'</a>');
				} else if (name == "modGraphic") {
					item.setValue('<a href="#" class="link">'+Eway.locale.monitor.devMonitor.modGraphic+'</a>');
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
			return Eway.locale.commen.warn;
		}
		if(value=='Fatal'){
			return Eway.locale.commen.fatal;
		}
		if(value=='Unknown'){
			return Eway.locale.commen.unknow;
		}
		if(value=='NoDevice'){
			return Eway.locale.monitor.devMonitor.noData;
		}
	}
});