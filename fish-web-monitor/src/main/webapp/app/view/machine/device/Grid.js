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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.info,
				glyph : 0xf129,
				action : 'info',
				code : 'deviceInfo',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'deviceAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},	{
				text :EwayLocale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'deviceUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},	{
				text : EwayLocale.button.adminBtn,
				glyph : 0xf007,
				action : 'admin',
				code : 'devicePerson',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : [ {
				text : EwayLocale.button.personTM,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				action : 'personTM'
				}, {
				text : EwayLocale.button.personM,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				action : 'personM'
				} ]
			},	{
				text :EwayLocale.machine.plan.servicePlan,
				glyph : 0xf133,
				action : 'openPlan',
				code : 'deviceOpenPlan',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'deviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.exported,
				glyph : 0xf1c3,
				action : 'export',
				code : 'deviceExport',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			columns : [ {
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : EwayLocale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgName',
				width : 140
			}, {
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName',
				width : 100
			}, {
				header : EwayLocale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName',
				width : 100
			}, {
				header : EwayLocale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName',
				width : 120
			}, {
				header : EwayLocale.machine.atmGroup.status,
				dataIndex : 'statusName',
				width : 100
			}, {
				header : EwayLocale.machine.device.onBankSignal,
				dataIndex : 'awayFlagName',
				width : 160
			}, {
				header : EwayLocale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName',
				width : 160
			}, {
				header : EwayLocale.machine.device.devAddress,
				dataIndex : 'address',
				width : 160
			}, {
				header :  EwayLocale.machine.atmGroup.installDate,
				dataIndex : 'installDate',
				width : 120
			}, {
				header : EwayLocale.machine.device.installStyle,
				dataIndex : 'setupTypeName',
				width : 120
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
