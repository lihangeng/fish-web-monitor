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
				header : '姓名',
				dataIndex : 'name'
			}, {
				header : '手机',
				dataIndex : 'mobile'
			}, {
				header : '固定电话',
				dataIndex : 'phone'
			}, {
				header : '邮件地址',
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