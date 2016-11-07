Ext.define('Eway.view.machine.detail.basic.HiddenStatusInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_hiddenStatusInfo',

	layout: {
        type: 'column',
    },
    closable:false,

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 105,
				width : '25%'
			},
			items : [{
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.idc,
				name : 'idcStatus',
				a_link : true,
				code : 'IDC',
				listeners : {
					'beforerender': this.isHidden
				}
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.nfc,
				name : 'nfcStatus',
				a_link : true,
				code : 'NFC',
				listeners : {
					'beforerender': this.isHidden
				}
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.jpr,
				name : 'jprStatus',
				a_link : true,
				code : 'JPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cdm,
				name : 'cdmStatus',
				a_link : true,
				code : 'CDM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cim,
				name : 'cimStatus',
				a_link : true,
				code : 'CIM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.siu,
				name : 'siuStatus',
				a_link : true,
				code : 'SIU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.rpr,
				name : 'rprStatus',
				a_link : true,
				code : 'RPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pin,
				name : 'pinStatus',
				a_link : true,
				code : 'PIN',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.ttu,
				name : 'ttuStatus',
				a_link : true,
				code : 'TTU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.isc,
				name : 'iscStatus',
				a_link : true,
				code : 'ISC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.icc,
				name : 'iccStatus',
				a_link : true,
				code : 'ICC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.fgp,
				name : 'fgpStatus',
				a_link : true,
				code : 'FGP',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pbk,
				name : 'pbkStatus',
				a_link : true,
				code : 'PBK',
				listeners : {
					'beforerender': this.isHidden
				}
			} , {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cam,
				name : 'camStatus',
				a_link : true,
				code : 'CAM',
				listeners : {
					'beforerender': this.isHidden
				}
			} , {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.bcr,
				name : 'bcrStatus',
				a_link : true,
				code : 'BCR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.ukd,
				name : 'ukdStatus',
				a_link : true,
				code : 'UKD',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .25,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.ukr,
				name : 'ukrStatus',
				a_link : true,
				code : 'UKR',
				listeners : {
					'beforerender': this.isHidden
				}
			} ]
		});
		
		this.callParent(arguments);
	},
    isHidden : function(field) {
		var type = field.up('detail_basicInfo').down("displayfield[name='devTypeName']").getValue();
		var typeData = Ext.typeLinkModData;
		var strs = typeData[type];
		var has = false;
		Ext.each(strs, function(item) {
			if(item == field.code) {
				has = true;
				return false;
			}
		});
		return has;
	},
	fillForm : function(record) {
		var me = this;
		me.record = record;
		var form = this.up('form').getForm();
		var fields = form.getFields();

		fields.each(function(item){
			var name = item.getName();
			var value = record.get(name);
			if(item.a_link){
				if(name=='idcStatus' || name=='jprStatus'
					|| name=='cdmStatus' || name=='pinStatus'
					|| name=='cimStatus' || name=='siuStatus'
					|| name=='rprStatus' || name=='ttuStatus'
					|| name=='iccStatus' || name=='iscStatus'
					|| name=='fgpStatus' || name=='pbkStatus'|| name=='nfcStatus'
						|| name=='camStatus' || name=='bcrStatus'
							|| name=='ukrStatus' || name=='ukdStatus'){
						var text = me._getText(value);
						if(value == 'Warning'){
							item.setValue('<a href="#" class="link warningHighLight">'+text+'</a>');
						}else if(value == 'Fatal'){
							item.setValue('<a href="#" class="link fatalHighLight">'+text+'</a>');
						}else {
							item.setValue(text);
						}
				}else if(name == "runStatus"){
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
					var div = '<div class="monitor_minicon monitor-div-mod-'+record.get("mod")+'">&nbsp;&nbsp;</div>';
					var className = '';
					if (value == EwayLocale.commen.noDevice) {
						item.setValue(div + ' ' + value);
					} else {
						if(value == EwayLocale.commen.warn) {
							className += ' warningHighLight ';
						} else if(value == EwayLocale.commen.fatal) {
							className += ' fatalHighLight ';
						}
						item.setValue(div + '<span class="'+className+'">' + value + '</span>');
					}

				}else if(name == "boxStatus"){
					var div = '<div class="monitor_minicon monitor-div-box-'+record.get("box")+'">&nbsp;&nbsp;</div>';
					var className = '';
					var boxFatals= [EwayLocale.monitor.devMonitor.cash.cimFull,EwayLocale.monitor.devMonitor.cash.cdmEmpty,
					                EwayLocale.monitor.devMonitor.cash.cimAFull,EwayLocale.monitor.devMonitor.cash.cashFault];
					if (value == EwayLocale.commen.noDevice) {
						item.setValue(div + ' ' + value);
					} else {
						if(Ext.Array.contains(boxFatals,value)){
							className += ' fatalHighLight ';
						} else if(value == EwayLocale.monitor.devMonitor.cash.cdmLow) {
							className += ' warningHighLight ';
						}
						item.setValue(div + '<span class="'+className+'">'+value+'</span>');
					}

				}else if(name == "netStatus"){
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
			return EwayLocale.monitor.deviceStatus.Healthy;
		}
		if(value=='Warning'){
			return EwayLocale.monitor.deviceStatus.Warning;
		}
		if(value=='Fatal'){
			return EwayLocale.monitor.deviceStatus.Fatal;
		}
		if(value=='Unknown'){
			return EwayLocale.monitor.deviceStatus.Unknown;
		}
		if(value=='NoDevice'){
			return EwayLocale.monitor.deviceStatus.NoDevice;
		}
	},
});
