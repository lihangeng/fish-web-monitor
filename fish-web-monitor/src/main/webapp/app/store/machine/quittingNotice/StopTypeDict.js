
Ext.define('Eway.store.machine.quittingNotice.StopTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'放假'}, 
	       {value:'1', display:'装修'}, 
	       {value:'2', display:'停电'}, 
	       {value:'3', display:'设备故障未修复'}, 
	       {value:'4', display:'其它'}]
});
