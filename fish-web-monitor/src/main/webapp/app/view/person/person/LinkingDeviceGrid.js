Ext.define('Eway.view.person.person.LinkingDeviceGrid', {
	alias : 'widget.person_linkingDeviceGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent : function() {
		var store = Ext.create('Eway.store.person.person.LinkingDevice');
		store.cleanUrlParam();
		Ext.apply(this, {
			store : store,
			initRegion : true,
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			tbar: [{text:Eway.locale.commen.canBindMachine,xtype:'tbtext'},'->', {
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.commen.bind,
				glyph : 0xf0c1,
				action: 'link'
			}],
			columns : [ {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80
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
				header : Eway.locale.commen.status,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return Eway.locale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return Eway.locale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return Eway.locale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return Eway.locale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : Eway.locale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : Eway.locale.commen.address,
				dataIndex : 'address',
				minWidth:100,
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