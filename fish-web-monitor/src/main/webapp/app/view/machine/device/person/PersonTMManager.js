/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.machine.device.person.PersonTMManager', {
	alias: 'widget.personTM_manager',
	extend: 'Ext.window.Window',
	
	title: '该机构人员信息',
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.machine.device.person.PersonTMManagerGrid'],
	
	width: 750,
	height: 550,
	layout : 'fit',
	initComponent: function() {
		Ext.apply(this, {
			title : '管机员',
			items : [{
				xtype : 'personTM_manager_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});