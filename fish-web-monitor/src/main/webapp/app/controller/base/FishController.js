Ext.define('Eway.controller.base.FishController', {
	extend : 'Eway.controller.base.Controller',

	views : [
		'base.Window'
	],

	requires : ['Eway.view.base.Window'],

	initBaseControl : function(){
	},

	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();
		if (bool == false) {// 查询输入验证
			Eway.alert(EwayLocale.tip.search.warn);
			return;
		}
		var values = form.getValues();
		var store = this.getGridPanel().getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	onAdd : function(){
		this._onAddOrUpdate('add');
	},

	onUpdate : function(){
		this._onAddOrUpdate('update');
	},

	onRemove : function(){
		var me = this;
		var grid = me.getGridPanel(),
			store = grid.getStore(),
			sm = grid.getSelectionModel(),
			count = sm.getCount();
		if(count == 0){
			Eway.alert(EwayLocale.tip.remove.none);
			return;
		}
		else if(count > 1){
			Eway.alert(EwayLocale.tip.remove.one);
			return;
		}
		Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title, EwayLocale.tip.remove.confirm.info,function(button,text){
			if(button == 'yes'){
				var record = sm.getLastSelected();
				record.erase({
					success: function(record,operation){
						Eway.alert(EwayLocale.updateSuccess);
						me.onQuery();
					},
					failure: function(record,operation){
						//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
						record.dropped = false;
						Eway.alert(EwayLocale.tip.remove.error+ operation.getError());
					},
					scope:this
				});
			}
		});
	},


	_onAddOrUpdate : function(action){
		var title = action=='add' ? EwayLocale.button.add+this.formConfig.title : EwayLocale.button.update+this.formConfig.title;
		var me = this;
		Ext.require([this.formConfig.form],function(){
			if(action=='update'){
				var grid = this.getGridPanel(),
					sm = grid.getSelectionModel(),
					count = sm.getCount();
				if(count == 0){
					Eway.alert(EwayLocale.choiceUpdateMsg);
					return;
				}
				else if(count > 1){
					Eway.alert(EwayLocale.tip.update.one);
					return;
				}
			}

			var win = Ext.create('Eway.view.base.Window',{
				title : title,
				width : this.formConfig.width ? this.formConfig.width : 500,
//				height : this.formConfig.height ?  this.formConfig.height : 500,
				items : [{
					xtype : this.formConfig.xtype
				}]
			});
			win.setAction(action);
			win.down('button[action="confirm"]').on('click',me._save,me);
			if(action == 'update'){
				grid = this.getGridPanel(),
				sm = grid.getSelectionModel(),
				form = win.down('form');
				var record = sm.getLastSelected();
				form.loadCusRecord(record);
				this.boforeShowUpdateWin(win,grid,record);
			}else{
				this.beforeShowAddWin(win,grid);
			}
			win.show();
		},this);
	},

	getGridPanel : function(){
		var view =  this.getEwayView();
		return view.down('gridpanel');
	},

	_save : function(button){
		var me = this,
			view = this.getEwayView(),
			win = button.up('window'),
			form = win.down('form');
		if(form.getForm().isValid()){//存在不合法的输入项
			button.disable();
			var grid = me.getGridPanel(),
				store = grid.getStore(),
				action = win.getAction(),
				oldParams = store.getUrlParams(),
				actionName,
				record;
				store.cleanUrlParam();

				if(action == 'add') {
					actionName = EwayLocale.button.add;
					var values = form.getCusValues();
					record = this.beforeAddSave(win,grid);
					if(undefined==record){
						record = Ext.create(store.getModelName(),values);
					}
					else{
						record = Ext.create(store.getModelName(),record);
					}
				}
				else if(action == 'update') {
					actionName = EwayLocale.button.update;
					record = grid.getSelectionModel().getLastSelected();
					form.updateCusRecord(record);
					this.beforeUpdateSave(win,grid,record);
				}
				var id = record.get("id");
				record.save({
					 success: function(recordInDB) {
						Eway.alert(actionName + EwayLocale.tip.success);
						win.close();
						if(action == 'add'){
							this.onQueryAfterAdd();
						}else{
							var resId = recordInDB.get("id");
							if(undefined==resId||""==resId||0==resId){
								recordInDB.set("id",id);
							}
							store.applyModel(recordInDB);
						}
					 },
					 failure: function(record,operation){
						store.setUrlParamsByObject(oldParams);
						Eway.alert(actionName + EwayLocale.tip.fail + operation.getError());
						store.rejectChanges();
						button.enable();
					 },
					 scope : this
				});
	 	}
	},

	//在显示增加页面之前
	beforeShowAddWin : function(win,grid){
	},

	//抽象方法，留给子类扩展
	//在增加之前处理逻辑
	beforeAddSave : function(win,grid){
	},

	//抽象方法，留给子类扩展
	//在显示更改页面之前
	boforeShowUpdateWin : function(updateWin,grid,record){
	},

	//抽象方法，留给子类扩展
	//在更新之前处理逻辑
	beforeUpdateSave : function(win,grid,record){
	},

	//增加后调用的查询，过滤条件不带入查询，且页面的查询不清除
	onQueryAfterAdd : function(){
		var store = this.getGridPanel().getStore();
		store.setUrlParamsByObject();
		store.loadPage(1);
	}


});