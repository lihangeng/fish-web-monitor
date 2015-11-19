Ext.define('Eway.view.field.report.CountChooseComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.report_CountChooseComboBox',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.report.baseReport.rank,
	name : 'rank',
	hiddenName : 'rank',
	msgTarget : 'side',
	store :  Ext.create('Ext.data.Store', {
	     model : Ext.define('rank', {
				     extend: 'Ext.data.Model'
				 }),
	     data : [
	         {display: '5',    value: '5'},
	         {display: '10',   value: '10'},
	         {display: '20',   value: '20'}
	     ]
 	}),
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});