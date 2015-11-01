
Ext.define('Eway.view.monitor.device.filterManager.Grid', {
	alias: 'widget.monitor_device_filtermanager_grid',
	extend: 'Eway.view.base.Grid',
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.monitor.device.DeviceFilter');
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar : [ '->',{
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.button.add,
				glyph : 0xf067,
				action : 'add'
			}, {
				text :Eway.locale.button.update,
				glyph : 0xf040,
				action : 'update'
			}, {
				text : Eway.locale.button.remove,
				glyph : 0xf014,
				action : 'remove'/*,
				code : 'deviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			} ],
			columns : [ {
				header : Eway.locale.monitor.devMonitor.filterManager.filterForm.filterName,
				dataIndex : 'filterName'
			}, {
				header : Eway.locale.machine.atmGroup.orgName,
				dataIndex : 'orgId',
				renderer : function(value, metaData, record) {
					
					if (value == null) {
						return Eway.locale.commen.all;
					}
					return record.get('orgName');
				}
			}, {
				header : Eway.locale.commen.devVendorName,
				dataIndex : 'brandItem',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return Eway.locale.commen.all;
					}
					return record.get('brandItemName');
				}
			}, {
				header : Eway.locale.commen.devTypeName,
				dataIndex : 'classifyItem',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return Eway.locale.commen.all;
					}
					return record.get('classifyItemName');
				}
			}, {
				header : Eway.locale.commen.insideOutside,
				dataIndex : 'ingItem',
				renderer : function(value) {
					
					if (value == 0) {
						return Eway.locale.commen.all;
					}
					if (value == 1) {
						return Eway.locale.machine.atmGroup.comboxAwayFlag.inBank;
					}
					if (value == 2) {
						return Eway.locale.machine.atmGroup.comboxAwayFlag.outBank;
					}
					if (value == 3) {
						return Eway.locale.machine.atmGroup.comboxAwayFlag.clickBank;
					}
				}
			}, {
				header : Eway.locale.commen.seviceMode,
				dataIndex : 'sellItem',
				renderer : function(value) {
					
					if (value == 0) {
						return Eway.locale.commen.all;
					}
					if (value == 1) {
						return Eway.locale.machine.device.operationSelf;
					}
					if (value == 2) {
						return Eway.locale.machine.device.cooperation;
					}
					if (value == 3) {
						return Eway.locale.machine.device.epiboly;
					}
				}
			}, {
				header : Eway.locale.monitor.devMonitor.atmGroup,
				dataIndex : 'atmGroup',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return Eway.locale.commen.all;
					}
					return record.get('atmGroupName');
				}
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.runStatus,
				dataIndex : 'runStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.run_all) {
						return Eway.locale.commen.all;
					}
					
					var str = '';
					if (value.run_unknown) {
						str += '|' + Eway.locale.commen.unknow;
					}
					if (value.run_initial) {
						str += '|' + Eway.locale.monitor.devMonitor.init;
					}
					if (value.run_healthy) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.healthy;
					}
					if (value.run_subHealth) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.halfSer;
					}
					if (value.run_customer) {
						str += '|' + Eway.locale.monitor.devMonitor.accTrans;
					}
					if (value.run_maintain) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.staff;
					}
					if (value.run_vdm) {
						str += '|' + Eway.locale.monitor.devMonitor.factureStaff;
					}
					if (value.run_halt) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.powerOff;
					}
					if (value.run_reboot) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.restart;
					}
					if (value.run_stopAtmp) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.pFault;
					}
					if (value.run_stopManmade) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.stopCash;
					}
					if (value.run_stopMod) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.pauseSer;
					}
					if (value.run_stopUnCashIn) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.pauseCash;
					}
					if (value.run_stopunknown) {
						str += '|' + Eway.locale.monitor.devMonitor.remote.pauseSerUnknow;
					}
					
					return str;
				}
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.modStatus,
				dataIndex : 'modStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.mod_all) {
						return Eway.locale.commen.all;
					}
					
					var str = '';
					if (value.mod_healthy) {
						str += '|' + Eway.locale.monitor.devMonitor.mod.healthy;
					}
					if (value.mod_waring) {
						str += '|' + Eway.locale.commen.warn;
					}
					if (value.mod_fatal) {
						str += '|' + Eway.locale.commen.fatal;
					}
					if (value.mod_unknown) {
						str += '|' + Eway.locale.commen.unknow;
					}
					if (value.mod_noDevice) {
						str += '|' + Eway.locale.monitor.devMonitor.noData;
					}

					return str;
				}
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'boxStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.box_all) {
						return Eway.locale.commen.all;
					}
					
					var str = '';
					if (value.box_healthy) {
						str += '|' + Eway.locale.commen.stateDict.normal;
					}
					if (value.box_low) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.low;
					}
					if (value.box_empty) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.empty;
					}
					if (value.box_high) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.cimAFull;
					}
					if (value.box_full) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.cimFull;
					}
					if (value.box_fatal) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.cashFault;
					}
					if (value.box_unknown) {
						str += '|' + Eway.locale.monitor.devMonitor.cash.cashUnknow;
					}
					
					return str;
				}
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.netStatus,
				dataIndex : 'netStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.net_all) {
						return Eway.locale.commen.all;
					}
					
					var str = '';
					if (value.net_healthy) {
						str += '|' + Eway.locale.monitor.devMonitor.netHealthy;
					}
					if (value.net_warning) {
						str += '|' + Eway.locale.monitor.devMonitor.netUnStable;
					}
					if (value.net_fatal) {
						str += '|' + Eway.locale.monitor.devMonitor.netFatal;
					}
					if (value.net_unknown) {
						str += '|' + Eway.locale.commen.unknow;
					}
					
					return str;
				},
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});