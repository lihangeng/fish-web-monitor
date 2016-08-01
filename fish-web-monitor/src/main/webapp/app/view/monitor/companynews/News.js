Ext.define('Eway.view.monitor.companynews.News', {
    extend: 'Ext.view.View',

    alias:'widget.companynews_News',
    
    requires : [
        		'Eway.controller.monitor.timeline.ShowBoxDetail',
            	'Ext.ux.BoxReorderer',
            	'Ext.ux.DataView.Animated',
            	'Ext.grid.plugin.RowExpander'
        	],
        	
//	screen : "",
	
//	viewConfig: {
//	    listeners: {
//	        itemclick: 'onCompanyClick',
//	        expandbody: 'onCompanyExpandBody',
//	        collapsebody: 'onCompanyCollapseBody'
//	    }
//	},
//	
//	plugins: [{
//	    ptype: 'ux-rowexpander',
//	    pluginId: 'rowexpander'
//	}],
            
    initComponent : function() {
    	var me = this;
		var timeTpl = new Ext.XTemplate(
			'<tpl for=".">',
		        '<div class="text-wrapper">',
		        	'<div style="height:1px;width:90%;border: 1px solid #c0c0cc;float:right;margin-top:-10px;"></div>',
		            '<div class="news-icon"><div class="{type}">{date}</div></div>',
		            '<div style="height:45px;margin-top:30px">',
		            	'<div style="font-size:20px;margin:0 0 0 130px;line-height:17px;float:left;">加钞信息详情&nbsp;&nbsp;&nbsp;加钞金额 : {author}</div>',
		            	'<div style="font-size:20px;margin:0 0 0 600px;line-height:17px;">清机信息详情&nbsp;&nbsp;&nbsp; 尾箱金额 : {group}</div>',
			            	'<div class="news-toggle-cash" onclick="javascript:Eway.controller.monitor.timeline.ShowBoxDetail.expandCashDetails(this);">',
		                    '<span>EXPAND</span></div>',
		            '</div>',
                    
		            '<div class="detailsOneHide">',
			            '<div class="cash-content">',
	                	'<div style="padding-left:30px;font-size:16px;"><span style="line-height:100px;">加钞日期：2016-07-01 12:09:01</span></div>',
	                	'</div>',
	                	
	                	'<div class="settle-cash">',
                    	'<div style="padding:15px 0 0 30px;font-size:16px;line-height:25px;"><font style="text-align:center;">结账日期 : 2016-06-01 12:09:01</font><br/>',
                    	'<div style="float:left">存款笔数 : 89<br/>取款笔数 : 69<br/>交易总笔数 : 100</div>',
                    	'<div style="padding-left:170px">存款金额  : 20000<br/>取款金额  : 5600<br/>交易总金额 : 10000</div></div>',
                    	'</div>',
                    	
                    	'<div class="news-toggle" onclick="javascript:Eway.controller.monitor.timeline.ShowBoxDetail.expandBoxDetails(this);">',
                        '<span>钞箱详情</span></div>',
		            '</div>',
		            
                    '<div class="detailsTwoHide">',
		            	'<div style="margin-left:120px;float:left"><table border="1" cellspacing="0" style="width:320px;">',
		            	'<tr style="background:lightgrey;font-size:16px;height:40px;"><th colspan="3">加钞-钞箱信息详情</th></tr>',
		            	'<tr style="font-size:16px;background:lightgrey;height:35px;"><th>钞箱ID</th><th>币种</th><th>初始金额</th></tr>',
		            	'<tpl for=".">',
		            	'<tr style="font-size:16px;text-align:center;height:35px;"><td>{time}</td><td>{group}</td><td>{title}</td></tr>',
		            	'</tpl>',
		            	'</table></div>',
	            	
		            	'<div style="margin-left:620px;"><table border="1" cellspacing="0" style="width:320px;">',
		            	'<tr style="background:lightgrey;font-size:16px;height:40px;"><th colspan="3">清机-钞箱信息详情</th></tr>',
		            	'<tr style="font-size:16px;background:lightgrey;height:35px;"><th>钞箱ID</th><th>币种</th><th>初始金额</th></tr>',
		            	'<tpl for=".">',
		            	'<tr style="font-size:16px;text-align:center;height:35px;"><td>{time}</td><td>{group}</td><td>{title}</td></tr>',
		            	'</tpl>',
		            	'</table></div>',
	            	'</div>',
		          '</div>' ,
	        '</tpl>'
		);
		var storeNews = Ext.create("Eway.store.monitor.companynews.News");
		Ext.apply(this, {
			autoScroll : true,
			// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
			// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
			style : 'overflow:auto;',
			frame : true,
			store : storeNews,
			tpl : timeTpl,
			multiSelect : false,
			trackOver : true,
//			overItemCls : 'x-item-over',
			itemSelector : 'div.thumb-wrap',
//			emptyText : '1111',
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
