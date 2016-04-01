Ext.define('Eway.view.parameter.appSystem.View',{
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_appSystem_view',

	requires : ['Eway.view.parameter.appSystem.Grid',
	            'Eway.view.parameter.appSystem.FilterForm'],

	 title: EwayLocale.param.application.title,
	 layout: 'border',

	 initComponent:function(){
		 Ext.apply(this,{
			 items:[{
				 region : 'north',
				 xtype : 'parameter_appSystem_filterForm'
			 },{
				 region : 'center',
				 xtype : 'parameter_appSystem_grid'
			 }]
		 });
		 this.callParent(arguments);
	 }
});