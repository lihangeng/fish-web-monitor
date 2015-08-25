Ext.define('Eway.view.machine.atmRuntimeInfo.Grid', {
			alias : 'widget.atmRuntimeInfo_Grid',
			extend : 'Eway.view.base.Grid',

			border : false,
			autoFit : true,

			initComponent : function() {
				var store = Ext.create('Eway.store.machine.atmRuntimeInfo.RuntimeInfo');
				store.loadPage(1);
				Ext.apply(this, {
					
			initRegion : true,
			store : store,
			tbar : [ '->',  {
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '导出',
				iconCls :'exportToExcel',
				action : 'export'
			},{
				text :'导出最后30天汇总信息',
				iconCls :'exportToExcel',
				action : 'exportLast30'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
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
			onReload : function(){
				this.getStore().load();
			}
		});