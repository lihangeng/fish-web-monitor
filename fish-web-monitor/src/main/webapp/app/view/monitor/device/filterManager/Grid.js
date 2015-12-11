
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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add'
			}, {
				text :EwayLocale.button.update,
				glyph : 0xf040,
				action : 'update'
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				action : 'remove'/*,
				code : 'deviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			} ],
			columns : [ {
				header : EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName,
				dataIndex : 'filterName'
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgId',
				renderer : function(value, metaData, record) {
					
					if (value == null) {
						return EwayLocale.commen.all;
					}
					
					var name = record.get('orgName');
					
					if (name == null) {
						metaData.style = 'color:#BBBBBB;';
						return value + '(已删除)';
					}
					return name;
				}
			}, {
				header : EwayLocale.commen.devVendorName,
				dataIndex : 'brandItem',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return EwayLocale.commen.all;
					}
					var name = record.get('brandItemName');
					
					if (name == null) {
						metaData.style = 'color:#BBBBBB;';
						return value + '(已删除)';
					}
					return name;
				}
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'classifyItem',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return EwayLocale.commen.all;
					}
					var name = record.get('classifyItemName');
					
					if (name == null) {
						metaData.style = 'color:#BBBBBB;';
						return value + '(已删除)';
					}
					return name;
				}
			}, {
				header : EwayLocale.commen.insideOutside,
				dataIndex : 'ingItem',
				renderer : function(value) {
					
					if (value == 0) {
						return EwayLocale.commen.all;
					}
					if (value == 1) {
						return EwayLocale.machine.atmGroup.comboxAwayFlag.inBank;
					}
					if (value == 2) {
						return EwayLocale.machine.atmGroup.comboxAwayFlag.outBank;
					}
					if (value == 3) {
						return EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank;
					}
				}
			}/*, {
				header : EwayLocale.commen.seviceMode,
				dataIndex : 'sellItem',
				renderer : function(value) {
					
					if (value == 0) {
						return EwayLocale.commen.all;
					}
					if (value == 1) {
						return EwayLocale.machine.device.operationSelf;
					}
					if (value == 2) {
						return EwayLocale.machine.device.cooperation;
					}
					if (value == 3) {
						return EwayLocale.machine.device.epiboly;
					}
				}
			}*/, {
				header : EwayLocale.monitor.devMonitor.atmGroup,
				dataIndex : 'atmGroup',
				renderer : function(value, metaData, record) {
					
					if (value == 0) {
						return EwayLocale.commen.all;
					}
					var name = record.get('atmGroupName');
					
					if (name == null) {
						metaData.style = 'color:#BBBBBB;';
						return value + '(已删除)';
					}
					return name;
				}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
				dataIndex : 'runStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.run_all) {
						return EwayLocale.commen.all;
					}
					
					var str = '';
					if (value.run_unknown) {
						str += '|' + EwayLocale.commen.unknow;
					}
					if (value.run_initial) {
						str += '|' + EwayLocale.monitor.devMonitor.init;
					}
					if (value.run_healthy) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.healthy;
					}
					if (value.run_subHealth) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.halfSer;
					}
					if (value.run_customer) {
						str += '|' + EwayLocale.monitor.devMonitor.accTrans;
					}
					if (value.run_maintain) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.staff;
					}
					if (value.run_vdm) {
						str += '|' + EwayLocale.monitor.devMonitor.factureStaff;
					}
					if (value.run_halt) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.powerOff;
					}
					if (value.run_reboot) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.restart;
					}
					if (value.run_stopAtmp) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.pFault;
					}
					if (value.run_stopManmade) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.stopCash;
					}
					if (value.run_stopMod) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.pauseSer;
					}
					if (value.run_stopUnCashIn) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.pauseCash;
					}
					if (value.run_stopunknown) {
						str += '|' + EwayLocale.monitor.devMonitor.remote.pauseSerUnknow;
					}
//					str = str.startsWith('|') ? str.substr(1) : str;
					str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
					return str;
				}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
				dataIndex : 'modStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.mod_all) {
						return EwayLocale.commen.all;
					}
					
					var str = '';
					if (value.mod_healthy) {
						str += '|' + EwayLocale.monitor.devMonitor.mod.healthy;
					}
					if (value.mod_waring) {
						str += '|' + EwayLocale.commen.warn;
					}
					if (value.mod_fatal) {
						str += '|' + EwayLocale.commen.fatal;
					}
					if (value.mod_unknown) {
						str += '|' + EwayLocale.commen.unknow;
					}
					if (value.mod_noDevice) {
						str += '|' + EwayLocale.monitor.devMonitor.noData;
					}
//					str = str.startsWith('|') ? str.substr(1) : str;
					str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
					return str;
				}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'boxStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.box_all) {
						return EwayLocale.commen.all;
					}
					
					var str = '';
					if (value.box_healthy) {
						str += '|' + EwayLocale.commen.stateDict.normal;
					}
					if (value.box_low) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.low;
					}
					if (value.box_empty) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.empty;
					}
					if (value.box_high) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.cimAFull;
					}
					if (value.box_full) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.cimFull;
					}
					if (value.box_fatal) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.cashFault;
					}
					if (value.box_unknown) {
						str += '|' + EwayLocale.monitor.devMonitor.cash.cashUnknow;
					}
//					str = str.startsWith('|') ? str.substr(1) : str;
					str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
					return str;
				}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
				dataIndex : 'netStatusFilterForm',
				minWidth : 200,
				renderer : function(value) {
					if (value.net_all) {
						return EwayLocale.commen.all;
					}
					
					var str = '';
					if (value.net_healthy) {
						str += '|' + EwayLocale.monitor.devMonitor.netHealthy;
					}
					if (value.net_warning) {
						str += '|' + EwayLocale.monitor.devMonitor.netUnStable;
					}
					if (value.net_fatal) {
						str += '|' + EwayLocale.monitor.devMonitor.netFatal;
					}
					if (value.net_unknown) {
						str += '|' + EwayLocale.commen.unknow;
					}
//					str = str.startsWith('|') ? str.substr(1) : str;
					str = Ext.String.startsWith(str, '|') ? str.substr(1) : str;
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