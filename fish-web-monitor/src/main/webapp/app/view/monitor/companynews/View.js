Ext.define('Eway.view.monitor.companynews.View',{
	extend : 'Eway.view.base.Panel',
	alias : 'widget.monitor_companynews_view',

	requires : ['Eway.view.monitor.companynews.News','Eway.view.monitor.companynews.FilterForm'],

	 title: '6666666',
	 layout: 'border',
	 
	 initComponent:function(){
		 var store = Ext.create('Eway.store.monitor.companynews.News');
		 Ext.apply(this,{
			 title:'001设备加钞清机信息详情',
			 items:[{
				 height:'80px',
				 region:'north',
				 xtype:'monitor_companynews_filterform',
//				 bodyStyle:'background:#edefef;',
			 },{
				 autoScroll : true,
				 name : 'time_line',
				 itemId : 'timeview',
				 region : 'center',
				 xtype : 'companynews_News',
				 store:store
			}]
		 });
		 this.callParent(arguments);
	 }
});

//	
