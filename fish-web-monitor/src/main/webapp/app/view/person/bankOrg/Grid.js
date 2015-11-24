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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
//			},{
//				text: EwayLocale.button.deepQuery,
//				glyph : 0xf002,
//				action: 'deepQuery'
			}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'bankOrgAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'bankOrgUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.bankOrgMove,
				glyph : 0xf0ec,
				action : 'move',
				code : 'bankOrgMove',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bankOrgDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.bankOrgAdmin,
				glyph : 0xf007,
				code : 'bankOrgAdmin',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu: [{
					text: EwayLocale.commen.setManager,
					action: 'setManager'
				}, {
					text: EwayLocale.person.bankOrg.removeManager,
					action: 'removeManager'
				}]
			}],
			columns : [{
				header : EwayLocale.person.bankOrg.code,
				dataIndex : 'code',
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'displayName',
				width:350,
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.orgLevel,
				dataIndex : 'orgLevel',
				sortable : true,
				renderer: Eway.lib.Util.dictRenderer('person.organization.OrganizationLevelDict')
			},{
				header : EwayLocale.person.bankOrg.zip,
				dataIndex : 'zip',
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.organizationType,
				dataIndex : 'organizationType',
				renderer: function(value,metadata,record){
					if(value==0){
	                	   return EwayLocale.person.bankOrg.bank;
	                   }else if(value==1){
	                	   return EwayLocale.person.bank.bankOrg.serviceOrg;
	                   }
				},
				hidden: true,
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.manager,
				dataIndex : 'manager'
			},{
				header : EwayLocale.person.bankOrg.address,
				dataIndex : 'address',
				sortable : true,
				width : 200
			},{
				header : EwayLocale.person.bankOrg.description,
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