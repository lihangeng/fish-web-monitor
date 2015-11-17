Ext.define('Eway.controller.version.VersionDistribute', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'version.ComboVersionType' ],
	views : [ 'version.distribute.View', 'version.distribute.VersionPie',
			'version.distribute.VersionStatusPie' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'version_distributeView',
		autoCreate : true,
		xtype : 'version_distributeView'
	}, {
		ref : 'versionPie',
		selector : 'version_pie'
	}, {
		ref : 'versionStatusPie',
		selector : 'versionStatus_pie'
	}, {
		ref : 'field_versionTypeComboBoxAdd',
		selector : 'field_versionTypeComboBoxAdd'
	} ],

	init : function() {
		this.control({
			'version_distributeView version_pie combo' : {
				change : this.onChange
			},
			'version_distributeView version_pie tool[type="refresh"]' : {
				click : this.versionPieRefresh
			},
			'version_distributeView version_pie polar' : {
				itemclick : this.diplayVersionStatusPie
			},
			'version_distributeView versionstatus_pie tool[type="refresh"]' : {
				click : this.versionStatusPieRefresh
			},
			'version_distributeView versionstatus_pie polar' : {
				itemclick : this.diplayVersionStatusDetail
			}
		})
	},
	versionPieRefresh:function(a,b,c){
		var me = this;
		var view = this.getEwayView();
		var versionTypeCombo = view.down('version_pie combo');
		var record = versionTypeCombo.getSelectedRecord();
		var selectedVersionName = record.get("desc");
		var versionTypeId=record.get("id");
		var panel = view.down('version_pie'); 
		var polar = view.down('version_pie polar');
		var pieStore = polar.getStore();
		pieStore.load({
			params : {
				versionTypeId : versionTypeId,
				displayNumber : 0
			},
			callback:function(records, operation, success){
				//如果没有响应的结果则进行清理操作
				if(undefined==records||records.length==0){
					me._statuspieClean();
					return;
				}
				me.versionStatusPieRefresh(a);
			}
		})
	
	},
	/**
	 * a 如果为undefined 则为selectChange
	 * 否则为刷新
	 * 刷新取数据规则,先取历史数据；如果历史数据没有了，则再去取version表中的第一条数据;如果还是无数据，则清空当前表
	 */
	versionStatusPieRefresh:function(a,b,c){
		var me = this;
		var view = this.getEwayView();
		var statusPanel = view.down('versionstatus_pie');
		var statuspolar = view.down('versionstatus_pie polar');
		var statuspieStore = statuspolar.getStore();
		var versionId = 0;
		//如果是切换com导致的事件,加载默认加载第一项版本信息;
		if(a==undefined){
			var polar = view.down('version_pie polar');
			var pieStore = polar.getStore();
			var versionArray=pieStore.data.items;
			if(undefined!=versionArray&&versionArray.length!=0){
				versionId = versionArray[0].get("versionId")
				statusPanel.setTitle(versionArray[0].get("versionNo")+EwayLocale.version.task.versionDownHisStatusPic);//"版本下发历史状态分布图");
			}
			else{
				me._statuspieClean();
				return;
			}
		}
		//如果版本类型没有变更进行刷新操作，则直接执行历史记录的刷新操作
		else{
			var versionStatusArray = statuspieStore.data.items;
			//如果状态信息中有数据,延续上次数据显示
			if(undefined!=versionStatusArray&&versionStatusArray.length!=0){
				versionId = versionStatusArray[0].get("versionId");
			}
			else{
				var polar = view.down('version_pie polar');
				var pieStore = polar.getStore();
				var versionArray=pieStore.data.items;
				if(undefined!=versionArray&&versionArray.length!=0){
					versionId = versionArray[0].get("versionId")
					statusPanel.setTitle(versionArray[0].get("versionNo")+EwayLocale.version.task.versionDownHisStatusPic);//"版本下发历史状态分布图");
				}
				else{
					me._statuspieClean();
					return;
				}
			}
		}
		
		statuspieStore.load({
			params : {
				versionId :versionId
			},	
			callback:function(records, operation, success){
				if(undefined==records||records.length==0){
					me._statuspieClean();
					return;
				}
				me.gridStoreRefresh(a);
			}
		});
	},
	gridStoreRefresh:function(a){
		var me = this;
		var view = this.getEwayView();
		var detailGrid = view.down('version_distribute_grid');
		var detailGridStore = detailGrid.getStore();
		if(a==undefined){
			var statuspolar = view.down('versionstatus_pie polar');
			var statuspieStore = statuspolar.getStore();
			var records = statuspieStore.data.items;
			if(undefined==records||records.length==0){
				return;
			}
			detailGrid.setTitle(records[0].get("taskStatusText"));
			detailGridStore.setBaseParam("versionId",records[0].get("versionId"));
			detailGridStore.setBaseParam("taskStatus",records[0].get("taskStatus"));
			detailGridStore.loadPage(1);
		}
		else{
			detailGridStore.loadPage(1);
		}
	},
	//初始化页面进行查询操作，下一图表默认加载当前图标第一项数据
	onChange : function(_this, newValue, oldValue, eOpts) {
		var me = this;
		me.getVersionPie().setTitle(_this.rawValue+EwayLocale.version.task.versionNoPic);
		me.versionPieRefresh();
	},
	//状态分布饼图数据清理
	_statuspieClean:function(){
		var view = this.getEwayView();
		var statusPanel = view.down('versionstatus_pie');
		var statuspolar = view.down('versionstatus_pie polar');
		statusPanel.setTitle("");
		var statuspieStore = statuspolar.getStore();
		statuspieStore.load({
			params : {
				versionId :0
			}	
		});
		this._versionStatusDetailClean();
	},
	//grid区域数据清理
	_versionStatusDetailClean:function(){
		var view = this.getEwayView();
		var detailGrid = view.down('version_distribute_grid');
		detailGrid.setTitle("");
		var detailGridStore = detailGrid.getStore();
		detailGridStore.load({
			params : {
				versionId :0,
				taskStatus:'OTHER'
			}
		});
	},
	//加载Grid表格数据
	diplayVersionStatusDetail:function(series, item, event, eOpts){
		var view = this.getEwayView();
		var detailGrid = view.down('version_distribute_grid');
		detailGrid.setTitle(item.record.data.taskStatusText);
		var detailGridStore = detailGrid.getStore();
		detailGridStore.setBaseParam("versionId",item.record.data.versionId);
		detailGridStore.setBaseParam("taskStatus",item.record.data.taskStatus);
		detailGridStore.loadPage(1);
//		detailGridStore.load({
//			params : {
//				versionId :item.record.data.versionId,
//				taskStatus:item.record.data.taskStatus
//			}
//		});
	},
	//加载版本分布饼图数据，并加载第一项数据
	diplayVersionStatusPie:function(series, item, event, eOpts ){
		var me = this;
		var view = this.getEwayView();
		var statuspolar = view.down('versionstatus_pie polar');
		statuspolar.setTitle(item.record.data.versionNo+EwayLocale.version.task.versionDownHisStatusPic);//"版本下发历史状态分布图");
		var statuspieStore = statuspolar.getStore();
		statuspieStore.load({
			params : {
				versionId :item.record.data.versionId
			},	
			callback:function(records, operation, success){
				var detailGrid = view.down('version_distribute_grid');
				if(undefined==records||records.length==0){
					me._versionStatusDetailClean();
					return;
				}
				var detailGrid = view.down('version_distribute_grid');
				detailGrid.setTitle(records[0].get("taskStatusText"));
				var detailGridStore = detailGrid.getStore();
				detailGridStore.setBaseParam("versionId",records[0].get("versionId"));
				detailGridStore.setBaseParam("taskStatus",records[0].get("taskStatus"));
				detailGridStore.loadPage(1);
			}
		});
	}
});
