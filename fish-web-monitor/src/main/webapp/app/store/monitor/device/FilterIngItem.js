Ext.define('Eway.store.monitor.device.FilterIngItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : EwayLocale.commen.all,
			value : '0'
		},{
			name : EwayLocale.machine.atmGroup.comboxAwayFlag.inBank,
			value : '1'
		},{
			name : EwayLocale.machine.atmGroup.comboxAwayFlag.outBank,
			value : '2'
		},{
			name : EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank,
			value : '3'
		}]
});