Ext.define('Eway.view.monitor.device.ModuleInfoWin',{

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_moduleInfoWin',
	layout : 'fit',
	width : 400,
	height : 260,
	modal : true,
	title:Eway.locale.monitor.devMonitor.faultDescription,
	requires : ['Eway.view.monitor.device.ModuleInfoForm'],
	bodyStyle: {
	    padding: '10px'
	},
	initComponent : function(){
		this.items = [{
			xtype : 'monitor_device_moduleInfoForm',
			border:false
		}];
		this.callParent(arguments);
	},

	display : function(code,mod){
		var me = this;
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/monitor/device/getModeState',
			params : {
				terminalId : code,
				mod : mod
			},
			success : me.fillForm,
			scope : this
		});
		this.show();
	},

	fillForm : function(response){
		var object = Ext.decode(response.responseText);
		var desc = object.data.description;
		var solution = object.data.solution;
		var form = this.down('monitor_device_moduleInfoForm');
		form.down('textareafield[name="description"]').setValue(desc);
		form.down('textareafield[name="solution"]').setValue(solution);
	}

})