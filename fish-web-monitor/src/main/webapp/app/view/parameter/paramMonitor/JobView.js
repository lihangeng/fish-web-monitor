Ext.define('Eway.view.parameter.paramMonitor.JobView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.parameter_paramMonitor_JobView',

	requires : [ 'Eway.view.parameter.paramMonitor.JobFilterForm',
			     'Eway.view.parameter.paramMonitor.JobGrid' ],
	
	layout:'border',
	closable:false,
	initComponent:function(){
		
		Ext.apply(this,{
			 items:[{
				 region : 'north',
				 xtype : 'parameter_paramMonitor_JobFilterForm'
			 },{
				 region : 'center',
				 xtype : 'parameter_paramMonitor_JobGrid'
			 }],
			 listeners:{
				 activate:function( _this, eOpts ){
					 var store = _this.down("parameter_paramMonitor_JobGrid").getStore();
					 store.load();
				 }
			 }
		 });
		this.callParent(arguments);
	}
});