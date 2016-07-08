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
            // id, type,     date, 				time, 		author,	  group, title, paragraph
            [ 1,  "cash-time",  "12/27/2016",   "10", 		"Teresa",  "2",  "1",  "Bear."],
            [ 2,  "settle-time",  "11/27/2016",   "1",  		"Tina",    "3",  "2",  "all." ],
            [ 3,  "cash-time",  "10/27/2016",   "7",	 	"Paula",   "4",  "3",  "name." ],
            [ 4,  "settle-time",  "9/27/2016",   "8", 		"Nicole",  "5",   "4",   "stuntman"],
            [ 5,  "cash-time",  "8/27/2016",   "6", 		"James",   "7",   "5",   "boss" ],

            [ 6,  "settle-time", "7/27/2016",   "9", 		"Denise",  "2",   "6",    "Round" ],
            [ 7,  "cash-time", "6/27/2016",   "4", 		"Maria",   "4",   "7",    " Hood." ],
            [ 8,  "settle-time", "5/27/2016",   "5", 		"Jose",    "5",   "8",	 "Thunder" ],
            [ 9,  "cash-time", "4/27/2016",   "3", 		"Carl",    "6",   "9",	 "world" ],
            [ 10, "settle-time", "3/27/2016",   "6", 		"Louise",  "8",   "10",   "Onepigeon" ],
            [ 11,  "cash-time", "7/27/2016",   "9", 		"dd",  		"2",   "6",    "ww" ],
            [ 12,  "settle-time", "6/27/2016",   "4", 		"cc",   	"4",   "7",    " dd." ],
            [ 13,  "settle-time", "5/27/2016",   "5", 		"xx",    	"5",   "8",	 "ff" ],
            [ 14,  "settle-time", "4/27/2016",   "3", 		"zz",    	"6",   "9",	 "ss" ],
            [ 15, "settle-time", "3/27/2016",   "6", 		"mm",  		"8",   "10",   "aa" ]
        ]
    }
});
