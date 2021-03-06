Ext.define('Eway.view.personal.PersonalSettingsController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.personalSettings',
	
	requires : ['Eway.model.person.person.BankPerson'],
	
	onBeforeRender : function(form){
		Eway.model.person.person.BankPerson.load(Eway.user.getPersonId(),{
			success: function(record) {
				form.loadRecord(record);
				form.up("tabpanel").down("updatePwd [name=name]").setValue(record.get("name"));;
		    }
		});
	}

});