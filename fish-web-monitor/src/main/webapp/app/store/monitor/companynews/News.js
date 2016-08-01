Ext.define('Eway.store.monitor.companynews.News', {
    extend: 'Ext.data.Store',
    alias: 'store.news',

    model: 'Eway.model.monitor.companynews.News',
    remoteFilter: true,
    autoLoad:true,
    proxy: {
        type: 'memory',
        reader: 'array',
        data: [
          // id, 	  type,     	date, 		 time, 		author,	  group,  title,  paragraph 
            [ 1,  "cash-time",    "2016-12-17",   "10", 	"Teresa",  "2",    "1",    "Bear."],
            [ 2,  "settle-time",  "11/27/2016",   "1",  	"Tina",    "3",    "2",    "all." ],
            [ 3,  "cash-time",    "2016-12-17",   "7",	 	"Paula",   "4",    "3",    "name." ],
            [ 4,  "settle-time",  "9/27/2016",    "8", 		"Nicole",  "5",    "4",    "stuntman"],
            [ 5,  "cash-time",    "2016-12-17",   "5",	 	"Paula",   "3",    "3",    "name." ],
            [ 6,  "settle-time",  "9/27/2016",    "8", 		"Nicole",  "5",    "4",    "stuntman"]
           
        ]
    }
});
