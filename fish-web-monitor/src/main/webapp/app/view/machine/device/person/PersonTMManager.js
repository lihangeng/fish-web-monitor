/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.machine.device.person.PersonTMManager', {
	alias: 'widget.personTM_manager',
	extend: 'Ext.window.Window',
	
	title: EwayLocale.person.bankOrg.addOrgTitle,
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
			title : EwayLocale.button.personTM,
			items : [{
				xtype : 'personTM_manager_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});