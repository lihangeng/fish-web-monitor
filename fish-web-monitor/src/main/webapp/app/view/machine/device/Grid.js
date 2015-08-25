Ext.define('Eway.view.machine.device.Grid', {
	alias : 'widget.device_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.machine.Device');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->',{
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '详细信息',
				glyph : 0xf129,
				action : 'info'
			}, {
				text : '增加',
				glyph : 0xf067,
				action : 'add',
				code : 'deviceAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '更改',
				glyph : 0xf040,
				action : 'update',
				code : 'deviceUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '删除',
				glyph : 0xf014,
				action : 'remove',
				code : 'deviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '导出',
				iconCls : 'exportToExcel',
				action : 'export'
			} ],
			columns : [ {
				header : '设备号',
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : '设备IP地址',
				dataIndex : 'ip',
				width : 120
			}, {
				header : '所属机构',
				dataIndex : 'orgName',
				width : 140
			}, {
				header : '设备型号',
				dataIndex : 'devTypeName',
				width : 90
			}, {
				header : '设备品牌',
				dataIndex : 'devVendorName',
				width : 80
			}, {
				header : '设备类型',
				dataIndex : 'devCatalogName',
				width : 80
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
				},
				width : 80
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
				},
				width : 120
			}, {
				header : '设备维护商',
				dataIndex : 'devServiceName',
				width : 100
			}, {
				header : '钞箱报警金额',
				dataIndex : 'cashboxLimit',
				width : 100
			}, {
				header : '安装日期',
				dataIndex : 'installDate',
				width : 90
			}, {
				header : '安装方式',
				dataIndex : 'setupType',
				renderer:function(value, metadata, record){
					if(value == 0){
						return '穿墙';
					}
					if(value == 1){
						return '大堂';
					}
				},
				width : 80
			}, {
				header : '设备地址',
				dataIndex : 'address',
				width : 160
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