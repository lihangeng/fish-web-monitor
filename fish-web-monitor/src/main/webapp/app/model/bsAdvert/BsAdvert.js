
Ext.define('Eway.model.bsAdvert.BsAdvert', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'groupId','groupName','advertName','actived','lastTime','advertFileName'
			 ,'personId','personName','activePersonId','activePersonName'],
    proxy: {
        type: 'rest',
        url : 'api/bsadvert/advert',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    },
    hasMany: 'BsAdvertResource' //{ model: 'AdvertResource', name: 'advertResources' }
});