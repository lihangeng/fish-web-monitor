Ext.define('Eway.view.parameter.paramMonitor.TaskFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_paramMonitor_TaskFilterForm',
	height :50,
	
	initComponent : function(){
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
				columnWidth : .3,
				items:[{
					xtype : 'textfield',
					name : 'id',
					fieldLabel : '任务ID'
				}]	
		});
		this.callParent(arguments);
	}
});