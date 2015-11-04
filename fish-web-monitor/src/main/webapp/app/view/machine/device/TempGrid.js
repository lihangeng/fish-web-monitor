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
				text : Eway.locale.button.search,
				iconCls : 'queryBtn',
				action : 'tempDevQuery'
			}, {
				text : Eway.locale.button.update,
				iconCls : 'updateBtn',
				action : 'tempDevUpdate',
		//		code : 'tempDevUpdate',
			/*	listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			}, {
				text : Eway.locale.button.remove,
				iconCls : 'deleteBtn',
				action : 'tempDevDelete',
		//		code : 'tempDevDel',
				/*listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}*/
			},{
			text : Eway.locale.button.detailBtn,
			iconCls : 'detailBtn',
			action : 'tempDevOpenPlan'
	    	}],		
			columns : [  {
				header : Eway.locale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : Eway.locale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				header : Eway.locale.machine.atmGroup.orgName,
				dataIndex : 'orgName',
				width : 140
			}, {
				header : Eway.locale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName',
				width : 90
			}, {
				header : Eway.locale.machine.atmGroup.devVendorName,
				dataIndex : 'devVendorName',
				width : 80
			}, {
				header : Eway.locale.machine.atmGroup.devCatalogName,
				dataIndex : 'devCatalogName',
				width : 80
			}, {
				header : Eway.locale.machine.atmGroup.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return Eway.locale.machine.atmGroup.comboxStatus.dredge;
					}
					if (value == 2) {
						return Eway.locale.machine.atmGroup.comboxStatus.close;
					}
				},
				width : 80
			}, {
				header : Eway.locale.machine.device.onBankSignal,
				dataIndex : 'awayFlag',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return Eway.locale.machine.device.inBank;
					}
					if (value == 2) {
						return Eway.locale.machine.device.outBank;
					}
					if (value == 3) {
						return Eway.locale.machine.device.clickBank;
					}
				},
				width : 120
			}, {
				header : Eway.locale.machine.atmGroup.devServiceName,
				dataIndex : 'devServiceName',
				width : 100
			}, {
				header :  Eway.locale.machine.atmGroup.cashboxLimit,
				dataIndex : 'cashboxLimit',
				width : 100
			}, {
				header :  Eway.locale.machine.atmGroup.installDate,
				dataIndex : 'installDate',
				width : 90
			}, {
				header : Eway.locale.machine.device.installStyle,
				dataIndex : 'setupType',
				renderer:function(value, metadata, record){
					if(value == 1){
						return Eway.locale.machine.device.crossWall;
					}
					if(value == 2){
						return Eway.locale.machine.device.mainRoom;
					}
				},
				width : 80
			}, {
				header : Eway.locale.machine.device.devAddress,
				dataIndex : 'address',
				width : 160
			} ,{
				header : Eway.locale.machine.device.effectiveDate,
				dataIndex : 'effectiveDate',

			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : Eway.locale.tip.displayMessage
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});