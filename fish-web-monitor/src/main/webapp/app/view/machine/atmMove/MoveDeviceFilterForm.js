
Ext.define('Eway.view.machine.atmMove.MoveDeviceFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmMove_moveDeviceFilterForm',

	requires : [ 'Eway.view.common.OrgComboOrgTree',
	             'Eway.lib.Util' ],
	layout : 'column',
	height : 70,
	initComponent: function() {
		Ext.apply(this, {

			items : [{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.atmMove.terminalId,
					labelAlign : 'right',
					vtype : 'terminalId',
					labelWidth : 60,
					maxLength:20,
					width: 200,
					name : 'terminalId',
					msgTarget : 'side'
				},{
					xtype : 'textfield',
					labelAlign : 'right',
					fieldLabel : EwayLocale.machine.atmMove.address,
					labelWidth : 60,
					maxLength:50,
					width: 200,
					name : 'address',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .55,
				items : [{
					labelWidth : 60,
					labelAlign : 'right',
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmMove.organization,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					width: 250,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				}]
			}]
		});

		this.callParent(arguments);
	}
});