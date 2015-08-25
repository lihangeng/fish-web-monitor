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
				header : '广告播放时长',
				dataIndex : 'playTime'
			},{
				header: '开始日期',
				dataIndex : 'beginDate'
			},{
				header: '结束日期',
				dataIndex : 'endDate'
			},{
				header: '开始时间',
				dataIndex : 'beginTime'
			},{
				header: '结束时间',
				dataIndex : 'endTime'
			},{
				header: '资源大小',
				dataIndex : 'fileSize'
			},{
				header: '播放资源内容',
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