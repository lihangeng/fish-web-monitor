
Ext.define('Eway.store.case.DevMod', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'IDC', display:EwayLocale.cases.caseFault.cardReaderModule},
	       {value:'CIM', display:EwayLocale.cases.caseFault.depoistModule},
		   {value:'CDM', display:EwayLocale.cases.caseFault.drawModule},
		   {value:'RPR', display:EwayLocale.cases.caseFault.rprModule},
		   {value:'JPR', display:EwayLocale.cases.caseFault.jprModule},
		   {value:'PIN', display:EwayLocale.cases.caseFault.pinModule},
		   {value:'TTU', display:EwayLocale.cases.caseFault.textTerminalUnit},
		   {value:'SIU', display:EwayLocale.cases.caseFault.sensoModule},
           {value:'CAM', display:EwayLocale.cases.caseFault.camModule},
           {value:'BCR', display:EwayLocale.cases.caseFault.bcrModule}]


});
