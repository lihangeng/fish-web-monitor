/**
 * 机构信息列表Grid：
 */
Ext.define('Eway.view.bsAdvert.BsAdvertGroupGrid', {
	alias: 'widget.bs_advert_group_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.bsAdvert.BsAdvertGroup');
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
			}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'bsAdvertGroupCreate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'bsAdvertGroupUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.bankOrgMove,
				glyph : 0xf0ec,
				action : 'move',
				code : 'bsAdvertGroupLinkDevice',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bsAdvertGroupDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'orgName',
				width:350,
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.orgLevel,
				dataIndex : 'orgLevel',
				sortable : true
			},{
				header : '组名称',
				dataIndex : 'groupName',
				sortable : true
			},{
				header : '广告组分类',
				dataIndex : 'groupType',
				sortable : true,
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});