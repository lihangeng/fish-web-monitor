Ext.define('Eway.view.version.field.JobType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.jobType',
	
	fieldLabel : '作业类型',
	name : 'jobType',
	hiddenName : 'jobType',
	store: 'JobType',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});