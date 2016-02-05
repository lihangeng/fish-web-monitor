function playAdvertTask(){}
playAdvertTask.prototype={
	runFlag:true,
	runArgs:'',
	fileIndex:0,
	advertiseTimer:undefined,
	getInstance:function(){
		return this;
	},
	running:function(me){
		var me = this;
		var jsonArrayResource = me.runArgs;
		var delayTime = 0;
		//获取广告图片信息
		var imgResources = $("#Layer2>img");
		if (me.fileIndex < imgResources.length) {
			var file = imgResources.eq(me.fileIndex);
			var resource = jsonArrayResource[me.fileIndex];
			if (validateFileDisplayTime(resource)) {
				delayTime = parseInt(resource.playTime) * 1000;
				if(me.fileIndex==0){
					imgResources.eq(imgResources.length-1).hide();
				}
				else{
					imgResources.eq(me.fileIndex-1).hide();
				}
				file.show();
				
			} else {

				delayTime = 0;
			}
			me.fileIndex++;
		} else {

			me.fileIndex = 0;
			delayTime = 0;
		}
		if (typeof (me.advertiseTimer) != 'undefined'&& me.advertiseTimer != undefined) {
			window.clearTimeout(me.advertiseTimer);
			me.advertiseTimer = undefined;
		}
		if(me.runFlag){
			me.advertiseTimer = window.setTimeout(function(){me.running(me)}, delayTime);
		}
	}
};