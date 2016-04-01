
Ext.define('Eway.store.parameter.element.ParamBelongs', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'1', display:EwayLocale.param.element.ATMC},
	       {value:'2', display:EwayLocale.param.element.monitoringCient}]
});
