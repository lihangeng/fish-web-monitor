
Ext.define('Eway.store.person.person.GenderFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.person.bankPer.allGender}, 
		   {value:'MALE', display:Eway.locale.person.bankPer.comboxGender.male}, 
	       {value:'FEMALE', display:Eway.locale.person.bankPer.comboxGender.female}]
});
