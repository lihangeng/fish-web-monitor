
Ext.define('Eway.model.advert.AdvertResource', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'advertId' ,
	         'playTime','beginDate','endDate','beginTime','endTime','content','fileSize','screen'],
    proxy: {
        type: 'rest',
        url : 'api/advert/resource',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    },
    belongsTo : 'Advert'
});