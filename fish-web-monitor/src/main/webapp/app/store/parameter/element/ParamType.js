
Ext.define('Eway.store.parameter.element.ParamType', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'1', display:EwayLocale.param.element.integer},
	       {value:'2', display:EwayLocale.param.element.character},
	       {value:'3', display:EwayLocale.param.element.boolean},
	       {value:'4', display:EwayLocale.param.element.ip}]
});
