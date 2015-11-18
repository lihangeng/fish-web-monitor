Ext.define('Eway.view.machine.atmGroup.DeviceAddingGrid', {
	alias : 'widget.atmGroup_deviceAddingGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,

	multiSelect:true,
	selModel:{selType:'checkboxmodel'},

	initComponent : function() {
		var store = Ext.create('Eway.store.machine.atmGroup.AddingDevice');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			},{
				text : EwayLocale.button.confirm,
				action : 'confirm'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.machine.atmGroup.ip,
				dataIndex : 'ip'
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
			}, {
				header : EwayLocale.machine.atmGroup.address,
				dataIndex : 'address',
				flex : 1
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