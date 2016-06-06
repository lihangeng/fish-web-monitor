Ext.define('Eway.controller.machine.atmMove.AtmMove', {
	extend : 'Ext.app.Controller',

	stores : [ 'machine.atmMove.MoveDevice','machine.atmMove.AtmMoveNotice'],

	models : [ 'machine.atmMove.MoveDevice','machine.atmMove.AtmMoveNotice'],

	views : ['machine.atmMove.AtmMoveView' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#atmMove',
		autoCreate : true,
		xtype : 'atmMove_atmMoveView',
		id : 'atmMove'
	}, {
		ref : 'atmMove',
		selector : 'atmMove_atmMove'
	}, {
		ref : 'update',
		selector : 'atmMove_update'
	}, {
		ref : 'atmMoveNoticeGrid',
		selector : 'atmMove_atmMoveNoticeGrid'
	}, {
		ref : 'moveDeviceGrid',
		selector : 'atmMove_moveDeviceGrid'
	}, {
		ref : 'moveDeviceFilterForm',
		selector : 'atmMove_moveDeviceFilterForm'
	}, {
		ref : 'noticeFilterForm',
		selector : 'atmMove_noticeFilterForm'
	} ],

	moveView : 'Eway.view.machine.atmMove.AtmMove',
	updateView : 'Eway.view.machine.atmMove.Update',

	init : function() {
		this.control({
			'atmMove_moveDeviceGrid button[action=query]':{
			 	click:this.onQueryMoveDevice
			},
			'atmMove_moveDeviceGrid button[action=move]' : {
				click : this.onMove
			},
			'atmMove_atmMoveNoticeGrid button[action=query]':{
			 	click:this.onQueryNotice
			},
			'atmMove_atmMoveNoticeGrid button[action=remove]' : {
				click : this.onRemove
			}
		});
	},

	onQueryMoveDevice : function(){
		var store = this.getMoveDeviceGrid().getStore();
		var data = this.getMoveDeviceFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		if(this.getMoveDeviceFilterForm().getForm().isValid() == true){
			store.setUrlParamsByObject(data);
			store.setBaseParam('organizationID',Eway.user.getOrgId());
			store.loadPage(1);
		}else{
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
		}
	},

	onQueryNotice : function(){
		var store = this.getAtmMoveNoticeGrid().getStore();
		var data = this.getNoticeFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		if(this.getNoticeFilterForm().getForm().isValid() == true){
			store.setUrlParamsByObject(data);
			store.loadPage(1);
		}else{
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
		}

	},

	onMove: function() {
		var grid = this.getMoveDeviceGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create(this.moveView);
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			var button = win.query('button[action=confirm]')[0];
			button.on('click', this.onMoveConfirm, this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.tip.selectMoveDev);
		}

	},

	onMoveConfirm: function() {
		var win = this.getAtmMove();
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.machine.atmMove.AtmMoveNotice',data);
		var store = this.getAtmMoveNoticeGrid().getStore();
		var quaryData = this.getNoticeFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		var moveDeviceGrid = this.getMoveDeviceGrid();
		var me =this;
		if(win.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.load();
					moveDeviceGrid.getStore().load();
					moveDeviceGrid.getSelectionModel().clearSelections();
					win.close();

					Eway.alert(EwayLocale.tip.moveSuc);

					me.getMoveDeviceGrid().getStore().loadPage(1);
			    },
			    failure: function(record,operation){
					Eway.alert(operation.getError());
				},
				button:win.down('button[action="confirm"]')
			});
		}
	},

	onRemove: function() {
		var grid = this.getAtmMoveNoticeGrid();
		var sm = grid.getSelectionModel();
		var store = this.getAtmMoveNoticeGrid().getStore();
		var quaryData = this.getNoticeFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.destroy({
								success: function(){
									Eway.alert(EwayLocale.updateSuccess);
									store.loadPage(1);
									store.load();
								},
								failure: function(record,operation){
									Eway.alert(operation.getError());
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(EwayLocale.choiceDeleteMsg);
		}
	}
});