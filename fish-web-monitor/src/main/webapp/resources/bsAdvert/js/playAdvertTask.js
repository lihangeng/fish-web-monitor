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
		//判断资源下标是否越界
		if (me.fileIndex < imgResources.length) {
			//获取将要显示的图片对象
			var file = imgResources.eq(me.fileIndex);
			//加载图片对象的资源信息
			var resource = jsonArrayResource[me.fileIndex];
			//验证资源是否符合播放条件，符合则播放
			if (validateFileDisplayTime(resource)) {
				delayTime = parseInt(resource.playTime) * 1000;
				imgResources.hide();
				file.show();
			}
			//不符合条件直接延迟0秒播放下一个图片
			else {
				delayTime = 0;
			}
			//图片对象集合下标加一
			me.fileIndex++;
		}
		//如果越界直接将下标置为0，延迟为0
		else {
			me.fileIndex = 0;
			delayTime = 0;
		}
		//查看定时器是否清空，未清空执行清理操作
		if (typeof (me.advertiseTimer) != 'undefined'&& me.advertiseTimer != undefined) {
			window.clearTimeout(me.advertiseTimer);
			me.advertiseTimer = undefined;
		}
		//如果为运行状态直接执行定时任务，如果不是运行状态，不再次执行定时任务
		if(me.runFlag){
			me.advertiseTimer = window.setTimeout(function(){me.running(me)}, delayTime);
		}
	}
};