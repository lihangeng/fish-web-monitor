/**
 * 显示操作日志窗口：
 */
Ext.define('Eway.view.person.user.ViewUserLogWin', {
	alias: 'widget.person_user_viewUserLogWin',
	extend: 'Ext.window.Window',
	
	title: EwayLocale.person.user.operDetailTitle,
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.user.UserLogGrid',
	           'Eway.view.person.user.UserLogFilterForm'],
	
	width: 750,
	height: 400,
	layout : 'border',
	initComponent: function() {
		Ext.apply(this, {
			title : EwayLocale.person.user.operLogList,
			items : [{
				region: 'north',
				xtype: 'userLogFilterForm',
				height: 40
			}, {
				region: 'center',
				xtype: 'userLog_grid',
				autoLoadStore : true
		}]
		});
		this.callParent(arguments);
	}
	
});