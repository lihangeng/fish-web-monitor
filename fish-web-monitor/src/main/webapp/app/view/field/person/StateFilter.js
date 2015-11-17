Ext.define('Eway.view.field.person.StateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.stateFilter',
	fieldLabel : EwayLocale.commen.state,
	name : 'state',
	hiddenName : 'state',
	msgTarget : 'side',
	store: 'person.person.PersonStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});