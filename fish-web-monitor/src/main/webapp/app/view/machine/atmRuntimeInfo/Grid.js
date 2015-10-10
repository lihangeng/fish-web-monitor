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
						return "开通";
					}
					if (value == 2) {
						return "停用";
					}
				}
			}, {
				header : Eway.locale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : '钞箱报警金额(单位：张数)',
				dataIndex : 'cashboxLimit'
			}, {
				header : Eway.locale.commen.installDate,
				dataIndex : 'installDate'
			}, {
				header : Eway.locale.commen.address,
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