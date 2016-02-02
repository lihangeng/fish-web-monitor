
Ext.define('Eway.view.bsAdvert.BsAdvertGrid', {
	alias: 'widget.bs_advert_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],
	store: 'bsAdvert.BsAdvert',
	border : false,

	height:400,
	initComponent: function() {
		var me = this;
		Ext.apply(this, {
			initRegion : true,
//			store:me.store,
			tbar: ['->', {
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text : EwayLocale.advert.createAdvert,
				glyph : 0xf067,
				action: 'add',
				code : 'bsAdvertAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text :'更改',
				glyph : 0xf067,
				action: 'update',
				code : 'bsAdvertUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bsAdvertDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '激活',
				glyph : 0xf014,
				action: 'actived',
				code : 'bsAdvertActive',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :EwayLocale.advert.preview,
				iconCls : 'adPreview',
				code : 'advertPreview',
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
				header: 'ID',
				dataIndex: 'id',
				width: 45
			},{
				header : '广告组',
				dataIndex : 'groupName',
				width: 200
			},/*{
				header : '广告下发方式',
				dataIndex : 'advertDownMethod',
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertDownMethod')
			},*/{
				header : '广告名称',
				dataIndex : 'advertName',
				width: 145
			},{
				header : '是否激活',
				dataIndex : 'actived',renderer:function(value,meta,record){
					if(value == true){
						return "激活";
					}else{
						return "未激活";
					}
				},
				width: 100
			},{
				header:'最近更改时间',
				dataIndex:'lastTime',
				width: 200
			},{
				header:'BS广告文件',
				dataIndex:'advertFileName',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				}
			},{
				header : '更改人',
				dataIndex:'personName',
				width: 100
			},{
				header : '激活人',
				dataIndex:'activePersonName',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : me.store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});