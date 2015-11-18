
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'queryDevice'	
			},{
				text: EwayLocale.button.select,
				action: 'select'
			}],
			columns : [{
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header : EwayLocale.machine.quittingNotice.address,
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