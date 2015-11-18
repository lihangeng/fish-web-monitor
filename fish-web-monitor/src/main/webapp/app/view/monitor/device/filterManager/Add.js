Ext.define('Eway.view.monitor.device.filterManager.Add',{

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_filtermanager_add',
	layout : 'border',
	
	width : 800,
	height : 600,
	modal : true,
	
	title : EwayLocale.monitor.devMonitor.filterManager.add,
	
	requires : [ 'Eway.view.monitor.device.filterManager.FilterConfig',
				  'Eway.view.monitor.device.filterManager.MonitorStateConfig' ],
  	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items: [ {
				region: 'north',
				xtype: 'monitor_device_filtermanager_filterconfig'
			}, {
				region: 'center',
				xtype: 'monitor_device_filtermanager_stateConfig'
			} ],
			buttonAlign : 'center',
			buttons : [ {
				xtype : 'button',
				text : EwayLocale.button.confirm,
				action : 'confirm'
			}, {
				xtype : 'button',
				text : EwayLocale.button.cancle,
				handler : this.onOver,
				scope : this
			} ],
			listeners : {
				scope : this
			}
		});
		this.callParent(arguments);
	},
	
	onOver : function() {
		this.close();
	}
});