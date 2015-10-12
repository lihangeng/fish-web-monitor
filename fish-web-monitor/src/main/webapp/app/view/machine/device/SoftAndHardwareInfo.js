Ext.define("Eway.view.machine.device.SoftAndHardwareInfo", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_softAndHardwareInfo',

//	layout : 'fit',
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'fieldset',
				title : Eway.locale.machine.device.sysHardwareInfo,
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
							fieldLabel : Eway.locale.machine.atmGroup.terminalId,
							name : 'termId',
							style : 'margin-top:2px'
						}, {
							fieldLabel : Eway.locale.machine.device.diskMem,
							name : 'diskMem'
						}, {
							fieldLabel : Eway.locale.machine.device.biosVersion,
							name : 'biosVersion'
						}, {
							fieldLabel : Eway.locale.machine.device.biosVendor,
							name : 'biosVendor'
						}, {
							fieldLabel : Eway.locale.machine.device.biosReleaseDate,
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
							fieldLabel :Eway.locale.machine.device.memorySize,
							name : 'memorySize',
							style : 'margin-top:2px'
						}, {
							fieldLabel : Eway.locale.machine.device.memoryUsed,
							name : 'used'
						}, {
							fieldLabel : Eway.locale.machine.device.memoryFree,
							name : 'free'
						}, {
							fieldLabel : Eway.locale.machine.device.memoryPercent,
							name : 'usedPercent'
						} ]
					} ]
				}, {
					xtype : 'tabpanel',
					padding : '1 0 0 0',
					items : [ {
						title : Eway.locale.machine.device.cpuItemID,
						itemid : 'cpuItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Cpu',
						columns : [ Ext.create('Ext.grid.RowNumberer'), {
							header : Eway.locale.machine.device.cpuFrequency,
							dataIndex : 'frequency'
						}, {
							header : Eway.locale.machine.device.cpuVendor,
							dataIndex : 'vendor'
						}, {
							header : Eway.locale.machine.device.cpuModel,
							dataIndex : 'model',
							flex : 1
						}, {
							header : Eway.locale.machine.device.cacheSize,
							dataIndex : 'cacheSize',
							hidden:true
						}, {
							header : Eway.locale.machine.device.totalCores,
							dataIndex : 'totalCores',
							hidden:true
						}, {
							header : Eway.locale.machine.device.userUsePercent,
							dataIndex : 'user',
							width : 90
						}, {
							header : Eway.locale.machine.device.sysUsePercent,
							dataIndex : 'sys',
							width : 90
						}, {
							header : Eway.locale.machine.device.idlePercent,
							dataIndex : 'idle',
							width : 90
						}, {
							header : Eway.locale.machine.device.combinedPercent,
							dataIndex : 'combined',
							width : 90
						} ]
					}, {
						title : Eway.locale.machine.device.diskItemID,
						itemid : 'diskItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Disk',
						columns : [ Ext.create('Ext.grid.RowNumberer'), {
							header : Eway.locale.machine.device.diskName,
							dataIndex : 'name'
						}, {
							header : Eway.locale.machine.device.diskFileSys,
							dataIndex : 'fileSys'
						}, {
							header : Eway.locale.machine.device.diskTotalSize,
							renderer : function(value, metadata, record) {
								return (value / (1024 * 1024)).toFixed(1) + "GB";
							},
							dataIndex : 'totalSize'
						}, {
							header : Eway.locale.machine.device.diskFreeSize,
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
				title : Eway.locale.machine.device.sysSoftInfo,
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
							fieldLabel : Eway.locale.machine.device.OSID,
							name : 'arch',
							style : 'margin-top:2px'
						}, {
							fieldLabel : Eway.locale.machine.device.OSDescription,
							name : 'description'
						}, {
							fieldLabel : Eway.locale.machine.device.OSType,
							name : 'type'
						}, {
							fieldLabel : Eway.locale.machine.device.sysPatchLevel,
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
							fieldLabel : Eway.locale.machine.device.chkCashData,
							name : 'chkCashData',
							style : 'margin-top:2px'
						}, {
							fieldLabel : Eway.locale.machine.device.OSVendor,
							name : 'vendor'
						}, {
							fieldLabel : Eway.locale.machine.device.OSVendorName,
							name : 'vendorName'
						}, {
							fieldLabel : Eway.locale.machine.device.sysVersion,
							name : 'version'
						} ]
					} ]
				}
			} ]
		});
		this.callParent(arguments);
	}
});