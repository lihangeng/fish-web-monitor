/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.machine.device.person.PersonMManager', {
	alias: 'widget.personM_manager',
	extend: 'Ext.window.Window',

	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,

	requires: ['Eway.view.machine.device.person.PersonMManagerGrid'],

	width: 750,
	height: 550,
	layout : 'fit',
	initComponent: function() {
		Ext.apply(this, {
			title : Eway.locale.button.personM,
			items : [{
				xtype : 'personM_manager_grid'
			}]
		});
		this.callParent(arguments);
	}

});