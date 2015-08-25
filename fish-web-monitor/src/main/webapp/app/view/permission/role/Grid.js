
Ext.define('Eway.view.permission.role.Grid', {
	alias: 'widget.permission_role_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	autoFit:true,
	
	initComponent: function() {
//		var states = Ext.create('Eway.store.permission.Combobox',{});
		var store = Ext.create('Eway.store.permission.Role');
		store.loadPage(1);
		Ext.apply(this, {
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
				code : 'roleAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action:'update',
				code : 'roleUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				scope: this
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'roleDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : '角色名称',
				dataIndex : 'name'
			}, {
				header: '是否是系统内置角色',
				dataIndex: 'system',
				renderer: function(value){
					if(value == false){
						return '否';
					}else if(value == true){
						return '是';
					}
				},
				width: 160
			
			},
//			{
//				header : '角色类型',
//				dataIndex : 'type',
//				renderer: Eway.lib.Util.typeRenderer('permission.ComboBox')//进行转换，时期显示角色类型 的‘name’字段，而不是abbr字段
//			}, 
			{
				header : '角色描述',
				dataIndex : 'description',
				storable: true,
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