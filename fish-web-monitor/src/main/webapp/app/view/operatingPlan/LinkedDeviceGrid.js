Ext.define('Eway.view.operatingPlan.LinkedDeviceGrid', {
	alias : 'widget.operatingPlan_linkedDeviceGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent : function() {
		var store = Ext.create('Eway.store.operatingPlan.LinkedDevice');
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
				text: Eway.locale.button.search,
				iconCls :'queryBtn',
				action: 'query'
			}, {
				text: Eway.locale.button.unlink,
				iconCls :'deleteBtn',
				action: 'unlink'
			}],
			columns : [ {
				header : Eway.locale.refs.terminalId,
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
						return Eway.locale.commen.comboxDevStatus.open;
					}
					if (value == 2) {
						return Eway.locale.commen.comboxDevStatus.stop;
					}
				}
			}, {
				header : Eway.locale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : Eway.locale.commen.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : Eway.locale.commen.installDate,
				dataIndex : 'installDate'
//				xtype : 'datecolumn',
//				format : 'Y-m-d'
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

	onReload : function() {
		this.getStore().load();
	}
});