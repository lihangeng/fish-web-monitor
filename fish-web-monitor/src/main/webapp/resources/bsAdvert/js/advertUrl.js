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
		//异域加载json数据请求当前设备的广告资源地址
		$.getJSON("http://172.18.30.62:8080/atmv/atm/msg/advertUrl?width="+me.width+"&height="+me.height+"&terminalId="+me.terminalId+"&jsoncallback=?",
		function(data){
			//异域加载资源成功清理播放历史任务
			window.clearTimeout(me.advertiseTimer);
			me.advertiseTimer=undefined;
			me.url=data.ret;
			//执行回调函数
			callback();
			me.writeFile();
		});
		//读取cookie中播放地址
		me.url=me.readFile();
		//如果两秒内异域加载异常或超时，直接执行历史资源播放
		me.advertiseTimer = setTimeout(callback, 2000);
		me.writeFile();
		//刷新广告资源间隔时间
		setTimeout(function(){me.getUrl(callback);}, 10000);
	},
	//将请求的得到的路径进行缓存（cookie）
	writeFile:function(){
		var me = this;
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+me.expiredays)
		document.cookie=me.pathName+ "=" +escape(me.url)+((me.expiredays==null) ? "" : ";expires="+exdate.toGMTString())
		
	},
	//读取cookie中信息
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
		return "1 1"
	}
	
}