Ext.define('Eway.store.version.JobStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',

	data: [{value:'NEW', display:Eway.locale.version.View.job.newCreate}, 
	       {value:'RUN', display:Eway.locale.version.View.job.running}, 
	       {value:'SCHEDULER', display:Eway.locale.version.View.job.scheduler},
	       {value:'READY_RUN', display:Eway.locale.version.View.job.ready},
	       {value:'PAUSE', display:Eway.locale.version.View.job.pause},
	       {value:'COMPLETE', display:Eway.locale.version.View.job.complete}]
});
