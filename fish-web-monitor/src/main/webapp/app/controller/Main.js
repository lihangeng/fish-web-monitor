Ext.define('Eway.controller.Main', {
	extend: 'Eway.controller.base.Controller',

	requires : ['Eway.lib.ButtonUtils','Eway.controller.index.Index','Ext.ux.window.Notification'],

	refs: [{
		ref: 'ewayView',
		selector: '#workspace'
	}],

	init: function() {
		var me = this;
		me.control({
			'#appmenu' :{
				itemclick: this.onItemClick,
				itemdbclick: this.onItemDbClick
			},
			'#appheader button[action=systemHelp]' :{
				click : this.onSystemHelp
			},
			'#appheader button[action=personalSettings]' :{
				click : this.onOpenPersonalSettings
			},/*F5刷新的功能，未完全实现,*/
			'#workspace' : {
	           tabchange : 'onTabChange'
	        }
		});

	   this.callParent(arguments);
	},

	// 系统启动时调用的方法
	onLaunch : function() {
		// 加载按钮数据
		Eway.lib.ButtonUtils.loadButtons(this);
		Ext.apply(Eway, {
			alert : function(msg){
				Ext.create('widget.uxNotification', {
					position: 't',
					closable: false,
					slideInDuration: 1000,
					slideBackDuration: 1000,
					autoCloseDelay: 4000,
					slideInAnimation: 'elasticIn',
					slideBackAnimation: 'elasticIn',
					html: msg
				}).show();
			}
		});
	},

	initTabPanel:function(){
		// 从cookie中找到最后一次打开的页面，找不到则使用worspace中默认配置的首页
		var lastEwayPage = Ext.util.Cookies.get("lastEwayPage");
		this.activeController("index.Index");
		if(undefined != lastEwayPage && "" != lastEwayPage){
			this.activeController(lastEwayPage);
		}
	},

	//主工作区tab切换时执行的动作，保存到hash到cookie中
    onTabChange : function(tabPanel, newItem) {
    	if(undefined == tabPanel.getActiveTab() || null == tabPanel.getActiveTab()/* || newItem.xtype == 'appindex'*/){
    		Ext.util.Cookies.set("lastEwayPage","");
    		return;
    	}
        var hash = newItem.controllers;
        this.redirectTo(hash);
        Ext.util.Cookies.set("lastEwayPage",hash)
    },

    //打开帮助
    onSystemHelp : function(){
   	     this.activeController('system.Help');
    },

	//打开个人设置
	onOpenPersonalSettings : function(btn){
		this.activeController('personal.Personal');
	},
	onItemDbClick:function(view, node, item, index, e){
		if(!node.isLeaf()){
			if(node.isExpanded()){
				node.collapse(true);
			}else{
				view.expand(node);
			}
		}
	},
	onItemClick : function(view, node, item, index, e){
		if(!node.isLeaf()){
			return;
		}else{
			var code = node.data.id;
			var text = node.data.text;
			this.openSystemMenu(code,text);
			this.openDeviceMenu(code,text);
			this.openMonitorMenu(code,text);
			this.openSoftMenu(code,text);
			this.openCaseMenu(code,text);
			this.openReportMenu(code,text);
		}
	},

	// 打开“系统管理”下子菜单
	openSystemMenu : function(code,text){
		if(code == "serviceOrg"){
			this.activeController('person.ServiceOrg',text);
		}else if(code == "bankOrg"){
			this.activeController('person.BankOrg',text);
		}else if(code =="servicePer"){
			this.activeController('person.ServicePer',text);
		}else if(code == "bankPer"){
			this.activeController('person.BankPer',text);
		}else if(code == "user"){
			this.activeController('person.User',text);
		}else if(code == "role"){
			this.activeController('permission.Role',text);
		}else if(code == "permission"){
			this.activeController('permission.Permission',text);
		}else if(code == "userLog"){
			this.activeController('person.UserLog',text);
		}else if(code == "serviceThread"){
			this.onServiceThreadInfo();
		}else if(code == "configuration"){
			this.activeController('machine.param.Param',text);
		}
	},

	// 打开“设备管理”下子菜单
	openDeviceMenu : function(code,text){
		if(code == "device"){
			this.activeController('machine.Device',text);
		}else if(code == "atmModule"){
			this.activeController('machine.atmModule.AtmModule',text);
		}else if(code == "atmBrand"){
			this.activeController('machine.atmBrand.AtmBrand',text);
		}else if(code == 'atmCatalog'){
			this.activeController('machine.atmCatalog.AtmCatalog',text);
		}else if(code == 'atmType'){
			this.activeController('machine.atmType.AtmType',text);
		}else if(code == 'atmGroup'){
			this.activeController('machine.atmGroup.AtmGroup',text);
		}else if(code == "device_quittingNotice"){
			this.activeController('machine.quittingNotice.QuittingNotice',text);
		}else if(code == "device_move"){
			this.activeController('machine.atmMove.AtmMove',text);
		}else if(code == "device_runtimeParams"){
			this.activeController('monitor.atmRunParams.DeviceRuntimeParams',text);
		}else if(code == "device_runtimeInfo"){
			this.activeController('machine.atmRuntimeInfo.RuntimeInfo',text);
		}else if(code == "retainCard"){
			this.activeController('monitor.card.CardInfo',text);
		}
//		else if(code == "monitor_cardAction"){
//			this.activeController('monitor.card.CardAction');
//		}else if(code == "monitor_cardDestroy"){
//			this.activeController('monitor.card.CardDestory');
//		}
		else if(code == "servicePlan"){
			this.activeController('operatingPlan.OpenPlan',text);
		}else if(code == "logBackup"){
			this.activeController('atmLog.LogBackup',text);
		}else if(code == "dayBackupJob"){
			this.activeController('atmLog.DayBackup',text);
		}else if(code == "atmLogInfo"){
			this.activeController('atmLog.AtmLogInfo',text);
		}
	},

	//打开“监控管理”下子菜单
	openMonitorMenu : function(code,text){
		if(code == "monitor_trans"){
			this.activeController('monitor.transaction.TransactionMonitor',text);
		}else if(code =="hits_trans"){
			this.activeController('monitor.transaction.HistoryTransaction',text);
		}else if(code =="cash_init"){
			this.activeController('monitor.cashinit.CashInit',text);
		}else if(code =="settlement"){
			this.activeController('monitor.settlement.Settlement',text);
		}else if(code =="monitor_device"){
			this.activeController('monitor.device.DeviceMonitor',text);
		}else if(code =="maintainService"){
			this.activeController('service.MaintainService',text);
		}else if(code =="moneyService"){
			this.activeController('service.MoneyService',text);
		}else if(code =="openPlan"){
			this.activeController('report.openPlan.OpenPlan',text);
		}else if(code =="monitorSummaryInfo"){
			this.activeController('monitor.MonitorSummaryInfo',text);
		}

	},

	//打开“软件管理”下子菜单
	openSoftMenu : function(code,text){
		if(code =="version"){
			this.activeController('version.Version',text);
		}else if(code == "versionMonitor"){
			this.activeController('version.VersionDownload',text);
		}else if(code == "advert"){
			this.activeController('advert.Advert',text);
		}else if(code == "deviceVersion"){
			this.activeController('version.DeviceVersion',text);
		}else if(code == "versionType"){
			this.activeController('version.VersionType',text);
		}else if(code=="versionAutoUpdate"){
			this.activeController('version.VersionAutoUpdate',text);
		}else if(code=="versionDistribute"){
			this.activeController('version.VersionDistribute',text);
		}

	},

	//打开“故障管理”下子菜单
	openCaseMenu : function(code,text){
		if(code =="notifyMould"){
			this.activeController('case.NotifyMould',text);
		}else if(code == "notifyMould"){
			this.activeController('case.NotifyMould',text);
		}else if(code == "faultConfig"){
			this.activeController('case.FaultClassify',text);
		}else if(code == "casefault"){
			this.activeController('case.CaseFault',text);
		}else if(code == "casenotify"){
			this.activeController('case.CaseNotify',text);
		}else if(code == "vendorCode"){
			this.activeController('case.VendorCode',text);
		}
	},

	//打开“报表管理”下子菜单
	openReportMenu : function(code,text){
		if(code =="reportRetainCardDetail"){
			this.activeController('report.baseReport.RetainCardReport',text);
		}else if(code =="reportRetainCardCount"){
			this.activeController('report.baseReport.RetainCardCountReport',text);
		}else if(code =="reportTransactionCount"){
			this.activeController('report.baseReport.TransactionCountReport',text);
		}else if(code =="reportTransactionResult"){
			this.activeController('report.baseReport.TransactionResultCountReport',text);
		}else if(code =="reportCashIn"){
			this.activeController('report.baseReport.CashInReport',text);
		}else if(code =="reportSettlement"){
			this.activeController('report.baseReport.SettlementReport',text);
		}else if(code =="reportVendor"){
			this.activeController('report.baseReport.DeviceTypeCountReport',text);
		}else if(code =="reporDeviceHardWare"){
			this.activeController('report.baseReport.DeviceHardWareReport',text);
		}else if(code =="reportDeviceUseCount"){
			this.activeController('report.baseReport.DeviceUseCountReport',text);
		}else if(code =="reportDeviceInfo"){
			this.activeController('report.baseReport.DeviceReport',text);
		}else if(code =="reportDeviceBoxDetail"){
			this.activeController('report.baseReport.DeviceBoxDetailReport',text);
		}else if(code =="deviceOpenRate"){
			this.activeController('report.openrate.DeviceOpenRate',text);
		}else if(code =="typeOpenRate"){
			this.activeController('report.openrate.TypeOpenRate',text);
		}else if(code =="orgOpenRate"){
			this.activeController('report.openrate.OrgOpenRate',text);
		}else if(code =="reportDayTrans"){
			this.activeController('report.baseReport.TransactionDaysCountReport',text);
		}else if(code =="reportDayHourTrans"){
			this.activeController('report.baseReport.TransactionHoursCountReport',text);
		}
	},

	setShortcutMenu : function(){
		var win = Ext.create('Eway.view.shortcutMenu.EditWin');
		win.loadCusRecord();
		var tree = win.down('treepanel');
		win.show();
		win.down('button[action="confirm"]').on('click',Ext.bind(this.onAddConfirm,this,[tree]), this);
	},

	onAddConfirm : function(tree){
		var records = tree.getChecked();
		var editWin = tree.up('window');
		var winEl = editWin.getEl();
		winEl.mask(EwayLocale.agent.submitingWaiting);
		var permissions = '';
		for(var i  in records){
			var record = records[i];
			var code = record.get('id');
			permissions += code+',';
		}
		permissions = permissions.substring(0,permissions.length-1);
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/shortcutMenu/editShortcutMenu',
			params : {
				permissions : permissions
			},
			success : function(response){
				var object = Ext.decode(response.responseText);
				if(object.success == true){
					winEl.unmask();
					Eway.alert(EwayLocale.tip.operateSuc);
					editWin.close();
					var treepanel = Ext.ComponentQuery.query('appindex')[0].down('treepanel');
					treepanel.getStore().load();
				}else{
					winEl.unmask();
					Eway.alert(EwayLocale.tip.operateWrong);
				}

			},
			faliure : function(response){
				winEl.unmask();
				Eway.alert(EwayLocale.agent.offServer);
			}

		});
	},

	onServiceThreadInfo : function(){
		/*var win = Ext.create('Eway.view.thread.ThreadWin');
		Ext.Ajax.request({
   			method : 'GET',
   			url : 'api/service/thread',
   			params :{},
   			success : function(response){
   				var object = Ext.decode(response.responseText);
   				if(object.success == true){
   					if(object.data != null){
   						win.down('form').down('displayfield[name="daybackThreadState"]').setValue(object.data.daybackThreadState);
   	   					win.down('form').down('displayfield[name="redoBackTreadState"]').setValue(object.data.redoBackTreadState);
   	   					win.down('form').down('displayfield[name="activeRuners"]').setValue(object.data.activeRuners);
   	   					win.down('form').down('displayfield[name="jobManagerState"]').setValue(object.data.jobManagerState);
   	   					win.down('form').down('displayfield[name="taskMangerState"]').setValue(object.data.taskMangerState);
   	   					win.down('form').down('displayfield[name="maxJobCount"]').setValue(object.data.maxJobCount);
   	   					win.down('form').down('displayfield[name="jobQueueCount"]').setValue(object.data.jobQueueCount);
   	   					win.down('form').down('displayfield[name="activeTaskCount"]').setValue(object.data.activeTaskCount);
   	   					win.show();
   					}else{
   						win.close();
   						Eway.alert("查看服务器线程信息失败，请重新操作.");
   					}
   				}else{
   					win.close();
   					Eway.alert("查看服务器线程信息失败，请重新操作.");
   				}
   			},
   			failure : function(){
   				win.close();
   				Eway.alert("查看服务器线程信息失败，请重新操作.");
   			}
		});
		win.on('destroy',function(){
			Ext.TaskManager.destroy();
		});
		var task = {
		    run: function(){
		        Ext.Ajax.request({
		   			method : 'GET',
		   			url : 'api/service/thread',
		   			params :{},
		   			success : function(response){
		   				var object = Ext.decode(response.responseText);
		   				if(object.success == true){
		   					if(object.data != null){
		   						win.hide();
		   						win.down('form').down('displayfield[name="daybackThreadState"]').setValue(object.data.daybackThreadState);
		   	   					win.down('form').down('displayfield[name="redoBackTreadState"]').setValue(object.data.redoBackTreadState);
		   	   					win.down('form').down('displayfield[name="activeRuners"]').setValue(object.data.activeRuners);
		   	   					win.down('form').down('displayfield[name="jobManagerState"]').setValue(object.data.jobManagerState);
		   	   					win.down('form').down('displayfield[name="taskMangerState"]').setValue(object.data.taskMangerState);
		   	   					win.down('form').down('displayfield[name="maxJobCount"]').setValue(object.data.maxJobCount);
		   	   					win.down('form').down('displayfield[name="jobQueueCount"]').setValue(object.data.jobQueueCount);
		   	   					win.down('form').down('displayfield[name="activeTaskCount"]').setValue(object.data.activeTaskCount);
		   	   					win.show();
		   					}else{
		   					}
		   				}else{
		   				}
		   			},
		   			failure : function(){
		   			}
		   		});
		    },
		    interval: 10000 // 60 second
		}
		Ext.TaskManager.start(task);*/
	}
});
