Ext.define('Eway.view.machine.atmGroup.DeviceGrid', {
	alias : 'widget.atmGroup_deviceGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.machine.atmGroup.Device');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : Eway.locale.button.info,
				glyph : 0xf129,
				maskOnDisable:true,
				action : 'info'
			}, {
				text : Eway.locale.button.search,
				glyph : 0xf002,
				maskOnDisable:true,
				action : 'query'
			}, {
				text : Eway.locale.button.add,
				glyph : 0xf067,
				maskOnDisable:true,
				action : 'add',
				code : 'atmGroupDeviceAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.remove,
				glyph : 0xf014,
				maskOnDisable:true,
				action : 'remove',
				code : 'atmGroupDeviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			columns : [ {
				header : Eway.locale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				header : Eway.locale.machine.atmGroup.orgName,
				dataIndex : 'orgName'
			}, {
				header : Eway.locale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : Eway.locale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : Eway.locale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : Eway.locale.machine.atmGroup.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return Eway.locale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return Eway.locale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return Eway.locale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return Eway.locale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : Eway.locale.machine.atmGroup.awayFlag,
				dataIndex : 'awayFlag',
				renderer : function(value, metadata, record) {
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
				header : Eway.locale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : Eway.locale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit'
			},{
				header : Eway.locale.machine.atmGroup.address,
				dataIndex : 'address',
				width : 150
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});