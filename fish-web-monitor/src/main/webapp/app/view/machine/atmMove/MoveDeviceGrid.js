
Ext.define('Eway.view.machine.atmMove.MoveDeviceGrid', {
	alias: 'widget.atmMove_moveDeviceGrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmMove.MoveDevice');
		store.setBaseParam('organizationID',ewayUser.getOrgId());
		store.loadPage(1);
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: Eway.locale.machine.atmMove.moveDev,
				iconCls :'moveBtn',
				action: 'move',
				code : 'deviceMove',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : Eway.locale.machine.atmMove.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.machine.atmMove.sOrgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header : Eway.locale.machine.atmMove.sAddress,
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