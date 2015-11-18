Ext.define('Eway.view.machine.device.TempGrid', {
	alias : 'widget.device_tempGrid',
	extend : 'Ext.grid.Panel',
	border : true,
	requires : [ 'Eway.lib.Util' ],

	initComponent : function() {
		var store = Ext.create('Eway.store.machine.TempDevice');
	    store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
		store : store,
		tbar : [ '->',{
				text : EwayLocale.button.search,
				iconCls : 'queryBtn',
				action : 'tempDevQuery'
			}, {
				text : EwayLocale.button.update,
				iconCls : 'updateBtn',
				action : 'tempDevUpdate',
		//		code : 'tempDevUpdate',
			/*	listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			}, {
				text : EwayLocale.button.remove,
				iconCls : 'deleteBtn',
				action : 'tempDevDelete',
		//		code : 'tempDevDel',
				/*listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			},{
			text : EwayLocale.button.detailBtn,
			iconCls : 'detailBtn',
			action : 'tempDevOpenPlan'
	    	}],		
			columns : [  {
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
				width : 90
			}, {
				header : EwayLocale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName',
				width : 80
			}, {
				header : EwayLocale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName',
				width : 80
			}, {
				header : EwayLocale.machine.atmGroup.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.machine.atmGroup.comboxStatus.dredge;
					}
					if (value == 2) {
						return EwayLocale.machine.atmGroup.comboxStatus.close;
					}
				},
				width : 80
			}, {
				header : EwayLocale.machine.device.onBankSignal,
				dataIndex : 'awayFlag',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.machine.device.inBank;
					}
					if (value == 2) {
						return EwayLocale.machine.device.outBank;
					}
					if (value == 3) {
						return EwayLocale.machine.device.clickBank;
					}
				},
				width : 120
			}, {
				header : EwayLocale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName',
				width : 100
			}, {
				header :  EwayLocale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit',
				width : 100
			}, {
				header :  EwayLocale.machine.atmGroup.installDate,
				dataIndex : 'installDate',
				width : 90
			}, {
				header : EwayLocale.machine.device.installStyle,
				dataIndex : 'setupType',
				renderer:function(value, metadata, record){
					if(value == 1){
						return EwayLocale.machine.device.crossWall;
					}
					if(value == 2){
						return EwayLocale.machine.device.mainRoom;
					}
				},
				width : 80
			}, {
				header : EwayLocale.machine.device.devAddress,
				dataIndex : 'address',
				width : 160
			} ,{
				header : EwayLocale.machine.device.effectiveDate,
				dataIndex : 'effectiveDate',

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