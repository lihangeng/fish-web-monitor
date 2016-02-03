function AdvertUrl(){}
AdvertUrl.prototype={
	terminalId:'',
	width:'',
	height:'',
	url:'',
	getUrl:function(callback){
		var me = this;
		$.getJSON("http://172.18.30.62:8080/atmv/atm/msg/advertUrl?width="+me.width+"&height="+me.height+"&terminalId="+me.terminalId+"&jsoncallback=?",
		function(data){
		  me.url=data.ret;
		  callback(me);
		});
	}
}