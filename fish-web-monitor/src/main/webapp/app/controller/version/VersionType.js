Ext.define('Eway.controller.version.VersionType', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'version.VersionType' ],
	models : [ 'version.VersionType' ],
	views : [ 'version.versionType.View','version.versionType.Form'],

	refs : [ {
		ref : 'ewayView',
		selector : 'versionTypeView',
		autoCreate : true,
		xtype : 'versionTypeView'
	}, {
		ref : 'grid',
		selector : 'versionType_grid'
	},{
		ref : 'addWin',
		selector: 'versionType_add'
	},{
		ref:'updateWin',
		selector:'versionType_update'
	},{
		ref : 'filterForm',
		selector: 'versionType_filterForm'
	} ],
	
	formConfig : {
		form : 'Eway.view.version.versionType.Form',
		xtype:'versionType_form',
		width: 500,
		title : EwayLocale.versionType.winTitle//'软件分类'
	},

	init : function() {
		 this.control({
			'versionTypeView button[action=query]' : {
				click : this.onQuery
			},
			'versionTypeView button[action=add]' : {
				click : this.onAdd
			},
			'versionTypeView button[action=remove]' : {
				click : this.onRemove
			},
			'versionTypeView button[action=update]' : {
				click : this.onUpdate
			}
		});

	},
	
	//在打开增加页面之前
	beforeShowAddWin: function(win,grid){
		var checkGroup = win.down('form').down("checkboxgroup");
		checkGroup.getLoader().load({
			params:{
				versionTypeId : 0
			}
		});
	},
	
	//在增加之前
	beforeAddSave: function(win,grid){
		var addForm = win.down('form').getForm();
		data = addForm.getValues();
		var atmTypes = data.atmTypes;
		if(atmTypes && !Ext.isArray(atmTypes)){
			data.atmTypes = [atmTypes];
		}
		return data;
	},
	
	//在打开修改页面之前
	boforeShowUpdateWin : function(updateWin,grid,record){
		var checkGroup = updateWin.down('form').down("checkboxgroup");
		updateWin.down("textfield[name='typeName']").setReadOnly( true );
		checkGroup.getLoader().load({
			params:{
				versionTypeId : record.getId()
			}
		});
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
						Eway.alert(EwayLocale.deleteSuccess);
						me.onQuery();
					},
					failure: function(record,operation){
						//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
						record.dropped = false;
						Eway.alert(operation.getError());
					},
					scope:this
				});
			}
		});
	},

	//在修改之前
	beforeUpdateSave : function(win,grid,record){
		var values = win.down('form').getForm().getValues();
		var atmTypes = values.atmTypes;
		if(atmTypes && !Ext.isArray(atmTypes)){
			values.atmTypes = [atmTypes];
		}
		record.data.atmTypes = values.atmTypes;
	}
});
