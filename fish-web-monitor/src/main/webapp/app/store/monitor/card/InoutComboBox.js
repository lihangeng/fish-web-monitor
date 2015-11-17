
Ext.define('Eway.store.monitor.card.InoutComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
			{value:'1', display:EwayLocale.machine.atmGroup.comboxAwayFlag.inBank}, 
	       {value:'2', display:EwayLocale.machine.atmGroup.comboxAwayFlag.outBank},
	       {value:'3', display:EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank}]
});
