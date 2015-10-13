Ext.define('Eway.store.monitor.device.FilterIngItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : Eway.locale.commen.all,
			value : '0'
		},{
			name : Eway.locale.machine.atmGroup.comboxAwayFlag.inBank,
			value : '1'
		},{
			name : Eway.locale.machine.atmGroup.comboxAwayFlag.outBank,
			value : '2'
		},{
			name : Eway.locale.machine.atmGroup.comboxAwayFlag.clickBank,
			value : '3'
		}]
});