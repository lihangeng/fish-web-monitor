Ext.define("Eway.view.machine.device.module.Status", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.machine_device_module_status',
	height : 180,
	layout : 'column',
	defaults : {
		border : false
	},
	// title:'设备模块状态',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				anchor : '90%',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.idc,
				name : 'statusIdc',
				minHeight : 20,
				code : 'IDC',
				listeners : {
					'beforerender': this.isHidden
				}
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.nfc,
				name : 'statusNfc',
				minHeight : 20,
				code : 'NFC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.jpr,
				name : 'statusJpr',
				minHeight : 20,
				code : 'JPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cdm,
				name : 'statusCdm',
				minHeight : 20,
				code : 'CDM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.siu,
				name : 'statusSiu',
				minHeight : 20,
				code : 'SIU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cim,
				name : 'statusCim',
				minHeight : 20,
				code : 'CIM',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.ttu,
				name : 'statusTtu',
				minHeight : 20,
				code : 'TTU',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.rpr,
				name : 'statusRpr',
				minHeight : 20,
				code : 'RPR',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pin,
				name : 'statusPin',
				minHeight : 20,
				code : 'PIN',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.icc,
				name : 'statusIcc',
				minHeight : 20,
				code : 'ICC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.isc,
				name : 'statusIsc',
				minHeight : 20,
				code : 'ISC',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.fgp,
				name : 'statusFgp',
				minHeight : 20,
				code : 'FGP',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.pbk,
				name : 'statusPbk',
				minHeight : 20,
				code : 'PBK',
				listeners : {
					'beforerender': this.isHidden
				}
			}, {
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.cam,
				name : 'statusCam',
				minHeight : 20,
				code : 'CAM',
				listeners : {
					'beforerender': this.isHidden
				}
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.monitor.devMonitor.mod.bcr,
				name : 'statusBcr',
				minHeight : 20,
				code : 'BCR',
				listeners : {
					'beforerender': this.isHidden
				}
			}  ]
		});
		this.callParent(arguments);
	},
	
	isHidden : function(field) {
		
		var record = field.up('window').record;
		
		var type = record.get('type');
		var typeData = Ext.typeLinkModData
		var strs = typeData[type];
		var has = false;
		Ext.each(strs, function(item) {
			if(item == field.code) {
				has = true;
				return false;
			}
		});
		return has;
	}
});