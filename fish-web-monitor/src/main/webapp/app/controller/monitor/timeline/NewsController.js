Ext.define('Eway.controller.monitor.timeline.NewsController', {
    extend: 'Eway.controller.base.FishController',//'Ext.app.ViewController',
    
    stores :['Eway.store.monitor.companynews.News'],
	models :['Eway.model.monitor.companynews.News'],
	views  :['Eway.view.monitor.companynews.View','Eway.view.monitor.companynews.News'],
	
	
	refs :[{
		ref:'ewayView',
		selector:'#monitor_companynews_view',
		xtype :'monitor_companynews_view',
		autoCreate:true,
		id:'monitor_companynews_view'
	},{
		ref:'newsView',
		selector:'companynews_News'
	}],
	
	init : function(){
		this.control({
//			'#parameter_appSystem_view button[action=query]' : {
//				click :this.onQuery
//			},
			'#monitor_companynews_view companynews_News' : {
				click :this.onUpdate
			}
		});
	},
	onUpdate:function(){
		alert('aaaaaa');
	}

});
