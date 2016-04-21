Ext.define('Eway.view.parameter.devParameter.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_devParameter_view',

	requires : [ 'Eway.view.parameter.devParameter.DevGrid',
	             'Eway.view.parameter.devParameter.ParamGrid',
	             'Eway.view.parameter.devParameter.DevFilterForm',
	             'Eway.view.parameter.devParameter.ParamFilterForm'],
	    
	title : EwayLocale.param.deviceParam.title,
	layout : 'border',
	initComponent : function() {
		 
		Ext.apply(this, {
					items : [{
						region: 'north',
						layout:'border',
						split:true,
						collapsible: true,
						height:'45%',
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
						name:'paramPanel',
						xtype:'tabpanel',
						tabPosition : 'left',
						headerPosition: 'left',
						plain:false,
					    activeTab: 0,
					    tabRotation:0
					}],
					listeners: {
				       'beforerender':function( _this, eOpts){
				    	   Ext.Ajax.request({
				   			method:'GET',
				   			url:'api/parameter/devParameter/getAppData',
				   			success:function(response){
				   				if(response.responseText){
				   					var appData=Ext.decode(response.responseText);
					   				for(var i=0;i<appData.total;i++){
					   					var tab=Ext.create('Eway.view.parameter.devParameter.ParamView',{appType:appData.data[i].id});
					   					_this.down("tabpanel").add(tab);
					   					tab.setTitle(appData.data[i].name);
					   				}
					   				_this.down("tabpanel").setActiveItem(0);
				   				}
				   			}
				   		});
				       }
				    }
		});

		this.callParent(arguments);
	}
	
});

