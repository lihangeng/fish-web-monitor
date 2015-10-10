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
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			},{
				text : '增加',
				action : 'confirm'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : Eway.locale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			}, {
				header : Eway.locale.commen.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : Eway.locale.commen.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : Eway.locale.commen.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : Eway.locale.commen.devStatus,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "启用";
					}
					if (value == 2) {
						return "停用";
					}
				}
			}, {
				header : '在行离行标志',
				dataIndex : 'awayFlag',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "在行自助服务区";
					}
					if (value == 2) {
						return "离行自助银行";
					}
					if (value == 3) {
						return "单机离行自助服务点";
					}
				}
			}, {
				header : Eway.locale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : Eway.locale.commen.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : Eway.locale.commen.installDate,
				dataIndex : 'installDate'
			}, {
				header : Eway.locale.commen.address,
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