
Ext.define('Eway.store.parameter.element.ParamRights', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'1', display:EwayLocale.param.element.editable},
	       {value:'2', display:EwayLocale.param.element.uneditable}]
});
