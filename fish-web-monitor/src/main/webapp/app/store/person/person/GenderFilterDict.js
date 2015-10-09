
Ext.define('Eway.store.person.person.GenderFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.commen.allGender}, 
		   {value:'MALE', display:Eway.locale.commen.comboxGender.male}, 
	       {value:'FEMALE', display:Eway.locale.commen.comboxGender.female}]
});
