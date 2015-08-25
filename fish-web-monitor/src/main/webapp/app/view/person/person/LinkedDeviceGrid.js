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
			tbar: [{text:'已关联的设备',xtype:'tbtext'},'->', {
				text:'查询',
				glyph : 0xf002,
				action:'query'
			},{
				text: '解除',
				glyph : 0xf014,
				action: 'unlink'
			}],
			columns : [ {
				header : '设备号',
				dataIndex : 'terminalId',
				width:80
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
			},{
				header : '地址',
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