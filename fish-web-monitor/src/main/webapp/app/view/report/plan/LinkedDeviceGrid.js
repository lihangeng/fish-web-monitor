Ext.define('Eway.view.report.plan.LinkedDeviceGrid', {
	alias : 'widget.plan_linkedDeviceGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent : function() {
		var store = Ext.create('Eway.store.report.plan.LinkedDevice');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->', {
				text: '解除',
				glyph : 0xf014,
				action: 'unlink'
			}],
			columns : [ {
				header : '编号',
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
				header : '设备维护商',
				dataIndex : 'devServiceName'
			}, {
				header : '钞箱报警金额(单位：张数)',
				dataIndex : 'cashboxLimit'
			}, {
				header : '安装日期',
				dataIndex : 'installDate'
//				xtype : 'datecolumn',
//				format : 'Y-m-d'
			}, {
				header : '地址',
				dataIndex : 'address',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
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