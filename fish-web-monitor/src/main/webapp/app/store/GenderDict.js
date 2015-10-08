
Ext.define('Eway.store.GenderDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'MALE', display:Eway.locale.person.bankPer.comboxGender.male}, 
	       {value:'FEMALE', display:Eway.locale.person.bankPer.comboxGender.female}]
});
