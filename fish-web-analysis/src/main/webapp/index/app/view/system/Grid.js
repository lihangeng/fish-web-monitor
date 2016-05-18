Ext.define('Eway.view.system.Grid', {
	alias: 'widget.bank_organization_grid',
	extend: 'Ext.grid.Panel',

	//requires: ['Eway.lib.Util'],

	border : true,

	initComponent: function() {
		var store = Ext.create('Eway.store.system.Organization');
		Ext.apply(this, {
			store : store,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->',{
				text: "查询",
//				glyph : 0xf002,
				iconCls:'x-fa fa-search',
				action: 'query',
                ui: 'soft-blue'
			},{
				text: "增加",
//				glyph : 0xf002,
				iconCls:'x-fa fa-plus',
				action: 'add',
                ui: 'soft-blue',
                listeners: {
                    click: 'onAdd'
                }
			},{
				text: "更多..",
//				glyph : 0xf007,
                ui: 'soft-green',
				code : 'bankOrgAdmin',
				
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				},
				menu: [{
					text: "设置管理员",
					action: 'setManager'
				}, {
					text: "取消管理员",
					action: 'removeManager'
				}, {
					xtype: "menuseparator"
				}, {
					text: "删除机构",
					action: 'remove'
				}]
			}],
			columns : [{
				header : "机构编号",
				dataIndex : 'code',
				sortable : true
			},{
				header : "机构名称",
				dataIndex : 'displayName',
				width:350,
				sortable : true
			},{
				header : "邮编",
				dataIndex : 'zip',
				sortable : true,
				flex:1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});