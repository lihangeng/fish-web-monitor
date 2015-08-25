
Ext.define('Eway.view.advert.Grid', {
	alias: 'widget.advert_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],
	store: 'advert.Advert',
	border : false,

	initComponent: function() {
		Ext.apply(this, {
			initRegion : true,
			tbar: ['->', {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text : '创建广告',
				glyph : 0xf067,
				menu : new Ext.menu.Menu({
					items : [  {
						text : '创建等待插卡广告',
						action:'wait',
						code : 'advertWait',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					}, {
						text : '创建交易页面广告',
						action:'trans',
						code : 'advertTrans',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					},{
						text : '创建文字滚动广告',
						action:'text',
						code : 'advertText',
						listeners:{
							'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
						}
					},{
						text: '创建公告',
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
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'advertDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*, {
				text: '更改',
				action: 'update'
			},{
				text :'广告详情',
				atcion: 'detail'
			},{
				text :'生成版本',
				iconCls : 'generateVersion',
				action:'generateVersion'
			}*/,{
				text:'下发广告',
//				iconCls : 'versionDown',
				glyph : 0xf0ed,
				action :'downAdvert',
				code : 'advertDownAdvert',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :'广告预览',
//				iconCls : 'adPreview',
				glyph : 0xf152,
				action:'preview',
				code : 'advertPreview',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header: '广告ID',
				dataIndex: 'id',
				width: 40
			},{
				header : '广告类型',
				dataIndex : 'advertType',
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertType')
			},{
				header : '广告下发方式',
				dataIndex : 'advertDownMethod',
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertDownMethod')
			},{
				header : '广告有效期',
				dataIndex : 'advertValidity',
				renderer: Eway.lib.Util.dictRenderer('advert.AdvertValidity')
			},{
				header : '制作时间',
				dataIndex : 'createdTime',
				width: 130
			},{
				header:'创建人',
				dataIndex:'userName'
			},{
				header : '广告版本状态',
				dataIndex:'versionStatus'
			},{
				header: '版本文件',
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
				store : this.store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});