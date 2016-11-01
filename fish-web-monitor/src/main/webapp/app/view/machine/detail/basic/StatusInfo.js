Ext.define('Eway.view.machine.detail.basic.StatusInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_statusInfo',
	

	requires : [],
	layout: {
        type: 'column',
    },
    closable:false,
    //bodyPadding: 10,
    

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
		var type = field.up('form').down("displayfield[name='devTypeName']").getValue();
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
	}
});
