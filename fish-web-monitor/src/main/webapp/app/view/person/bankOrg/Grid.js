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
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			},{
				text: Eway.locale.button.deepQuery,
				glyph : 0xf002,
				action: 'deepQuery'
			}, {
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'bankOrgAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'bankOrgUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.bankOrgMove,
				glyph : 0xf0ec,
				action : 'move',
				code : 'bankOrgMove',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bankOrgDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.bankOrgAdmin,
				glyph : 0xf007,
				code : 'bankOrgAdmin',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu: [{
					text: Eway.locale.commen.setManager,
					action: 'setManager'
				}, {
					text: Eway.locale.person.bankOrg.removeManager,
					action: 'removeManager'
				}]
			}],
			columns : [{
				header : Eway.locale.person.bankOrg.code,
				dataIndex : 'code',
				sortable : true
			},{
				header : Eway.locale.person.bankOrg.name,
				dataIndex : 'displayName',
				sortable : true
			},{
				header : Eway.locale.person.bankOrg.orgLevel,
				dataIndex : 'orgLevel',
				sortable : true,
				renderer: Eway.lib.Util.dictRenderer('person.organization.OrganizationLevelDict')
			},{
				header : Eway.locale.person.bankOrg.zip,
				dataIndex : 'zip',
				sortable : true
			},{
				header : Eway.locale.person.bankOrg.organizationType,
				dataIndex : 'organizationType',
				renderer: function(value,metadata,record){
					if(value==0){
	                	   return Eway.locale.person.bankOrg.bank;
	                   }else if(value==1){
	                	   return Eway.locale.person.bank.bankOrg.serviceOrg;
	                   }
				},
				hidden: true,
				sortable : true
			},{
				header : Eway.locale.person.bankOrg.manager,
				dataIndex : 'manager'
			},{
				header : Eway.locale.person.bankOrg.address,
				dataIndex : 'address',
				sortable : true,
				width : 200
			},{
				header : Eway.locale.person.bankOrg.description,
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