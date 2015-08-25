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
			fieldLabel : '描述',
			anchor : '100%',
			name : 'description'
		},{
			xtype : 'textareafield',
			fieldLabel : '建议解决方案',
			anchor : '100%',
			name : 'solution'
		}];
		this.callParent(arguments);
	}

})