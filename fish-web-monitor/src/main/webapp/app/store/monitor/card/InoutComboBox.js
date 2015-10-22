
Ext.define('Eway.store.monitor.card.InoutComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
			{value:'1', display:Eway.locale.machine.atmGroup.comboxAwayFlag.inBank}, 
	       {value:'2', display:Eway.locale.machine.atmGroup.comboxAwayFlag.outBank},
	       {value:'3', display:Eway.locale.machine.atmGroup.comboxAwayFlag.clickBank}]
});
