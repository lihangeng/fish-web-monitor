Ext.define('Eway.view.machine.device.person.TmGrid', {
	alias : 'widget.machine_device_person_tmGrid',
	extend : 'Eway.view.base.Grid',
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.machine.PersonTM');
		// store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			autoScroll: true,
			height: 200,
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header : EwayLocale.machine.device.name,
				dataIndex : 'name'
			}, {
				header : EwayLocale.machine.device.mobile,
				dataIndex : 'mobile'
			}, {
				header : EwayLocale.machine.device.phone,
				dataIndex : 'phone'
			}, {
				header : EwayLocale.machine.device.email,
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