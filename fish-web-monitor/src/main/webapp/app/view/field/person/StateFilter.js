Ext.define('Eway.view.field.person.StateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.stateFilter',
	fieldLabel : Eway.locale.person.bankPer.state,
	name : 'state',
	hiddenName : 'state',
	msgTarget : 'side',
	store: 'person.person.PersonStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	queryMode : 'local',
	editable : false,
	emptyText : Eway.locale.combox.select
});