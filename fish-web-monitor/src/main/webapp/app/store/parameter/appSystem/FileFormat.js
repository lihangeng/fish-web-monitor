Ext.define('Eway.store.parameter.appSystem.FileFormat',{
	extend :'Ext.data.Store',
	
	model:'Eway.model.Dict',
	
	data:[{value:'0',display:'JSON'},
	      {value:'1',display:'PROPERTIES'},
	      {value:'2',display:'INI'},
	      {value:'3',display:'XML'}]
});



