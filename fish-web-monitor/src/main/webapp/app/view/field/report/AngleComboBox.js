Ext.define('Eway.view.field.report.AngleComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.report_AngleComboBox',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.report.baseReport.angle,
	name : 'angle',
	hiddenName : 'angle',
	msgTarget : 'side',
	store :  Ext.create('Ext.data.Store', {
	     model : Ext.define('angle', {
				     extend: 'Ext.data.Model'
				 }),
	     data : [
	         {display: EwayLocale.report.baseReport.device,    value: '1'},
	         {display: EwayLocale.report.baseReport.vendor,    value: '2'},
	         {display: EwayLocale.report.baseReport.devType,    value: '3'},
	         {display: EwayLocale.report.baseReport.mod,    value: '4'}
	     ]
 	}),
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});