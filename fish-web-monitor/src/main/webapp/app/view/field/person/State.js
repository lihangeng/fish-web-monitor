
Ext.define('Eway.view.field.person.State', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.state',

	fieldLabel : Eway.locale.person.bankPer.state,
	name : 'state',
	hiddenName : 'state',
	msgTarget : 'side',
	store: 'person.person.PersonStateDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	clearClick: function(){
		this.setValue("");
	}

});