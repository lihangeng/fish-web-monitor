Ext.define('Eway.view.monitor.charts.MonitorDeviceDetailWindow',{

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_detail',
	maximized:true,
    height: 350, 
	width:500,
	modal : true,
	requires : ['Eway.view.monitor.charts.MonitorDeviceGrid'],
	initComponent : function(){
		Ext.apply(this, {
			layout:'border',
		    items:[{
		    	xtype : 'monitor_device_grid',
				region: 'center'
		    }]
		});
		this.callParent(arguments);
	}
});