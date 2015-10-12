Ext.define("Eway.view.machine.device.SpInfo", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_spinfo',
	
	autoScroll : true,
	
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%' 
			}
		});
		this.callParent(arguments);
	}
});