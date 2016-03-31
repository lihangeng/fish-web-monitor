
Ext.define('Eway.store.parameter.element.ParamType', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'1', display:'整型'},
	       {value:'2', display:'字符型'},
	       {value:'3', display:'布尔型'},
	       {value:'4', display:'IP地址型'}]
});
