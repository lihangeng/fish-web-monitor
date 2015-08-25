Ext.define('Eway.view.field.person.StateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.stateFilter',
	fieldLabel : '状态',
	name : 'state',
	hiddenName : 'state',
	msgTarget : 'side',
	store: 'person.person.PersonStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	queryMode : 'local',
	editable : false,
	emptyText : '--请选择--'
});