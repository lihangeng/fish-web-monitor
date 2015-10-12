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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.button.exported,
				iconCls :'exportToExcel',
				action : 'export'
			},{
				text :Eway.locale.machine.atmRuntimeInfo.exportLast30,
				iconCls :'exportToExcel',
				action : 'exportLast30'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : Eway.locale.machine.atmRuntimeInfo.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.machine.atmRuntimeInfo.netIp,
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
						return Eway.locale.machine.atmGroup.open;
					}
					if (value == 2) {
						return Eway.locale.machine.atmGroup.close;
					}
				}
			}, {
				header : Eway.locale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : Eway.locale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : Eway.locale.machine.atmGroup.installDate,
				dataIndex : 'installDate'
			}, {
				header : Eway.locale.machine.atmGroup.address,
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