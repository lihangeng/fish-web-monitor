Ext.define("Eway.view.machine.device.SoftAndHardwareInfo", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_softAndHardwareInfo',

//	layout : 'fit',
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'fieldset',
				title : '系统硬件信息',
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
							fieldLabel : Eway.locale.commen.terminalId,
							name : 'termId',
							style : 'margin-top:2px'
						}, {
							fieldLabel : '硬盘大小',
							name : 'diskMem'
						}, {
							fieldLabel : 'Bios版本',
							name : 'biosVersion'
						}, {
							fieldLabel : 'Bios厂商',
							name : 'biosVendor'
						}, {
							fieldLabel : 'Bios发布日期',
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
							fieldLabel : '内存总数',
							name : 'memorySize',
							style : 'margin-top:2px'
						}, {
							fieldLabel : '已使用内存',
							name : 'used'
						}, {
							fieldLabel : '空闲内存',
							name : 'free'
						}, {
							fieldLabel : '内存使用率',
							name : 'usedPercent'
						} ]
					} ]
				}, {
					xtype : 'tabpanel',
					padding : '1 0 0 0',
					items : [ {
						title : 'cpu信息',
						itemid : 'cpuItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Cpu',
						columns : [ Ext.create('Ext.grid.RowNumberer'), {
							header : 'CPU频率(MHz)',
							dataIndex : 'frequency'
						}, {
							header : 'CPU的厂商',
							dataIndex : 'vendor'
						}, {
							header : 'CPU的类别',
							dataIndex : 'model',
							flex : 1
						}, {
							header : '缓冲存储器数量',
							dataIndex : 'cacheSize',
							hidden:true
						}, {
							header : 'CPU核数',
							dataIndex : 'totalCores',
							hidden:true
						}, {
							header : '用户使用率',
							dataIndex : 'user',
							width : 90
						}, {
							header : '系统使用率',
							dataIndex : 'sys',
							width : 90
						}, {
							header : '当前空闲率',
							dataIndex : 'idle',
							width : 90
						}, {
							header : '总的使用率',
							dataIndex : 'combined',
							width : 90
						} ]
					}, {
						title : '磁盘信息',
						itemid : 'diskItemID',
						xtype : 'grid',
						store : 'machine.atmHardSoft.Disk',
						columns : [ Ext.create('Ext.grid.RowNumberer'), {
							header : '磁盘分区名称',
							dataIndex : 'name'
						}, {
							header : '磁盘文件系统',
							dataIndex : 'fileSys'
						}, {
							header : '磁盘总大小',
							renderer : function(value, metadata, record) {
								return (value / (1024 * 1024)).toFixed(1) + "GB";
							},
							dataIndex : 'totalSize'
						}, {
							header : '磁盘可用空间大小',
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
				title : '系统软件信息',
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
							fieldLabel : '操作系统ID',
							name : 'arch',
							style : 'margin-top:2px'
						}, {
							fieldLabel : 'OS描述',
							name : 'description'
						}, {
							fieldLabel : 'OS类型',
							name : 'type'
						}, {
							fieldLabel : '系统补丁级别',
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
							fieldLabel : '验钞数据版本',
							name : 'chkCashData',
							style : 'margin-top:2px'
						}, {
							fieldLabel : 'OS供应商',
							name : 'vendor'
						}, {
							fieldLabel : 'OS供应商名',
							name : 'vendorName'
						}, {
							fieldLabel : '系统版本号',
							name : 'version'
						} ]
					} ]
				}
			} ]
		});
		this.callParent(arguments);
	}
});