Ext.define('Eway.controller.atmLog.AtmLogInfo',{

	extend : 'Eway.controller.base.FishController',

	stores : [],
	models : [],
	views : ['Eway.view.atmLog.AtmLogInfoView'],

	refs : [{
		ref : 'ewayView',
		selector : '#atmLog_AtmLogInfoView',
		autoCreate : true,
		xtype : 'atmLog_AtmLogInfoView'
	},{
		ref : 'atmLogInfoGrid',
		xtype : 'atmLog_AtmLogInfoGrid'
	}],

	init : function(){
		this.control({
			'atmLog_AtmLogInfoGrid' : {
//				itemclick : this.onClickHref,
				cellclick : this.onCellClick
			},
			'atmLog_AtmLogInfoGrid button[action="query"]':{
				click : this.onQuery
			},
			'atmLog_AtmLogInfoGrid button[action="export"]':{
				click : this.onExport
			}

		});
	},

	onCellClick : function(table,td,cellIndex,record,tr,rowIndex,e,options){
		if(cellIndex == 3){
			var backupErrorNumber = record.get('backupErrorNumber');
			backupErrorNumber = backupErrorNumber.toString();
			if(e && e.getTarget().innerHTML === backupErrorNumber && backupErrorNumber > 0){ //当单击超链接的时候
					var backupDate = record.data.backupDate;
					var orgId = record.data.orgId;
					var win = Ext.create('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoWin');
					var grid = win.down('atmLog_errorAtmLogInfo_ErrorAtmLogInfoGrid');
					var store = grid.getStore();
					store.proxy.extraParams = {backupDate : backupDate,orgId : orgId};
					win.down('button[action="export"]').on('click',Ext.bind(this.onExportError,this,[backupDate,orgId]),this);
					win.show();
					store.load();
			}
		}
		else if(cellIndex == 2){
			var backupSuccessNumber = record.get('backupSuccessNumber');
			backupSuccessNumber = backupSuccessNumber.toString();
			if(e && e.getTarget().innerHTML === backupSuccessNumber && backupSuccessNumber > 0){
				var backupDate = record.data.backupDate;
				var orgId = record.data.orgId;
				var win = Ext.create('Eway.view.atmLog.successAtmLogInfo.SuccessAtmLogInfoWin');
				var grid = win.down('atmLog_successAtmLogInfo_SuccessAtmLogInfoGrid');
				var store = grid.getStore();
				store.proxy.extraParams = {backupDate : backupDate,orgId : orgId};
				win.down('button[action="export"]').on('click',Ext.bind(this.onExportSuccess,this,[backupDate,orgId]),this);
				win.show();
				store.load();
			}

		}

	},

		onClickHref : function(grid, record, item, index, e, options) {

			var fileName = record.get('backupErrorNumber');
			fileName = fileName.toString();
			if (e && e.getTarget().innerHTML === fileName && fileName > 0) { //当单击超链接的时候
				var sm = grid.getSelectionModel();
				var record = sm.getLastSelected();
				var sm = grid.getSelectionModel();
				var backupDate = record.data.backupDate;
				var win = Ext.create('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoWin');
				var grid = win.down('atmLog_errorAtmLogInfo_ErrorAtmLogInfoGrid');
				var store = grid.getStore();
				store.load({
					params : {
						backupDate : backupDate
					}
				});
				win.down('button[action="export"]').on('click',Ext.bind(this.onExportError,this,[backupDate]),this);
				win.show();

		}

		},

		onExportError :function(backupDate, orgId){
			window.location.href = 'api/machine/atmLogInfo/errorPoiExcel?backupDate='+backupDate + '&orgId=' + orgId;
		},

		onExportSuccess : function(backupDate, orgId){
			window.location.href = 'api/machine/atmLogInfo/successPoiExcel?backupDate='+backupDate + '&orgId='+orgId;
		},

		onExport : function(){
			var view = this.getEwayView();
			var form = view.down('form').getForm();
			var bool = form.isValid();
			// 查询输入验证
			if (bool == false) {
				Eway.alert("查询项中存在不合法的输入,不能导出.");
				return
			}

           var values = form.getValues();
           var params = "?";
           for(var key in values){
        	   if(!Ext.isEmpty(values[key])){
        		   var prefix = params == "?" ? "" : "&";
        		   params = params + prefix + key + "=" + values[key];
        	   }
           }
			window.location.href = 'api/machine/atmLogInfo/poiExcel'+ params;
		}

});