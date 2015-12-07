Ext.define('Eway.controller.atmLog.DayBackup',{

	extend : 'Eway.controller.base.FishController',
	
	stores : [],
	models : [],
	views : ['atmLog.DayBackup'],
	
	refs : [{
		ref : 'ewayView',
		selector : '#atmLog_DayBackup',
		autoCreate : true,
		xtype : 'atmLog_DayBackup'
	},{
		ref :'dayBackupGrid',
		xtype :'atmLog_DayBackupGrid'
	}],
	
	init : function(){
		this.onQuery();
		this.control({
			'atmLog_DayBackupGrid button[action="query"]' : {
				'click' : this.onQuery
			},
			'atmLog_DayBackupGrid' : {
				cellclick : this.onCellClick
			}
		});
	},
	onCellClick : function(table,td,cellIndex,record,tr,rowIndex,e,options){
		if(cellIndex == 4){
			var deviceSucCount = record.get('deviceSucCount');
			deviceSucCount = deviceSucCount.toString();
			if(e && e.getTarget().innerHTML === deviceSucCount && deviceSucCount > 0){ //当单击超链接的时候
				var logBackupPanel = Ext.create('Eway.view.atmLog.LogBackup');
				var logBackupGrid = logBackupPanel.down('atmLog_LogBackupGrid');
				logBackupStore = logBackupGrid.getStore();		
				var currentDay = Ext.util.Format.date(record.get('date'), 'Y-m-d');
				var dt = new Date(currentDay);
			    var lastDay = Ext.Date.format(Ext.Date.add(dt, Ext.Date.DAY, -1),'Y-m-d');
				logBackupStore.load({params:{dateTime:lastDay,backupResult:'SUCCESS'}})
				var view = this.getEwayView();
				var gridPanel = view.query('panel[name=backupDetail]')[0];
				logBackupPanel.setTitle(record.get('date')+EwayLocale.agent.remote.backupLogSucList);
				var exitBackupPanel = gridPanel.down('atmLog_LogBackup');
				if(exitBackupPanel != null)
				{
					gridPanel.remove(exitBackupPanel);
				}	
				gridPanel.add(logBackupPanel);		
				var backupResult = "SUCCESS";
				logBackupPanel.down("button[action='query']").on('click',Ext.bind(this.onQuerybackUp,this,[record,logBackupPanel,logBackupStore,backupResult]),this);
				logBackupPanel.down("atmLog_LogBackupGrid pagingtoolbar").on("beforechange",Ext.bind(this.searchBackupLog,this,[lastDay,logBackupStore,backupResult]),this);
			}
		}
		else if(cellIndex == 5){
			var deviceFailCount = record.get('deviceFailCount');
			deviceFailCount = deviceFailCount.toString();
			if(e && e.getTarget().innerHTML === deviceFailCount && deviceFailCount > 0){
				var logBackupPanel = Ext.create('Eway.view.atmLog.LogBackup');
				var logBackupGrid = logBackupPanel.down('atmLog_LogBackupGrid');
				logBackupStore = logBackupGrid.getStore();		
				var currentDay = Ext.util.Format.date(record.get('date'), 'Y-m-d');
				var dt = new Date(currentDay);
			    var lastDay = Ext.Date.format(Ext.Date.add(dt, Ext.Date.DAY, -1),'Y-m-d');
				logBackupStore.load({params:{dateTime:lastDay,backupResult:'BackUpError'}})
				var view = this.getEwayView();
				var gridPanel = view.query('panel[name=backupDetail]')[0];
				logBackupPanel.setTitle(record.get('date')+EwayLocale.agent.remote.backupLogFailList);
				var exitBackupPanel = gridPanel.down('atmLog_LogBackup');				
				if(exitBackupPanel != null)
				{
					gridPanel.remove(exitBackupPanel);
				}	
				gridPanel.add(logBackupPanel);	
				var backupResult = "BackUpError";
				logBackupPanel.down("button[action='query']").on('click',Ext.bind(this.onQuerybackUp,this,[record,logBackupPanel,logBackupStore,backupResult]),this);
				logBackupPanel.down("atmLog_LogBackupGrid pagingtoolbar").on("beforechange",Ext.bind(this.searchBackupLog,this,[lastDay,logBackupStore,backupResult]),this);
			}

		}

	},
	onQuerybackUp: function(record,logBackupPanel,logBackupStore,backupResult){		
		var terminalId = logBackupPanel.down('textfield[name=terminalId]').value;
		console.log(terminalId);
		logBackupStore.load({
			params:{
				dateTime:record.get('date'),
				backupResult:backupResult,
				terminalId:terminalId
			}
		});
	},
	
	searchBackupLog : function(date,logBackupStore,backupResult){
			var extraParams = {
				dateTime:date,
				backupResult:backupResult
			};
			logBackupStore.proxy.extraParams = extraParams;
	}
});