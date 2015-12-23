Ext.apply(EwayLocale,{
	//**********************************************************/
	versionType:{
		title:'Application Type',//EwayLocale.versionType.title 软件分类管理
		treeRoot:'All Types',//EwayLocale.versionType.treeRoot 所有软件分类
		defaultInstallPath:'Default Install Path',//EwayLocale.versionType.defaultInstallPath 默认安装路径
		needRestart:'Need Reboot For Upgrade',//EwayLocale.versionType.needRestart 需要重启设备完成升级
		devTypeOfUser:'Suitable Device Type',//EwayLocale.versionType.devTypeOfUser //适用的设备型号
		winTitle:'Application Type',//EwayLocale.versionType.winTitle 软件分类
		versionTypeNameRegText:'Just for 1-30 character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’'//EwayLocale.versionType.versionTypeNameRegText 只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)
	},
	statics:{
		title:'Distribution Statitics',//EwayLocale.statics.title
		versionInfo:'Version Info',//EwayLocale.statics.versionInfo //版本信息
		msg:'Info'	//EwayLocale.statics.msg
	},
	//广告模块
	advert:{
		title:'Advertisement',//EwayLocale.advert.title //广告管理
		createAdvert:'Create Advert',//EwayLocale.advert.createAdvert 创建广告
		idleAdvert:'Idle Advert',//EwayLocale.advert.idleAdvert 创建等待插卡广告
		transAdvert:'Transaction Advert',//EwayLocale.advert.transAdvert 创建交易页面广告
		textAdvert:'Text Advert',//EwayLocale.advert.textAdvert 创建文字滚动广告
		annoucementAdvert:'Create',//EwayLocale.advert.annoucementAdvert 创建公告
		updateTitle:'Modify',//EwayLocale.advert.updateTitle 更改广告信息
		downloadButton:'Deploy',//EwayLocale.advert.downloadButton 下发广告
		preview:'Preview',//EwayLocale.advert.preview 广告预览
		preview1024:'Preview 1024 resolution',//EwayLocale.advert.preview1024预览1024分辨率
		preview800:'Preview 800 resolution',//EwayLocale.advert.preview800预览800分辨率
		preview600:'Preview 600 resolution',//EwayLocale.advert.preview600预览600分辨率
		id:'ID',//EwayLocale.advert.id 广告ID
		type:'Advert Type',//EwayLocale.advert.type 广告类型
		downType:'Issuing way',//EwayLocale.advert.downType 广告下发方式
		validity:'Expiry date',//EwayLocale.advert.validity 广告有效期
		createdTime:'Created Time',//EwayLocale.advert.createdTime 制作时间
		userName:'Creater',//EwayLocale.advert.userName 制作人
		versionStatus:'Version status',//EwayLocale.advert.versionStatus 广告版本状态
		advertVersionFile:'Version file',//EwayLocale.advert.advertVersionFile 版本文件
		createdTimeStart:'Create time begin with',//EwayLocale.advert.createdTimeStart 制作时间开始于
		createdTimeStop:'End time end with',//EwayLocale.advert.createdTimeStop 制作时间结束于
		downloadAdvertConfig:'Deploy Settings',//EwayLocale.advert.downloadAdvertConfig 下发广告配置
		versionType:'Version type',//EwayLocale.advert.versionType 软件分类
		jobPriority:'Job priority',//EwayLocale.advert.jobPriority //作业优先级
		jobType:'Job type',//EwayLocale.advert.jobType 作业类型
		toVersionButton:'Has not generate version information,click button to [generate version]。',//EwayLocale.advert.toVersionButton 还没有生成版本信息，可以单击按钮[生成版本]。
		playTime:'Play Time',//EwayLocale.advert.playTime 广告播放时长
		beginDate:'Start Date',//EwayLocale.advert.beginDate 开始日期
		endDate:'End Date',//EwayLocale.advert.endDate 结束日期
		beginTime:'Start Time',//EwayLocale.advert.beginTime 开始时间
		endTime:'End Time',//EwayLocale.advert.endTime 结束时间
		fileSize:'Resource size',//EwayLocale.advert.fileSize 资源大小
		content:'Comment of play resource ',//EwayLocale.advert.content 播放资源内容
		advertConfig:'Settings',//EwayLocale.advert.advertConfig 广告配置
		addIdleTitle:'Add card-insert waiting advertisement',//EwayLocale.advert.addIdleTitle 增加等待插卡广告信息
		addIdleMore:'Add resource of advertisement again',//EwayLocale.advert.addIdleMore 再增加一个广告资源
		advertBasicInfo:'Basic info',//EwayLocale.advert.advertBasicInfo 广告基本信息
		idleAdvertInfo:'Card-insert waiting advertisement',//EwayLocale.advert.idleAdvertInfo 等待插卡页面广告
		advertValidity:'Expiry date',//EwayLocale.advert.advertValidity 广告有效期
		validityTemp:'Play temporary',//EwayLocale.advert.validityTemp 临时播放
		validityAlways:'Play forever',//EwayLocale.advert.validityAlways 永久播放
		idleAdvertResConfig:'Set the resource of card-insert waiting advertisement',//EwayLocale.advert.idleAdvertResConfig 等待插卡页面广告资源配置
		addTransTitle:'Add information of transacton-advertisement',//EwayLocale.advert.addTransTitle 增加交易页面广告信息
		transInfoAdvert:'Page of transcatiion-advertisement',//EwayLocale.advert.transInfoAdvert	 交易页面广告
		transAdvertResConfig:'Set the resource of transacton-advertisement-page',//EwayLocale.advert.transAdvertResConfig 交易页面广告资源配置
		addTextTitle:'Add information of rolling-characters-advertisement',//EwayLocale.advert.addTextTitle 增加文字滚动广告信息
		textInfoAdvert:'Characters-advertisement',//EwayLocale.advert.textInfoAdvert	 文字广告
		textAdvertResConfig:'Set the resource of rolling-characters-advertisement',//EwayLocale.advert.textAdvertResConfig 文字广告资源配置
		addAnnoucementTitle:'Add announcement',//EwayLocale.advert.addAnnoucementTitle 增加公告信息
		annoucementBasicInfo:'Basic information of announcement ',//EwayLocale.advert.annoucementBasicInfo 公告基本信息
		annoucementInfoAdvert:'Announcement',//EwayLocale.advert.annoucementInfoAdvert	 公告
		annoucementAdvertResConfig:'Set the resource of announcement-advertisement',//EwayLocale.advert.annoucementAdvertResConfig 公告页面广告资源配置
		advertTypeSelectEmpty:'Please choose type of advertisement',//EwayLocale.advert.advertTypeSelectEmpty 请选择广告类型
		advertTypeTrans:'Page of transaction',//EwayLocale.advert.advertTypeTrans 交易页面广告
		advertTypeIdle:'Page of card-insert waiting',//EwayLocale.advert.advertTypeIdle 等待插卡广告
		advertTypeText:'Rolling character advertisement',//EwayLocale.advert.advertTypeText 文字滚动广告
		advertTypeAnnou:'Announcement',//EwayLocale.advert.advertTypeAnnou 公告
		annoucementMoreTitle:'Add announcement',//EwayLocale.advert.annoucementMoreTitle 添加公告
		annoucementContext:'Comment of announcement',//EwayLocale.advert.annoucementContext Add announcement
		annoucementContextRegText:'Blank is not allowed',//EwayLocale.advert.annoucementContextRegText 不能包含空格
		times:'Duration',//EwayLocale.advert.times 时长
		timesTips:'Unit:second,tip:duration below 60 second will be better',//EwayLocale.advert.timesTips 单位:秒，提示：广告播放时长请控制在60秒内
		hourDisplay:'Hour',//EwayLocale.advert.hourDisplay 时
		minuteDisplay:'Minute',//EwayLocale.advert.minuteDisplay 分
		secondeDisplay:'Second',//EwayLocale.advert.secondeDisplay 秒
		textMoreTitle:'Add page with rolling-character',//EwayLocale.advert.textMoreTitle 添加文字滚动页面广告
		textContext:'Rolling character',//EwayLocale.advert.textContext 滚动文字
		idleMoreTitle:'Add page of card-insert waiting advertisement',//EwayLocale.advert.idleMoreTitle 添加等待插卡页面广告
		chooseMediaFile:'Please choose media file',//EwayLocale.advert.chooseMediaFile 请选择媒体文件
		uploadResource:'Upload resource...',//EwayLocale.advert.uploadResource 上传资源
		uploadResourceBlank:'Please upload resource...',//EwayLocale.advert.uploadResourceBlank 请上传资源
		uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//EwayLocale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
		resourceFormatTips:'Only .jpg,.aiv file is supported',//EwayLocale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
		resourceAlias:'File name after modified',//EwayLocale.advert.resourceAlias 修改后的文件名
		transMoreTitle:'Add page of transaction advertisement',//EwayLocale.advert.transMoreTitle 添加交易页面广告
		chooseMediaFile:'Please choose media file',//EwayLocale.advert.chooseMediaFile 请选择媒体文件
		uploadResource:'Upload resource...',//EwayLocale.advert.uploadResource 上传资源
		uploadResourceBlank:'Please upload resource',//EwayLocale.advert.uploadResourceBlank 请上传资源
		addMorePic:'Add a Picture',//EwayLocale.advert.addMorePic添加图片
		uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//EwayLocale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
		resourceFormatTips:'Only .jpg,.aiv file is supported',//EwayLocale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
		resourceAlias:'File name after modified',//EwayLocale.advert.resourceAlias 修改后的文件名
		advertDownMethodCover:'Override',//EwayLocale.advert.advertDownMethodCover 覆盖
		uploading:'Resource uploading...',//EwayLocale.advert.uploading 正在上传资源...
		advertPreviewTitle0:'Preview advertisement (Total ',//EwayLocale.advert.advertPreviewTitle0 广告预览(共有
		advertPreviewTitle1:'resources,play the no. ',//EwayLocale.advert.advertPreviewTitle1 个资源,当前播放第
		advertPreviewTitle2:'at the present) ',//EwayLocale.advert.advertPreviewTitle2 //个
		choosedAdvertRes:'You have choosed',//EwayLocale.advert.choosedAdvertRes 您已经选择了
		limitNumberTenForEveryResolution:'Only upload up to 10 pictures at each resolution.',//EwayLocale.advert.limitNumberTenForEveryResolution每种分辨率下最多只能上传10张图片
		mustContainerOnePicAt1024:'1024 image resolution comprises at least one',//EwayLocale.advert.mustContainerOnePicAt10241024分辨率下至少包含一个图片
		deleteAdvertResource:'Delete the picture',//EwayLocale.advert.deleteAdvertResource删除该图片
		fileName:'File Name',//EwayLocale.advert.fileName文件名
		resourceName:'Resource Name',//EwayLocale.advert.resourceName资源名称
		perviewAdertWithIEBrowse:'Unsupported preview the video without IE explorer.',//EwayLocale.advert.perviewAdertWithIEBrowse 非IE浏览器不支持视频广告的预览
		fileFormatTipsInfo:"<font color='red'>Upload picture format is not supported, only upload .jpg format images</font>",//EwayLocale.advert.fileFormatTipsInfo上传的图片格式不支持,只能上传.jpg格式的图片
		idleAdvertUpTipsInfo:'<font color="red">Only supports .jpg and .gif format images; each resolution upload up to 10 pictures; each picture maximum 5M</font>',//EwayLocale.advert.idleAdvertUpTipsInfo仅支持.jpg和.gif格式的图片;每种分辨率最多上传10张图片;每张图片最大5M
		configTitle:'Detail Settings'//EwayLocale.advert.configTitle 广告详细配置
	},
	//版本管理模块
	version:{
		selectDeviceInfo0:"Selected Devices (<font color='red'>",//EwayLocale.version.selectDeviceInfo0 //已选择的设备 (<font color='red'>
		selectDeviceInfo1:"</font>)",//EwayLocale.version.selectDeviceInfo1 </font>)台
		addVersionTitle:'Add Version information',//EwayLocale.version.addVersionTitle 增加版本信息
		batchTaskName:'Batch task name',//EwayLocale.version.batchTaskName 任务批次名称
		batchTaskNameEmpty:'Example:****first requirement update',//EwayLocale.version.batchTaskNameEmpty 例如:****需求第1批次升级
		UpdateTitle:'Update version information',//EwayLocale.version.UpdateTitle 更改版本信息
		addJobTitle:'Set version information',//EwayLocale.version.addJobTitle 配置作业信息
		downloadVersionId:'Issue version ID',//EwayLocale.version.downloadVersionId 下发版本ID
		taskType:'Task type',//EwayLocale.version.taskType 任务类型
		taskTypeManual:'Manual Update',//EwayLocale.version.taskTypeManual 手动升级
		taskTypeAuto:'Auto Update',//EwayLocale.version.taskTypeAuto 自动升级
		taskTypeScheduler:'Job plan',//EwayLocale.version.taskTypeScheduler 计划作业
		planTime:'Plan to execute time',//EwayLocale.version.planTime 计划执行时间
		selectableDevice:'Device can be issue',//EwayLocale.version.selectableDevice 可以下发的设备
		linkedDevice:'Selected Devices',//EwayLocale.version.linkedDevice 已选择的设备
		downloadVerFile:'Download version file',//EwayLocale.version.downloadVerFile 下载版本文件
		installPath:'Install Path',
		View:{
			title:'Patch', //版本管理
			versionDetail:'',//EwayLocale.version.View.versionDetail 版本详情
			remark:'Description', //EwayLocale.version.View.remark 备注
			newCreate:'New',//EwayLocale.version.View.newCreate 新建
			downLoaded:'DownLoaded',//EwayLocale.version.View.downLoaded 已下发
			waitting:'Waitting',//EwayLocale.version.View.waitting 等待下发
			versionPath:'Version Path',//EwayLocale.version.View.versionPath 版本路径
			versionPathRegText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',//EwayLocale.version.View.versionPathRegText
			versionPathDesc:'(path of version file be installed)',//EwayLocale.version.View.versionPathDesc (版本文件在自助设备上的安装路径)
			versionPerson:'Creater',//EwayLocale.version.View.versionPerson 创建人
			versionType:'Version Type',//EwayLocale.version.View.versionType 版本类型
			versionFile:'Version File',//EwayLocale.version.View.versionFile 版本文件
			versionFileButton:'Choose...',//EwayLocale.version.View.versionFileButton 浏览...
			versionFileRegexText:'Only .zip or .rar file can be uploaded',//EwayLocale.version.View.versionFileRegexText 只能上传zip或rar格式的文件
			versionFileUploadMsg:'File uploading...',//EwayLocale.version.View.versionFileUploadMsg 正在上传文件...
			versionFileEmpty:'Please zip the version file(or folder) to .zip or .rar format',//EwayLocale.version.View.versionFileEmpty 请将要下发的版本文件(或者文件夹)打包zip或rar格式
			versionTypeCode:'Code',//EwayLocale.version.View.versionTypeCode 软件分类编码
			versionTypeName:'Name',//EwayLocale.version.View.versionTypeName 软件分类名称
			versionTypeId:'ID',//EwayLocale.version.View.versionTypeId 版本类型ID
			versionTypeEmpty:'-Select Application Type-',//EwayLocale.version.View.versionTypeEmpty -请选择版本类型-
			versionTime:'Created Time',//EwayLocale.version.View.versionTime 创建时间
			versionNo:'Version No.',//EwayLocale.version.View.versionNo 版本号
			nowVersionNo:'Current Version No.',//EwayLocale.version.View.nowVersionNo 当前版本号
			versionStatus:'Version Status',//EwayLocale.version.View.versionStatus 版本状态
			versionStatusEmptyText:'All', //全部
			autoUpdate:'Auto Update',//EwayLocale.version.View.autoUpdate 允许自动更新
			autoUpdateYes:'Yes',//EwayLocale.version.View.autoUpdateYes //是
			autoUpdateNo:'No',//EwayLocale.version.View.autoUpdateNo 否
			autoUpdateEmptyText:'All', //全部
			dependVersion:'Dependented Version',//EwayLocale.version.View.dependVersion 依赖版本
			dependVersionEmptyText:'Please select type dependent on', //请选择依赖类型
			execBefore:'Execute script before update',//EwayLocale.version.View.execBefore 升级前执行脚本
			execBeforeEmptyText:'Please input the file in the update package which name is end with bat or cmd',//EwayLocale.version.View.execBeforeEmptyText 请输入升级包中的以bat或cmd结尾的文件
			execBeforeRegexText:'Only the file which name end with ‘bat’ or ‘cmd’ can be inputed',//EwayLocale.version.View.execBeforeRegexText 只能输入bat或cmd结尾的文件
			versionDesc:'Description',//EwayLocale.version.View.versionDesc 版本描述
			versionDescEmpty:'Please describe this version with words (At most 20 words)',//EwayLocale.version.View.versionDescEmpty 请用文字描述此版本需求
			otherConfigTitle:'Additional Settings',//EwayLocale.version.View.otherConfigTitle 其他配置
			otherConfigAutoDown:'Allow update automatic (only while the ATM check new version from server ,the version which update automatic will be return to ATM )',//EwayLocale.version.View.otherConfigAutoDown 允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)
			otherConfigUncompress:'Uzip automatic(when choose this option, the ATM will unzip automatic )&nbsp;<font color="red">attention:if the file is not zip file at first ,and then zipped to zip file ,please select this option!</font>',//EwayLocale.version.View.otherConfigUncompress 自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>
			versionServerPath:'File Path In Server', //EwayLocale.version.View.versionServerPath 文件在服务器上的位置
			versionName:'Name',//EwayLocale.version.View.versionName 版本名称
			downloadVersionName:'Version issued',//EwayLocale.version.View.downloadVersionName 下发的版本
			downloadVersionNameEmpty:'Please choose a version which you want to issue',//EwayLocale.version.View.downloadVersionNameEmpty 下发的版本
			distributionPic:'Patch Distribution',//EwayLocale.version.View.distributionPic 版本分布图
			job:{
				newCreate:'New', //新建
				running:'Running', //运行中
				scheduler:'In Plan',//计划中
				ready:'Prepare Running',//准备运行
				pause:'Pause',//暂停
				complete:'Finish' //完成
			}
		},
		VersionInstallInfo:{
			title:'Chart of version installed count' //版本安装信息统计图
		},
		jobPriority:{
			hight:'Hight',//EwayLocale.version.jobPriority.hight 高
			middle:'Middle',//EwayLocale.version.jobPriority.middle 中等
			general:'Low'//EwayLocale.version.jobPriority.general 普通
		},
		jobType:{
			float:'Manual',//EwayLocale.version.jobType.float 手工作业(立即下发)
			fix:'Plan'//EwayLocale.version.jobType.fix 计划作业(定时下发)
		},
		taskStatus:{
			news:'New',//EwayLocale.version.taskStatus.news 新建
			running:'Running',//EwayLocale.version.taskStatus.running 运行中
			noticed:'Notice successful',//EwayLocale.version.taskStatus.noticed 通知成功
			noticedFail:'Notice failed',//EwayLocale.version.taskStatus.noticedFail 通知失败
			downloaded:'Issued',//EwayLocale.version.taskStatus.downloaded 已下发
			downloadedFail:'Issue failed',//EwayLocale.version.taskStatus.downloadedFail 下发失败
			deployed:'Installing',//EwayLocale.version.taskStatus.deployed 正在部署
			deployedWait:'WaitInstall',//EwayLocale.version.taskStatus.deployedWait 等待部署
			deployedFail:'Install failed',//EwayLocale.version.taskStatus.deployedFail 部署失败
			checked:'Finish',//EwayLocale.version.taskStatus.checked 部署已确认
			noticeOk:'Noticed',//EwayLocale.version.taskStatus.noticeOk 已通知应用
			taskResetSuccessTips:'Success reset task!',//EwayLocale.version.taskStatus.taskResetSuccessTips
			taskResetFailTips : 'Fail reset task！',// EwayLocale.version.taskStatus.taskResetFailTips
			noticeFail:'Noticed Fail',//EwayLocale.version.taskStatus.noticeFail 通知应用失败
			downloading:'Issueing'// EwayLocale.version.taskStatus.downloading
		},
		versionCatalog:{
			app:'Application',//EwayLocale.version.versionCatalog.app 应用程序
			agent:'Monitor Agent',//EwayLocale.version.versionCatalog.agent 监控代理
			param:'Application Settings'//EwayLocale.version.versionCatalog.param 应用配置
		},
		download:{
			title:'Deployment Monitor',//EwayLocale.version.download.title 分发监控
			updateTitle:'Update Version Info',//EwayLocale.version.download.updateTitle 修改版本下发信息
			taskQueryTips:'Query job detail by condition',//EwayLocale.version.download.taskQueryTips 根据条件查询选中作业下的详情信息
			autoRefresh:'Start auto refresh',//EwayLocale.version.download.autoRefresh 开启自动刷新
			stopAutoRefresh:'Stop auto refresh',//EwayLocale.version.download.stopAutoRefresh 停止自动刷新
			cancelBatch:'cancelBatch',//EwayLocale.version.download.cancelBatch
			autoRefreshTips:'Refresh periodic 60 second',//EwayLocale.version.download.autoRefreshTips 刷新周期60秒
			resetTaskStatus:'Reset Status',//EwayLocale.version.download.resetTaskStatus
			selectTask:'Please select a task record！',//EwayLocale.version.download.selectTask请选择一条任务记录
			selectAllDevice:'All equipment',//EwayLocale.version.download.selectAllDevice全部设备
			checkedTaskCantResetTips:'Non-running task can not be reset！',//EwayLocale.version.download.checkedTaskCantResetTips非运行中的任务无法重置
			taskExportTips:'Export all issue result of job selected'//EwayLocale.version.download.taskExportTips 导出选中作业下的全部下发结果
		},
		task:{
			selectJobStartRefresh:'Please choose a job,and then start automatic refresh',//EwayLocale.version.task.selectJobStartRefresh 请选择一个作业,再开启定时刷新！
			jobBatchName:'Job batch name',//EwayLocale.version.task.jobBatchName 作业批次名称
			patchVersion:'Distribute version',//EwayLocale.version.task.patchVersion 分发版本
			taskStatus:'Task status',//EwayLocale.version.task.taskStatus 任务状态
			beforeUpdate:'Version before distribute',//EwayLocale.version.task.beforeUpdate 分发前的版本
			exceptVersion:'Version expect',//EwayLocale.version.task.exceptVersion 预期版本
			actionTime:'Execute time',//EwayLocale.version.task.actionTime 执行时间
			downSource:'Source download',//EwayLocale.version.task.downSource 下载源
			planTime:'Plan time',//EwayLocale.version.task.planTime 计划时间
			excuteMachine:'Execute machine',//EwayLocale.version.task.excuteMachine 执行服务器
			restartATM:'Restart ATM',//EwayLocale.version.task.restartATM 重启ATM
			restartATMTips:'Confirm execute reboot command?it may brings some risk.',//EwayLocale.version.task.restartATMTips 执行重启命令可能存在风险,确认重启?
			sendRestartCmd:'Reboot command has been sent!',//EwayLocale.version.task.sendRestartCmd 已发送重启命令！
			cancelDownloadSuccess:'Cancel issue successful!',//EwayLocale.version.task.cancelDownloadSuccess 取消下发成功
			cancelDownload:'Cancel issue',//EwayLocale.version.task.cancelDownload 取消下发
			jobName:'Job name',//EwayLocale.version.task.jobName 作业名称
			jobStatus:'Job status',//EwayLocale.version.task.jobStatus 作业状态
			chooseTitleDevice:'Choose machine',//EwayLocale.version.task.chooseTitleDevice 选择设备
			closeWindow:'Close',//EwayLocale.version.task.closeWindow 关闭窗口
			queryByFilter:'Query by filter',//EwayLocale.version.task.queryByFilter 根据条件查找
			displayNumPerPage:'Display on each page',//EwayLocale.version.task.displayNumPerPage 每页显示条数
			targetVersionNo:'Target version',//EwayLocale.version.task.targetVersionNo 目标版本
			downloadStatus:'Issue status',//EwayLocale.version.task.downloadStatus 下发状态
			downloadResult:'Issue result',//EwayLocale.version.task.downloadResult 下发结果
			cancelJob:'Cancel job',//EwayLocale.version.task.cancelJob 撤销作业
			jobId:'Job ID',	//EwayLocale.version.task.jobId 作业ID
			selectDownloadDevice:'Choose deivce to issue',	//EwayLocale.version.task.selectDownloadDevice 选择下发的设备
			versionNoBeforeUpdate:'Version before update',	//EwayLocale.version.task.versionNoBeforeUpdate 升级前版本号
			versionNoAfterUpdate:'Target version no',	//EwayLocale.version.task.versionNoAfterUpdate 目标版本号
			deviceVersionHis:'View device history version',	//EwayLocale.version.task.deviceVersionHis 查看设备历史版本
			downloadUser:'Issue man',	//EwayLocale.version.task.downloadUser 下发人
			downloadTime:'Issue time',	//EwayLocale.version.task.downloadTime 下发时间
			downloadResult:'Issue result',	//EwayLocale.version.task.downloadResult 下发结果
			deviceVersionHisTitle:'Information of device history version',	//EwayLocale.version.task.deviceVersionHisTitle 设备历史版本信息
			deviceVersions:'Device version',	//EwayLocale.version.task.deviceVersions 设备版本
			deviceVersionHisTip:'View information of device history version',	//EwayLocale.version.task.deviceVersionHisTip 查看设备历史版本信息
			autoUpdateInfo:'Information of auto update',//EwayLocale.version.task.autoUpdateInfo 自动更新信息
			selectAJob:'Please choose a job.',//EwayLocale.version.task.selectAJob 请选择一个作业
			versionDownHisStatusPic:'&nbsp;&nbsp;StatusDistribute',//EwayLocale.version.task.versionDownHisStatusPic 版本下发历史状态分布图
			versionNoPic:'&nbsp;&nbsp;VersionDistribute',//EwayLocale.version.task.versionNoPic
			cantCancelCompleteJob:'Can not cancel job which status is ‘finish’.',//EwayLocale.version.task.cantCancelCompleteJob 不能撤销"完成"状态的作业
			doSureCancelTheJob:'Cancel the selected job?(running job can only cancel the task which has not running)',//EwayLocale.version.task.doSureCancelTheJob 是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)
			deleting:'Deleting......',//EwayLocale.version.task.deleting 正在删除
			cancelSuccessBut:'Cancel the task in the job which has not run yet successful,the status is "running" at the present,please refresh the list',//EwayLocale.version.task.cancelSuccessBut
			cancelJobSuccess:'Cancel job successful',//EwayLocale.version.task.cancelJobSuccess 成功撤销作业
			updateResult:'Update result',	//EwayLocale.version.task.updateResult 升级结果
			updateResultSuccess:'Successful',	//EwayLocale.version.task.updateResultSuccess 升级结果
			updateResultFailed:'Failed'	//EwayLocale.version.task.updateResultFailed 升级结果
		}
	}
});
