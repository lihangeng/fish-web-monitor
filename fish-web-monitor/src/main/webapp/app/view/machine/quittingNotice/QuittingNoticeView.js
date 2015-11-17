
Ext.define('Eway.view.machine.quittingNotice.QuittingNoticeView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.quittingNotice_quittingNoticeView',

	requires: ['Eway.view.machine.quittingNotice.FilterForm',
	           'Eway.view.machine.quittingNotice.QuittingNoticeGrid'],

	title: EwayLocale.machine.quittingNotice.closeManage,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'quittingNotice_FilterForm'
			},
			{
				region: 'center',
				xtype: 'quittingNotice_quittingNoticeGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('quittingNotice_quittingNoticeGrid').onReload();
				}
			}
		});

		this.callParent(arguments);
	}
});