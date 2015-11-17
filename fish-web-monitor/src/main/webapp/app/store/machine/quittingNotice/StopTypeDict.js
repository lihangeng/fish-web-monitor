
Ext.define('Eway.store.machine.quittingNotice.StopTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.machine.quittingNotice.comboxStopType.recess}, 
	       {value:'1', display:EwayLocale.machine.quittingNotice.comboxStopType.fit}, 
	       {value:'2', display:EwayLocale.machine.quittingNotice.comboxStopType.power}, 
	       {value:'3', display:EwayLocale.machine.quittingNotice.comboxStopType.devFailue}, 
	       {value:'4', display:EwayLocale.machine.quittingNotice.comboxStopType.other}]
});
