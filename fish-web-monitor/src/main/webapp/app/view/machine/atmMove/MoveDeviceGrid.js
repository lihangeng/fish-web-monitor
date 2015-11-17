
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: EwayLocale.machine.atmMove.moveDev,
				iconCls :'moveBtn',
				action: 'move',
				code : 'deviceMove',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.machine.atmMove.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.machine.atmMove.sOrgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header : EwayLocale.machine.atmMove.sAddress,
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