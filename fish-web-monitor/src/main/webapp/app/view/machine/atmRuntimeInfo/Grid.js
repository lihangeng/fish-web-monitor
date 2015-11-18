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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.exported,
				glyph : 0xf1c3,
				action : 'export'
			},{
				text :EwayLocale.machine.atmRuntimeInfo.exportLast30,
				glyph : 0xf1c3,
				action : 'exportLast30'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.machine.atmRuntimeInfo.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.machine.atmRuntimeInfo.netIp,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.machine.atmGroup.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.machine.atmGroup.open;
					}
					if (value == 2) {
						return EwayLocale.machine.atmGroup.close;
					}
				}
			}, {
				header : EwayLocale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : EwayLocale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : EwayLocale.machine.atmGroup.address,
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