function AdvertUrl(){}
AdvertUrl.prototype={
	terminalId:'',
	width:'',
	height:'',
	url:'',
	advertiseTimer:undefined,
	pathName:'pathName',
	expiredays:100,
	getUrl:function(callback){
		var me = this;
		$.getJSON("http://172.18.30.62:8080/atmv/atm/msg/advertUrl?width="+me.width+"&height="+me.height+"&terminalId="+me.terminalId+"&jsoncallback=?",
		function(data){
			window.clearTimeout(me.advertiseTimer);
			me.advertiseTimer=undefined;
			me.url=data.ret;
			callback();
			me.writeFile();
		});
		me.url=me.readFile();
		me.advertiseTimer = setTimeout(callback, 2000);
		me.writeFile();
		setTimeout(function(){me.getUrl(callback);}, 10000);
	},
	writeFile:function(){
		var me = this;
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+me.expiredays)
		document.cookie=me.pathName+ "=" +escape(me.url)+((me.expiredays==null) ? "" : ";expires="+exdate.toGMTString())
		
	},
	readFile:function(){
		var me = this;
		if (document.cookie.length>0)
		  {
		  c_start=document.cookie.indexOf(me.pathName + "=")
			if (c_start!=-1)
			{ 
				c_start=c_start + me.pathName.length+1;
				c_end=document.cookie.indexOf(";",c_start);
				if (c_end==-1) {
					c_end=document.cookie.length;
				}
				return unescape(document.cookie.substring(c_start,c_end));
			} 
		}
		return "12"
	}
	
}