
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
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'moveDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : '设备号',
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : '源地址',
				dataIndex : 'address',
				width : 150
			}, {
				header : '目标地址',
				dataIndex : 'targetAddress',
				width : 150
			}, {
				header : '源机构',
				dataIndex : 'orgName',
				width : 100
			}, {
				header : '源机构负责人',
				dataIndex : 'destPerson',
				width : 100
			}, {
				header : '目标机构',
				dataIndex : 'targetOrganization',
				width : 100
			}, {
				header : '目标机构负责人',
				dataIndex : 'targetPerson',
				width : 100
			}, {
				header : '责任人',
				dataIndex : 'responsibility',
				width : 100
			}, {
				header : '日期',
				dataIndex : 'date',
				width : 100
			}, {
				header : '备注',
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