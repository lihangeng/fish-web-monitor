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
		var workspace = this.getEwayView();
		var ps = Ext.ComponentQuery.query('personalSettings')[0];
		if(!ps){
			 ps = Ext.create('Eway.view.personal.PersonalSettings');
			 workspace.add(ps);
		}
		workspace.setActiveTab(ps);
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
			this.openSystemMenu(code);
			this.openDeviceMenu(code);
			this.openMonitorMenu(code);
			this.openSoftMenu(code);
			this.openCaseMenu(code);
			this.openReportMenu(code);
		}
	},

	// 打开“系统管理”下子菜单
	openSystemMenu : function(code){
		if(code == "serviceOrg"){
			this.activeController('person.ServiceOrg');
		}else if(code == "bankOrg"){
			this.activeController('person.BankOrg');
		}else if(code =="servicePer"){
			this.activeController('person.ServicePer');
		}else if(code == "bankPer"){
			this.activeController('person.BankPer');
		}else if(code == "user"){
			this.activeController('person.User');
		}else if(code == "role"){
			this.activeController('permission.Role');
		}else if(code == "permission"){
			this.activeController('permission.Permission');
		}else if(code == "userLog"){
			this.activeController('person.UserLog');
		}else if(code == "serviceThread"){
			this.onServiceThreadInfo();
		}else if(code == "configuration"){
			this.activeController('machine.param.Param');
		}
	},

	// 打开“设备管理”下子菜单
	openDeviceMenu : function(code){
		if(code == "device"){
			this.activeController('machine.Device');
		}else if(code == "atmModule"){
			this.activeController('machine.atmModule.AtmModule');
		}else if(code == "atmBrand"){
			this.activeController('machine.atmBrand.AtmBrand');
		}else if(code == 'atmCatalog'){
			this.activeController('machine.atmCatalog.AtmCatalog');
		}else if(code == 'atmType'){
			this.activeController('machine.atmType.AtmType');
		}else if(code == 'atmGroup'){
			this.activeController('machine.atmGroup.AtmGroup');
		}else if(code == "device_quittingNotice"){
			this.activeController('machine.quittingNotice.QuittingNotice');
		}else if(code == "device_move"){
			this.activeController('machine.atmMove.AtmMove');
		}else if(code == "device_runtimeParams"){
			this.activeController('monitor.atmRunParams.DeviceRuntimeParams');
		}else if(code == "device_runtimeInfo"){
			this.activeController('machine.atmRuntimeInfo.RuntimeInfo');
		}else if(code == "retainCard"){
			this.activeController('monitor.card.CardInfo');
		}
//		else if(code == "monitor_cardAction"){
//			this.activeController('monitor.card.CardAction');
//		}else if(code == "monitor_cardDestroy"){
//			this.activeController('monitor.card.CardDestory');
//		}
		else if(code == "logBackup"){
			this.activeController('atmLog.LogBackup');
		}else if(code == "dayBackupJob"){
			this.activeController('atmLog.DayBackup');
		}else if(code == "atmLogInfo"){
			this.activeController('atmLog.AtmLogInfo');
		}
	},

	//打开“监控管理”下子菜单
	openMonitorMenu : function(code){
		if(code == "monitor_trans"){
			this.activeController('monitor.transaction.TransactionMonitor');
		}else if(code =="hits_trans"){
			this.activeController('monitor.transaction.HistoryTransaction');
		}else if(code =="cash_init"){
			this.activeController('monitor.cashinit.CashInit');
		}else if(code =="settlement"){
			this.activeController('monitor.settlement.Settlement');
		}else if(code =="monitor_device"){
			this.activeController('monitor.device.DeviceMonitor');
		}else if(code =="maintainService"){
			this.activeController('service.MaintainService');
		}else if(code =="moneyService"){
			this.activeController('service.MoneyService');
		}else if(code =="openPlan"){
			this.activeController('report.openPlan.OpenPlan');
		}else if(code =="monitorSummaryInfo"){
			this.activeController('monitor.MonitorSummaryInfo');
		}
		
	},

	//打开“软件管理”下子菜单
	openSoftMenu : function(code){
		if(code =="version"){
			this.activeController('version.Version');
		}else if(code == "versionMonitor"){
			this.activeController('version.VersionDownload');
		}else if(code == "advert"){
			this.activeController('advert.Advert');
		}else if(code == "deviceVersion"){
			this.activeController('version.DeviceVersion');
		}else if(code == "versionType"){
			this.activeController('version.VersionType');
		}else if(code=="versionAutoUpdate"){
			this.activeController('version.VersionAutoUpdate');
		}else if(code=="versionDistribute"){
			this.activeController('version.VersionDistribute');
		}
		
	},

	//打开“故障管理”下子菜单
	openCaseMenu : function(code){
		if(code =="notifyMould"){
			this.activeController('case.NotifyMould');
		}else if(code == "notifyMould"){
			this.activeController('case.NotifyMould');
		}else if(code == "faultConfig"){
			this.activeController('case.FaultClassify');
		}else if(code == "casefault"){
			this.activeController('case.CaseFault');
		}else if(code == "casenotify"){
			this.activeController('case.CaseNotify');
		}else if(code == "vendorCode"){
			this.activeController('case.VendorCode');
		}
	},

	//打开“报表管理”下子菜单
	openReportMenu : function(code){
		if(code =="reportRetainCardDetail"){
			this.activeController('report.baseReport.RetainCardReport');
		}else if(code =="reportRetainCardCount"){
			this.activeController('report.baseReport.RetainCardCountReport');
		}else if(code =="reportTransactionCount"){
			this.activeController('report.baseReport.TransactionCountReport');
		}else if(code =="reportTransactionResult"){
			this.activeController('report.baseReport.TransactionResultCountReport');
		}else if(code =="reportCashIn"){
			this.activeController('report.baseReport.CashInReport');
		}else if(code =="reportSettlement"){
			this.activeController('report.baseReport.SettlementReport');
		}else if(code =="reportVendor"){
			this.activeController('report.baseReport.DeviceTypeCountReport');
		}else if(code =="reporDeviceHardWare"){
			this.activeController('report.baseReport.DeviceHardWareReport');
		}else if(code =="reportDeviceUseCount"){
			this.activeController('report.baseReport.DeviceUseCountReport');
		}else if(code =="reportDeviceInfo"){
			this.activeController('report.baseReport.DeviceReport');
		}else if(code =="reportDeviceBoxDetail"){
			this.activeController('report.baseReport.DeviceBoxDetailReport');
		}else if(code =="deviceOpenRate"){
			this.activeController('report.openrate.DeviceOpenRate');
		}else if(code =="typeOpenRate"){
			this.activeController('report.openrate.TypeOpenRate');
		}else if(code =="orgOpenRate"){
			this.activeController('report.openrate.OrgOpenRate');
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
		winEl.mask("正在提交,请稍等...");
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
					Eway.alert('操作成功.');
					editWin.close();
					var treepanel = Ext.ComponentQuery.query('appindex')[0].down('treepanel');
					treepanel.getStore().load();
				}else{
					winEl.unmask();
					Eway.alert('操作错误.');
				}

			},
			faliure : function(response){
				winEl.unmask();
				Eway.alert('与服务器断开连接.');
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