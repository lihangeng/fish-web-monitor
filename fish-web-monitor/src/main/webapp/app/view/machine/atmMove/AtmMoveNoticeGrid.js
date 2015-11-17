
Ext.define('Eway.view.machine.atmMove.AtmMoveNoticeGrid', {
	alias: 'widget.atmMove_atmMoveNoticeGrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmMove.AtmMoveNotice');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'moveDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.machine.atmMove.terminalId,
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.address,
				dataIndex : 'address',
				width : 150
			}, {
				header : EwayLocale.machine.atmMove.targetAddress,
				dataIndex : 'targetAddress',
				width : 150
			}, {
				header : EwayLocale.machine.atmMove.orgName,
				dataIndex : 'orgName',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.destPerson,
				dataIndex : 'destPerson',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.targetOrganization,
				dataIndex : 'targetOrganization',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.targetPerson,
				dataIndex : 'targetPerson',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.responsibility,
				dataIndex : 'responsibility',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.date,
				dataIndex : 'date',
				width : 100
			}, {
				header : EwayLocale.machine.atmMove.notice,
				dataIndex : 'notice',
				width : 150
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});