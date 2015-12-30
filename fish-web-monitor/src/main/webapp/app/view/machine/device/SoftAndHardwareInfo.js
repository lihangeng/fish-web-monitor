Ext.define("Eway.view.machine.device.SoftAndHardwareInfo", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_softAndHardwareInfo',

//	layout : 'fit',
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'fieldset',
				title : EwayLocale.machine.device.sysHardwareInfo,
				collapsible : true,
				defaults : {
					border : false
				},
				items : [ {
					layout : 'column',
					xtype : 'panel',
					items : [ {
						columnWidth : .5,
						layout : 'anchor',
						defaultType : 'textfield',
						border : false,
						defaults : {
							readOnly : true,
							anchor : '90%'
						},
						items : [ {
							fieldLabel : EwayLocale.machine.atmGroup.terminalId,
							name : 'termId',
							style : 'margin-top:2px'
						}, {
							fieldLabel : EwayLocale.machine.device.diskMem,
							name : 'diskMem'
						}, {
							fieldLabel : EwayLocale.machine.device.biosVersion,
							name : 'biosVersion'
						}, {
							fieldLabel : EwayLocale.machine.device.biosVendor,
							name : 'biosVendor'
						}, {
							fieldLabel : EwayLocale.machine.device.biosReleaseDate,
							name : 'biosReleaseDate'
						} ]
					}, {
						columnWidth : .5,
						layout : 'anchor',
						border : false,
						defaults : {
							readOnly : true,
							xtype : 'textfield',
							anchor : '90%'
						},
						items : [ {
							fieldLabel :EwayLocale.machine.device.memorySize,
							name : 'memorySize',
							style : 'margin-top:2px'
						}, {
							fieldLabel : EwayLocale.machine.device.memoryUsed,
							name : 'used'
						}, {
							fieldLabel : EwayLocale.machine.device.memoryFree,
							name : 'free'
						}, {
							fieldLabel : EwayLocale.machine.device.memoryPercent,
							name : 'usedPercent'
						} ]
					} ]
				}, {
					xtype : 'tabpanel',
					padding : '1 0 0 0',
					items : [ {
						title : EwayLocale.machine.device.cpuItemID,
						itemid : 'cpuItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Cpu',
						columns : [ Ext.create('Ext.grid.RowNumberer'), /*{
							header : EwayLocale.machine.device.cpuFrequency,
							dataIndex : 'frequency'
						}, */{
							header : EwayLocale.machine.device.cpuModel,
							dataIndex : 'model',
							flex : 1
						}, {
							header : EwayLocale.machine.device.cpuVendor,
							dataIndex : 'vendor'
						},/* {
							header : EwayLocale.machine.device.cacheSize,
							dataIndex : 'cacheSize',
							hidden:true
						}, {
							header : EwayLocale.machine.device.totalCores,
							dataIndex : 'totalCores',
							hidden:true
						}, {
							header : EwayLocale.machine.device.userUsePercent,
							dataIndex : 'user',
							width : 90
						}, {
							header : EwayLocale.machine.device.sysUsePercent,
							dataIndex : 'sys',
							width : 90
						}, */{
							header : EwayLocale.machine.device.combinedPercent,
							dataIndex : 'combined',
							width : 90
						} , {
							header : EwayLocale.machine.device.idlePercent,
							dataIndex : 'idle',
							width : 90
						}]
					}, {
						title : EwayLocale.machine.device.diskItemID,
						itemid : 'diskItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Disk',
						columns : [ Ext.create('Ext.grid.RowNumberer'), {
							header : EwayLocale.machine.device.diskName,
							dataIndex : 'name'
						}, {
							header : EwayLocale.machine.device.diskFileSys,
							dataIndex : 'fileSys'
						}, {
							header : EwayLocale.machine.device.diskTotalSize,
							renderer : function(value, metadata, record) {
								return (value / (1024 * 1024)).toFixed(1) + "GB";
							},
							dataIndex : 'totalSize'
						}, {
							header : EwayLocale.machine.device.diskFreeSize,
							renderer : function(value, metadata, record) {
								return (value / (1024 * 1024)).toFixed(1) + "GB";
							},
							dataIndex : 'freeSize',
							flex : 1
						} ]
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : EwayLocale.machine.device.sysSoftInfo,
				collapsible : true,
				border : false,
				defaults : {
					border : false
				},
				items : {
					layout : 'column',
//					padding : '1 0 0 0',
					items : [ {
						columnWidth : .5,
						layout : 'anchor',
						defaultType : 'textfield',
						border : false,
						defaults : {
							anchor : '90%',
							readOnly : true
						},
						items : [ {
							fieldLabel : EwayLocale.machine.device.OSID,
							name : 'arch',
							style : 'margin-top:2px'
						}, {
							fieldLabel : EwayLocale.machine.device.OSDescription,
							name : 'description'
						}, {
							fieldLabel : EwayLocale.machine.device.OSType,
							name : 'type'
						}, {
							fieldLabel : EwayLocale.machine.device.sysPatchLevel,
							name : 'patchLevel'
						} ]
					}, {
						columnWidth : .5,
						layout : 'anchor',
						border : false,
						defaults : {
							anchor : '90%',
							readOnly : true,
							xtype : 'textfield'
						},
						items : [ {
							fieldLabel : EwayLocale.machine.device.chkCashData,
							name : 'chkCashData',
							style : 'margin-top:2px'
						}, {
							fieldLabel : EwayLocale.machine.device.OSVendor,
							name : 'vendor'
						}, {
							fieldLabel : EwayLocale.machine.device.OSVendorName,
							name : 'vendorName'
						}, {
							fieldLabel : EwayLocale.machine.device.sysVersion,
							name : 'version'
						} ]
					} ]
				}
			} ]
		});
		this.callParent(arguments);
	}
});