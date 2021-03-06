
Ext.define('Eway.view.advert.Grid', {
	alias: 'widget.advert_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],
	store: 'advert.Advert',
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
				menu : new Ext.menu.Menu({
					items : [  {
						text : EwayLocale.advert.idleAdvert,
						action:'wait',
						code : 'advertWait',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						text : EwayLocale.advert.transAdvert,
						action:'trans',
						code : 'advertTrans',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					},{
						text : EwayLocale.advert.textAdvert,
						action:'text',
						code : 'advertText',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					},{
						text: EwayLocale.advert.annoucementAdvert,
						action:'annoucement',
						code : 'advertAnnoucement',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					} ]
				}),
				code : 'advertCreate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'advertDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header: 'ID',
				dataIndex: 'id',
				width: 45
			},{
				header : EwayLocale.advert.type,
				dataIndex : 'advertType',
				width: 200,
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertType')
			},/*{
				header : '广告下发方式',
				dataIndex : 'advertDownMethod',
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertDownMethod')
			},*/{
				header : EwayLocale.advert.validity,
				dataIndex : 'advertValidity',
				width: 145,
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertValidity'),
				hidden:true
			},{
				header : EwayLocale.advert.createdTime,
				dataIndex : 'createdTime',
				width: 200
			},{
				header:EwayLocale.advert.userName,
				dataIndex:'userName',
				width: 200
			},{
				header : EwayLocale.advert.versionStatus,
				dataIndex:'versionStatus',
				width: 200
			},{
				header: EwayLocale.advert.advertVersionFile,
				dataIndex:'versionFile',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				},
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