
Ext.define('Eway.view.machine.atmBrand.AtmBrandGrid', {
	alias: 'widget.atmBrand_AtmBrandGrid',
	extend: 'Eway.view.base.Grid',


//	store: 'machine.atmBrand.AtmBrand',
	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmBrand.AtmBrand');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:'查询',
				glyph : 0xf002,
				action:'query'
			},{
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'atmBrandAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '更改',
				glyph : 0xf040,
				action:'update',
				code : 'atmBrandUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'atmBrandDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '品牌名称',
				dataIndex : 'name',
				flex : 1
			},{
				header : '生产商国家或地区',
				dataIndex : 'country',
				flex : 2
			},{
				header : '生产商热线1',
				dataIndex : 'hotline1',
				flex : 1
			},{
				header : '生产商热线2',
				dataIndex : 'hotline2',
				flex : 1
			},{
				header : '生产商地址',
				dataIndex : 'address',
				flex : 3
			},{
				header : '生产商状态',
				dataIndex : 'status',
				sortable : true,
				renderer: function(value){
	                   if(value==1){
	                	   return "设备供应";
	                   }else if(value==3){
	                	   return "设备服役";
	                   }
					},
				flex : 1
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