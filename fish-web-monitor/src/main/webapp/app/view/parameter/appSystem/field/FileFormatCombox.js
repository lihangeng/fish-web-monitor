Ext.define('Eway.view.parameter.appSystem.field.FileFormatCombox',{
	extend :'Ext.form.field.ComboBox',
	alias :'widget.field_appSystem_fileFormat',
	name :'configForm',
	hiddenName:'configForm',
	store:'parameter.FileFormat',
	valueField :'value',
	displayField:'display',
	queryMode:'local',
	editable :false,
	emptyText : EwayLocale.combox.select
});


