
Ext.define('Eway.store.person.person.PersonStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:Eway.locale.commen.comboxStatus.onJob}, 
	       {value:'2', display:Eway.locale.commen.comboxStatus.onAdjust}, 
	       {value:'3', display:Eway.locale.commen.comboxStatus.onVacation}, 
	       {value:'0', display:Eway.locale.commen.comboxStatus.other}]
});