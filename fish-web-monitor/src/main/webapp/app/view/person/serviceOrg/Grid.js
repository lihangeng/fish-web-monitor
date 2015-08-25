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
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '增加',
				glyph : 0xf067,
				action : 'add',
				code : 'serviceOrgAdd',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '更改',
				glyph : 0xf040,
				action : 'update',
				code : 'serviceOrgUpdate',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '删除',
				glyph : 0xf014,
				action : 'remove',
				code : 'serviceOrgDel',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '管理员',
				glyph : 0xf007,
				code : 'serviceOrgAdmin',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : [ {
					text : '设置',
					action : 'setManager'
				}, {
					text : '删除',
					action : 'removeManager'
				} ]
			} ],
			columns : [ {
				header : '厂商编号',
				dataIndex : 'code',
				sortable : true
			}, {
				header : '厂商名称',
				dataIndex : 'name',
				sortable : true
			}, {
				header : '邮政编码',
				dataIndex : 'zip',
				sortable : true
			}, {
				header : '管理员',
				dataIndex : 'manager'
			}, {
				header : '厂商地址',
				dataIndex : 'address',
				sortable : true,
				width : 250
			}, {
				header : '厂商描述',
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