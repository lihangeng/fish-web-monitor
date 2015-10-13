
Ext.define('Eway.store.monitor.card.CardTypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:Eway.locale.monitor.business.card.idCard}, 
	       {value:'2', display:Eway.locale.monitor.business.card.accountPaper},
	       {value:'3', display:Eway.locale.monitor.business.card.drivePaper},
	       {value:'4', display:Eway.locale.monitor.business.card.passport},
	       {value:'5', display:Eway.locale.monitor.business.card.officer},
	       {value:'6', display:Eway.locale.monitor.business.card.soldier},
	       {value:'7', display:Eway.locale.monitor.business.card.busnessPaper},
	       {value:'8', display:Eway.locale.monitor.business.card.busnessCode},
	       {value:'9', display:Eway.locale.monitor.business.card.taxPaper}]
});
