
Ext.define('Eway.model.advert.Advert', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'advertType','advertDownMethod','advertValidity','createdTime','resources'
			 ,'versionId','versionStatus','versionFile','versionType','versionNo','userName','versionDesc'],
    proxy: {
        type: 'rest',
        url : 'api/advert',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    },
    hasMany: 'AdvertResource' //{ model: 'AdvertResource', name: 'advertResources' }
});