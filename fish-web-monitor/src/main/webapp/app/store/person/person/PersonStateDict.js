
Ext.define('Eway.store.person.person.PersonStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:Eway.locale.person.bankPer.comboxStatus.onJob}, 
	       {value:'2', display:Eway.locale.person.bankPer.comboxStatus.onAdjust}, 
	       {value:'3', display:Eway.locale.person.bankPer.comboxStatus.onVacation}, 
	       {value:'0', display:Eway.locale.person.bankPer.comboxStatus.other}]
});