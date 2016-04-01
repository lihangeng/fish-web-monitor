Ext.define('Eway.view.parameter.devParameter.field.ParamClassify', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.devParameter_field_ParamClassify',

	name : 'ClassifyId',
	hiddenName : 'ClassifyId',
	store: 'parameter.devParameter.ParamClassify',
	valueField : 'id',
	displayField : 'name',
//	queryMode : 'local',
	editable : false,
	emptyText: EwayLocale.combox.select

});

