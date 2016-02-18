Ext.define('Eway.view.advert.AdvertResourceGrid', {
	alias : 'widget.advert_advertResourceGrid',
	extend : 'Ext.grid.Panel',

	store : 'advert.AdvertResource',
	border : false,

	height:700,
	initComponent : function() {
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '{name}'
    	});
		Ext.apply(this, {
			initRegion : true,
			features: [groupingFeature],
			tbar: [{
				text:EwayLocale.advert.downloadButton,
				iconCls : 'versionDown',
				action :'downAdvert',
				glyph : 0xf0ed,
				code : 'advertDownAdvert',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :EwayLocale.advert.preview,
				iconCls : 'adPreview',
				code : 'advertPreview',
				glyph : 0xf1c5,
				disabled : true,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : new Ext.menu.Menu({
					items : [  {
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
				header: EwayLocale.advert.content,
				dataIndex : 'content',
				width: 200
			},{
				header : EwayLocale.advert.playTime,
				dataIndex : 'playTime',
				width: 100
			},{
				header: EwayLocale.advert.beginDate,
				dataIndex : 'beginDate',
				width: 150
			},{
				header: EwayLocale.advert.endDate,
				dataIndex : 'endDate',
				width: 150
			},{
				header: EwayLocale.advert.beginTime,
				dataIndex : 'beginTime',
				width: 150
			},{
				header: EwayLocale.advert.endTime,
				dataIndex : 'endTime',
				width: 150
			},{
				header: EwayLocale.advert.fileSize,
				dataIndex : 'fileSize',
				flex : 1
			}]/*,
			dockedItems : [ { // 分页栏
				xtype : 'pagingtoolbar',
				store : this.store, // same store GridPanel is using
				dock : 'bottom',
				displayInfo : true
			} ]*/
		});

		this.callParent(arguments);
	}
});