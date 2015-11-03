/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.machine.device.person.SelectPersonManager', {
	alias: 'widget.select_person_manager.js',
	extend: 'Ext.window.Window',
	
	title: Eway.locale.person.bankOrg.addOrgTitle,
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,

	requires: ['Eway.view.machine.device.person.SelectPersonManagerGrid',
	           'Eway.view.person.bankPer.FilterForm'],

	width: 750,
	height: 500,
	layout : 'border',
	initComponent: function() {
		Ext.apply(this, {
			title : Eway.locale.person.bankOrg.personList,
			items: [{
				region: 'north',
				xtype: 'bank_person_filterform'
			},{
				region: 'center',
				xtype: 'select_person_manager_grid'
			}]
		});
		this.callParent(arguments);
	}

});