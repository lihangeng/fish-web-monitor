
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
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'roleAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'roleUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				scope: this
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'roleDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.permission.role.name,
				dataIndex : 'name'
			}, {
				header: EwayLocale.permission.role.isSysRole,
				dataIndex: 'system',
				renderer: function(value){
					if(value == false){
						return EwayLocale.commen.no;
					}else if(value == true){
						return EwayLocale.commen.yes;
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
				header : EwayLocale.permission.role.description,
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