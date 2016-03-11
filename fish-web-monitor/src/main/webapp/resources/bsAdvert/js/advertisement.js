// JavaScript Document
// AD JS
$().ready(
	function(){
		var defaultLoadingPicName = "loading.gif"
		var url = window.location.search;
		var width,height,terminalId;
		width=getArgs(url,"width=","height=");//url.subString(url.indexOf("?width=")+7);
		height=getArgs(url,"height=","terminalId=");
		terminalId=getArgs(url,"terminalId=");
		height = checkRate(height)?height:768;
		width=(width==1024||width==800||width==600)?width:1024;
		//加载默认图片
		var div2 = $("#Layer3");
		div2.css("width", width);
		div2.css("height", height);
		
		var imgLoading = $("<img/>");
		imgLoading.attr("id", "id0");
		imgLoading.css("display", "block");
		imgLoading.css("width", width);
		imgLoading.css("height", height);
		imgLoading.attr("src",defaultLoadingPicName);
		$("#Layer3").append(imgLoading);
		//设置广告图片大小
		var div1 = $("#Layer2");
		div1.css("width", width);
		div1.css("height", height);
		/*
		*/
		var advert = new AdvertUrl();
		advert.width = width;
		advert.height = height;
		advert.terminalId = terminalId;
		advert.getUrl(function (){
			loadUrlByAdvert(advert);
		});
	}
);

function loadUrlByAdvert(advert){
	loadZongHangConfig(advert.width, advert.height,advert.url);
}
//加载资源配置文件
function loadZongHangConfig(width, height,groupPath) {
	var resourePath = groupPath+"/AD_IDLE/"+width+"/";
	var me = this;
	$.ajax({
		type : 'GET',
		url : resourePath+'config.json?time=' +new Date().getTime(),
		dataType : 'json',
		success : function(data) {
			var jsonArrayResource = eval(data.resources);
			me.oldResource = addResources(jsonArrayResource,width, height,resourePath);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if(player==""){
				$("#Layer3").show();
				$("#Layer2").hide();
			}
			else{
				addResources(player.runArgs,width, height,resourePath);
			}
		}
	});
}

//加载图片资源
function addResources(_resources,width,height,resourePath) {
	var resourceLength = _resources.length;
	$("#Layer2").hide();
	$("#Layer3").show();
	$("#Layer2").empty();
	for (var i = 0; i < resourceLength; i++) {
		var img = $("<img/>");
		img.attr("id", "id0");
		img.attr("src", resourePath+_resources[i].content);
		img.css("width", width);
		img.css("height", height);
		img.css("display", "none");
		img.css("overflow", "hidden");
		$("#Layer2").append(img);
	}
	//资源加载完毕，默认图片隐藏,播放广告
	$("#Layer2 img")[resourceLength-1].onload=function(){
		$("#Layer3").hide();
		$("#Layer2").show();
		if(player!=""){
			player.runFlag = false;
		}
		player = playAdvert(_resources);
	};
}
//是否继续运行之前的定时任务
var player ="";


function playAdvert(jsonArrayResource){

	var advertTask = new playAdvertTask();
	advertTask.runFlag = true;
	advertTask.runArgs = jsonArrayResource;
	advertTask.running();
	return advertTask;
	

}

/** ********************************************空闲广告模块************************ */
/**
 * 
 * 验证当前需要播放的广告事件是否满足
 * 
 * @param file
 * @returns {Boolean}
 * @see [类、类#方法、类#属性]
 * @since [起始版本]
 */
function validateFileDisplayTime(file) {
	var now = new Date();
	var nowDate = now.format("yyyy/MM/dd");
	var beginDate = file.beginDate.replace(/-/g, "\/");
	var endDate = file.endDate.replace(/-/g, "\/");

	var nowTime = now.format("yyyy/MM/dd hh:mm:ss");
	var beginTime = now.format("yyyy/MM/dd") + " " + file.beginTime;
	var endTime = now.format("yyyy/MM/dd") + " " + file.endTime;

	if (Date.parse(nowDate) >= Date.parse(beginDate)
			&& (Date.parse(nowDate) <= Date.parse(endDate)||endDate=="")
			&& Date.parse(nowTime) >= Date.parse(beginTime)
			&& (Date.parse(nowTime) <= Date.parse(endTime)||endTime="")) {
		return true;
	} else {
		return false;
	}
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
 function checkRate(nubmer)
{
     var re = /^[0-9]+$/;   //判断字符串是否为数字     
     if (!re.test(nubmer))
    {
        return false;
    }
	return true;
}
function getArgs(url,argA,argB){
	if(argB){
		return url.substring(url.indexOf(argA)+argA.length,url.indexOf(argB)-1);
	}
	return url.substring(url.indexOf(argA)+argA.length);
}
