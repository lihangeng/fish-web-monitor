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
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bsAdvertGroupDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : '关联设备',
				glyph : 0xf0c1,
				action : 'link',
				disabled : true,
				code : 'bsAdvertGroupLinkDevice',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :EwayLocale.advert.preview,
				iconCls : 'adPreview',
				code : 'advertPreview',
				glyph : 0xf1c5,
				disabled : true,
				menu : new Ext.menu.Menu({
					items : [{
						text : EwayLocale.advert.preview1024,
						action:'preview1024'
					},{
						text : EwayLocale.advert.preview800,
						action:'preview800'
					},{
						text : EwayLocale.advert.preview600,
						action:'preview600'
					}]
				})
			}],
			columns : [{
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'orgName',
				width:250,
				sortable : true
			},{
				header : EwayLocale.person.bankOrg.orgLevel,
				dataIndex : 'orgLevel',
				sortable : true
			},{
				header : '组名称',
				dataIndex : 'groupName',
				width:200,
				sortable : true,
				
			},{
				header : '广告组分类',
				dataIndex : 'groupType',
				width:100,
				sortable : true,
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return '默认';
					}
					if (value == 0) {
						return '通用';
					}
				}
			},{
				header : '已激活的广告',
				dataIndex : 'activedAdv',
				sortable : true,
				flex : 1,
				renderer : function(value, metadata, record) {
					if (value == 'null') {
						return "无";
					}else{
						return value;
					}
					
				}
				
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});