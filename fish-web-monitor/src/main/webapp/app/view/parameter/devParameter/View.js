Ext.define('Eway.view.parameter.devParameter.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_devParameter_view',

	requires : [ 'Eway.view.parameter.devParameter.DevGrid',
	             'Eway.view.parameter.devParameter.ParamGrid',
	             'Eway.view.parameter.devParameter.DevFilterForm',
	             'Eway.view.parameter.devParameter.ParamFilterForm'],
	    
	title : '设备参数管理',
	layout : 'border',
	initComponent : function() {
		 
		Ext.apply(this, {
					items : [{
						region: 'west',
						layout:'border',
						width:'55%',
//						plain:true,
						//split:true,
						collapsible:true,
						items:[{
							region:'north',
							xtype:'parameter_devParameter_devFilterForm'
						},{
							region:'center',
							xtype:'parameter_devParameter_devGrid'
						}]
					},{
						region: 'center',
						layout:'border',
						name:'groupPanel',
						xtype:'tabpanel',
						tabPosition : 'left',
						headerPosition: 'left',
						plain:true,
					    activeTab: 0,
					    tabRotation:0
					    
					}],
					listeners: {
				       'render':function( _this, eOpts){
				    	   Ext.Ajax.request({
				   			method:'GET',
				   			url:'api/parameter/devParameter/getAppData',
				   			success:function(response){
				   				var appData=Ext.decode(response.responseText);
				   				for(var i=0;i<appData.total;i++){
				   					var param=Ext.create('Eway.view.parameter.devParameter.ParamView',{appType:appData.data[i].id});
				   					_this.down("tabpanel").add(param);
				   					param.setTitle(appData.data[i].name);
				   				}
				   			}
				   		});
				       }
				    }
		});

		this.callParent(arguments);
	}
	
//	getData:function(){
//		Ext.Ajax.request({
//			method:'GET',
//			url:'api/parameter/appSystem',
//			success:function(response){
//				var appData=Ext.decode(response.responseText);
//				console.log(appData);
//			}
//		});
		
//		console.log('111111');
//	}
	
});

