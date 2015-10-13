Ext.define('Eway.view.advert.AdvertResourceGrid', {
	alias : 'widget.advert_advertResourceGrid',
	extend : 'Ext.grid.Panel',

	store : 'advert.AdvertResource',
	border : false,

	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar : [ /*'->', {
				text : '查询',
				action : 'query1'
			} */],
			columns : [ {
				header : Eway.locale.advert.playTime,//'广告播放时长',
				dataIndex : 'playTime'
			},{
				header: Eway.locale.advert.beginDate,//'开始日期',
				dataIndex : 'beginDate'
			},{
				header: Eway.locale.advert.endDate,//'结束日期',
				dataIndex : 'endDate'
			},{
				header: Eway.locale.advert.beginTime,//'开始时间',
				dataIndex : 'beginTime'
			},{
				header: Eway.locale.advert.endTime,//'结束时间',
				dataIndex : 'endTime'
			},{
				header: Eway.locale.advert.fileSize,//'资源大小',
				dataIndex : 'fileSize'
			},{
				header: Eway.locale.advert.content,//'播放资源内容',
				dataIndex : 'content',
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