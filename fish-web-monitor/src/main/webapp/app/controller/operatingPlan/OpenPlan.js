/**
 *  开机方案--controller
 */

Ext.define('Eway.controller.operatingPlan.OpenPlan', {
	extend : 'Eway.controller.base.FishController',

	stores : ['operatingPlan.OpenPlan',
	          'operatingPlan.OpenPlanDetail',
	          'operatingPlan.OpenPlanForDevice',
	          'operatingPlan.LinkedDevice',
	          'operatingPlan.LinkingDevice',
	          'operatingPlan.PlanStateType',
	          'Hour',
	          'Minute',
	          'operatingPlan.WeekSelect',
	          'operatingPlan.PlanState',
	          'machine.DeviceAtmType',
	          'machine.atmType.DeviceAtmCatalog'],

	models : ['operatingPlan.OpenPlan','operatingPlan.OpenPlanDetail','operatingPlan.LinkDevice','operatingPlan.PlanDevice'],

	views : ['Eway.view.operatingPlan.View'],

	refs : [ {
		ref : 'ewayView',
		selector : 'operatingPlan_view',
		autoCreate : true,
		xtype : 'operatingPlan_view'
	}, {
		ref : 'filterForm',
		selector : 'operatingPlan_filterForm'
	}, {
		ref : 'Grid',
		selector : 'operatingPlan_grid'
	} , {
		ref : 'drivceGrid',
		selector : 'planInfo_device_grid'
	} , {
		ref : 'drivceGridWin',
		selector : 'planInfo_for_device'
	} , {
		ref : 'viewForDevice',
		selector : 'operatingPlan_viewForDevice'
	} , {
		ref : 'gridForDevice',
		selector : 'operatingPlan_gridForDevice'
	},{
		ref : 'devPlanWin',
		selector : 'operatingPlan_planDevice'
	} ],
	isInit : false,
	idArray:null,
	init : function() {
		if(this.isInit == false){
		this.control({
			'operatingPlan_grid button[action=query]' :{
				click : this.onQuery
			},
			'operatingPlan_grid button[action=queryDetail]' :{
				click : this.onQueryDetail
			},
			'operatingPlan_grid button[action=remove]': {
				click: this.onRemove
			},
			'operatingPlan_grid button[action=add]': {
				click: this.onAdd
			},
			'operatingPlan_grid button[action=update]': {
				click: this.onUpdate
			},
			'operatingPlan_grid button[action=link]': {
				click: this.onLink
			},
			'planInfo_device_grid button[action=deviceQueryDetail]': {
				click: this.onDeviceQueryDetail
			},
			'planInfo_device_grid button[action=deviceUnlink]': {
				click: this.onDeviceUnlink
			},
			'planInfo_device_grid button[action=deviceLink]': {
				click: this.onDeviceLink
			},
			'operatingPlan_gridForDevice button[action=query]': {
				click: this.onDeviceQuery
			},
			'operatingPlan_gridForDevice button[action=link]': {
				click: this.onDeviceLinkOpenPlan
			},
			'operatingPlan_grid button[action=exportPlan]' : {
				click : this.onExport
			}
		});
		this.isInit = true;
		}
	},

	//查询开机方案：
	onDeviceQuery : function(){
		var viewWin = this.getViewForDevice();
		var store = viewWin.down('operatingPlan_gridForDevice').getStore();
		var values = viewWin.down('operatingPlan_filterForm').getForm().getValues();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
		viewWin.show();
	},
	onExport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var values = form.getValues();
		var params = '_dc='+"srcb";
		if (values.name) {
			params += '&name=' + values.name;
		}
		if (values.startDate) {
			params += '&startDate=' + values.startDate;
		}
		if (values.endDate) {
			params += '&endDate=' + values.endDate;
		}
		window.location.href = 'api/plan/exportPlan?' + params;
	},
	onDeviceLinkOpenPlan : function(){
		var grid = this.getGridForDevice();
		var c = this.getController('machine.Device');
		var cGrid = c.getGrid();
		var csm = cGrid.getSelectionModel();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1&&csm.getCount() == 1) {
			var record = sm.getLastSelected();
			var cRecord = csm.getLastSelected();
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/plan/link',
				params : {planId :record.data.id,deviceId:cRecord.data.id},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						var viewWin = this.getViewForDevice();
						viewWin.close();
						this.onOpenPlan(this.getDrivceGridWin(),cRecord.data.id,cRecord.data.terminalId,1)
					}else{
						Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.relatedFail);
					}
				},
				failure: function(response){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.relatedFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.selectRelatedDev);
		}
	},

	onDeviceUnlink :function(){
		var grid = this.getDrivceGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
		var record = sm.getLastSelected();
		Ext.Ajax.request({
			scope : this,
			method : 'POST',
			url : 'api/plan/unlink',
			params : {
				planId :record.data.id,
				deviceId:record.data.deviceId
				},
			success: function(response){
				var object = Ext.decode(response.responseText);
				if(object.success == true){
					this.onOpenPlan(this.getDrivceGridWin(),record.data.deviceId,record.data.terminalId,2);
				}else{
					Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.removeFail);
				}
			},
			failure: function(response){
				Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.removeFail);
			},
			scope:this
		});
	}else{
		Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.selectRemoveDev);
	}
	},

	onDeviceLink :function(){
		var grid = this.getDrivceGrid();
		var count=grid.getStore().getCount();
		if(count==0){
			var viewWin = Ext.create('Eway.view.operatingPlan.ViewForDevice');
			//var store = viewWin.down('operatingPlan_gridForDevice').getStore();
			//store.loadPage(1);
			viewWin.show();
		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.devRelatedPlan);
		}
	},

	onOpenPlan : function(detailWin,deviceId,terminalId,type) {
		var store = detailWin.down('planInfo_device_grid').getStore();
		store.load({
			params : {
				deviceId:deviceId
			},
			callback: function(records, operation, success) {
					detailWin.setTitle(EwayLocale.tip.business.device.term+terminalId+EwayLocale.machine.plan.servicePlan);
					detailWin.show();
					if(type==2){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.msg.removeSuccess);
					}else if(type == 1){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.msg.saveSuccess);
					}
		    }
		});
	},

	//查询方案详情：
	onDeviceQueryDetail : function(){
		var grid = this.getDrivceGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var detailWin = Ext.create('Eway.view.operatingPlan.PlanInfoWin');
			var record = sm.getLastSelected();
			var store = detailWin.down('planInfo_grid').getStore();
			store.load({
				params : {
					openPlanId:record.data.id
				},
				callback: function(records, operation, success) {
			        if(Ext.isEmpty(records)){
			        	detailWin.close();
			        	Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.planNoConf);
			        }else{
			        	if(record.data.planType=="DATE"){
							detailWin.down('planInfo_grid').columns[0].hidden=true;
							detailWin.setTitle(EwayLocale.tip.planDate);
							detailWin.show();
						}else{
							detailWin.setTitle(EwayLocale.tip.planWeek);
							detailWin.show();
						}
			        }
			    }
			});
		}else {
			Eway.alert(EwayLocale.tip.choosePlan);
		}
	},

	//增加开机方案
	onAdd: function() {
		var addWin = Ext.create('Eway.view.operatingPlan.Add');

		// 显示组件,绑定事件需要在窗口显示出来后，再做
		// 否则有可能出现显示不完全情况或者事件绑定不成功
		// 因为css样式在组件在窗口是隐藏的情况下,设置width,height是无效的．
		addWin.show();

		var layout = addWin.down('#cardPanelId').getLayout();
		layout.setActiveItem(0);

		var index = 1;
		var baseForm = addWin.down('form');
		baseForm.down('radiogroup').on('change',function(){
			layout.setActiveItem(index);
			if(index==1){
				index = 0;
			}else{
				index = 1;
			}
		});
		var detailWeekForm = addWin.down('#cardPanelId').down('#weekFormId');
		var detailDateForm = addWin.down('#cardPanelId').down('#dateFormId');
		var infoWeekGrid = addWin.down('#weekPlanInfoID');
		var infoDateGrid = addWin.down('#datePlanInfoID');
		//星期类型详情增加
		detailWeekForm.down('button[action="add"]').on('click',function(){
			if(!detailWeekForm.getForm().isValid()){
				return;
			}

			var data = detailWeekForm.getForm().getValues();

			var alertMsg = null;

			var myCheckboxGroup = detailWeekForm.down('#checkboxgroupId');
			for (var i = 0; i < myCheckboxGroup.getChecked().length; i++) {
				var weekDate = {};
				weekDate.startTime = data.startTimeHour + ":" + data.startTimeMinute + ":" + data.startTimeSecond;
				weekDate.endTime = data.endTimeHour + ":" + data.endTimeMinute + ":" + data.endTimeSecond;
				weekDate.week = myCheckboxGroup.getChecked()[i].name;
				weekDate.openClose = data.openClose;
			    var flag = true;
				infoWeekGrid.getStore().each(function(record){
					if(weekDate.openClose != record.data.openClose){

						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.planOlonOne);
					flag=false;
					return;
				}
					if(weekDate.week==record.data.week){
						if(weekDate.startTime>=weekDate.endTime){
							flag=false;
							return;
						}
						if(record.data.startTime<=weekDate.startTime&&weekDate.startTime<=record.data.endTime){
							flag=false;
							return;
						}
						if(record.data.startTime<=weekDate.endTime&&weekDate.endTime<=record.data.endTime){
							flag=false;
							return;
						}
						if(weekDate.startTime<=record.data.startTime&&weekDate.endTime>=record.data.endTime){
							flag=false;
							return;
						}
					}
				});
				if(flag){
					var record = Ext.create( 'Eway.model.operatingPlan.OpenPlanDetail',weekDate);
					infoWeekGrid.getStore().add(record);
				}else{
					if(alertMsg ==null){
						alertMsg = myCheckboxGroup.getChecked()[i].boxLabel;
					}else{
						alertMsg = alertMsg +","+myCheckboxGroup.getChecked()[i].boxLabel;
					}
				}
			}
			if(alertMsg!=null){
				Eway.alert(EwayLocale.confirm.title,EwayLocale.machine.serviceplan.week+alertMsg+EwayLocale.machine.serviceplan.timeEare);
			alertMsg = null;
			}
		});

		//日期类型详情增加
		detailDateForm.down('button[action="add"]').on('click',function(){
			if(!detailDateForm.getForm().isValid()){
				return;
			}
			var data = detailDateForm.getForm().getValues();
			data.startTime = data.startTimeHour + ":" + data.startTimeMinute + ":" + data.startTimeSecond;
			data.endTime = data.endTimeHour + ":" + data.endTimeMinute + ":" + data.endTimeSecond;
			if(data.startTime>=data.endTime){
				Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
				return;
			}
			var flag = true;
			infoDateGrid.getStore().each(function(record){
				if(data.openClose!=record.data.openClose){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.planOlonOne);
					flag=false;
					return;
				}
				if(data.startTime>=data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					flag=false;
					return;
				}
				if(record.data.startTime<=data.startTime&&data.startTime<=record.data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					flag=false;
					return;
				}
				if(record.data.startTime<=data.endTime&&data.endTime<=record.data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					flag=false;
					return;
				}
				if(data.startTime<=record.data.startTime&&data.endTime>=record.data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					flag=false;
					return;
				}
			})
			if(!flag){
				return;
			}
			var record = Ext.create('Eway.model.operatingPlan.OpenPlanDetail',data);
			infoDateGrid.getStore().add(record);
		});

		detailWeekForm.down('button[action="remove"]').on('click',function(){
			var sm = infoWeekGrid.getSelectionModel();
			if(sm.getCount() == 1) {
				var record = sm.getLastSelected();
				infoWeekGrid.getStore().remove(record);
			}
			else {
				Eway.alert(EwayLocale.confirm.title, EwayLocale.choiceDeleteMsg);
			}
		});
		detailDateForm.down('button[action="remove"]').on('click',function(){
			var sm = infoDateGrid.getSelectionModel();
			if(sm.getCount() == 1) {
				var record = sm.getLastSelected();
				infoDateGrid.getStore().remove(record);
			}
			else {
				Eway.alert(EwayLocale.confirm.title, EwayLocale.choiceDeleteMsg);
			}
		});

		addWin.down('button[action="confirm"]').on('click',Ext.bind(this.onAddConfirm,this,[addWin]),this);
	},

	//增加方案确认增加
	onAddConfirm : function(addWin){
		var view = this.getEwayView();
		var store = view.down('operatingPlan_grid').getStore();
		var addForm = addWin.down('form');
		var data = addForm.getForm().getValues();
		//字段验证：
		if(!addForm.getForm().isValid()){
			return;
		}
		var planDetails = "";
		if(data.planType=='WEEK'){
			var detailStore = addWin.down('#weekPlanInfoID').getStore();
			detailStore.each(function(record){
				var openPlanDetailstr = "{'week':" +"'" +record.data.week +"'" + ",'openClose':'" + record.data.openClose + "','startTime':'"+record.data.startTime
				+ "','endTime':'" + record.data.endTime +"'}";
				planDetails = planDetails + "," + openPlanDetailstr;
			})
		}else{
			var detailStore = addWin.down('#datePlanInfoID').getStore();
			detailStore.each(function(record){
				var openPlanDetailstr = "{'openClose':'" + record.data.openClose + "','startTime':'"+record.data.startTime
				+ "','endTime':'" + record.data.endTime +"'}";
				planDetails = planDetails + "," + openPlanDetailstr;
			})
		}
		if(planDetails != ""){
			planDetails = planDetails.substring(1);
			planDetails = "[" + planDetails + "]";
		var data1 = new Object();
		var record1 = Ext.create( 'Eway.model.operatingPlan.OpenPlan',data1);
    	record1.set('id',0);
    	record1.set('name',data.name);
    	record1.set('desc',data.desc);
    	record1.set('startDate',data.startDate);
    	record1.set('endDate',data.endDate);
    	record1.set('planType',data.planType);
    	record1.set('planState',data.planState);
    	record1.set('openPlanDetailForms',planDetails);
    	record1.save({
			success : function(record,operation){
				addWin.close();
				var values = view.down('operatingPlan_filterForm').getForm().getValues();
				var store = view.down('operatingPlan_grid').getStore();
				store.setUrlParamsByObject(values);
				store.loadPage(1);
				Eway.alert(EwayLocale.machine.serviceplan.addSuccess);
			},
//			failure: function(record,operation){
//				Ext.Msg.alert("提示", operation.request.scope.reader.jsonData.errors);
//			}
			failure: function(record,operation){
				Eway.alert(EwayLocale.confirm.title, operation.getError());

			 }


		});
		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.setTime);
    	}
	},

	//查询开机方案：
	onQuery : function(){
		var view = this.getEwayView();
		var values = view.down('operatingPlan_filterForm').getForm().getValues();
		var store = view.down('operatingPlan_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	//查询方案详情：
	onQueryDetail : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var detailWin = Ext.create('Eway.view.operatingPlan.PlanInfoWin');
			var record = sm.getLastSelected();
			var store = detailWin.down('planInfo_grid').getStore();
			store.load({
				params : {
					openPlanId:record.data.id
				},
				callback: function(records, operation, success) {
			        if(Ext.isEmpty(records)){
			        	detailWin.close();
			        	Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.planNoConf);
			        }else{
			        	if(record.data.planType=="DATE"){
							detailWin.down('planInfo_grid').columns[0].hidden=true;
							detailWin.setTitle(EwayLocale.tip.planDate);
							detailWin.show();
						}else{
							detailWin.setTitle(EwayLocale.tip.planWeek);
							detailWin.show();
						}
			        }
			    }
			});
		}else {
			Eway.alert(EwayLocale.tip.choosePlan);
		}
	},

	//方案的修改
	onUpdate : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {

			var win = Ext.create('Eway.view.operatingPlan.Update');

			// 显示组件,绑定事件需要在窗口显示出来后，再做
			// 否则有可能出现显示不完全情况或者事件绑定不成功
			// 因为css样式在组件在窗口是隐藏的情况下,设置width,height是无效的．
			win.show();


			var layout = win.down('#cardPanelId').getLayout();
			layout.setActiveItem(0);
			var index = 1;
			var baseForm = win.down('form');
			baseForm.down('radiogroup').on('change',function(){
				layout.setActiveItem(index);
				if(index==1){
					index = 0;
				}else{
					index = 1;
				}
			});
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			var detailWeekForm = win.down('#cardPanelId').down('#weekFormId');
			var detailDateForm = win.down('#cardPanelId').down('#dateFormId');
			var infoWeekGrid = win.down('#weekPlanInfoID');
			var infoDateGrid = win.down('#datePlanInfoID');
			if(record.data.planType=='WEEK'){
				var store = infoWeekGrid.getStore();
				store.load({
					params : {
						openPlanId:record.data.id
					}
				});
			}else{
				var store = infoDateGrid.getStore();
				store.load({
					params : {
						openPlanId:record.data.id
					}
				});
			}
			detailWeekForm.down('button[action="add"]').on('click',function(){
				if(!detailWeekForm.getForm().isValid()){
					return;
				}
				var data = detailWeekForm.getForm().getValues();
				var myCheckboxGroup = detailWeekForm.down('#checkboxgroupId');
				data.startTime = data.startTimeHour + ":" + data.startTimeMinute + ":" + data.startTimeSecond;
				data.endTime = data.endTimeHour + ":" + data.endTimeMinute + ":" + data.endTimeSecond;
				if(data.startTime>=data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					return;
				}
				var alertMsg = null;
				for (var i = 0; i < myCheckboxGroup.getChecked().length; i++) {
				data.week = myCheckboxGroup.getChecked()[i].name;
				var flag = true;
				infoWeekGrid.getStore().each(function(record){
					if(data.openClose!=record.data.openClose){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.planOlonOne);
						flag=false;
						return;
					}
						if(data.week==record.data.week){
							if(data.startTime>=data.endTime){
								flag=false;
								return;
							}
							if(record.data.startTime<=data.startTime&&data.startTime<=record.data.endTime){
								flag=false;
								return;
							}
							if(record.data.startTime<=data.endTime&&data.endTime<=record.data.endTime){
								flag=false;
								return;
							}
							if(data.startTime<=record.data.startTime&&data.endTime>=record.data.endTime){
								flag=false;
								return;
							}
						}
					})
					if(!flag){
						if(alertMsg ==null){
							alertMsg = myCheckboxGroup.getChecked()[i].boxLabel;
						}else{
							alertMsg = alertMsg +","+myCheckboxGroup.getChecked()[i].boxLabel;
						}
					}else{
						var record = Ext.create( 'Eway.model.operatingPlan.OpenPlanDetail',data);
						infoWeekGrid.getStore().add(record);
					}
				}
				if(alertMsg!=null){
					Eway.alert(EwayLocale.confirm.title,EwayLocale.machine.device.person.week+alertMsg+EwayLocale.machine.serviceplan.timeEare);
					alertMsg = null;
				}
			});


			detailDateForm.down('button[action="add"]').on('click',function(){
				if(!detailDateForm.getForm().isValid()){
					return;
				}
				var data = detailDateForm.getForm().getValues();
				data.startTime = data.startTimeHour + ":" + data.startTimeMinute + ":" + data.startTimeSecond;
				data.endTime = data.endTimeHour + ":" + data.endTimeMinute + ":" + data.endTimeSecond;
				if(data.startTime>=data.endTime){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
					return;
				}
				var flag = true;
				infoDateGrid.getStore().each(function(record){
					if(data.openClose!=record.data.openClose){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.planOlonOne);
						flag=false;
						return;
					}
					if(data.startTime>=data.endTime){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
						flag=false;
						return;
					}
					if(record.data.startTime<=data.startTime&&data.startTime<=record.data.endTime){
						Eway.alert(EwayLocale.confirm.title,EwayLocale.machine.serviceplan.timeEare);
						flag=false;
						return;
					}
					if(record.data.startTime<=data.endTime&&data.endTime<=record.data.endTime){
						Eway.alert(EwayLocale.confirm.title,EwayLocale.machine.serviceplan.timeEare);
						flag=false;
						return;
					}
					if(data.startTime<=record.data.startTime&&data.endTime>=record.data.endTime){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.timeEare);
						flag=false;
						return;
					}
				})
				if(!flag){
					return;
				}
				var record = Ext.create('Eway.model.operatingPlan.OpenPlanDetail',data);
				infoDateGrid.getStore().add(record);
			});

			detailWeekForm.down('button[action="remove"]').on('click',function(){
				var sm = infoWeekGrid.getSelectionModel();
				if(sm.getCount() == 1) {
					var record = sm.getLastSelected();
					infoWeekGrid.getStore().remove(record);
				}
				else {
					Eway.alert(EwayLocale.confirm.title, EwayLocale.choiceDeleteMsg);
				}
			});
			detailDateForm.down('button[action="remove"]').on('click',function(){
				var sm = infoDateGrid.getSelectionModel();
				if(sm.getCount() == 1) {
					var record = sm.getLastSelected();
					infoDateGrid.getStore().remove(record);
				}
				else {
					Eway.alert(EwayLocale.confirm.title, EwayLocale.choiceDeleteMsg);
				}
			});

			win.down('button[action="confirm"]').on('click',Ext.bind(this.onUpdateConfirm,this,[win]),this);

		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	onUpdateConfirm : function(win) {
		var view = this.getEwayView();
		var store = view.down('operatingPlan_grid').getStore();
		var addForm = win.down('form');
		var data = addForm.getForm().getValues();
		//字段验证：
		if(!addForm.getForm().isValid()){
			return;
		}
		var planDetails = "";
		if(data.planType=='WEEK'){
			var detailStore = win.down('#weekPlanInfoID').getStore();
			detailStore.each(function(record){
				var openPlanDetailstr = "{'week':" +"'" +record.data.week +"'" + ",'openClose':'" + record.data.openClose + "','startTime':'"+record.data.startTime
				+ "','endTime':'" + record.data.endTime +"'}";
				planDetails = planDetails + "," + openPlanDetailstr;
			})
		}else{
			var detailStore = win.down('#datePlanInfoID').getStore();
			detailStore.each(function(record){
				var openPlanDetailstr = "{'openClose':'" + record.data.openClose + "','startTime':'"+record.data.startTime
				+ "','endTime':'" + record.data.endTime +"'}";
				planDetails = planDetails + "," + openPlanDetailstr;
			})
		}
		if(planDetails != ""){
			planDetails = planDetails.substring(1);
			planDetails = "[" + planDetails + "]";
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		var record1 = sm.getLastSelected();
    	record1.set('name',data.name);
    	record1.set('desc',data.desc);
    	record1.set('startDate',data.startDate);
    	record1.set('endDate',data.endDate);
    	record1.set('planType',data.planType);
    	record1.set('planState',data.planState);
    	record1.set('openPlanDetailForms',planDetails);
    	record1.save({
    		success : function(record,operation){
				Eway.alert(EwayLocale.updateSuccess);
				var values = view.down('operatingPlan_filterForm').getForm().getValues();
				var store = view.down('operatingPlan_grid').getStore();
				store.setUrlParamsByObject(values);
				store.loadPage(1);
				win.close();
			},
			failure: function(record,operation){
				if(operation.request.scope.reader.jsonData.type==null){
					Eway.alert(EwayLocale.confirm.title, operation.getError(),function(){
						//解决脏数据
						store.rejectChanges();
					});
				}else{
					Eway.alert(EwayLocale.confirm.title, operation.getError(),function(){
						//解决脏数据
						store.rejectChanges();
						win.close();
						store.load();
					});
				}
			}

		});
		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.setTime);
    	}

	},

	/**
	 * 关联设备管理
	 */
	onLink : function(){
		idArray = new Array();
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var PlanDeviceWin = Ext.create('Eway.view.operatingPlan.PlanDevice');
			var linkedPanel = PlanDeviceWin.down('operatingPlan_linkedDevicePanel');
			linkedPanel.down("field[name='planId']").setValue(record.data.id);
			var linkingPanel = PlanDeviceWin.down('operatingPlan_linkingDevicePanel');
			linkingPanel.down("field[name='planId']").setValue(record.data.id);
			var linkedDeviceGrid = PlanDeviceWin.down('operatingPlan_linkedDeviceGrid');
			var actionTip1 = linkedDeviceGrid.down("tbtext[action=tip]");
			actionTip1.setText(EwayLocale.commen.bindMachine);
			var linkingDeviceGrid = PlanDeviceWin.down('operatingPlan_linkingDeviceGrid');
			var actionTip2 = linkingDeviceGrid.down("tbtext[action=tip]");
			actionTip2.setText(EwayLocale.commen.canBindMachine);
			if(record.data.planState=="Stoped"){
				actionTip1.setText(EwayLocale.commen.bindMachine+'<font color="red">'+EwayLocale.machine.serviceplan.thisPlanStop+'</font>');
				actionTip2.setText(EwayLocale.commen.canBindMachine+'<font color="red">'+EwayLocale.machine.serviceplan.thisPlanStop+'</font>');
				linkedDeviceGrid.down('button[action="unlink"]').setDisabled(true);
				linkingDeviceGrid.down('button[action="link"]').setDisabled(true);
			}
			this.queryLinkDevice(linkedPanel,linkedDeviceGrid);
			this.queryLinkDevice(linkingPanel,linkingDeviceGrid);
			linkedDeviceGrid.down('button[action="unlink"]').on('click',
					Ext.bind(this.onUnlinkConfirm,this,[linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid,record]),this
				);
			linkingDeviceGrid.down('button[action="link"]').on('click',
					Ext.bind(this.onLinkConfirm,this,[linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid,record]),this
				);

			/*linkingDeviceGrid.down('button[action="importMachineCode"]').on('click',
					Ext.bind(this.onImport,this,[linkedDeviceGrid,PlanDeviceWin]),this
				);*/

			linkingDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.queryLinkDevice,this,[linkingPanel,linkingDeviceGrid]),this
				);
			linkedDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.queryLinkDevice,this,[linkedPanel,linkedDeviceGrid]),this
				);
			linkedDeviceGrid.on("destroy",Ext.bind(this.onQuery,this));
			PlanDeviceWin.show();
		}
		else {
			Eway.alert(EwayLocale.tip.selectPlan);
		}
	},

	/**
	 * 获得选中的行id数组：
	 * @param {} grid
	 * @return {}
	 */
	multiSelect : function(grid){
		var record=grid.getSelectionModel().getSelection();
        if(record == null || record.length == 0){
        	return null;
        }
        var array = new Array(record.length);
        for(var i=0;i<record.length;i++)
        {
            array[i] = record[i].get('id');
        }
        return array;
	},


	/**
	 * 解除关联
	 * @param {} linkedDeviceGrid
	 * @param {} linkingDeviceGrid
	 * @param {} record
	 */
	onUnlinkConfirm : function(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid,record){
		var array = this.multiSelect(linkedDeviceGrid);
		if(array != null) {
			var info = '';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/plan/unlink',
				params : {planId :record.data.id,deviceId:info},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.msg.removeSuccess,this.onlinkRefrece(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid));
					}else{
						Eway.alert(EwayLocale.confirm.title, Ext.decode(response.responseText).errorMsg+EwayLocale.machine.serviceplan.placeRefresh,this.onlinkRefrece(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid));
					}
				},
				failure: function(response){
					Eway.alert(EwayLocale.confirm.title, EwayLocale.msg.removeSuccess);
				},
				scope:this
			});

		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.selectRemoveDev);
		}
	},
	onlinkRefrece:function(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid){
		this.queryLinkDevice(linkedPanel,linkedDeviceGrid);
		this.queryLinkDevice(linkingPanel,linkingDeviceGrid);
	},
	/**
	 * 进行关联
	 * @param {} linkingDeviceGrid
	 * @param {} linkedDeviceGrid
	 * @param {} record
	 */
	onLinkConfirm : function(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid,record){
		var array = this.multiSelect(linkingDeviceGrid);
		var planWin = this.getDevPlanWin();
		 var linkingDeviceForm = linkingDeviceGrid.up('window').down("form[name=LinkingDeviceFilter]").getForm();
		 var deviceIdsField = linkingDeviceForm.findField("deviceIds");
		 var arr = deviceIdsField.value;
		 var array= arr.substring(1);
		 if(array.length > 0)
		  {
			var info = array;
		//	confirmButton.disabled  = true;
			var winEl = planWin.getEl();
			winEl.mask(EwayLocale.machine.serviceplan.linking);
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				timeout : 90000,
				url : 'api/plan/link',
				params : {planId :record.data.id,deviceId:info},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.linkSuccess,this.onlinkRefrece(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid));
						 var field = linkingDeviceForm.findField("deviceIds");
		                 field.setValue("");
		                 idArray = new Array();

					}else{
						Eway.alert(EwayLocale.confirm.title, Ext.decode(response.responseText).errors+EwayLocale.machine.serviceplan.tipAddError,this.onlinkRefrece(linkedPanel,linkingPanel,linkedDeviceGrid,linkingDeviceGrid));
						 var field = linkingDeviceForm.findField("deviceIds");
		                 field.setValue("");
		                 idArray = new Array();

					}
				//	confirmButton.disabled  = false;
					winEl.unmask();
				},
				failure: function(response){
					Eway.alert(EwayLocale.confirm.title, Ext.decode(response.responseText).errorMsg+"条关联失败，请刷新后查看！");
					 var field = linkingDeviceForm.findField("deviceIds");
	                 field.setValue("");
	                 idArray = new Array();
	            //     confirmButton.disabled  = false;
	                 winEl.unmask();

				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.confirm.title, EwayLocale.tip.selectRelatedDev);
		}
	},



	/**
	 * 删除方案：
	 */
	onRemove: function() {
		var view = this.getEwayView();
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									var values = view.down('operatingPlan_filterForm').getForm().getValues();
									var store = view.down('operatingPlan_grid').getStore();
									store.setUrlParamsByObject(values);
									store.loadPage(1);
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									record.dropped = false;
									Eway.alert(EwayLocale.confirm.title, operation.getError());
									grid.getStore().load();
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(EwayLocale.tip.remove.none);
		}
	},
	queryLinkDevice:function(linkPanel,linkGrid){
		var values =linkPanel.down('form').getForm().getValues();
		var store = linkGrid.getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	onImport:function(grid,win){
		var importWin = Ext.create('Eway.view.operatingPlan.ImportLinkedMachine');
		importWin.show();
		var form = importWin.down('form').getForm();
		importWin.down("button[action=import]").on("click",Ext.bind(this.onImportMachineCodeConfirm,this,[form,grid,win]),this);

	},
	onImportMachineCodeConfirm:function(addForm,linkingDeviceGrid,linkingDeviceWin){
	       if(addForm.isValid( ) )
			 if( addForm.findField("file").getValue()!='')
				{
				   Ext.Msg.wait(EwayLocale.machine.serviceplan.testingPlaceWaiting);
				};
			addForm.submit({
				 	url: 'api/plan/uploadExcel',
				    success: function(grid, action) {
			        if(action.result.success == true){
					if(action.result.message == -1)
					  {
						msg= EwayLocale.machine.serviceplan.leastOne;
					  }
					else if(action.result.message == -2)
					  {
						msg= EwayLocale.machine.serviceplan.notMore;
					  }
					else if(action.result.message == 0)
					  {
						  msg= EwayLocale.machine.serviceplan.checkFile;
					  }
					else{
						var records = action.result.data;

			    	/*	var grid = linkingDeviceGrid;
						var store = grid.getStore();*/
						var deviceIds = '';
			    		for(var i in records) {
			    			//对重复导入的设备进行过滤
			    			if(!Ext.Array.contains(idArray,records[i].id))
			    			{
			    			//	 store.add(records[i]);
								 deviceIds =deviceIds+","+records[i].id;
								 idArray.push(records[i].id);

			    			}
						}
				    var form = linkingDeviceWin.down("form[name=LinkingDeviceFilter]").getForm();
					var deviceIdsField = form.findField("deviceIds");
					var deviceIds =deviceIdsField.value + deviceIds;
					form.setValues([{id:'deviceIds', value:deviceIds}]);
					var selectGrid = linkingDeviceWin.down('operatingPlan_linkingDeviceGrid');
					var selectStore = selectGrid.getStore();
					selectStore.loadPage(1);
					var selectedGrid = linkingDeviceWin.down('operatingPlan_linkedDeviceGrid');
					selectedGrid.onReload();
					// 这行特别消耗时间  TODO
						msg="<a class='link' href='api/plan/downloadFile'>"
						+ action.result.message + EwayLocale.machine.serviceplan.tipExportSuccess +action.result.total + EwayLocale.machine.serviceplan.tipLookUp+"</a>"
					}
					Eway.alert(EwayLocale.confirm.title, msg,function callback(){
			    		  Ext.Ajax.request({
			    				method : 'POST',
			    				url : 'api/plan/delFile'
			    			});
					    });

				  	}
				    },
				    failure: function(form, action) {
				    	Eway.alert(EwayLocale.confirm.title, EwayLocale.machine.serviceplan.fileNotAllowed);
				    },
				   scope: this
			});
		}
});
