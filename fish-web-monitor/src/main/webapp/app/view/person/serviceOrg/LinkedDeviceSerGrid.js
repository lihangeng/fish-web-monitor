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
				header : '设备号',
				dataIndex : 'terminalId'
			}, {
				header : '网络地址',
				dataIndex : 'ip'
			}, {
				header : '所属机构',
				dataIndex : 'orgName'
			}, {
				header : '设备型号',
				dataIndex : 'devTypeName'
			}, {
				header : '设备品牌',
				dataIndex : 'devVendorName'
			}, {
				header : '设备类型',
				dataIndex : 'devCatalogName'
			}, {
				header : '设备状态',
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "开通";
					}
					if (value == 2) {
						return "停用";
					}
				}
			}, {
				header : '设备厂商',
				dataIndex : 'devServiceName'
			}, {
				header : '钞箱报警金额',
				dataIndex : 'cashboxLimit'
			}, {
				header : '安装日期',
				dataIndex : 'installDate'
			}, {
				header : '地址',
				dataIndex : 'address',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : '总共：{2}条，显示{0}-{1}'
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});