/**
 * 机构信息列表Grid：
 */
Ext.define('Eway.view.person.bankOrg.Grid', {
	alias: 'widget.bank_organization_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.BankOrganization');
		Ext.apply(this, {
			store : store,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->',{
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			},{
				text: '深度查询',
				glyph : 0xf002,
				action: 'deepQuery'
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'bankOrgAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'bankOrgUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '组织迁移',
				glyph : 0xf0ec,
				action : 'move',
				code : 'bankOrgMove',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'bankOrgDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '管理员',
				glyph : 0xf007,
				code : 'bankOrgAdmin',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu: [{
					text: '设置',
					action: 'setManager'
				}, {
					text: '删除',
					action: 'removeManager'
				}]
			}],
			columns : [{
				header : '机构编号',
				dataIndex : 'code',
				sortable : true
			},{
				header : '机构名称',
				dataIndex : 'name',
				sortable : true
			},{
				header : '机构级别',
				dataIndex : 'orgLevel',
				sortable : true,
				renderer: Eway.lib.Util.dictRenderer('person.organization.OrganizationLevelDict')
			},{
				header : '邮政编码',
				dataIndex : 'zip',
				sortable : true
			},{
				header : '机构类型',
				dataIndex : 'organizationType',
				renderer: function(value,metadata,record){
					if(value==0){
	                	   return "银行";
	                   }else if(value==1){
	                	   return "维护商";
	                   }
				},
				hidden: true,
				sortable : true
			},{
				header : '管理员',
				dataIndex : 'manager'
			},{
				header : '机构地址',
				dataIndex : 'address',
				sortable : true,
				width : 200
			},{
				header : '机构描述',
				dataIndex : 'description',
				sortable : true,
				flex : 1
//			},{
//				header : '服务对象',
//				dataIndex : 'serviceObjectName',
//				sortable : true,
//				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});