Ext.define('Eway.view.advert.AdvertResourceGrid', {
	alias : 'widget.advert_advertResourceGrid',
	extend : 'Ext.grid.Panel',

	store : 'advert.AdvertResource',
	border : false,

	initComponent : function() {
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '{name}'
    	});
		Ext.apply(this, {
			initRegion : true,
			features: [groupingFeature],
			tbar: [{
				text:Eway.locale.advert.downloadButton,
				iconCls : 'versionDown',
				action :'downAdvert',
				code : 'advertDownAdvert',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text :Eway.locale.advert.preview,
				iconCls : 'adPreview',
				code : 'advertPreview',
				disabled : true,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				},
				menu : new Ext.menu.Menu({
					items : [  {
						text : Eway.locale.advert.preview1024,
						action:'preview1024'
					},{
						text : Eway.locale.advert.preview800,
						action:'preview800'
					},{
						text : Eway.locale.advert.preview600,
						action:'preview600'
					}]
				})
			}],
			columns : [{
				header: Eway.locale.advert.content,
				dataIndex : 'content'
			},{
				header : Eway.locale.advert.playTime,
				dataIndex : 'playTime',
				width: 100
			},{
				header: Eway.locale.advert.beginDate,
				dataIndex : 'beginDate'
			},{
				header: Eway.locale.advert.endDate,
				dataIndex : 'endDate'
			},{
				header: Eway.locale.advert.beginTime,
				dataIndex : 'beginTime'
			},{
				header: Eway.locale.advert.endTime,
				dataIndex : 'endTime'
			},{
				header: Eway.locale.advert.fileSize,
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