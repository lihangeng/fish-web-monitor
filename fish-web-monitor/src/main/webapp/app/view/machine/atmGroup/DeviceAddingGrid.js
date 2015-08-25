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
				header : '设备维护商',
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
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});