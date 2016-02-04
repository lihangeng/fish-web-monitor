// JavaScript Document
// AD JS
$(document).ready(function() {
	var sHerf = window.location.href;
	var width = "720";
	var height = "500";
	var div1 = $("#Layer2");
	var div2 = $("#Layer3");
	width=getArgs(sHerf,"?width=","&height=");//url.subString(url.indexOf("?width=")+7);
	height=getArgs(sHerf,"&height=","&groupPath=");
	groupPath=getArgs(sHerf,"&groupPath=");
		div1.css("width", width);
		div1.css("height", height);
		div2.css("width", width);
		div2.css("height", height);
	loadZongHangConfig(width, height,groupPath)
});


function getArgs(url,argA,argB){
	if(argB){
		return url.substring(url.indexOf(argA)+argA.length,url.indexOf(argB));
	}
	return url.substring(url.indexOf(argA)+argA.length);
}

function addResources(_resources,width,height,resourePath) {
	var resourceLength = _resources.length;
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
	$("#Layer2 img")[resourceLength-1].onload=function(){
		$("#Layer3 img").hide();
		playAdvert(_resources);
	};
}

var flag = false;
function loadZongHangConfig(width, height,groupPath) {
var resourePath = groupPath+"/AD_IDLE/"+width+"/";
	$.ajax({
		type : 'GET',
		url : resourePath+'config.json',
		dataType : 'json',
		success : function(data) {
			var jsonArrayResource = eval(data.resources);
			
			var imgLoading = $("<img/>");
			imgLoading.attr("id", "id0");
			imgLoading.css("display", "block");
			imgLoading.css("width", width);
			imgLoading.css("height", height);
			imgLoading.attr("src","defaultload.gif");
			$("#Layer3").append(imgLoading);
			

			addResources(jsonArrayResource,width, height,resourePath);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest);
			alert(errorThrown);
			alert(textStatus);
		}
	});
}

function playAdvert(jsonArrayResource){

	var imgResources = $("#Layer2>img");
	
	// 记录当前文件延时时间
	var delayTime = 0;
	// 播放文件索引
	var fileIndex = 0;
	var playNext = function() {
		// 广告页面图片播放定时器
		if (fileIndex < imgResources.length) {
			var file = imgResources.eq(fileIndex);
			var resource = jsonArrayResource[fileIndex];
			if (validateFileDisplayTime(resource)) {
				delayTime = parseInt(resource.playTime) * 1000;
				if(fileIndex==0){
					imgResources.eq(imgResources.length-1).hide();
				}
				else{
					imgResources.eq(fileIndex-1).hide();
				}
				file.show();
				
			} else {

				delayTime = 0;
			}
			fileIndex++;
		} else {

			fileIndex = 0;
			delayTime = 0;
		}
		// 清除上个定时器
		if (typeof (advertiseTimer) != 'undefined'&& advertiseTimer != null) {
			window.clearTimeout(advertiseTimer);
			advertiseTimer = null;
		}
		advertiseTimer = window.setTimeout(playNext, delayTime);
	};
	playNext();

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
			&& Date.parse(nowDate) <= Date.parse(endDate)
			&& Date.parse(nowTime) >= Date.parse(beginTime)
			&& Date.parse(nowTime) <= Date.parse(endTime)) {
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
