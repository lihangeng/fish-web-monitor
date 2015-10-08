Ext.define('Eway.view.field.person.PersonJobComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_person_personJobComboBox',
	fieldLabel : Eway.locale.person.bankPer.personJobName,
	name : 'personJobCode',
	msgTarget : 'side',
	store : 'person.person.PersonJob',
	valueField : 'code',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : Eway.locale.combox.select,
	listeners : {
		beforerender : function() {
			this.store.load();
		},
		change : function(text, newValue, oldValue) {
			if (newValue && newValue !== "") {
				text.getTrigger("clear").show();
			} else {
				text.getTrigger("clear").hide();
			}
		}
	}
});