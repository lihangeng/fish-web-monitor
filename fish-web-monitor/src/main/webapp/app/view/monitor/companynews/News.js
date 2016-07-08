Ext.define('Eway.view.monitor.companynews.News', {
    extend: 'Ext.view.View',

    alias:'widget.companynews_News',
    
    requires : [
        		'Eway.controller.monitor.timeline.ShowBoxDetail',
            	'Ext.ux.BoxReorderer',
            	'Ext.ux.DataView.Animated'
        	],
        	
        	screen : "",
    initComponent : function() {
    	var me = this;
		var timeTpl = new Ext.XTemplate(
				'<tpl for=".">',
			        '<div class="text-wrapper">' ,
			            '<div class="news-icon "><div class="{type}">&nbsp;&nbsp;2222</div></div>',
//			            '<div style="margin:15px 0 8px 18px;font-size:18px">周期ID为：8  的加钞清机信息详情</div>',
	                    '<div class="cash-content"><p style="font-size:20px;text-align:center;color:#0a0295;font-weight:bold;">加钞信息详情</p>',
	                    	'<div style="padding-left:30px;font-size:16px;line-height:22px;">加钞日期：2016-07-01 12:09:01<br/>加钞金额：10000<br/>',
	                    	'<a href="javascript:;" onclick="javascript:Eway.controller.monitor.timeline.ShowBoxDetail.showCashBox();">点击查看各钞箱金额</a></div>',
	                    '</div>',
	                    '<div class="settle-cash"><p style="font-size:20px;text-align:center;color:#0a0295;font-weight:bold;">清机信息详情</p>',
	                    	'<div style="padding-left:40px;font-size:16px;line-height:20px;">结账日期：2016-06-01 12:09:01<br/>尾箱余额：10000<br/>交易总笔数：100<br/>交易总金额：10000',
	                    	'<br/><a href="javascript:;" onclick="javascript:Eway.controller.monitor.timeline.ShowBoxDetail.showSettleCash();">点击查看各钞箱金额</a></div>',
	                    '</div>',
	                    '<div class="news-toggle expand" {expanded}><span>EXPAND</span>',
	                    '<img src="resources/images/timeline/expand-news.png"></div>',
			          '</div>' ,
		        '</tpl>'
		);
		var storeNews = Ext.create("Eway.store.monitor.companynews.News");
		Ext.apply(this, {
			autoScroll : true,
			// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
			// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
//			style : 'overflow:auto;',
			frame : true,
			store : storeNews,
			tpl : timeTpl,
			multiSelect : false,
			trackOver : true,
			overItemCls : 'x-item-over',
			itemSelector : 'div.thumb-wrap',
			emptyText : '1111',
			loadData : function(store){
				var myStore = this.getStore();
				var records = [];
				store.each(function(record){
					records[records.length] = record;
				});
				myStore.loadRecords(records);
			}
		});
		this.loadData(storeNews);
		this.callParent(arguments);

    },
    
    onReload : function() {
		this.getStore().load();
	}
	
});
