Ext.define('Eway.view.monitor.device.DeviceInfoStatus', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_DeviceInfoStatus',
	requires:['Ext.ux.TransparentButton'],
	width : 800,
	maximizable : true,
	modal : true,
	border : false,
	layout : 'fit',
	constrain : true,
	constrainHeader : true,
	bodyStyle:'padding:10px',
	tools: [ {
		type:'refresh',
		tooltip: EwayLocale.button.refresh,
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
				title : EwayLocale.commen.devInfo,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : EwayLocale.commen.terminalId,
						name : 'code',
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : EwayLocale.commen.orgNameBelongs,
						name : 'org'
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : EwayLocale.commen.devTypeName,
						name : 'type'
					}, {
						columnWidth : .49,
						fieldLabel : EwayLocale.commen.personnel,
						name : 'personnel',
						a_link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .5,
						fieldLabel : EwayLocale.commen.ip,
						name : 'ip'
					},{
						columnWidth : .49,
						fieldLabel : EwayLocale.commen.appVersion,
						name : 'appRelease'
					}]
				},{
					layout : 'column',
					defaultType : 'displayfield',
					items : [{
						columnWidth : .99,
						fieldLabel : EwayLocale.machine.device.devAddress,
						name : 'address',
						tips : true,
						fieldCls : 'text_ellipsis'
					} ]
				}]
			}, {
				xtype : 'fieldset',
				title : EwayLocale.commen.devStatus,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
						name : 'runStatus',
						labelWidth:120,
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : EwayLocale.monitor.devMonitor.cash.boxInitCount,
						name : 'boxInitCount',
						labelWidth:180
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
				        columnWidth : .5,
				        fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
						name : 'modStatus',
						labelWidth:120,
						a_link : true

					}, {
						columnWidth : .49,
						xtype: 'displayfield',
						fieldLabel : EwayLocale.monitor.devMonitor.cash.boxCurrentCount,
						name : 'boxCurrentCount',
						labelWidth:180
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
						name : 'boxStatus',
						labelWidth:120,
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : EwayLocale.monitor.devMonitor.retainCardCount,
						name : 'retainCardCount',
						labelWidth:180
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .5,
						fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
						name : 'netStatus',
						labelWidth:120,
						a_link : true
					}, {
						columnWidth : .49,
						fieldLabel : EwayLocale.monitor.devMonitor.registerStatus,
						name : 'registerStatus',
						labelWidth:180
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : EwayLocale.monitor.devMonitor.devModStatus,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.idc,
						name : 'idcStatus',
						a_link : true
					},{
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.jpr,
						name : 'jprStatus',
						labelWidth:100,
						a_link : true
					},{
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.cdm,
						name : 'cdmStatus',
						a_link : true
					}, {
						columnWidth : .24,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.cim,
						name : 'cimStatus',
						a_link : true
					} ]
				},{
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.siu,
						name : 'siuStatus',
						a_link : true
					},{
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.rpr,
						name : 'rprStatus',
						labelWidth:100,
						a_link : true
					}, {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.pin,
						name : 'pinStatus',
						a_link : true
					},{
						columnWidth : .24,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.ttu,
						name : 'ttuStatus',
						a_link : true
					} ]
				}, {
					layout : 'column',
					defaultType : 'displayfield',
					items : [ {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.isc,
						name : 'iscStatus',
						a_link : true
					}, {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.icc,
						name : 'iccStatus',
						labelWidth:100,
						a_link : true
					}, {
						columnWidth : .25,
						fieldLabel : EwayLocale.monitor.devMonitor.mod.fgp,
						name : 'fgpStatus',
						a_link : true
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : EwayLocale.monitor.devMonitor.remote.control,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					xtype:'panel',
//					defaultType : 'transparentbutton',
					defaultType : 'displayfield',
					minHeight : 90,
					defaults : {
//						hideLabel : true
					},
					items : [ {
						columnWidth : .24,
						name : 'remoteScreenAction',
						code : 'remoteScreen',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.screen+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logAction',
						code : 'takeLog',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.log+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'netAction',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.net+'</a>'
					}, {
						columnWidth : .24,
						name : 'softwareListAction',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.softwareList+'</a>'
					}, {
						columnWidth : .24,
						name: 'closeAction',
						code : 'close',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.powerOff+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'restartAction',
						code : 'restart',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.restart+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicOpenAction',
						code : 'logicOpen',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.logicOpen+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'logicCloseAction',
						code : 'logicClose',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.logicClose+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteBrowserAction',
						code : 'remoteBrowser',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.remoteBrowser+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'processListAction',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.processList+'</a>'
					}, {
						columnWidth : .24,
						name : 'screenCameraAction',
						code : 'screenCamera',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.screenCamera+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'resetAction',
						code : 'reset',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.reset+'</a>',
						listeners : {
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						columnWidth : .24,
						name : 'remoteLookAction',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.remoteLook+'</a>'
					}, {
						columnWidth : .24,
						name : 'remoteCheckATMAction',
						code : 'remoteCheckATM',
						onlyText : true,
						value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.remoteCheckATM+'</a>',
						listeners : {
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
					
					var runFatals= [EwayLocale.monitor.devMonitor.remote.halfSer,EwayLocale.monitor.devMonitor.remote.staff,
					                EwayLocale.monitor.devMonitor.remote.powerOff,EwayLocale.monitor.devMonitor.remote.restart,
					                EwayLocale.monitor.devMonitor.remote.pFault,
					                EwayLocale.monitor.devMonitor.remote.stop,EwayLocale.monitor.devMonitor.remote.pauseFault,
					                EwayLocale.monitor.devMonitor.remote.pauseCash,EwayLocale.monitor.devMonitor.remote.pauseSer];
					if(Ext.Array.contains(runFatals,value)){
						item.setValue(div + "<span class='fatalHighLight'>"+ value + "</span>");
					}else{
						item.setValue(div + value);
					}
				}else if(name == "modStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getModulePath(record.get("mod")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-mod-'+record.get("mod")+'">&nbsp;&nbsp;</div>';
					
					var className = 'link ';

					if(value == EwayLocale.commen.warn){
						className += ' warningHighLight ';
					}else if(value == EwayLocale.commen.fatal){
						className += ' fatalHighLight ';
					}
					item.setValue(div + '<a href="#" class="'+className+'">' + value + '</a>');

				}else if(name == "boxStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getBoxPath(record.get("box")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-box-'+record.get("box")+'">&nbsp;&nbsp;</div>';
					var className = 'link';
					var boxFatals= [EwayLocale.monitor.devMonitor.cash.cimFull,EwayLocale.monitor.devMonitor.cash.cdmEmpty,
					                EwayLocale.monitor.devMonitor.cash.cimAFull,EwayLocale.monitor.devMonitor.cash.cashFault];
					if(Ext.Array.contains(boxFatals,value)){
						className += ' fatalHighLight ';
					} else if(value == EwayLocale.monitor.devMonitor.cash.cdmLow) {
						className += ' warningHighLight ';
					}
					item.setValue(div + '<a href="#" class="'+className+'">'+value+'</a>');

				}else if(name == "netStatus"){
//					var img = '<div style="float:left"><img class="left" height="17px" src="' + view.getNetPath(record.get("net")) + '"/>&nbsp;&nbsp;</div>';
					var div = '<div class="monitor_minicon monitor-div-net-'+record.get("net")+'">&nbsp;&nbsp;</div>';
					if(value ==EwayLocale.commen.fatal){
						item.setValue(div + "<span class='fatalHighLight'>"+ value + "</span>");
					}else if(value ==EwayLocale.commen.unStable){
						item.setValue(div + "<span class='warningHighLight'>"+ value + "</span>");
					}else{
						item.setValue(div + value);
					}
				} else if (name == 'personnel') {
					item.setValue('<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.manaAndstaff+'</a>');
				} else if (name == "modGraphic") {
					item.setValue('<a href="#" class="link">'+EwayLocale.monitor.devMonitor.modGraphic+'</a>');
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
			return EwayLocale.commen.stateDict.normal;
		}
		if(value=='Warning'){
			return EwayLocale.commen.warn;
		}
		if(value=='Fatal'){
			return EwayLocale.commen.fatal;
		}
		if(value=='Unknown'){
			return EwayLocale.commen.unknow;
		}
		if(value=='NoDevice'){
			return EwayLocale.monitor.devMonitor.noData;
		}
	}
});
