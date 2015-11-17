
Ext.define('Eway.store.monitor.card.CardTypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:EwayLocale.monitor.business.card.idCard}, 
	       {value:'2', display:EwayLocale.monitor.business.card.accountPaper},
	       {value:'3', display:EwayLocale.monitor.business.card.drivePaper},
	       {value:'4', display:EwayLocale.monitor.business.card.passport},
	       {value:'5', display:EwayLocale.monitor.business.card.officer},
	       {value:'6', display:EwayLocale.monitor.business.card.soldier},
	       {value:'7', display:EwayLocale.monitor.business.card.busnessPaper},
	       {value:'8', display:EwayLocale.monitor.business.card.busnessCode},
	       {value:'9', display:EwayLocale.monitor.business.card.taxPaper}]
});
