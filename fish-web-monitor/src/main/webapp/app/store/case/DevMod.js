
Ext.define('Eway.store.case.DevMod', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',
	
	data: [{value:'IDC', display:Eway.locale.cases.caseFault.cardReaderModule}, 
	       {value:'CIM', display:Eway.locale.cases.caseFault.depoistModule},
		   {value:'CDM', display:Eway.locale.cases.caseFault.drawModule},
		   {value:'RPR', display:Eway.locale.cases.caseFault.rprModule},
		   {value:'JPR', display:Eway.locale.cases.caseFault.jprModule},
		   {value:'PIN', display:Eway.locale.cases.caseFault.pinModule},
		   {value:'TTU', display:Eway.locale.cases.caseFault.textTerminalUnit},
		   {value:'SIU', display:Eway.locale.cases.caseFault.sensoModule}]

});
