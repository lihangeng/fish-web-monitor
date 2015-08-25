
Ext.define('Eway.view.field.person.State', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.state',

	fieldLabel : '状态',
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