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
			'version_distributeView field_versionTypeComboBoxAdd' : {
				change : this.onQuery
			},
			'version_distributeView version_pie polar' : {
				itemclick : this.diplayVersionStatusPie
			},
			'version_distributeView versionstatus_pie polar' : {
				itemclick : this.diplayVersionStatusDetail
			}
		})
	},
	//初始化页面进行查询操作，下一图表默认加载当前图标第一项数据
	onQuery : function(_this, newValue, oldValue, eOpts) {
		var me = this;
		var view = this.getEwayView();
		var versionTypeCombo = view.down('field_versionTypeComboBoxAdd');
		var record = versionTypeCombo.getSelectedRecord();
		var selectedVersionName = record.get("desc");
		var panel = view.down('version_pie'); 
		var polar = view.down('version_pie polar');
		var pieStore = polar.getStore();
		pieStore.load({
			params : {
				versionTypeId : newValue,
				displayNumber : 0
			},
			callback:function(records, operation, success){
				//如果没有响应的结果则进行清理操作
				if(undefined==records||records.length==0){
					me._statuspieClean();
					return;
				}

				var statuspolar = view.down('versionstatus_pie polar');
				var statuspieStore = statuspolar.getStore();
				var detailGrid = view.down('version_distribute_grid');
				var detailGridStore = detailGrid.getStore();
				statuspolar.setTitle(records[0].get("versionNo")+"版本下发历史状态分布图");
				statuspieStore.load({
					params : {
						versionId :records[0].get("versionId")
					},	
					callback:function(records, operation, success){
						if(undefined==records||records.length==0){
							return;
						}
						detailGrid.setTitle(records[0].get("taskStatusText"));
						detailGridStore.load({
							params : {
								versionId :records[0].get("versionId"),
								taskStatus:records[0].get("taskStatus")
							}
						});
					}
				});
			}
		})
	},
	//状态分布饼图数据清理
	_statuspieClean:function(){
		var view = this.getEwayView();
		var statuspolar = view.down('versionstatus_pie polar');
		statuspolar.setTitle("");
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
		detailGridStore.load({
			params : {
				versionId :item.record.data.versionId,
				taskStatus:item.record.data.taskStatus
			}
		});
	},
	//加载版本分布饼图数据，并加载第一项数据
	diplayVersionStatusPie:function(series, item, event, eOpts ){
		var me = this;
		var view = this.getEwayView();
		var statuspolar = view.down('versionstatus_pie polar');
		statuspolar.setTitle(item.record.data.versionNo+"版本下发历史状态分布图");
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
				detailGridStore.load({
					params : {
						versionId :records[0].get("versionId"),
						taskStatus:records[0].get("taskStatus")
					}
				});
			}
		});
	}
});
