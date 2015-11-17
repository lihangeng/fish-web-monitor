
Ext.define('Eway.store.GenderDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'MALE', display:EwayLocale.commen.comboxGender.male}, 
	       {value:'FEMALE', display:EwayLocale.commen.comboxGender.female}]
});
