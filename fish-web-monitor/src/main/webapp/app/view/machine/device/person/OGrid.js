Ext.define('Eway.view.machine.device.person.OGrid', {
	alias : 'widget.machine_device_person_oGrid',
	extend : 'Eway.view.base.Grid',
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.person.person.OrgManager');
		// store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			autoScroll: true,
			height: 200,
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header :Eway.locale.commen.name,
				dataIndex : 'name'
			}, {
				header : Eway.locale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : Eway.locale.machine.device.phone,
				dataIndex : 'phone'
			}, {
				header : Eway.locale.machine.device.email,
				width : 200,
				dataIndex : 'email'
			} ]
		});

		this.callParent(arguments);
	},

	onReload : function(params) {
		this.getStore().load({
			params : params
		});
	}
});