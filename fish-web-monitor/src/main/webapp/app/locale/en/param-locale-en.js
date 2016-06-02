Ext.apply(EwayLocale,{

    param:{
    	application:{
    		title:'Application System',//EwayLocale.param.application.title
    		name:'name',//EwayLocale.param.application.name
    		form:'Application System',//EwayLocale.param.application.form
    		configureName:'ConfigureFileName',//EwayLocale.param.application.configureName
    		configurePath:'ConfigureFilePath',//EwayLocale.param.application.configurePath
    		configureForm:'ConfigureFileForm',//EwayLocale.param.application.configureForm
    		remark:'Remark'//EwayLocale.param.application.remark
    	},
    	element:{
    		title:'Param Element',//EwayLocale.param.element.title
    		name:'Param Element',//EwayLocale.param.element.name
    		paramName:'ParamName',//EwayLocale.param.element.paramName
    		paramValue:'ParamValue',//EwayLocale.param.element.paramValue
    		paramType:'ParamType',//EwayLocale.param.element.paramType
    		paramClassify:'ParamClassify',//EwayLocale.param.element.paramClassify
    		paramRights:'ParamRights',//EwayLocale.param.element.paramRights
    		paramBelongs:'ParamBelongs',//EwayLocale.param.element.ParamBelongs
    		remark:'Remark',//EwayLocale.param.element.remark
    		createTime:'CreateTime',//EwayLocale.param.element.createTime
    		lastModifyTime:'LastModifyTime',//EwayLocale.param.element.lastModifyTime
    		integer:'Integer',//EwayLocale.param.element.integer
    		character:'String',//EwayLocale.param.element.character
    		editable:'Editable',//EwayLocale.param.element.editable
    		uneditable:'Uneditable',//EwayLocale.param.element.uneditable
    		ATMC:'ATMC',//EwayLocale.param.element.ATMC
            monitoringCient:'Monitoring Client',//EwayLocale.param.element.monitoringCient
            versionNo:'VersionNo',//EwayLocale.param.element.versionNo
            importSuccess:'Import parameter element successfully!',//EwayLocale.param.element.importSuccess
            regex1:'Can only input numbers! ',//EwayLocale.param.element.regex1
            regex2:'Can only input english,numbers,underline,line',//EwayLocale.param.element.regex2
            imports:'Import parameter elenemt file',//EwayLocale.param.element.imports
            importFile:'Import file',//EwayLocale.param.element.importFile
            importEmptyText:'Please chose file!'//EwayLocale.param.element.importEmptyText
    	},
    	classify:{
    		title:'Param Classify',//EwayLocale.param.classify.title
    		name:'ClassifyName',//EwayLocale.param.classify.name
    		remark:'ClassifyRemark',//EwayLocale.param.classify.remark
    		regexText1:"Can only enter 1 to 32 letters' A-Z 'or' A-Z 'digital' 0-9 ', Chinese, a minus sign' - ', underline' ', dots'.', only at the beginning of the Chinese, letter or number.",//EwayLocale.param.classify.regexText1
    		regexText2:"Can only enter 1 to 128 characters' A-Z 'or' A-Z 'digital' 0-9 ', Chinese, a minus sign' - 'and underscore' ', dots'.', commas, periods, brackets, quotation marks, only at the beginning of the Chinese, letter or number.",//EwayLocale.param.classify.regexText2
    		notUpdate:'Default classify cannot be updated',//EwayLocale.param.classify.notUpdate
    		notRemove:'Default classify cannot be removed'//EwayLocale.param.classify.notRemove
    	},
    	deviceParam:{
    		title:'The Manage Of Device Paramter',//EwayLocale.param.deviceParam.title
    		downloadFailure:'Download Failure',//EwayLocale.param.deviceParam.downloadFailure
    		downloadSuccess:'Download Success',//EwayLocale.param.deviceParam.downloadSuccess
    		notMatch:'is not match the type of parameter',//EwayLocale.param.deviceParam.notMatch
    		tooLong:'The length of parameter value is too long,Please enter again!',//EwayLocale.param.deviceParam.tooLong
    		notExist:'Operation is failure:Device is not exist!',//EwayLocale.param.deviceParam.notExist
    		noDevice:'Does Not Choose Device,Can Not Release Parameter.'//EwayLocale.param.deviceParam.noDevice
    	},
    	template:{
        	addTitle:'Add Parameter Template',//EwayLocale.param.template.addTitle
        	updateTitle:'Update Parameter Template',//EwayLocale.param.template.updateTitle
        	title:'Parameter Template',//EwayLocale.param.template.title
            templateName:'Template Name',//EwayLocale.param.template.templateName
            templateRemark:'Template Remark',//EwayLocale.param.template.templateRemark
            paramGridTitle:'<font color="black"><b>You Can Add Parameters (Using Drag And Drop)</b></font>',//EwayLocale.param.template.paramGridTitle
            addedParamGridTitle:'<font color="black"><b>Parameters Have Been Added (Editable Parameters Can Be Modified Directly)</b></font>',//EwayLocale.param.template.addedParamGridTitle
            paramName:'Parameter Name',//EwayLocale.param.template.paramName
        	paramValue:'Parameter Value',//EwayLocale.param.template.paramValue
        	paramType:'Parameter Type',//EwayLocale.param.template.paramType
        	integer:'Integer',//EwayLocale.param.template.integer
        	character:'Character',//EwayLocale.param.template.character
        	paramRights:'Parameter Root',//EwayLocale.param.template.paramRights
        	editable:'Editable',//EwayLocale.param.template.editable
        	uneditable:'Uneditable',//EwayLocale.param.template.uneditable
        	paramTemplateName:'Parameter Template Name',//EwayLocale.param.template.paramTemplateName
        	applyFlag:'Status',//EwayLocale.param.template.applyFlag
        	published:'Published',//EwayLocale.param.template.published
        	unpublished:'Unpublished',//EwayLocale.param.template.unpublished
        	paramBelongs:'Subordinate System',//EwayLocale.param.template.paramBelongs
        	bankPerlink:'Visual Plant',//EwayLocale.param.template.bankPerlink
        },
        paramDownloadMonitor:{
        	title:'Parameter Download Monitor',//EwayLocale.param.paramDownloadMonitor.title
        	jobName:'Job Name',//EwayLocale.param.paramDownloadMonitor.jobName
        	taskId:'Task ID',//EwayLocale.param.paramDownloadMonitor.taskId
        	devCode:'Device TerminalId',//EwayLocale.param.paramDownloadMonitor.devCode
        	versionNo: 'Version Number',//EwayLocale.param.paramDownloadMonitor.versionNo
        	downloadStartTime: 'The Start Time Of Download',//EwayLocale.param.paramDownloadMonitor.downloadStartTime
        	downloadFinishTime: 'The Finish Time Of Download',//EwayLocale.param.paramDownloadMonitor.downloadFinishTime
        	reason: 'Reason',//EwayLocale.param.paramDownloadMonitor.reason
        	taskStatus: 'Task Status',//EwayLocale.param.paramDownloadMonitor.taskStatus
        	jobDetail:'Check Job Detail',//EwayLocale.param.paramDownloadMonitor.jobDetail
        	jobId: 'Job ID',//EwayLocale.param.paramDownloadMonitor.jobId
        	date: 'Date',//EwayLocale.param.paramDownloadMonitor.date
        	publisherName: 'Publisher',//EwayLocale.param.paramDownloadMonitor.publisherName
        	downloadDetail:'Monitor Download Detail',//EwayLocale.param.paramDownloadMonitor.downloadDetail
            job:'Job: ',//EwayLocale.param.paramDownloadMonitor.job
            chooseRecord:'Please Choose A Record',//EwayLocale.param.paramDownloadMonitor.chooseRecord
            aotuJump:' Job Sucessfully,Skip To `Parameter Download Monitor` Page?',//EwayLocale.param.paramDownloadMonitor.aotuJump
        	task:'Task:',//EwayLocale.param.paramDownloadMonitor.task
        	StatusDetail:' Status Detail'//EwayLocale.param.paramDownloadMonitor.StatusDetail
        }

    }

});
