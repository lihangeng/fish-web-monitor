
Ext.define('Eway.view.bsAdvert.BsAdvertGroupAdd', {
	extend: 'Ext.window.Window',
	alias: 'widget.bsAdvert_groupAdd',
	
	requires: ['Eway.view.common.OrgComboOrgTree'],
	
	title: EwayLocale.bsAdvertGroup.addTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'orgId'
					}, {
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'groupName',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
				},{
					fieldLabel : EwayLocale.bsAdvert.advertGroupType,
					xtype : 'radiogroup',
					anchor : '69%',
					items : [ {
						boxLabel : EwayLocale.bsAdvertGroup.commenType,
						name : 'groupType',
						checked : true,
						inputValue : 0
					}, {
						boxLabel : EwayLocale.bsAdvertGroup.defaultType,
						name : 'groupType',
						inputValue : 1
					}]
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'add'
				}, {
					text: EwayLocale.button.reset,
					handler: this.onReset
				}, {
					text: EwayLocale.button.back,
					handler: this.onOver
				}]
			}
		});
		
		this.callParent(arguments);
	},
	
	onReset: function() {
		this.up('form').getForm().reset();
	},
	
	onOver: function() {
		this.up('window').close();
	}
});