
Ext.define('Eway.view.machine.quittingNotice.DeviceGrid', {
	alias: 'widget.quittingNotice_DeviceGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.quittingNotice.QuittingDevice');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'queryDevice'	
			},{
				text: Eway.locale.button.select,
				action: 'select'
			}],
			columns : [{
				header : Eway.locale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.machine.atmGroup.orgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header : Eway.locale.machine.quittingNotice.address,
				dataIndex : 'address',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});