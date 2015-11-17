Ext.define('Eway.view.person.person.LinkedDeviceGrid', {
	alias : 'widget.person_linkedDeviceGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

//	store : 'person.person.LinkedDevice',
	border : false,
	autoFit : true,

	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent : function() {
		var store = Ext.create('Eway.store.person.person.LinkedDevice');
		store.cleanUrlParam();
		Ext.apply(this, {
			store : store,
			initRegion : true,
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			tbar: [{text:EwayLocale.commen.bindMachine,xtype:'tbtext'},'->', {
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.commen.lift,
				glyph : 0xf014,
				action: 'unlink'
			}],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80
			}, {
				header : EwayLocale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.commen.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.commen.devStatus,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return EwayLocale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return EwayLocale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return EwayLocale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : EwayLocale.commen.devServiceName,
				dataIndex : 'devServiceName'
			},{
				header : EwayLocale.commen.address,
				dataIndex : 'address',
				minWidth:120,
				flex:1
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