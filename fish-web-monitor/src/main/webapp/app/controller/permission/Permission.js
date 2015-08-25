Ext.define('Eway.controller.permission.Permission', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'permission.PermissionTree'],
	models : [ 'permission.Permission'],

	views : ['permission.permission.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#permission',
		autoCreate : true,
		xtype : 'permission.view',
		id : 'permission'
	}],

	addRecord : function(data) {
		var record = Ext.create('Eway.model.Role',data);
		this.getDeviceStore().add(record);
	},

	removeRecord : function(record) {
		this.getDeviceStore().remove(record);
	}

});