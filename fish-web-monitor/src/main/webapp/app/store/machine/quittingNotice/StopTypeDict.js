
Ext.define('Eway.store.machine.quittingNotice.StopTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.machine.quittingNotice.comboxStopType.recess}, 
	       {value:'1', display:Eway.locale.machine.quittingNotice.comboxStopType.fit}, 
	       {value:'2', display:Eway.locale.machine.quittingNotice.comboxStopType.power}, 
	       {value:'3', display:Eway.locale.machine.quittingNotice.comboxStopType.devFailue}, 
	       {value:'4', display:Eway.locale.machine.quittingNotice.comboxStopType.other}]
});
