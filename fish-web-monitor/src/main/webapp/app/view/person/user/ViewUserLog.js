/**
 * 显示操作日志窗口：
 */
Ext.define('Eway.view.person.user.ViewUserLog', {
	alias: 'widget.person_user_viewUserLog',
	extend: 'Eway.view.base.Panel',

	title: EwayLocale.person.user.operTitle,
	layout: 'border',

	requires: ['Eway.view.person.user.UserLogGrid',
	           'Eway.view.person.user.UserLogFilterForm'],

	initComponent: function() {
		Ext.apply(this, {
			items : [{
				region: 'north',
				xtype: 'userLogFilterForm'
			}, {
				region: 'center',
				xtype: 'userLog_grid'
			}]

		});
		this.callParent(arguments);
	}

});