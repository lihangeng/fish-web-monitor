
Ext.define('Eway.store.person.person.GenderFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:EwayLocale.commen.all}, 
		   {value:'MALE', display:EwayLocale.commen.comboxGender.male}, 
	       {value:'FEMALE', display:EwayLocale.commen.comboxGender.female}]
});
