
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
				text: '查询',
				glyph : 0xf002,
				action: 'queryDevice'	
			},{
				text: '选择',
				action: 'select'
			}],
			columns : [{
				header : '设备号',
				dataIndex : 'terminalId'
			}, {
				header : '所属机构',
				dataIndex : 'orgName',
				flex : 1
			}, {
				header : '所属地址',
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