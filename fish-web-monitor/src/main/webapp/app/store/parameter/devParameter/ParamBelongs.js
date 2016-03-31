
Ext.define('Eway.store.parameter.devParameter.ParamBelongs', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'ATMC', display:'ATMC系统'},
	       {value:'Agent', display:'监控客户端'}]
});
