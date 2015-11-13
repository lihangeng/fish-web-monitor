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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			},{
				text : Eway.locale.button.confirm,
				action : 'confirm'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : Eway.locale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.machine.atmGroup.ip,
				dataIndex : 'ip'
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
			}, {
				header : Eway.locale.machine.atmGroup.address,
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