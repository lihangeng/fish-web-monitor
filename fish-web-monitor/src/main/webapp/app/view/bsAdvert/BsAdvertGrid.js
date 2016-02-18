
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
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'bsAdvertAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				//'更改'
				text :EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update',
				disabled : true,
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
				//'激活'
				text: EwayLocale.button.active,
				action: 'actived',
				glyph : 0xf0ed,
				disabled : true,
				code : 'bsAdvertActive',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :EwayLocale.advert.preview,
				iconCls : 'adPreview',
				glyph : 0xf1c5,
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
				//'广告组'
				header : EwayLocale.bsAdvert.advertGroup,
				dataIndex : 'groupName',
				width: 200
			},{
				//'广告名称'
				header : EwayLocale.bsAdvert.advertName,
				dataIndex : 'advertName',
				width: 145
			},{
				//'是否激活'
				header : EwayLocale.bsAdvert.advertStatus,
				dataIndex : 'bsAdvertStatus',
				width: 100
			},{
				//'制作时间'
				header:EwayLocale.bsAdvert.createdTime,
				dataIndex:'lastTime',
				width: 200
			},{
				//'广告文件'
				header:EwayLocale.bsAdvert.advertFile,
				dataIndex:'advertFileName',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				}
			},{
				//'制作人'
				header : EwayLocale.bsAdvert.userName,
				dataIndex:'userName',
				width: 100
			},{
				//'激活人'
				header : EwayLocale.bsAdvert.activedUserName,
				dataIndex:'activeUserName',
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