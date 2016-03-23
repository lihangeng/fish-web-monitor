Ext.define('Eway.view.parameter.appSystem.field.FileFormCombox',{
	extend :'Ext.form.field.ComboBox',
	alias :'widget.field_appSystem_fileForm',
	name :'configForm',
	hiddenName:'configForm',
	store:'parameter.FileForm',
	valueField :'value',
	displayField:'display',
	queryMode:'local',
	editable :false,
	emptyText : EwayLocale.combox.select
});


