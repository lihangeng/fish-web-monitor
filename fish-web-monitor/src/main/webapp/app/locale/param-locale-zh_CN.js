Ext.apply(EwayLocale,{

    param:{
    	application:{
    		title:'应用系统管理',//EwayLocale.param.application.title
    		name:'名称',//EwayLocale.param.application.name
    		form:'应用系统',//EwayLocale.param.application.form
    		configureName:'配置文件名称',//EwayLocale.param.application.configureName
    		configurePath:'配置文件路径',//EwayLocale.param.application.configurePath
    		configureForm:'配置文件格式',//EwayLocale.param.application.configureForm
    		remark:'备注'//EwayLocale.param.application.remark
    	},
    	element:{
    		title:'参数元数据管理',//EwayLocale.param.element.title
    		name:'参数元数据',//EwayLocale.param.element.name
    		paramName:'参数名',//EwayLocale.param.element.paramName
    		paramValue:'参数值',//EwayLocale.param.element.paramValue
    		paramType:'参数值类型',//EwayLocale.param.element.paramType
    		paramClassify:'参数分类',//EwayLocale.param.element.paramClassify
    		paramRights:'参数权限',//EwayLocale.param.element.ParamRights
    		paramBelongs:'归属应用系统',//EwayLocale.param.element.ParamBelongs
    		remark:'备注',//EwayLocale.param.element.remark
    		createTime:'创建时间',//EwayLocale.param.element.createTime
    		lastModifyTime:'最后修改时间',//EwayLocale.param.element.lastModifyTime
    		integer:'数字',//EwayLocale.param.element.integer
        	character:'字符串',//EwayLocale.param.element.character
        	editable:'可编辑',//EwayLocale.param.element.editable
        	uneditable:'不可编辑',//EwayLocale.param.element.uneditable
        	ATMC:'ATMC',//EwayLocale.param.element.ATMC
            monitoringCient:'监控客户端',//EwayLocale.param.element.monitoringCient
            versionNo:'版本号',//EwayLocale.param.element.versionNo
            importSuccess:'导入参数元数据成功！',//EwayLocale.param.element.importSuccess
            regex1:'只能输入数字',//EwayLocale.param.element.regex1
            regex2:'只能输入英文，数字，下划线，横线',//EwayLocale.param.element.regex2
            import:'导入参数元数据文件',//EwayLocale.param.element.import
            importFile:'导入文件',//EwayLocale.param.element.importFile
            importEmptyText:'请选择导入文件'//EwayLocale.param.element.importEmptyText

    	},
    	classify:{
    		title:'参数元数据分类管理',//EwayLocale.param.classify.title
    		name:'参数分类',//EwayLocale.param.classify.name
    		remark:'参数分类备注',//EwayLocale.param.classify.remark
    		regexText1:'只能输入1到32字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’， 只能以中文、字母或数字开头！',//EwayLocale.param.classify.regexText1
        	regexText2:'只能输入1到128字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’、逗号、句号、括号、双引号， 只能以中文、字母或数字开头！.'//EwayLocale.param.classify.regexText2

    	},
    	deviceParam:{
    		title:'设备参数管理',//EwayLocale.param.deviceParam.title
    		downloadFailure:'下发失败',//EwayLocale.param.deviceParam.downloadFailure
    		downloadSuccess:'下发成功 ',//EwayLocale.param.deviceParam.downloadSuccess
    		notMatch:'数值类型不匹配'//EwayLocale.param.deviceParam.notMatch
    	},
        template:{
        	addTitle:'增加参数模板',//EwayLocale.param.template.addTitle
        	updateTitle:'更改参数模板',//EwayLocale.param.template.updateTitle
        	title:'参数模板管理',//EwayLocale.param.template.title
            templateName:'模板名称',//EwayLocale.param.template.templateName
            templateRemark:'模板备注',//EwayLocale.param.template.templateRemark
            paramGridTitle:'<font color="black"><b>可添加参数(使用拖拽的方式)</b></font>',//EwayLocale.param.template.paramGridTitle
            addedParamGridTitle:'<font color="black"><b>已添加的参数(可编辑的参数可以直接修改)</b></font>',//EwayLocale.param.template.addedParamGridTitle
            paramName:'参数名称',//EwayLocale.param.template.paramName
        	paramValue:'参数值',//EwayLocale.param.template.paramValue
        	paramType:'参数类型',//EwayLocale.param.template.paramType
        	integer:'数字',//EwayLocale.param.template.integer
        	character:'字符串',//EwayLocale.param.template.character
        	paramRights:'参数权限',//EwayLocale.param.template.paramRights
        	editable:'可编辑',//EwayLocale.param.template.editable
        	uneditable:'不可编辑',//EwayLocale.param.template.uneditable
        	paramTemplateName:'参数模板名称',//EwayLocale.param.template.paramTemplateName
        	applyFlag:'状态',//EwayLocale.param.template.applyFlag
        	published:'已发布',//EwayLocale.param.template.published
        	unpublished:'未发布',//EwayLocale.param.template.unpublished
        	paramBelongs:'所属系统',//EwayLocale.param.template.paramBelongs
        	bankPerlink:'关联设备',//EwayLocale.param.template.bankPerlink
        },
        paramDownloadMonitor:{
        	title:'参数下发监控',//EwayLocale.param.paramDownloadMonitor.title
        	jobName:'作业名',//EwayLocale.param.paramDownloadMonitor.jobName
        	taskId:'任务ID',//EwayLocale.param.paramDownloadMonitor.taskId
        	devCode:'设备终端号',//EwayLocale.param.paramDownloadMonitor.devCode
        	versionNo: '版本号',//EwayLocale.param.paramDownloadMonitor.versionNo
        	downloadStartTime: '下载开始时间',//EwayLocale.param.paramDownloadMonitor.downloadStartTime
        	downloadFinishTime: '下载结束时间',//EwayLocale.param.paramDownloadMonitor.downloadFinishTime
        	reason: '原因',//EwayLocale.param.paramDownloadMonitor.reason
        	taskStatus: '任务状态',//EwayLocale.param.paramDownloadMonitor.taskStatus
        	jobDetail:'查看作业明细',//EwayLocale.param.paramDownloadMonitor.jobDetail
        	jobId: '作业ID',//EwayLocale.param.paramDownloadMonitor.jobId
        	date: '日期',//EwayLocale.param.paramDownloadMonitor.date
        	publisherName: '发布者',//EwayLocale.param.paramDownloadMonitor.publisherName
        	downloadDetail:'监控下发详情',//EwayLocale.param.paramDownloadMonitor.downloadDetail
        	job:'作业: ',//EwayLocale.param.paramDownloadMonitor.job
        	chooseRecord:'请选中一条记录',//EwayLocale.param.paramDownloadMonitor.chooseRecord
        	aotuJump:'次作业保存成功，是否跳转到参数下发监控界面！',//EwayLocale.param.paramDownloadMonitor.aotuJump
        	task:'任务:',//EwayLocale.param.paramDownloadMonitor.task
        	StatusDetail:' 状态详情'//EwayLocale.param.paramDownloadMonitor.StatusDetail
        }

    }

});
