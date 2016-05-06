Ext.define('Eway.view.parameter.paramMonitor.Form',{
	extend : 'Ext.window.Window',
	alias : 'widget.parameter_paramMonitor_form',
	
	requires:['Eway.view.parameter.paramMonitor.AppResultGrid'],
//	title : '任务状态详情',
	initComponent:function(){
		Ext.apply(this,{
			items:[{
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 900,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items:[{
					xtype : 'panel',
					trackResetOnLoad : true,
					selectOnFocus : true,
					layout:'column',
					width : 450,
					height: 300,
					region : 'center',
					layout : 'border',
					items:[{
						region : 'center',
						xtype : 'parameter_paramMonitor_AppResultGrid',
						autoLoadStore : true
					}]
				}]
			}]
				
		});
		this.callParent(arguments);
	}
});
