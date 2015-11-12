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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'serviceOrgAdd',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'serviceOrgUpdate',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'serviceOrgDel',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : Eway.locale.person.serviceOrg.serviceOrgAdmin,
				glyph : 0xf007,
				code : 'serviceOrgAdmin',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : [ {
					text : Eway.locale.person.serviceOrg.setManager,
					action : 'setManager'
				}, {
					text : Eway.locale.person.serviceOrg.removeManager,
					action : 'removeManager'
				} ]
			} ],
			columns : [ {
				header : Eway.locale.person.serviceOrg.code,
				dataIndex : 'code',
				sortable : true
			}, {
				header : Eway.locale.person.serviceOrg.name,
				dataIndex : 'displayName',
				sortable : true
			}, {
				header : Eway.locale.person.serviceOrg.zip,
				dataIndex : 'zip',
				sortable : true
			}, {
				header : Eway.locale.person.serviceOrg.serviceOrgAdmin,
				dataIndex : 'manager'
			}, {
				header : Eway.locale.person.serviceOrg.address,
				dataIndex : 'address',
				sortable : true,
				width : 250
			}, {
				header : Eway.locale.person.serviceOrg.description,
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