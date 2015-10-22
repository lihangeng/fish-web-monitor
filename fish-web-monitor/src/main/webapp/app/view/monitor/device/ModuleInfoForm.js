Ext.define('Eway.view.monitor.device.ModuleInfoForm',{

	extend : 'Ext.form.Panel',
	alias : 'widget.monitor_device_moduleInfoForm',
	border : 0 ,

	bodyStyle: {
	     padding: '10px'
	},
	initComponent : function(){
		this.items = [{
			xtype : 'textareafield',
			fieldLabel : Eway.locale.commen.description,
			anchor : '100%',
			name : 'description'
		},{
			xtype : 'textareafield',
			fieldLabel : Eway.locale.monitor.devMonitor.solution,
			anchor : '100%',
			name : 'solution'
		}];
		this.callParent(arguments);
	}

})