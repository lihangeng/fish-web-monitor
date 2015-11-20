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
			fieldLabel : EwayLocale.commen.description,
			anchor : '100%',
			readOnly : true,
			name : 'description'
		},{
			xtype : 'textareafield',
			fieldLabel : EwayLocale.monitor.devMonitor.solution,
			anchor : '100%',
			readOnly : true,
			name : 'solution'
		}];
		this.callParent(arguments);
	}

})