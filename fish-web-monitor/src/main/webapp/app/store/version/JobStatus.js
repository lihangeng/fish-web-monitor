Ext.define('Eway.store.version.JobStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',

	data: [{value:'NEW', display:EwayLocale.version.View.job.newCreate}, 
	       {value:'RUN', display:EwayLocale.version.View.job.running}, 
	       {value:'SCHEDULER', display:EwayLocale.version.View.job.scheduler},
	       {value:'READY_RUN', display:EwayLocale.version.View.job.ready},
	       {value:'PAUSE', display:EwayLocale.version.View.job.pause},
	       {value:'COMPLETE', display:EwayLocale.version.View.job.complete}]
});
