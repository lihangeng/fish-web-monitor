Ext.define('Eway.controller.version.DeviceVersion', {
	extend : 'Ext.app.Controller',

	stores : [ 'version.DeviceVersion','monitor.card.DeviceAtmVendor','monitor.card.DeviceType' ],
	models : [ 'version.DeviceVersion'],
	views : [ 'version.deviceVersion.View'],

	refs : [ {
		ref : 'ewayView',
		selector : '#deviceVersion',
		autoCreate : true,
		xtype : 'deviceVersionView',
		id : 'deviceVersion'
	}, {
		ref : 'grid',
		selector : 'deviceVersion_grid'
	},{
		ref : 'filterForm',
		selector: 'deviceVersion_filterForm'
	} ],

	init : function() {
		 this.control({
			'#deviceVersion button[action=query]' : {
				click : this.onQuery
			},
			'#deviceVersion button[action=history]' : {
				click : this.onHistroy
			}
		});

	},

	onQuery: function(){
		var view = this.getEwayView();
		var form = view.down('deviceVersion_filterForm').getForm();
		if(!form.isValid()){
			return;
		}
		var values = view.down('deviceVersion_filterForm').getForm().getValues();
		var store = view.down('deviceVersion_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	onHistroy: function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var win = Ext.create('Eway.view.version.deviceVersion.VersionHistoryWin');
			var hisGrid = win.down('versionHistory_grid');
			var historyStore = hisGrid.getStore();
			historyStore.cleanUrlParam();
			historyStore.proxy.extraParams = {deviceId:record.get('id')};
			historyStore.load();
			/*grid.down('pagingtoolbar').on('beforechange',Ext.bind(this.onRefleshPerson,this,[personGrid.down('pagingtoolbar'),record.data.guid]),this);*/
			win.show();
		}else{
			Eway.alert(EwayLocale.msg.chooseOneDevice);//"请选择一台设备.");
		}
	}

});
