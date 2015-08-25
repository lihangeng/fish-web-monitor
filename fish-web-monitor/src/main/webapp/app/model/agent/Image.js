
Ext.define('Eway.model.agent.Image', {
	extend: 'Ext.data.Model',
	    fields: [
			        { name:'src', type:'string' }
			    ],
			        proxy: {
			        type: 'rest',
			        url : 'api/agent/screenshot/image',
			        reader: {
			            type: 'json',
			            rootProperty: 'data'
			        },
					wirter : {
						type : 'json'
					}
			    }
});