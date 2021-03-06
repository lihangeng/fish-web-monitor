/**
 * 厂商信息列表Grid：
 */
Ext.define('Eway.view.person.serviceOrg.Grid', {
	alias : 'widget.ser_organization_grid',
	extend : 'Eway.view.base.Grid',

	border : false,

	initComponent : function() {
		var store = Ext
				.create('Eway.store.person.organization.ServiceOrganization');
		Ext.apply(this, {
			store : store,
			tbar : [ {
				text : '',
				action : 'tip',
				xtype : 'tbtext'
			}, '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'serviceOrgAdd',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'serviceOrgUpdate',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'serviceOrgDel',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.person.serviceOrg.serviceOrgAdmin,
				glyph : 0xf007,
				code : 'serviceOrgAdmin',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : [ {
					text : EwayLocale.person.serviceOrg.setManager,
					action : 'setManager'
				}, {
					text : EwayLocale.person.serviceOrg.removeManager,
					action : 'removeManager'
				} ]
			} ],
			columns : [ {
				header : EwayLocale.person.serviceOrg.code,
				dataIndex : 'code',
				sortable : true
			}, {
				header : EwayLocale.person.serviceOrg.name,
				dataIndex : 'displayName',
				sortable : true,
				width : 200
			}, {
				header : EwayLocale.person.serviceOrg.zip,
				dataIndex : 'zip',
				sortable : true
			}, {
				header : EwayLocale.person.serviceOrg.serviceOrgAdmin,
				dataIndex : 'manager'
			}, {
				header : EwayLocale.person.serviceOrg.address,
				dataIndex : 'address',
				sortable : true,
				width : 200
			}, {
				header : EwayLocale.person.serviceOrg.description,
				dataIndex : 'description',
				sortable : true,
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});