Ext.define('Eway.view.person.serviceOrg.LinkedDeviceSerGrid', {
	alias : 'widget.linkedDeviceSerGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.person.organization.LinkedDeviceSer');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.commen.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.commen.devStatus,
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
				header : EwayLocale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : EwayLocale.commen.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : EwayLocale.commen.address,
				dataIndex : 'address',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : EwayLocale.commen.toolbar
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});