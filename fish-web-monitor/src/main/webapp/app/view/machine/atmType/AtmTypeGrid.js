
Ext.define('Eway.view.machine.atmType.AtmTypeGrid', {
	alias: 'widget.atmType_AtmTypeGrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmType.AtmType');
		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			initRegion : true,
			tbar: ['->',{
					text:'查询',
					glyph : 0xf002,
					action:'query'
				},{
					text: '增加',
					glyph : 0xf067,
					action: 'add',
					code : 'atmTypeAdd',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				},{
					text: '更改',
					glyph : 0xf040,
					action:'update',
					code : 'atmTypeUpdate',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				},{
					text: '删除',
					glyph : 0xf014,
					action: 'remove',
					code : 'atmTypeDel',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				}],
			columns : [/*{
				header : '编号',
				dataIndex : 'no',
				flex : 1
			}, */{
				header : '设备型号',
				dataIndex : 'name',
				flex : 1
			}, {
				header : '所属品牌',
				dataIndex : 'devVendorName',
				flex : 1
			},{
				header : '所属类型',
				dataIndex : 'devCatalogName',
				flex : 1
			},{
				header : '设备规格',
				dataIndex : 'spec',
				flex : 1
			},{
				header : '设备重量',
				dataIndex : 'weight',
				flex : 1
			},{
				header : '平均功率',
				dataIndex : 'watt',
				flex : 1
			},{
				header : '非现金标志',
				dataIndex : 'cashtype',
				flex : 1,
				renderer: function(value,metadata,record){
	                   if(value==1){
	                	   return "现金";
	                   }else if(value==2){
	                	   return "非现金";
	                   }
					}
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload: function() {
		this.getStore().load();
	}
});