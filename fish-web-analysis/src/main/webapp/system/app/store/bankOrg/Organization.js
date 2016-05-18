Ext.define('Eway.store.bankOrg.Organization', {
	extend: 'Ext.data.Store',
	model: 'Eway.model.bankOrg.Organization',
	autoSync : false,
	autoLoad: true,
	remoteFilter: true,//在服务器端过滤，默认是false
	data: [{id:1,guid:"1",code:"ABC",name:"农总行",displayName:"农总行",zip:"1111111"},
	       {id:2,guid:"2",code:"ABC2",name:"农总行2",displayName:"农总行2",zip:"2222222"},
	       {id:3,guid:"3",code:"ABC3",name:"农总行3",displayName:"农总行3",zip:"3333333"},
	       {id:4,guid:"4",code:"ABC4",name:"农总行4",displayName:"农总行4",zip:"4444444"},
	       {id:5,guid:"5",code:"ABC5",name:"农总行5",displayName:"农总行5",zip:"5555555"},
	       {id:6,guid:"6",code:"ABC6",name:"农总行6",displayName:"农总行6",zip:"6666666"},
	       {id:7,guid:"7",code:"ABC7",name:"农总行7",displayName:"农总行7",zip:"7777777"},
	       {id:8,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:9,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:10,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:11,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:12,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:13,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:14,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:15,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:16,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:17,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:18,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:19,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"},
	       {id:20,guid:"8",code:"ABC8",name:"农总行8",displayName:"农总行8",zip:"8888888"}
	       ]
});