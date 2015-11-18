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
				text : EwayLocale.button.info,
				glyph : 0xf129,
				maskOnDisable:true,
				action : 'info'
			}, {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				maskOnDisable:true,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				maskOnDisable:true,
				action : 'add',
				code : 'atmGroupDeviceAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				maskOnDisable:true,
				action : 'remove',
				code : 'atmGroupDeviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			columns : [ {
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.machine.atmGroup.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return EwayLocale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return EwayLocale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return EwayLocale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : EwayLocale.machine.atmGroup.awayFlag,
				dataIndex : 'awayFlag',
				renderer : function(value, metadata, record) {
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
			}, {
				header : EwayLocale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : EwayLocale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit'
			},{
				header : EwayLocale.machine.atmGroup.address,
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