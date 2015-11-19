
Ext.define('Eway.store.person.person.PersonStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:EwayLocale.commen.comboxStatus.onJob}, 
	       {value:'2', display:EwayLocale.commen.comboxStatus.onAdjust}, 
	       {value:'3', display:EwayLocale.commen.comboxStatus.onVacation}]
});