
Ext.define('Eway.view.machine.atmMove.AtmMoveView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmMove_atmMoveView',

	requires: ['Eway.view.machine.atmMove.MoveDeviceGrid',
				'Eway.view.machine.atmMove.MoveDeviceFilterForm',
				'Eway.view.machine.atmMove.NoticeFilterForm',
	           'Eway.view.machine.atmMove.AtmMoveNoticeGrid'],

	title: EwayLocale.machine.atmMove.title,
	layout: 'border',
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				title : EwayLocale.machine.atmMove.moveRecordInfo,
			    region: 'center',
			    margins: '5 0 0 5',
			    split: true,
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'atmMove_noticeFilterForm'
				},
				{
					region: 'center',
					xtype: 'atmMove_atmMoveNoticeGrid'
				}]
			},{
				title :EwayLocale.machine.atmMove.waitMove,
				region: 'west',
				width: 500,
				margins: '5 0 0 5',
				split: true,
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'atmMove_moveDeviceFilterForm'
				},
				{
					region: 'center',
					xtype: 'atmMove_moveDeviceGrid'
				}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}

					panel.down('atmMove_moveDeviceGrid').getSelectionModel().clearSelections();
					panel.down('atmMove_moveDeviceGrid').getStore().load();
					var orgTrees = panel.down('atmMove_moveDeviceFilterForm').query('common_orgComboOrgTree');
					Ext.Array.each(orgTrees, function() {
						// 刷新维护商和所属机构信息
						this.reflesh();
					});
				}
			}
		});

		this.callParent(arguments);
	}
});